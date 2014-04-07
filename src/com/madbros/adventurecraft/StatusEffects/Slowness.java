package com.madbros.adventurecraft.StatusEffects;

import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Time;
import com.madbros.adventurecraft.GameObjects.Actor;

public class Slowness extends TimedStatusEffect{
	
	public Slowness() {
		potionEffectTime = 5000;
		speedAmount = 0.02f;
		id = 1;
		timeTriggered = Time.getTime();
		
	}
	
	public void update(Actor actor) {
//		System.out.println("TIME: "+ Time.getTime());
//		System.out.println("timeTriggered+potioneffecttime:  "+timeTriggered+ potionEffectTime);
		if(Time.getTime() >= timeTriggered+ potionEffectTime) {
			//System.out.println("Potion time is up!");
			actor.moveSpeed = actor.moveSpeed + speedAmount;
			actor.currentSpeed = actor.moveSpeed ;
			
			actor.eraseTimedStatusEffect(this.id);
			
		}
	}
	
}
