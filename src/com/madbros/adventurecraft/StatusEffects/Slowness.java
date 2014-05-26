package com.madbros.adventurecraft.StatusEffects;

import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Time;
import com.madbros.adventurecraft.GameObjects.Actor;
import com.madbros.adventurecraft.Sprites.Sprites;

public class Slowness extends TimedStatusEffect{
	
	public Slowness() {
		//potionEffectTime = 15000;
		speedAmount = 0.05f;
		id = 3;
		timeTriggered = Time.getTime();
		sprite = Sprites.sprites.get(Sprites.SLOWNESS_STATUS);
		
	}
	
	public void update(Actor actor) {

	}
	
	public void applyEffect() {
		Game.hero.moveSpeed = Game.hero.moveSpeed - speedAmount;
		Game.hero.currentSpeed = Game.hero.moveSpeed;
	}
	
	public void eraseEffect() {
		Game.hero.moveSpeed = Game.hero.moveSpeed + speedAmount;
		Game.hero.currentSpeed = Game.hero.moveSpeed ;
		
		Game.hero.eraseTimedStatusEffect(this.id);
	}
	
	
}
