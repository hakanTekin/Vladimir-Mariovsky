package MarioAndComponents;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mario.game.sMario;

import Screens.DeathScreen;
import Screens.PlayScreen;
import Weapons.Bullet;

public class Mario extends Sprite{
	
	sMario game;

	public Hud hud;

	public World world;
	public Body b2body;
	public OrthographicCamera camera=new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
	public FixtureDef fdef;
	public FixtureDef jumperFDef;
	public Texture texture=new Texture("mario_facing_right.png");
	public float marioSpeed = 100f;
	public boolean isFacingRight;
	public boolean isJumpable;
	public boolean isAlive;
	public boolean isVodkaActive;

	public ArrayList<Bullet> bulletToRemove = new ArrayList<Bullet>();


	public Mario(World world, sMario game) {
		
		this.game = game;
		this.world = world;
		
		defineMario();
	}

	private void defineMario() {
		BodyDef bdef = new BodyDef();
		isFacingRight = true;
		isJumpable = true;
		isAlive = true;
		isVodkaActive = false;
		setPosition(150, 50);

		// TODO Auto-generated method stub
		bdef.position.set(getX(),getY());
		bdef.type = BodyDef.BodyType.DynamicBody;

		b2body = world.createBody(bdef);

		this.setRegion(texture);

		this.setSize(20, 20);

		b2body.setActive(true);
		fdef = new FixtureDef();

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(6, 6);
		fdef.shape = shape;
		b2body.createFixture(fdef);

		jumperFDef = new FixtureDef();
		PolygonShape jumperShape = new PolygonShape();
		jumperShape.setAsBox(4f, 2f,new Vector2(0f,-this.getHeight()/2+4),0);
		jumperFDef.shape =jumperShape;
		b2body.createFixture(jumperFDef);

		hud = new Hud();

	}
	public void handleInput(float dt, Viewport gamePort, Map map) {
		
		if (Gdx.input.isKeyPressed(Keys.D)) {
			if (!isFacingRight) {
				texture = new Texture("mario_turntoright.png");
				this.setRegion(texture);
				this.isFacingRight = true;
			}
			if (this.getX() >= gamePort.getScreenX()) {
				if (this.b2body.getLinearVelocity().x < this.marioSpeed)
					this.b2body.applyLinearImpulse(new Vector2(this.marioSpeed / 10, 0), this.b2body.getWorldCenter(),
							true);
				update();
				// System.out.println(this.b2body.getLinearVelocity().y);
				// this.translateX(-150*dt);
			}
			// this.setPosition(this.getX()+200*dt, this.getY());

		}
		if (Gdx.input.isKeyPressed(Keys.A)) {
			if (isFacingRight) {
				texture = new Texture("mario_turntoleft.png");
				this.setRegion(texture);
				this.isFacingRight = false;
			}
			if (this.getX() > 0) {
				if (this.b2body.getLinearVelocity().x < this.marioSpeed)
					b2body.applyLinearImpulse(new Vector2(-this.marioSpeed / 10, 0), this.b2body.getWorldCenter(),
							true);
				update();
			}
			// this.b2body.applyForceToCenter(new Vector2(-9000,0), true);
		}
		if (Gdx.input.isKeyPressed(Keys.W)) {
			/*
			 * this.b2body.setLinearVelocity(new Vector2(0,0)); this.b2body.applyForce(new
			 * Vector2(0,7000), this.b2body.getWorldCenter(), true);
			 * this.setPosition(this.b2body.getPosition().x, this.b2body.getPosition().y);
			 */

			if (isJumpable) {
				if(isFacingRight) {
					texture = new Texture("mario_jumping_right.png");
				}else {
					texture = new Texture("mario_jumping_left.png");
				}
				this.setRegion(texture);
				b2body.applyLinearImpulse(new Vector2(b2body.getLinearVelocity().x, 3000), this.b2body.getWorldCenter(),
						true);
			}
		}
		if (Gdx.input.isKeyJustPressed(Keys.SPACE) && isVodkaActive && Bullet.numberOfBullets > 0) {
			Bullet.bullets.add(new Bullet(this.getX() + 17, this.getY() + 10, world,isFacingRight,this));
			hud.DecreaseBullets();
		}if(Gdx.input.isKeyPressed(Keys.E)) {
			b2body.applyLinearImpulse(new Vector2(0,5), b2body.getWorldCenter(), true);

		}
	}

	public void renderMario(float dt, SpriteBatch batch, Camera gamecam) {

		if(!isJumpable) {
		}
		if(this.getY()<-20)this.die();	
		this.setPosition(this.b2body.getPosition().x-this.getWidth()/2, this.b2body.getPosition().y-this.getHeight()/2);

		//bullet begin

		for(Bullet bullet : Bullet.bullets) {
			bullet.update(dt);
			if(bullet.isRemove)
				bulletToRemove.add(bullet);
		}
		Bullet.bullets.removeAll(bulletToRemove);

		//bullet end

		//if you dont set the matrix to camera then the rendering operations will not be relative to the camera, hence making the textures go faster than their bodies
		batch.setProjectionMatrix(gamecam.combined);

		batch.begin();

		for(Bullet bullet : Bullet.bullets) {
			bullet.render(batch);
		}

		this.draw(batch);
		batch.end();

		hud.update(this, batch, camera);
	}


	// ahmet moving animation
	int time = 0;

	public void update() {
		time++;
		if (isJumpable) {
			if (isFacingRight) {
				if (time < 6) {
					texture = new Texture("mario_run1_right.png");
				} else if (time < 12) {
					texture = new Texture("mario_run2_right.png");
				} else if (time < 18) {
					texture = new Texture("mario_run3_right.png");
				} else {
					time = 0;
				}
			} else {
				if (time < 6) {
					texture = new Texture("mario_run1_left.png");
				} else if (time < 12) {
					texture = new Texture("mario_run2_left.png");
				} else if (time < 18) {
					texture = new Texture("mario_run3_left.png");
				} else {
					time = 0;
				}
			}
			this.setRegion(texture);
		}
	}
	//
	public void changeJumpImageToIdle() {
		System.out.println("facing right: " + isFacingRight);
		if(isJumpable) {
			if(isFacingRight)
				texture = new Texture("mario_facing_right.png");
			if(!isFacingRight)
				texture = new Texture("mario_facing_left.png");
			this.setRegion(texture);
		}

	}
	public void activateVodka() {
		isVodkaActive = true;
		Bullet.bulletRefill(hud);
	}
	public void die() {
		System.out.println("Deadass");
		isAlive = false;
		PlayScreen.bodiesToDestroy.add(this.b2body);////////////////////////
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DeathScreen ds = new DeathScreen(game,isAlive);
	}
}
