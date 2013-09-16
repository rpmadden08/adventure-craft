package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

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
				Game.level.autoTileHighlightedBlock();
			}
		}
	}
}
