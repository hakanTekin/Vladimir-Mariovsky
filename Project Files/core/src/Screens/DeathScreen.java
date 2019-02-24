package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mario.game.sMario;

public class DeathScreen implements Screen {

	private sMario game;
	private boolean isDead;
	private Texture try_again_active;
	private Texture try_again_inactive;
	private Texture failed;
	private Texture exit_inactive;
	private Texture exit_active;
	private Texture victory;
	private Texture background;
	int pointer = 0;
	private SpriteBatch batch;

	public DeathScreen(sMario game, boolean isDead) {
		this.game = game;
		this.isDead = isDead;
		this.batch = new SpriteBatch();
		createDeathScreen();
		game.setScreen(this);
		// System.out.println("i was set tho");
		game = new sMario();
	}

	// endtext missionfailed
	// tryagain returntext
	// exit
	public void createDeathScreen() {
		try_again_active = new Texture("try_again_active.png");
		try_again_inactive = new Texture("try_again_inactive.png");
		exit_active = new Texture("exit_active.png");
		exit_inactive = new Texture("exit_inactive.png");
		failed = new Texture("failed.png");
		victory = new Texture("victory.png");
		background = new Texture("deadBack.png");
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	int try_x = 257;
	int try_y = 231;
	int exit_x = 293;
	int exit_y = 181;

	public void drawButtons(int point) {
		if (!isDead) {
			batch.draw(failed, 165, 350);
		} else {
			batch.draw(victory, 320 - victory.getWidth() / 2, 350);// x e bak
		}
		if (point == 0) {// try again
			batch.draw(try_again_active, 320 - try_again_inactive.getWidth() / 2, 250 - try_again_inactive.getHeight());
			batch.draw(exit_inactive, 320 - exit_inactive.getWidth() / 2, 200 - exit_inactive.getHeight());
			if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
				tryAgain();
			}
		} else if (point == 1) {// exit
			batch.draw(try_again_inactive, 320 - try_again_inactive.getWidth() / 2,
					250 - try_again_inactive.getHeight());
			batch.draw(exit_active, 320 - exit_inactive.getWidth() / 2, 200 - exit_inactive.getHeight());
			if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
				Exit();
			}
		}

	}

	private void tryAgain() {
		game.dispose();
		game.setPlayScreen();
	}

	private void Exit() {
		batch.dispose();
		System.out.println("Bye !");
		System.exit(0);
	}

	// 400,220
	@Override
	public void render(float delta) {
		batch.begin();
		batch.draw(background, 0, 0);
		System.out.println();
		// System.out.println(failed.getWidth());//310
		// 480
		// batch.draw(failText, game.V_WIDTH/2, game.V_HEIGHT/2+30);
		if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
			if (pointer < 1) {
				pointer++;
			} else {
				pointer = 0;
			}
		} else if (Gdx.input.isKeyJustPressed(Keys.UP)) {
			if (pointer > 0) {
				pointer--;
			} else {
				pointer = 1;
			}
		}
		/*
		 * int try_x=257; int try_y=231; int exit_x=293; int exit_y=181;
		 */
		if (Gdx.input.getX() > try_x && Gdx.input.getX() < (try_x+try_again_inactive.getWidth()) && Gdx.input.getY() > (480 - (try_y + 19))
				&& Gdx.input.getY() < (480 - try_y)) {
			pointer = 0;
			if(Gdx.input.isTouched()) {
				tryAgain();
			}
			
		} else if (Gdx.input.getX() > exit_x && Gdx.input.getX() < (exit_x + exit_inactive.getWidth())
				&& Gdx.input.getY() > (480 - (exit_y + 19)) && Gdx.input.getY() < (480 - exit_y)) {
			pointer = 1;
			// options
			if(Gdx.input.isTouched()) {
				Exit();
			}
		}

		drawButtons(pointer);
		batch.end();
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}
}
