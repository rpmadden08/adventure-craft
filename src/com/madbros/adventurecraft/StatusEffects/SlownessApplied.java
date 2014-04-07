package com.madbros.adventurecraft.StatusEffects;

import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.GameObjects.Actor;
import static com.madbros.adventurecraft.Constants.*;


public class SlownessApplied extends AppliedStatusEffect{
	public SlownessApplied() {
		id = SLOWNESS_APPLIED;
		usesLeft = 5;
	}

	public boolean canApplyEffect(Actor actor) {
		if(actor.timedStatusEffects[SLOWNESS].id == SLOWNESS) {
			return false;
		} else {
			return true;
		}
		
	}
	public void applySlownessEffect(Actor actor) {
		//System.out.println("Slowness Applied...  Uses Left:  "+usesLeft);
		actor.timedStatusEffects[SLOWNESS] = new Slowness();
		actor.moveSpeed = actor.moveSpeed - Slowness.speedAmount;
		actor.currentSpeed = actor.moveSpeed;
		
		
		usesLeft = usesLeft-1;
		if(usesLeft <=0) {
			Game.hero.eraseAppliedStatusEffect(this.id);
		}
	}
}
