package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;


import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Sprites.Sprites;

public class Axe extends ToolItem {
	public Axe() {
		id = AXE;
		sprite = Sprites.sprites.get(Sprites.AXE_ITEM);
		attackPower = 20;
		is32 = true;
		isInUse = false;
		sound = "sounds/axeChop.wav";
		
		
	}
	
	@Override
	public Axe createNew() {
		return new Axe();
	}
	
	public void useLeft() {
		
//		Tile topTile = Game.level.highlightedBlock.getTopTile();
		if(Game.level.tileBeingAttacked.isChoppable && isInRange == true) {
			if(isInUse == false) {
				swingRemaining = 0;
				isInUse = true;
				
					
				
			} else {
				swingRemaining = swingRemaining -1;
				if (swingRemaining <= 0) {
					swingRemaining = swingSpeed;
					Game.level.tileBeingAttacked.currentHp -= attackPower;
					if(Game.level.tileBeingAttacked.currentHp < 1) {
						Game.level.highlightedBlock.deleteObjectTile();
						Game.level.tileBeingAttacked.deleteMe(Game.level.highlightedBlockX, Game.level.highlightedBlockY, Game.level.activeBlocks);
						//Game.level.autoTileHighlightedBlock();
						isInUse = false;
					}
					Game.soundController.create(sound);
					//System.out.println(Game.level.tileBeingAttacked.currentHp);
					
					int x = Game.level.highlightedBlock.absRect.x +(TILE_SIZE/2);
					int y = Game.level.highlightedBlock.absRect.y + (TILE_SIZE/2);
					
					Game.p.setPosition((float)x,(float)y);
					//Game.p.update(Gdx.graphics.getRawDeltaTime());
					Game.p.start();
				}
			}
		}
	}
}
