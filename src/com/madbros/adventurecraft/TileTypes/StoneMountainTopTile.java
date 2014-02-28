package com.madbros.adventurecraft.TileTypes;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Utils.Margin;

public class StoneMountainTopTile extends CollisionTile {
	public StoneMountainTopTile() {
		super();
		currentSpriteId = 0;
		sprites = Sprites.spriteCollections.get(Sprites.STONE_MOUNTAIN_TOP);
		margin = new Margin(9, 9, 12, 11);
		id = STONE_MOUNTAIN_TOP;
		autoTileID = id;
		layer = ABOVE_LAYER_2;
		z = Z_ABOVE_LAYER;
		isDiggable = false;
		isPickable = true;
	}
	
	public void render(int x, int y, int layer) {
//		float z;
//		Helpers.println(String.valueOf(layer / 100f));	//FIXME!!
//		z = this.z + Math.max(layer / 100f, layer / 100f);
	//sprites[currentSpriteId].draw(x, y, z, TILE_SIZE * Game.pixelModifier, TILE_SIZE * Game.pixelModifier);
	sprites[autoTile].draw(x, y, z);
	}
	
	public Tile createNew() {
		return new StoneMountainTopTile();
	}
}