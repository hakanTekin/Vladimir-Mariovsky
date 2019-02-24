package com.mario.game.desktop;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mario.game.sMario;

public class DesktopLauncher {
	public static void main (String[] arg) throws UnsupportedAudioFileException, IOException, LineUnavailableException  {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new sMario(), config);

MusicPlayer background=new MusicPlayer();
		background.play();
	}
}
