package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mario.game.sMario;

public class MenuScreen implements Screen {

	sMario game;
	Texture Background;
	Texture startButtonActive;
	Texture startButtonInactive;
	Texture optionsButtonActive;
	Texture optionsButtonInactive;
	Texture exitButtonActive;
	Texture exitButtonInactive;
	
	SpriteBatch batch;
	
	
	int start_x = 433;
	int start_y = 295;
	int options_x = 433;
	int options_y = 241;
	int exit_x = 433;
	int exit_y = 187;
	int pointer = 0;
	public MenuScreen(sMario game) {
		this.game = game;
		
		batch = new SpriteBatch();
		
		Background = new Texture("background.png");
		startButtonActive = new Texture("start_active.png");
		startButtonInactive = new Texture("start_inactive.png");
		optionsButtonActive = new Texture("options_active.png");
		optionsButtonInactive = new Texture("options_inactive.png");
		exitButtonActive = new Texture("exit_active.png");
		exitButtonInactive = new Texture("exit_inactive.png");
	}
	@Override
	public void show() {

	}
	public void drawButtons(int point) {
		if (point == 0) {//start
			batch.draw(startButtonActive, start_x, start_y);
			batch.draw(optionsButtonInactive, options_x, options_y);
			batch.draw(exitButtonInactive, exit_x, exit_y);
			if(Gdx.input.isTouched()||Gdx.input.isKeyJustPressed(Keys.ENTER)){
				game.setPlayScreen();
			}
		} else if (point == 1) {//options
			batch.draw(startButtonInactive, start_x, start_y);
			batch.draw(optionsButtonActive, options_x, options_y);
			batch.draw(exitButtonInactive, exit_x, exit_y);
		} else {//exit
			batch.draw(startButtonInactive, start_x, start_y);
			batch.draw(optionsButtonInactive, options_x, options_y);
			batch.draw(exitButtonActive, exit_x, exit_y);
			
			if(Gdx.input.isTouched()||Gdx.input.isKeyJustPressed(Keys.ENTER)) {
				batch.dispose();
				System.out.println("Bye !");
				System.exit(0);
			}
		}
	}

	@Override
	public void render(float delta) {
		// Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1);
		// Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(Background, 0, 0);
		// klavye kontrol
		
		/*
		 * mouse kontrol 
		 * start width152 start height18 
		 * opti width105 opti height18
		 * exit width55 exit height19
		 *	Gdx.input y yukaridan 0 altta 480 ters olacak
		 */
		if(Gdx.input.getX()>start_x&&
				Gdx.input.getX()<(start_x+152)&&
				Gdx.input.getY()>(480-(start_y+18))&&
				Gdx.input.getY()<(480-start_y)){
			pointer=0;
		}else if(Gdx.input.getX()>options_x&&Gdx.input.getX()<(options_x+105)&&Gdx.input.getY()>(480-(options_y+18))&&Gdx.input.getY()<(480-options_y)){
			pointer=1;
			//options
		}else if(Gdx.input.getX()>exit_x&&Gdx.input.getX()<(exit_x+55)&&Gdx.input.getY()>(480-(exit_y+19))&&Gdx.input.getY()<(480-exit_y)){
			pointer=2;
		}
		drawButtons(pointer);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {
		batch.dispose();
		startButtonActive.dispose();
		startButtonInactive.dispose();
		optionsButtonActive.dispose();
		optionsButtonInactive.dispose();
		exitButtonActive.dispose();
		exitButtonInactive.dispose();
	}
}
