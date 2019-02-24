package collectables;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import MarioAndComponents.Mario;
import Weapons.Bullet;

public class Vodka {

	public Body body;
	private Sprite sprite;
	private Texture texture = new Texture("vodka.png");
	private TextureRegion region;
	
	public boolean isGet = false;

	public static ArrayList<Vodka> vodkaList = new ArrayList<Vodka>();

	public Vodka(World world, FixtureDef fdex, Body body, BodyDef bdof, PolygonShape shape) {
		this.body =body;
		sprite = new Sprite();
		region = new TextureRegion(texture);
		sprite.setRegion(region);
		sprite.setSize(20, 20);
		sprite.setPosition(body.getPosition().x-sprite.getWidth()/2, body.getPosition().y-sprite.getHeight()/2);
		Bullet.numberOfBullets = 20;
		new SpriteBatch();
		vodkaList.add(this);
	}

	public static void update(SpriteBatch batch) {
		for(Vodka vodka : vodkaList) {
			if(!vodka.isGet) {
				batch.begin();
				vodka.sprite.draw(batch);
				batch.end();
			}
		}
	}
	public void Die(Mario mario) {
		isGet = true;
		mario.activateVodka();
		dispose();
		//Vodka.vodkaList.remove(this);
	}
	public void dispose() {
		texture.dispose();
	}
}
