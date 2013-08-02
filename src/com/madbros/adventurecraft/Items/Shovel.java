package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Block;
import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Textures;
import com.madbros.adventurecraft.TileTypes.Tile;

public class Shovel extends ToolItem {
	public Shovel() {
		id = SHOVEL;
		texture = Textures.shovelTexture;
	}
	
	@Override
	public Shovel createNew() {
		return new Shovel();
	}
	
	public void useLeft() {
		//check if highlightedblock.topTile is diggable
		//take hp from highlightedblock
		//check if hp is out
		//if hp is out, destroy tile
		
//		Tile tile = TILE_HASH.get(tileID).createNew();
//		Block hB = Game.level.highlightedBlock;
//		if(tile.id != hB.layers[GRASS_LAYER].id && (hB.layers[DARK_DIRT_LAYER].id == DIRT || hB.layers[LIGHT_DIRT_LAYER].id == DIRT) && 
//			(!hB.layers[WATER_LAYER].isMiddleTile || hB.layers[WATER_LAYER].id == AIR)) {
//			hB.layers[GRASS_LAYER] = tile;
//			stackSize -= 1;
//			Game.inventory.deleteItemIfNecessary();
//			Game.level.autoTileHighlightedBlock();
//		}
	}
}
