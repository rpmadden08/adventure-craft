package com.madbros.tileminer.StatusEffects;

import com.madbros.tileminer.Game;
import com.madbros.tileminer.Sprites.Sprites;


public class HarmingApplied extends AppliedStatusEffect{
	public HarmingApplied() {
		id = 1;
		usesLeft = 10;//5
		sprite = Sprites.sprites.get(Sprites.HARMING_APPLIED);
	}

	public int getHarmingDamageIncrease(int damage) {
		usesLeft = usesLeft-1;
		if(usesLeft <=0) {
			Game.hero.eraseAppliedStatusEffect(this.id);
		}
		//System.out.println("USES LEFT = "+usesLeft);
		return (int)(0.5 * damage) +damage;
	}
}
