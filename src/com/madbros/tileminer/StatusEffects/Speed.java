package com.madbros.tileminer.StatusEffects;


import com.madbros.tileminer.Time;
import com.madbros.tileminer.GameObjects.Actor;
import com.madbros.tileminer.Sprites.Sprites;

public class Speed extends TimedStatusEffect{
	
	public Speed() {
		potionEffectTime = 65000; //15000
		speedAmount = 0.25f;
		id = 2;
		timeTriggered = Time.getTime();
		sprite = Sprites.sprites.get(Sprites.SPEED_STATUS);
		
	}
	
	public void update(Actor actor) {
		if(Time.getTime() >= timeTriggered+ potionEffectTime) {
			actor.speedSpeed = 0f;
			actor.checkSpeed();
			
			actor.eraseTimedStatusEffect(this.id);
			
		}
	}
	
}
