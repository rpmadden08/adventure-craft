package com.madbros.tileminer.TileTypes;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Margin;

public class StoneMountainTopTile extends CollisionTile {
	public StoneMountainTopTile() {
		super();
		currentSpriteId = 0;
		sprites = Sprites.spriteCollections.get(Sprites.GRASS_NEW);
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
