package com.madbros.tileminer;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.openal.Wav.Sound;

public class SoundController {
	public ArrayList<Sound> sounds = new ArrayList<Sound>();
	public ArrayList<SoundData> soundsData = new ArrayList<SoundData>();
	
	public void update() {
		if(Game.isSoundOn == true) {
		//for every sound in this list do...
			for(int i = 0; i < sounds.size(); i++) {
				//Plays the sound on update
				if(!soundsData.get(i).isPlayed) {
					sounds.get(i).play(soundsData.get(i).volume);
					soundsData.get(i).isPlayed = true;
				}
				soundsData.get(i).timePlayed = Time.getTime();
				int test = (int) (soundsData.get(i).timePlayed -soundsData.get(i).timeStarted);
				//Deletes the sound if its been played.  
				if(test >= 1000) {
					sounds.get(i).dispose();
					sounds.remove(i);
					soundsData.remove(i);
				}
			}
		}
	}
	
	public void create(String name, float volume) {
		sounds.add((Sound) Gdx.audio.newSound(Gdx.files.internal(name)));
		soundsData.add(new SoundData());
		int test = sounds.size()-1;
		soundsData.get(test).volume = volume;
		
	}
	
	public void remove(Sound sound) {
		sounds.remove(sound);
		soundsData.remove(sound);
	}
}
