package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Game;

public abstract class FoodItem extends StackableItem{
	int tileId = POTATOES;
	double energyPercentage = 0.1;
	
	@Override
	public abstract FoodItem createNew();
	
	@Override
	public void useRight() {
		if(Game.hero.eP != Game.hero.maxEP) {
			double amountToGain = energyPercentage * Game.hero.maxEP;
			if(amountToGain + Game.hero.eP > Game.hero.maxEP) {
				Game.hero.eP = Game.hero.maxEP;
			} else {
				Game.hero.eP = Game.hero.eP + amountToGain;
			}
			stackSize -= 1;
			Game.inventory.deleteItemIfNecessary();
		}
	}
}
