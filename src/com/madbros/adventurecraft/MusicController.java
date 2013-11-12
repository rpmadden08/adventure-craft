package com.madbros.adventurecraft;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class MusicController {
	public int musicSelection = 0;
	public Music music = Gdx.audio.newMusic(Gdx.files.internal("music/overworld.wav"));
	
	public MusicController() {
		
	}
	
	public void update() {
		if(Game.level.isDay == true) {
			if (musicSelection != 1) {
				musicSelection = 1;
				if(music.isPlaying()) {
					music.stop();
					music.dispose();
				}
				music = Gdx.audio.newMusic(Gdx.files.internal("music/overworld.wav"));
				
				music.play();
				music.setLooping(true);
			}
		} else if (Game.level.isDay == false) {
			if (musicSelection != 2) {
				musicSelection = 2;
				if(music.isPlaying()) {
					music.stop();
					music.dispose();
				}
				music = Gdx.audio.newMusic(Gdx.files.internal("music/darkworld.wav"));
				
				music.play();
				music.setLooping(true);
			}
		}
	}
}
