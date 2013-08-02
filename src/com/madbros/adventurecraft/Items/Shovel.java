package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Textures;
import com.madbros.adventurecraft.TileTypes.*;

public class Shovel extends ToolItem {
	public Shovel() {
		id = SHOVEL;
		texture = Textures.shovelTexture;
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
