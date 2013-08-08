package com.madbros.adventurecraft.TileTypes;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Utils.Margin;

public class TreeTile extends CollisionTile {
	public TreeTile() {
		super();
		currentTexture = 0;
		textures = Textures.treeTextures;
		margin = new Margin(3, 3, 0, 12);
		id = TREE;
		layer = WATER_LAYER;
		isDiggable = false;
	}
	
	@Override
	public void render(int x, int y) {
		textures[currentTexture].draw(x, y, TILE_SIZE * Game.pixelModifier, TILE_SIZE * Game.pixelModifier);
	}
	
	public Tile createNew() {
		return new TreeTile();
	}
}
