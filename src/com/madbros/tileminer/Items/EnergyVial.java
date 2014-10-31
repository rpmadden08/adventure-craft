package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Game;
import com.madbros.tileminer.Items.Food.FoodItem;
import com.madbros.tileminer.Sprites.Sprites;

public class EnergyVial extends FoodItem {
	public EnergyVial() {
		id = ENERGY_VIAL;
		name = "Vial of Energy";
		sprite = Sprites.sprites.get(Sprites.EP_ITEM);
//		itemsPossiblyBrewable = new int[]{SLOWNESS_POTION};
//		itemsPossiblyCraftable = new int[]{};
//		energyPercentage = 0.3;
		
	}
	@Override
	public FoodItem createNew() {
		return new EnergyVial();
	}
	
	public void useRight() {
		if(Game.level.tileBeingAttacked.isUseable) {
			checkUsability();
		} else {
			if(Game.level.hasPlacedItemOnClick == false) {
				Game.level.hasPlacedItemOnClick = true;
				if(Game.hero.maxEP < 51) { 
					Game.hero.maxEP = Game.hero.maxEP +5;
					Game.hero.eP = Game.hero.eP +5;
					stackSize -= 1;
					Game.inventory.deleteItemIfNecessary();
				} else {
					//TODO add notification..
				}
			}
		}
	}
}
