package Screens;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mario.game.sMario;

import MarioAndComponents.Mario;
import Monsters.BasicMonster;
import Monsters.Monster;
import Weapons.Bullet;
import collectables.Coin;
import collectables.EndRod;
import collectables.Vodka;

public class PlayScreen implements Screen{
	private sMario game;

	private OrthographicCamera gamecam;
	private Viewport gamePort;
	private Mario Mariovsky;
	private TmxMapLoader mapLoader;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;

	//Box2d variables
	private World world;
	private Box2DDebugRenderer b2dr;
	private SpriteBatch batch;

	static public ArrayList<Body> bodiesToDestroy = new ArrayList<Body>();

	private boolean AnythingToDestroy = false;
	public boolean isMarioAlive() {
		if(Mariovsky.isAlive) {
			return true;
		}else {
			return false;
		}
	}
	public PlayScreen(sMario smario) {
		//System.out.println("play screen constructor called");
		this.game = smario;
		//create cam used to follow mario through cam world
		gamecam = new OrthographicCamera();			

		//create a FitViewport to maintain virtual aspect ratio
		gamePort = new FitViewport(sMario.V_WIDTH, sMario.V_HEIGHT, gamecam);

		//create our game HUD
		//hud = new Hud(game.batch);
		mapLoader = new TmxMapLoader();
		map = mapLoader.load("level1.tmx");
		renderer = new OrthogonalTiledMapRenderer(map);
		gamecam.position.set(0+gamecam.viewportWidth/2,0+gamecam.viewportHeight/2,0);
		gamecam.setToOrtho(false,Gdx.graphics.getWidth()/2-32, Gdx.graphics.getHeight()/2-32);
		//gamecam.setToOrtho(false,gamePort.getScreenWidth()/2, gamePort.getScreenHeight()/2);

		world = new World(new Vector2(0,-200), true);
		System.out.println("Body count : " + world.getBodyCount());
		b2dr = new Box2DDebugRenderer();

		Mariovsky=new Mario(world,smario);

		batch=new SpriteBatch();

		createWorld();
	}

