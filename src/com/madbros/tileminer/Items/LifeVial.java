package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Game;
import com.madbros.tileminer.Sprites.Sprites;

public class LifeVial extends FoodItem {
	public LifeVial() {
		id = LIFE_VIAL;
		name = "Vial of Life";
		sprite = Sprites.sprites.get(Sprites.HP_ITEM);
		craftCost = new int[]{HEART_CONTAINER};
		craftCostAmount = new int[]{1};
		workSpaceNeeded = new int[]{BARE_HANDS_WORKSPACE,TABLE_WORKSPACE};
//		itemsPossiblyBrewable = new int[]{SLOWNESS_POTION};
//		itemsPossiblyCraftable = new int[]{};
//		energyPercentage = 0.3;
		
	}
	@Override
	public FoodItem createNew() {
		return new LifeVial();
	}
	
	public void useRight() {
		if(Game.level.tileBeingAttacked.isUseable) {
			checkUsability();
		} else {
			if(Game.level.hasPlacedItemOnClick == false) {
				Game.level.hasPlacedItemOnClick = true;
				if(Game.hero.maxHP < 51) { 
					Game.hero.maxHP = Game.hero.maxHP +5;
					Game.hero.hP = Game.hero.hP +5;
					stackSize -= 1;
					Game.inventory.deleteItemIfNecessary();
				} else {
					//TODO add notification..
				}
			}
		}
	}
}
