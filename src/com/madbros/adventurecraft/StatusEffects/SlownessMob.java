package com.madbros.adventurecraft.StatusEffects;

import com.madbros.adventurecraft.Time;
import com.madbros.adventurecraft.GameObjects.Actor;

public class SlownessMob extends TimedStatusEffect{
	
	public SlownessMob() {
		potionEffectTime = 5000;
		speedAmount = 0.25f;
		id = 1;
		timeTriggered = Time.getTime();
		
	}
	
	public void update(Actor actor) {
//		System.out.println("TIME: "+ Time.getTime());
//		System.out.println("timeTriggered+potioneffecttime:  "+timeTriggered+ potionEffectTime);
		if(Time.getTime() >= timeTriggered+ potionEffectTime) {
			//System.out.println("Potion time is up!");
			actor.slownessSpeed = 0f;
			actor.checkSpeed();
			
			actor.eraseTimedStatusEffect(this.id);
			
		}
	}
	
}