	public void createWorld() {
		BodyDef bdef = new BodyDef();
		PolygonShape shape = new PolygonShape();
		FixtureDef fdef = new FixtureDef();
		Body body;


		//enemy begin

		for(int i = 0; i<map.getLayers().size(); i++) {
			for(MapObject object: map.getLayers().get(i).getObjects().getByType(RectangleMapObject.class)) {
				com.badlogic.gdx.math.Rectangle rect = ((RectangleMapObject) object).getRectangle();

				if(i == 4) {
					bdef.type = BodyType.StaticBody;				

					bdef.position.set(rect.getX()+rect.getWidth()/2, rect.getY() + rect.getHeight()/2);

					body = world.createBody(bdef);

					shape.setAsBox(rect.getWidth()/2, rect.getHeight()/2);
					fdef.shape = shape;
					body.createFixture(fdef);							
					new Coin(world,fdef,body,bdef,shape);
				}else {
					if(i == 6) {// enemy index is 6
						bdef.type = BodyType.DynamicBody;
					}
					else bdef.type = BodyType.StaticBody;

					bdef.position.set(rect.getX()+rect.getWidth()/2, rect.getY() + rect.getHeight()/2);

					body = world.createBody(bdef);

					shape.setAsBox(rect.getWidth()/2, rect.getHeight()/2);
					fdef.shape = shape;
					body.createFixture(fdef);

					if(i == 6) new BasicMonster(world, fdef,body,bdef,shape);

					if (i == 8) new Vodka(world, fdef, body, bdef, shape);

					if(i == 9) new EndRod(fdef, body, bdef, shape, game);
				}
			}
		}

		//enemy end

		//vodka begin

		//vodka end

		//coins begin

		//coins end
		//System.out.println(BasicMonster.Monsters.size());


		world.setContactListener(new ContactListener() {

			@Override
			public void preSolve(Contact arg0, Manifold arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void postSolve(Contact arg0, ContactImpulse arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void endContact(Contact contact) {
				// TODO Auto-generated method stub
				if(Mariovsky.isAlive) {
					if(Mariovsky.b2body.getFixtureList().get(1).equals(contact.getFixtureA()) || Mariovsky.b2body.getFixtureList().get(1).equals(contact.getFixtureB())) {
						Mariovsky.isJumpable = false;
					}
				}
			}

			@Override
			public void beginContact(Contact contact) {
				if(Mariovsky.isAlive) {
					//System.out.println(contact.getFixtureA() +  " has hit " + contact.getFixtureB());
					if(Mariovsky.b2body.getFixtureList().get(1).equals(contact.getFixtureA()) || Mariovsky.b2body.getFixtureList().get(1).equals(contact.getFixtureB())) {
						Mariovsky.isJumpable = true;
						Mariovsky.changeJumpImageToIdle();
					}

					for(int i = 0; i<Monster.Monsters.size(); i++) {
						if((Monster.Monsters.get(i).body.getFixtureList().get(0).equals(contact.getFixtureA()) && Mariovsky.b2body.getFixtureList().get(1).equals(contact.getFixtureB())) ||((Monster.Monsters.get(i).body.getFixtureList().get(0).equals(contact.getFixtureB()) && Mariovsky.b2body.getFixtureList().get(1).equals(contact.getFixtureA())))) { 
							AnythingToDestroy = true;
							Monster.Monsters.get(i).die(map,Mariovsky.hud);
						}
						else if((Monster.Monsters.get(i).body.getFixtureList().get(0).equals(contact.getFixtureA()) && Mariovsky.b2body.getFixtureList().get(0).equals(contact.getFixtureB())) ||((Monster.Monsters.get(i).body.getFixtureList().get(0).equals(contact.getFixtureB()) && Mariovsky.b2body.getFixtureList().get(0).equals(contact.getFixtureA()))))
							Mariovsky.die();
						else if(Monster.Monsters.get(i).body.getFixtureList().get(0).equals(contact.getFixtureA()) || Monster.Monsters.get(i).body.getFixtureList().get(0).equals(contact.getFixtureB())) Monster.Monsters.get(i).isFacingRight = !Monster.Monsters.get(i).isFacingRight;
					}

					//Bullets Begin
					for(int i = 0; i<Bullet.bullets.size(); i++) {
						if((Bullet.bullets.get(i).b2body.getFixtureList().get(0).equals(contact.getFixtureA()) ||Bullet.bullets.get(i).b2body.getFixtureList().get(0).equals(contact.getFixtureB()))){
							for(int j = 0; j<Monster.Monsters.size();j++) {
								if(Bullet.bullets.get(i).b2body.getFixtureList().get(0).equals(contact.getFixtureA()) && Monster.Monsters.get(j).body.getFixtureList().get(0).equals(contact.getFixtureB()) || Bullet.bullets.get(i).b2body.getFixtureList().get(0).equals(contact.getFixtureB()) && Monster.Monsters.get(j).body.getFixtureList().get(0).equals(contact.getFixtureA())) {
									AnythingToDestroy = true;
									Monster.Monsters.get(j).die(map,Mariovsky.hud);    
								}
							}
							AnythingToDestroy = true;
							Bullet.bullets.get(i).die(map);  
						}

					}

					//coin begin
					for(int i = 0; i<Coin.Coins.size(); i++) {
						if(!Coin.Coins.get(i).isGet) {
							if((Coin.Coins.get(i).body.getFixtureList().get(0).equals(contact.getFixtureB()) && Mariovsky.b2body.getFixtureList().get(0).equals(contact.getFixtureA())) ||((Coin.Coins.get(i).body.getFixtureList().get(0).equals(contact.getFixtureA()) && Mariovsky.b2body.getFixtureList().get(0).equals(contact.getFixtureB())))) {
								Coin.Coins.get(i).die(map,i);
								Mariovsky.hud.increaseScore();
							}
						}
					}
					//vodka begin
					for(Vodka vodka : Vodka.vodkaList) {
						if(!vodka.isGet) {
							if((contact.getFixtureA().equals(vodka.body.getFixtureList().get(0)) || contact.getFixtureB().equals(vodka.body.getFixtureList().get(0))) && (Mariovsky.b2body.getFixtureList().get(0).equals(contact.getFixtureA()) || Mariovsky.b2body.getFixtureList().get(0).equals(contact.getFixtureB()))) {
								System.out.println("Dokundu");
								AnythingToDestroy = true;
								vodka.Die(Mariovsky);
								bodiesToDestroy.add(vodka.body);

							}
						}
					}

					for(EndRod rod : EndRod.rodList) {
						if((contact.getFixtureA().equals(rod.b2body.getFixtureList().get(0)) || contact.getFixtureB().equals(rod.b2body.getFixtureList().get(0))) && (Mariovsky.b2body.getFixtureList().get(0).equals(contact.getFixtureA()) || Mariovsky.b2body.getFixtureList().get(0).equals(contact.getFixtureB()))) {
							rod.successfulFinishEnd();
							System.out.println("Yay you finished the game");
						}
					}
				}
			}

		});
	}

	public void update(float dt) {
		if(Mariovsky.isAlive) {
			world.step(1/60f, 6, 2);
			if(AnythingToDestroy)
				DestroyThemBodies();

			if(Mariovsky.getX()>10) 
				Mariovsky.handleInput(dt,gamePort,map);


			if(Mariovsky.getX()>150) {
				gamecam.position.set(new Vector3(Mariovsky.getX(), gamecam.viewportHeight/2, 0));
			}
			gamecam.update();

			renderer.setView(gamecam);
		}
	}

	public void render(float delta) {
		// Clear the game screen with Black
		if(Mariovsky.isAlive) {

			Gdx.gl.glClearColor(1, 0, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

			update(delta);

			//render our game map
			renderer.render();

			//render mario end enemies
			Mariovsky.renderMario(delta, batch, gamecam);

			for(int i = 0; i<Monster.Monsters.size(); i++) {
				Monster.Monsters.get(i).renderme(batch, delta, gamecam);
			}
			b2dr.render(world, gamecam.combined);

			if(Coin.isThereConnectedCoin) {	

				int CoinContactID = Coin.deadID.get(Coin.deadID.size()-1);
				System.out.println(CoinContactID);
				int originPos = (int) Coin.Coins.get(CoinContactID).body.getPosition().y;

				if(Coin.Coins.get(CoinContactID).sprite.getY()> originPos+100) {
					Coin.Coins.get(CoinContactID).sprite.setTexture(null);
					Coin.isThereConnectedCoin = false;
				} else {
					System.out.println(originPos + " " + Coin.Coins.get(CoinContactID).sprite.getY());
					Coin.Coins.get(CoinContactID).sprite.setPosition(Coin.Coins.get(CoinContactID).sprite.getX(), Coin.Coins.get(CoinContactID).sprite.getY()+10);
					//Coin.Coins.get(CoinContactID).sprite.setPosition(Mariovsky.getX(), Mariovsky.getY() + 20);
					Coin.Coins.get(CoinContactID).renderme(batch, delta, gamecam);
				}
				System.out.println("Contact coin");
				System.out.println("Coin pos :" + Coin.Coins.get(CoinContactID).body.getPosition().x + " " + Coin.Coins.get(CoinContactID).sprite.getX());


				for(int i = 0; i<Coin.deadID.size();i++){
					Coin.Coins.get(Coin.deadID.get(i));

				}
			}
			//Coin.Coins.
		}
		Vodka.update(batch);
	}
	public void DestroyThemBodies() {
		System.out.println("Bodies to destroy : " + bodiesToDestroy.size());
		for(int i = 0; i<bodiesToDestroy.size(); i++) {
			System.out.println(bodiesToDestroy.get(i).toString() + " get that weak shit outta here boi");
			System.out.println(world.toString());
			world.destroyBody(bodiesToDestroy.get(i));
		}
		bodiesToDestroy.clear();
		AnythingToDestroy = false;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		batch.dispose();

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

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