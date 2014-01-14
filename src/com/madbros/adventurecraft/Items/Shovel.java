package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Sprites.Sprites;

public class Shovel extends ToolItem {
	public Shovel() {
		id = SHOVEL;
		name = "Shovel";
		sprite = Sprites.sprites.get(Sprites.SHOVEL_ITEM);
		attackPower = 5;
		is32 = false;
		isRepeatable = true;
	}
	
	@Override
	public Shovel createNew() {
		return new Shovel();
	}
	
	public void useLeft() {
		if(Game.level.tileBeingAttacked.isDiggable && Game.level.tileBeingAttacked2.isDiggable 
				&& Game.level.tileBeingAttacked3.isDiggable 
				&& Game.level.tileBeingAttacked4.isDiggable &&isInRange == true) {
			swing();

		}
		Game.hero.attack(this);
	}
	public void impact() {
		Game.level.tileBeingAttacked.currentHp -= attackPower;
		if(Game.level.tileBeingAttacked.currentHp < 1) {
			
			Game.level.highlightedBlock.deleteTopTile();
			Game.level.activeBlocks[Game.level.highlightedBlockX+1][Game.level.highlightedBlockY].deleteTopTile();
			Game.level.activeBlocks[Game.level.highlightedBlockX][Game.level.highlightedBlockY+1].deleteTopTile();
			Game.level.activeBlocks[Game.level.highlightedBlockX+1][Game.level.highlightedBlockY+1].deleteTopTile();
			
			Game.level.tileBeingAttacked.deleteMe(Game.level.highlightedBlockX, Game.level.highlightedBlockY, Game.level.activeBlocks);
			
			Game.level.autoTileHighlightedBlock();
			calculateUsage();
		}
	}
}
