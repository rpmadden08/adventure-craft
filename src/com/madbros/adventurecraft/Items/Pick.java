package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;


//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Sprites.Sprites;

public class Pick extends ToolItem {
	public Pick() {
		id = PICK;
		is32 = false;
		sprite = Sprites.sprites.get(Sprites.PICK);
		attackPower = 5;
	}
	
	@Override
	public Pick createNew() {
		return new Pick();
	}
	
	public void useLeft() {
		
//		Tile topTile = Game.level.highlightedBlock.getTopTile();
		
		if(Game.level.tileBeingAttacked.isPickable && Game.level.tileBeingAttacked2.isPickable 
				&& Game.level.tileBeingAttacked3.isPickable 
				&& Game.level.tileBeingAttacked4.isPickable &&isInRange == true) {
			swing();

		}
	}
	public void impact() {
		Game.level.tileBeingAttacked.currentHp -= attackPower;
		if(Game.level.tileBeingAttacked.currentHp < 1) {
			Game.level.highlightedBlock.deleteObjectTile();
			Game.level.activeBlocks[Game.level.highlightedBlockX+1][Game.level.highlightedBlockY].deleteObjectTile();
			Game.level.activeBlocks[Game.level.highlightedBlockX][Game.level.highlightedBlockY+1].deleteObjectTile();
			Game.level.activeBlocks[Game.level.highlightedBlockX+1][Game.level.highlightedBlockY+1].deleteObjectTile();
			
			Game.level.highlightedBlock.collisionTile = null;
			Game.level.activeBlocks[Game.level.highlightedBlockX+1][Game.level.highlightedBlockY].collisionTile = null;
			Game.level.activeBlocks[Game.level.highlightedBlockX][Game.level.highlightedBlockY+1].collisionTile = null;
			Game.level.activeBlocks[Game.level.highlightedBlockX+1][Game.level.highlightedBlockY+1].collisionTile = null;
			
			Game.level.activeBlocks[Game.level.highlightedBlockX][Game.level.highlightedBlockY-2].deleteTile(ABOVE_LAYER_1);
			Game.level.activeBlocks[Game.level.highlightedBlockX][Game.level.highlightedBlockY-1].deleteTile(ABOVE_LAYER_1);
			Game.level.activeBlocks[Game.level.highlightedBlockX+1][Game.level.highlightedBlockY-2].deleteTile(ABOVE_LAYER_1);
			Game.level.activeBlocks[Game.level.highlightedBlockX+1][Game.level.highlightedBlockY-1].deleteTile(ABOVE_LAYER_1);
			
			Game.level.autoTileBlock(Game.level.highlightedBlockX, Game.level.highlightedBlockY-2);
			Game.level.autoTileHighlightedBlock();
			calculateUsage();
		}
	}
}
