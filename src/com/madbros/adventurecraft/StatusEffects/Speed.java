package com.madbros.adventurecraft.StatusEffects;

import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Time;
import com.madbros.adventurecraft.GameObjects.Actor;

public class Speed extends TimedStatusEffect{
	
	public Speed() {
		potionEffectTime = 15000;
		speedAmount = 0.1f;
		id = 2;
		timeTriggered = Time.getTime();
		
	}
	
	public void update(Actor actor) {
//		System.out.println("TIME: "+ Time.getTime());
//		System.out.println("timeTriggered+potioneffecttime:  "+timeTriggered+ potionEffectTime);
		if(Time.getTime() >= timeTriggered+ potionEffectTime) {
			//System.out.println("Potion time is up!");
			actor.moveSpeed = actor.moveSpeed - speedAmount;
			actor.currentSpeed = actor.moveSpeed ;
			
			actor.eraseTimedStatusEffect(this.id);
			
		}
	}
	
}
