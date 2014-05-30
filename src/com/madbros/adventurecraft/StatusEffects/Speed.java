package com.madbros.adventurecraft.StatusEffects;


import com.madbros.adventurecraft.Time;
import com.madbros.adventurecraft.GameObjects.Actor;
import com.madbros.adventurecraft.Sprites.Sprites;

public class Speed extends TimedStatusEffect{
	
	public Speed() {
		potionEffectTime = 65000; //15000
		speedAmount = 0.25f;
		id = 2;
		timeTriggered = Time.getTime();
		sprite = Sprites.sprites.get(Sprites.SPEED_STATUS);
		
	}
	
	public void update(Actor actor) {
//		System.out.println("TIME: "+ Time.getTime());
//		System.out.println("timeTriggered+potioneffecttime:  "+timeTriggered+ potionEffectTime);
		if(Time.getTime() >= timeTriggered+ potionEffectTime) {
			//System.out.println("Potion time is up!");
			actor.speedSpeed = 0f;
			actor.checkSpeed();
			
			actor.eraseTimedStatusEffect(this.id);
			
		}
	}
	
}
