package com.madbros.adventurecraft.StatusEffects;

import com.madbros.adventurecraft.GameObjects.Actor;

public class AppliedStatusEffect extends StatusEffect{
	public int usesLeft = 0;
	
	public void use() {
		
	}
	
	public boolean canApplyEffect(Actor actor) {
		return true;
	}
	
	public int getHarmingDamageIncrease(int damage) {
		return damage;
	}
	
	public void applySlownessEffect(Actor actor) {
		
	}

}
