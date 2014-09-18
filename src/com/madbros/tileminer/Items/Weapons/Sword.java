package com.madbros.tileminer.Items.Weapons;


//import com.badlogic.gdx.backends.openal.Wav.Sound;
import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Game;
import com.madbros.tileminer.Items.Item;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Rect;

public class Sword extends Item {
	public Sword() {
		id = SWORD;
		name = "Sword";
		sprite = Sprites.sprites.get(Sprites.SWORD);
		swingSprite = sprite;
		originX = 0;
		originY = 32;
		weaponOffsetX = 0;
		weaponOffsetY = 0;
		cRectFinal = new Rect (0,0,0,0);
		sound = "sounds/swordSwing1.wav";
		knockBackPower = 11;
		
		cRectU = new Rect (0,-6,82,32);
		cRectD = new Rect (0,54,82,32);
		cRectL = new Rect (-22,0,32,82);
		cRectR = new Rect (46,0,32,82);
	}
	
	@Override
	public void useLeft() {
		if(!Game.hero.isAttacking && Game.hero.attackButtonReleased && Game.hero.isSwimming == false) {
			
			Game.hero.attack(this);
			Game.soundController.create(sound, 0.5f);
		}
	}
	
	@Override
	public Sword createNew() {
		return new Sword();
	}
}
