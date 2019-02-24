package Monsters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

public class BasicMonster extends Monster {

	public BasicMonster(World world, FixtureDef fdef, Body body, BodyDef bdef, Shape shape) {
		this.world = world;

		this.body = body;
		this.fixtureDef = fdef;
		this.bdef = bdef;

		sprite.setRegion(region);
		sprite.setSize(20, 20);
		sprite.setPosition(body.getPosition().x, body.getPosition().y);
		monsterNumber = monsterID;

		Monsters.add(this);

		monsterID++;

	}

	int time = 0;
	int step = 1;
	public void update() {
		time++;
		if (step == 0 && time == 10) {
			texture = new Texture("goomba_left.png");
			step = 1;
			time = 0;
		} else if (time == 10) {
			step = 0;
			texture = new Texture("goomba_right.png");
			time = 0;
		}
		region = new TextureRegion(texture);
		sprite.setRegion(texture);
	}

	@Override
	public void move() {
		if (isFacingRight) {
			this.body.applyLinearImpulse(new Vector2(speed, 0), this.body.getWorldCenter(), true);
			update();
		} else {
			this.body.applyLinearImpulse(new Vector2(-speed, 0), this.body.getWorldCenter(), true);
			update();
		}
	}
}
