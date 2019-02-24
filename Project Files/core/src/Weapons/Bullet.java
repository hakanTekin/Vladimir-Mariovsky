package Weapons;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import MarioAndComponents.Hud;
import MarioAndComponents.Mario;
import Screens.PlayScreen;

public class Bullet extends Sprite{
	
	public static final int SPEED = 500;
	public static final int RANGE = 75;
	
	public static ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	//public static final int DEFAULT_Y = 24;
	private static Texture texture;
	
	public Body b2body;
	public BodyDef bdef;
	public FixtureDef fdef;
	public Sprite sprite = new Sprite();
	public TextureRegion region;
	public World world;
	public static int numberOfBullets = 0;

	
	float x,y,distanceTaken,ox;
	
	public boolean isRemove = false;
	
	private boolean isFacingRight = true;
	
	public Bullet(float x, float y, World world, boolean isFacingRight, Mario mario) {
		Bullet.numberOfBullets--;
		this.x = x;
		this.y = y;
		if(!isFacingRight) {
			this.x = x-20;
			this.y = y;
		}
		ox=x;
		this.isFacingRight = isFacingRight;

		if(texture == null && isFacingRight)
			texture = new Texture("shard1.png");
		else if (texture == null && !isFacingRight)
			texture = new Texture("shard1Left.png");
		if(region == null)
			region = new TextureRegion(texture);
		
		this.world = world;
		
		BodyDef bdef = new BodyDef();
		
		// TODO Auto-generated method stub
		bdef.position.set(getX(),getY());
		bdef.type = BodyDef.BodyType.DynamicBody;
		
		b2body = world.createBody(bdef);
		
		this.setRegion(texture);
		this.setSize(20,5);

		b2body.setActive(true);
		fdef = new FixtureDef();
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(4, 1);
		fdef.shape = shape;
		b2body.createFixture(fdef);
		
		this.setPosition(x, y);
		
	}
	public void die(Map map) {
		
		isRemove = true;
		//Coins.remove(index);
		//bullets.remove(this);
		PlayScreen.bodiesToDestroy.add(this.b2body);
		System.out.println("I, bullet am daing");
		
	}
	
	public void update(float dTime) {
		
		if(isFacingRight) {
			x+=SPEED*dTime;
			b2body.setTransform(x, y, 0);
		}
		else {
			x-=SPEED*dTime;
			b2body.setTransform(x, y, 0);
		}
		distanceTaken+=SPEED*dTime;
		
		this.setPosition(b2body.getPosition().x, b2body.getPosition().y);
		
		/*if(distanceTaken - ox > RANGE) {
			isRemove = true;
			//bullets.remove(this);
			PlayScreen.bodiesToDestroy.add(this.b2body);
		}*/
	}
	
	public void render(SpriteBatch batch) {
		this.draw(batch);
	}
	public static void bulletRefill(Hud hud) {
		numberOfBullets = 10;
		hud.DecreaseBullets();
	}
}
