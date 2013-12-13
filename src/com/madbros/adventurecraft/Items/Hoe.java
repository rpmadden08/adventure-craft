package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;


//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Sprites.Sprites;

public class Hoe extends ToolItem {
	public Hoe() {
		id = HOE;
		is32 = false;
		sprite = Sprites.sprites.get(Sprites.HOE);
		attackPower = 1;
	}
	
	@Override
	public Hoe createNew() {
		return new Hoe();
	}
	
	public void useLeft() {
		
//		Tile topTile = Game.level.highlightedBlock.getTopTile();
		
		if(Game.level.tileBeingAttacked.isTillable && Game.level.tileBeingAttacked2.isTillable 
				&& Game.level.tileBeingAttacked3.isTillable 
				&& Game.level.tileBeingAttacked4.isTillable &&isInRange == true) {
			//take hp from top tile in highlightedblock
			Game.level.tileBeingAttacked.currentHp -= attackPower;
			if(Game.level.tileBeingAttacked.currentHp < 1) {
				Game.level.highlightedBlock.deleteTopTileTilled();
				Game.level.activeBlocks[Game.level.highlightedBlockX+1][Game.level.highlightedBlockY].deleteTopTileTilled();
				Game.level.activeBlocks[Game.level.highlightedBlockX][Game.level.highlightedBlockY+1].deleteTopTileTilled();
				Game.level.activeBlocks[Game.level.highlightedBlockX+1][Game.level.highlightedBlockY+1].deleteTopTileTilled();
				Game.level.autoTileHighlightedBlock();
			}
		}
	}
}
