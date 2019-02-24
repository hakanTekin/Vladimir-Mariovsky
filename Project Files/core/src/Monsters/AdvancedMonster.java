package Monsters;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

public class AdvancedMonster extends Monster{
	
	public AdvancedMonster(World world, FixtureDef fdef, Body body, BodyDef bdef, Shape shape) {
	}
	@Override
	public void move() {}
	
	public void shoot() {}
}
