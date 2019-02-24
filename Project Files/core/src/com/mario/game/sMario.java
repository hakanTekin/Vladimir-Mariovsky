package com.mario.game;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Monsters.Monster;
import Screens.MenuScreen;
import Screens.PlayScreen;
import Weapons.Bullet;
import collectables.Coin;
import collectables.Vodka;
//oyun ana class
	public class sMario extends Game {
	public static final int V_WIDTH = 400;
	public static final int V_HEIGHT = 220;
	public static final int PPX = 10;
	
	
	public SpriteBatch batch;	
	public PlayScreen ps;
	public MenuScreen ms;
	public sMario() {
		
	}
	@Override
	public void create () {
		batch = new SpriteBatch();
		ms = new MenuScreen(this);
		//this.setScreen(new MenuScreen(this));
		this.setScreen(ms);
	}
	@Override
	public void render () {
		if(this.getScreen().equals(ps)) {
			ps.render(Gdx.graphics.getDeltaTime());
			if(!ps.isMarioAlive()) {
				
			}
		}else {
			super.render();
		}
	}

	@Override
	public void dispose () {
		super.dispose();
		//img.dispose();
	}
	
	public void setPlayScreen() {
		
		if(Monster.Monsters != null) Monster.Monsters.clear();
		if(Bullet.bullets != null) Bullet.bullets.clear();
		if(Coin.Coins != null) Coin.Coins.clear();
		if(PlayScreen.bodiesToDestroy != null) PlayScreen.bodiesToDestroy.clear();
		if(Vodka.vodkaList != null) Vodka.vodkaList.clear();
		
		ps = new PlayScreen(this);
		this.setScreen(ps);
	}
}
