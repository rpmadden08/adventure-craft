package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.openal.Wav.Sound;
import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Utils.Rect;

public class Sword extends WeaponItem {
	public Sword() {
		id = SWORD;
		sprite = Sprites.sprites.get(Sprites.SWORD);
		originX = 0;
		originY = 32;
		weaponOffsetX = 0;
		weaponOffsetY = 0;
		cRectFinal = new Rect (0,0,0,0);
		sound = (Sound) Gdx.audio.newSound(Gdx.files.internal("sounds/swordSwing1.wav"));
		
		cRectU = new Rect (0,-6,82,32);
		cRectD = new Rect (0,54,82,32);
		cRectL = new Rect (-22,0,40,82);
		cRectR = new Rect (46,0,40,82);
	}
	
	@Override
	public void useLeft() {
		if(!Game.hero.isAttacking && Game.hero.attackButtonReleased) {
			
			Game.hero.attack(this);
			sound.play();
		}
	}
	
	@Override
	public Sword createNew() {
		return new Sword();
	}
}
