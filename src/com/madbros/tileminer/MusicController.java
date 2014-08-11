package com.madbros.tileminer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

import static com.madbros.tileminer.Constants.*;

public class MusicController {
	public int musicSelection = 0;
	public Music music = Gdx.audio.newMusic(Gdx.files.internal("music/blank.mp3"));
	//public Music music = null;
	
	public MusicController() {
		
	}
	public void stopPlaying() {
		if(music.isPlaying()) {
			music.stop();
			music.dispose();
		}
	}
	public void update() {
		if(Game.isMusicOn == true) {
			if(Game.currentLevel == UNDERGROUND_1_FOLDER) {
				if (musicSelection != 3) {
					musicSelection = 3;
					if(music.isPlaying()) {
						music.stop();
						music.dispose();
					}
					music = Gdx.audio.newMusic(Gdx.files.internal("music/lookingForClues.mp3"));
					//music.setVolume(0);
					music.play();
					music.setLooping(true);
				}
			} else if(Game.level.isDay == true) {
				if (musicSelection != 1) {
					musicSelection = 1;
					if(music.isPlaying()) {
						music.stop();
						music.dispose();
					}
					music = Gdx.audio.newMusic(Gdx.files.internal("music/swimming.mp3"));
					//music.setVolume(0);
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
					music = Gdx.audio.newMusic(Gdx.files.internal("music/conductingExperiments.mp3"));
					
					music.play();
					music.setLooping(true);
				}
			} else {
				
			}
		}
	}
}
