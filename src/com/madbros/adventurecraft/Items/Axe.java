package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Sprites.Sprites;

public class Axe extends ToolItem {
	public Axe() {
		id = AXE;
		sprite = Sprites.sprites.get(Sprites.AXE_ITEM);
		attackPower = 1;
		is32 = true;
	}
	
	@Override
	public Axe createNew() {
		return new Axe();
	}
	
	public void useLeft() {
		
//		Tile topTile = Game.level.highlightedBlock.getTopTile();
		if(Game.level.tileBeingAttacked.isChoppable) {
			//take hp from top tile in highlightedblock
			
			Game.level.tileBeingAttacked.currentHp -= attackPower;
			if(Game.level.tileBeingAttacked.currentHp < 1) {
				Game.level.highlightedBlock.deleteObjectTile();
				Game.level.tileBeingAttacked.deleteMe(Game.level.highlightedBlockX, Game.level.highlightedBlockY, Game.level.activeBlocks);
				Game.level.autoTileHighlightedBlock();
			}
		}
	}
}
