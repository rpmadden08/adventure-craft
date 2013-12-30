package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;
import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Sprites.Sprites;

public class Axe extends ToolItem {
	public Axe() {
		id = AXE;
		sprite = Sprites.sprites.get(Sprites.AXE_ITEM);
		attackPower = 5;
		is32 = true;
		isInUse = false;
		sound = "sounds/axeChop.wav";
		
		
	}
	
	@Override
	public Axe createNew() {
		return new Axe();
	}
	public void impact() {
		//Game.p.allowCompletion();
		Game.level.tileBeingAttacked.currentHp -= attackPower;
		if(Game.level.tileBeingAttacked.currentHp < 1) {
			Game.level.highlightedBlock.deleteObjectTile();
			Game.level.tileBeingAttacked.deleteMe(Game.level.highlightedBlockX, Game.level.highlightedBlockY, Game.level.activeBlocks);
			//Game.level.autoTileHighlightedBlock();
			calculateUsage();
		}
		Game.soundController.create(sound);
		
		Game.p.x= Game.level.highlightedBlock.absRect.x +(TILE_SIZE/2);
		Game.p.y = Game.level.highlightedBlock.absRect.y + (TILE_SIZE/2);
		Game.p.start();
	}
	public void useLeft() {
		if(Game.level.tileBeingAttacked.isChoppable && isInRange == true) {
			swing();
		}
	}
}
