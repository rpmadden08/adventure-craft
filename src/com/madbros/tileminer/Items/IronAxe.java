package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.*;
import com.madbros.tileminer.Sprites.Sprites;

public class IronAxe extends Axe {
	public IronAxe() {
		id = IRON_AXE;
		name = "Iron Axe";
		sprite = Sprites.sprites.get(Sprites.IRON_AXE);
		swingSprite = sprite;
		attackPower = 5;
		is32 = true;
		isInUse = false;
		sound = "sounds/axeChop.wav";
		craftCost = new int[]{IRON_BAR, STICK};
		craftCostAmount = new int[]{3, 2};
		isRepeatable = true;
		
	}
	
	@Override
	public IronAxe createNew() {
		return new IronAxe();
	}
	public void useLeft() {
		if(Game.level.tileBeingAttacked.isChoppable && isInRange == true) {
			swing();
		}
		
			Game.hero.attack(this);
			//Game.soundController.create(sound);
	}
	
	public void highlightItem(Block block, int x, int y) {
		if(Game.level.tileBeingAttacked.isChoppable && Game.level.activeBlocks[block.getX(Game.level.activeBlocks)][block.getY(Game.level.activeBlocks)].layers[OBJECT_LAYER].id != AIR) {
			Game.level.tileBeingAttacked.highlightEntireObject(block.getX(Game.level.activeBlocks),block.getY(Game.level.activeBlocks), x, y);
		}
	}
}
