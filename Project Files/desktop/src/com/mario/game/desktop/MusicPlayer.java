package com.mario.game.desktop;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MusicPlayer {
	AudioInputStream audioInputStream;
	String filePath="./music.wav";
	Clip clip;
	File music=new File(filePath);
	public MusicPlayer() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		audioInputStream=AudioSystem.getAudioInputStream(music);
		clip=AudioSystem.getClip();
		
	}
	public void play() throws IOException, LineUnavailableException {
		clip.open(audioInputStream);
		clip.start();
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		
	}
	
}