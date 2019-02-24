package collectables;

import java.util.ArrayList;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.mario.game.sMario;

import Screens.DeathScreen;

public class EndRod {

	public Body b2body;
	FixtureDef fdef;
	BodyDef bdef;
	PolygonShape shape;
	sMario game;
	
	public static ArrayList<EndRod> rodList = new ArrayList<EndRod>();
	
	public EndRod(FixtureDef fdef, Body b2body, BodyDef bdef, PolygonShape shape, sMario game) {
		
		this.game = game;
		this.fdef = fdef;
		this.b2body = b2body;
		this.bdef = bdef;
		this.shape = shape;
		rodList.add(this);
		
	}
	
	public void successfulFinishEnd() {
		
		DeathScreen ds = new DeathScreen(game,true);
	}
	
}
