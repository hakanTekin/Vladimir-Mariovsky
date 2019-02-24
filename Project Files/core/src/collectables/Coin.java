package collectables;

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
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;


public class Coin extends Sprite{	
	
	public static ArrayList<Coin> Coins = new ArrayList<Coin>();
	public static ArrayList<Integer> deadID = new ArrayList<Integer>();
	
	static int CoinID = 0;
	public int deadCoinID;
	
	public int getdeadCoinID() {
		return deadCoinID;
	}
	
	public boolean isGet=false;
	float speed = 100;
	float range;		
	public static boolean isThereConnectedCoin = false;
	
	public Body body;
	public BodyDef bdef;
	public FixtureDef fixtureDef;
	public Sprite sprite = new Sprite();
	public Texture texture = new Texture("bitcoin.png");
	public TextureRegion region = new TextureRegion(texture);
	public World world;

	public void renderme (SpriteBatch batch, float dt, Camera gamecam) {
		
		batch.setProjectionMatrix(gamecam.combined);
		//sprite.setPosition(body.getPosition().x - sprite.getWidth()/2, body.getPosition().y - sprite.getHeight()/2);
		
		
		batch.begin();
		sprite.draw(batch);
		batch.end();
	}

	public void die(Map map, int index) {
		isGet = true;
		//Coins.remove(index);
		deadID.add(index);
		isThereConnectedCoin = true;
		System.out.println("I am daing");
		
		
	}
	
	public Coin(World world, FixtureDef fdef, Body body, BodyDef bdef, Shape shape) {
		this.world = world;
		
		this.body = body;
		this.fixtureDef = fdef;
		this.bdef = bdef;
		deadCoinID = CoinID;
		sprite.setRegion(region);
		sprite.setSize(10, 10);
		sprite.setPosition(body.getPosition().x, body.getPosition().y);
		Coins.add(this);
		CoinID++;
	}
	
	
}
