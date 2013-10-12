package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Sprites.Sprites;

public class Shovel extends ToolItem {
	public Shovel() {
		id = SHOVEL;
		sprite = Sprites.sprites.get(Sprites.SHOVEL_ITEM);
		attackPower = 1;
	}
	
	@Override
	public Shovel createNew() {
		return new Shovel();
	}
	
	public void useLeft() {
		
//		Tile topTile = Game.level.highlightedBlock.getTopTile();
		
		if(Game.level.tileBeingAttacked.isDiggable) {
			//take hp from top tile in highlightedblock
			Game.level.tileBeingAttacked.currentHp -= attackPower;
			if(Game.level.tileBeingAttacked.currentHp < 1) {
				Game.level.highlightedBlock.deleteTopTile();
				Game.level.activeBlocks[Game.level.highlightedBlockX+1][Game.level.highlightedBlockY].deleteTopTile();
				Game.level.activeBlocks[Game.level.highlightedBlockX][Game.level.highlightedBlockY+1].deleteTopTile();
				Game.level.activeBlocks[Game.level.highlightedBlockX+1][Game.level.highlightedBlockY+1].deleteTopTile();
				Game.level.autoTileHighlightedBlock();
			}
		}
	}
}
