package MarioAndComponents;

import java.awt.Label;

import javax.xml.validation.SchemaFactoryConfigurationError;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mario.game.sMario;

import Weapons.Bullet;


public class Hud extends java.lang.Object{
	public com.badlogic.gdx.scenes.scene2d.Stage stage;
	private Viewport viewport;

	private Integer worldTimer;
	private float timeCount;
	private Integer score;

	/*com.badlogic.gdx.scenes.scene2d.ui.Label countdownLabel;
	com.badlogic.gdx.scenes.scene2d.ui.Label scoreLabel;
	com.badlogic.gdx.scenes.scene2d.ui.Label timeLabel;
	com.badlogic.gdx.scenes.scene2d.ui.Label levelLabel;
	com.badlogic.gdx.scenes.scene2d.ui.Label worldLabel;
	com.badlogic.gdx.scenes.scene2d.ui.Label marioLabel;*/
	Sprite scoreSpriteTens;
	Sprite scoreSpriteOnes;
	
	Sprite bulletAmountSpriteOnes;
	Sprite bulletAmountSpriteTens;
	Sprite bulletSprite;
	
	TextureRegion t ;
	Texture texture;
	public Hud() {
		worldTimer = 300;
		timeCount = 0;
		score = 0 ;
		viewport = new FitViewport(sMario.V_WIDTH, sMario.V_HEIGHT, new OrthographicCamera());
		//stage = new com.badlogic.gdx.scenes.scene2d.Stage(viewport, sb);


		texture = new Texture("0.png");
		t = new TextureRegion(texture);
		
		
		scoreSpriteOnes = new Sprite(t);
		scoreSpriteTens = new Sprite(t);
		scoreSpriteOnes.setSize(30, 30);
		scoreSpriteTens.setSize(30, 30);
		
		texture = new Texture("shard1.png");
		t = new TextureRegion(texture);
		bulletSprite = new Sprite(t);
		bulletSprite.setSize(30, 10);
		
		texture = new Texture("0.png");
		t = new TextureRegion(texture);
		bulletAmountSpriteTens = new Sprite(t);
		bulletAmountSpriteTens.setSize(20, 20);
		
		bulletAmountSpriteOnes = new Sprite(t);
		bulletAmountSpriteOnes.setSize(20, 20);
		
		Table table = new Table();
		table.top();
		table.setFillParent(true);
		
		
		//countdownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		//scoreLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		//table.add().expand().padTop(10);
	}
	public void update(Mario mariovsky, SpriteBatch batch, OrthographicCamera camera) {
		
		batch.setProjectionMatrix(camera.combined);
		scoreSpriteTens.setPosition(camera.position.x-15,camera.position.y+200);
		scoreSpriteOnes.setPosition(camera.position.x+15,camera.position.y+200);
		
		bulletSprite.setPosition(camera.position.x-camera.viewportWidth/2, camera.position.y+205);
		bulletAmountSpriteTens.setPosition(camera.position.x+20-camera.viewportWidth/2+20,camera.position.y+200);
		bulletAmountSpriteOnes.setPosition(camera.position.x+20-camera.viewportWidth/2+40,camera.position.y+200);
		
		batch.begin();
		scoreSpriteTens.draw(batch);
		scoreSpriteOnes.draw(batch);
		bulletAmountSpriteOnes.draw(batch);
		bulletAmountSpriteTens.draw(batch);
		bulletSprite.draw(batch);
		batch.end();
	}
	public void increaseScore() {
		score++;
		switch (score%10) {
		case 0:
			texture = new Texture("0.png");
			scoreSpriteOnes.setRegion(texture);
			break;
		case 1:
			texture = new Texture("1.png");
			scoreSpriteOnes.setRegion(texture);
			break;
		case 2:
			texture = new Texture("2.png");
			scoreSpriteOnes.setRegion(texture);
			break;
		case 3:
			texture = new Texture("3.png");
			scoreSpriteOnes.setRegion(texture);
			break;
		case 4:
			texture = new Texture("4.png");
			scoreSpriteOnes.setRegion(texture);
			break;
		case 5:
			texture = new Texture("5.png");
			scoreSpriteOnes.setRegion(texture);
			break;
		case 6:
			texture = new Texture("6.png");
			scoreSpriteOnes.setRegion(texture);
			break;
		case 7:
			texture = new Texture("7.png");
			scoreSpriteOnes.setRegion(texture);
			break;
		case 8:
			texture = new Texture("8.png");
			scoreSpriteOnes.setRegion(texture);
			break;
		case 9:
			texture = new Texture("9.png");
			scoreSpriteOnes.setRegion(texture);
			break;
		}
		switch (score/10) {
		case 0:
			texture = new Texture("0.png");
			scoreSpriteTens.setRegion(texture);
			break;
		case 1:
			texture = new Texture("1.png");
			scoreSpriteTens.setRegion(texture);				
			break;
		case 2:
			texture = new Texture("2.png");
			scoreSpriteTens.setRegion(texture);				
			break;
		case 3:
			texture = new Texture("3.png");
			scoreSpriteTens.setRegion(texture);				
			break;
		case 4:
			texture = new Texture("4.png");
			scoreSpriteTens.setRegion(texture);				
			break;
		case 5:
			texture = new Texture("5.png");
			scoreSpriteTens.setRegion(texture);				
			break;
		case 6:
			texture = new Texture("6.png");
			scoreSpriteTens.setRegion(texture);				
			break;
		case 7:
			texture = new Texture("7.png");
			scoreSpriteTens.setRegion(texture);				
			break;
		case 8:
			texture = new Texture("8.png");
			scoreSpriteTens.setRegion(texture);				
			break;
		case 9:
			texture = new Texture("9.png");
			scoreSpriteTens.setRegion(texture);				
			break;

		}
	}
	
	public void DecreaseBullets() {
		switch (Bullet.numberOfBullets%10) {
		case 0:
			texture = new Texture("0.png");
			bulletAmountSpriteOnes.setRegion(texture);
			break;
		case 1:
			texture = new Texture("1.png");
			bulletAmountSpriteOnes.setRegion(texture);			
			break;
		case 2:
			texture = new Texture("2.png");
			bulletAmountSpriteOnes.setRegion(texture);			
			break;
		case 3:
			texture = new Texture("3.png");
			bulletAmountSpriteOnes.setRegion(texture);
			break;
		case 4:
			texture = new Texture("4.png");
			bulletAmountSpriteOnes.setRegion(texture);			
			break;
		case 5:
			texture = new Texture("5.png");
			bulletAmountSpriteOnes.setRegion(texture);			
			break;
		case 6:
			texture = new Texture("6.png");
			bulletAmountSpriteOnes.setRegion(texture);			
			break;
		case 7:
			texture = new Texture("7.png");
			bulletAmountSpriteOnes.setRegion(texture);			
			break;
		case 8:
			texture = new Texture("8.png");
			bulletAmountSpriteOnes.setRegion(texture);
			break;
		case 9:
			texture = new Texture("9.png");
			bulletAmountSpriteOnes.setRegion(texture);
			break;
		}
		switch (Bullet.numberOfBullets/10) {
		case 0:
			texture = new Texture("0.png");
			bulletAmountSpriteTens.setRegion(texture);
			break;
		case 1:
			texture = new Texture("1.png");
			bulletAmountSpriteTens.setRegion(texture);				
			break;
		}
	}

}
