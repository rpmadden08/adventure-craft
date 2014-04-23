package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

//import com.badlogic.gdx.backends.openal.Wav.Sound;
import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Utils.Rect;

public class StoneSword extends WeaponItem {
	public StoneSword() {
		id = STONE_SWORD;
		name = "Stone Sword";
		sprite = Sprites.sprites.get(Sprites.STONE_SWORD);
		swingSprite = sprite;
		originX = 0;
		originY = 32;
		weaponOffsetX = 0;
		weaponOffsetY = 0;
		cRectFinal = new Rect (0,0,0,0);
		sound = "sounds/swordSwing1.wav";
		craftCost = new int[]{STONE, STICK};
		craftCostAmount = new int[]{2, 1};
		attackPower = 6;
		maxUses = 40;
		uses = 40;
		
		cRectU = new Rect (0,-6,82,32);
		cRectD = new Rect (0,54,82,32);
		cRectL = new Rect (-22,0,40,82);
		cRectR = new Rect (46,0,40,82);
	}
	
	@Override
	public void useLeft() {
		if(!Game.hero.isAttacking && Game.hero.attackButtonReleased) {
			
			Game.hero.attack(this);
			Game.soundController.create(sound, 1);
		}
	}
	
	@Override
	public StoneSword createNew() {
		return new StoneSword();
	}
}
