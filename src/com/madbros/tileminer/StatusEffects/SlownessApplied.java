package com.madbros.tileminer.StatusEffects;

import com.madbros.tileminer.Game;
import com.madbros.tileminer.GameObjects.Actor;
import com.madbros.tileminer.Sprites.Sprites;

import static com.madbros.tileminer.Constants.*;


public class SlownessApplied extends AppliedStatusEffect{
	public SlownessApplied() {
		id = SLOWNESS_APPLIED;
		usesLeft = 5;
		sprite = Sprites.sprites.get(Sprites.SLOWNESS_APPLIED);
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
		actor.timedStatusEffects[SLOWNESS] = new SlownessMob();
		actor.slownessSpeed = SlownessMob.speedAmount;
		//System.out.println(SlownessMob.speedAmount);
		actor.checkSpeed();
		
		
		usesLeft = usesLeft-1;
		if(usesLeft <=0) {
			Game.hero.eraseAppliedStatusEffect(this.id);
		}
	}
}
