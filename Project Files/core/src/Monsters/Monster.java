package Monsters;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import MarioAndComponents.Hud;
import Screens.PlayScreen;

public class Monster extends Sprite{

	public static ArrayList<Monster> Monsters = new ArrayList<Monster>();
	static int monsterID = 0;
	public int monsterNumber;
	boolean isAlive;
	float speed = 150;
	float range;
	public boolean isFacingRight;

	public Body body;
	BodyDef bdef;
	FixtureDef fixtureDef;
	Sprite sprite = new Sprite();
	Texture texture = new Texture("mario.png");
	TextureRegion region = new TextureRegion(texture);
	World world;

	public void renderme (SpriteBatch batch, float dt, Camera gamecam) {
		
		batch.setProjectionMatrix(gamecam.combined);
		sprite.setPosition(body.getPosition().x - sprite.getWidth()/2, body.getPosition().y - sprite.getHeight()/2);

		batch.begin();
		sprite.draw(batch);
		batch.end();
		this.move();
	}

	public void move() {
	}
	public void die(Map map, Hud hud) {
		hud.increaseScore();
		isAlive = false;
		Monsters.remove(this);
		PlayScreen.bodiesToDestroy.add(this.body);
		System.out.println("Siradaki id : " + this.body);
		System.out.println(PlayScreen.bodiesToDestroy.size());
		for(int i = 0; i<PlayScreen.bodiesToDestroy.size(); i++) {
			System.out.println(PlayScreen.bodiesToDestroy.get(i));
		}
	}
}
