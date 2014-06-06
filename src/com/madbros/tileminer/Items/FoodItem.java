package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Game;

public abstract class FoodItem extends StackableItem{
	int tileId = POTATOES;
	double energyPercentage = 0.1;
	
	@Override
	public abstract FoodItem createNew();
	
	@Override
	public void useRight() {
		
		if(Game.level.tileBeingAttacked.isUseable) {
			checkUsability();
		} else {
			if(Game.hero.eP != Game.hero.maxEP && Game.level.hasPlacedItemOnClick == false) {
				Game.level.hasPlacedItemOnClick = true;
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
}
