package com.madbros.adventurecraft.TileTypes;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.*;

public class TreeTile extends CollisionTile {
	public TreeTile() {
		super();
		currentTexture = 0;
		textures = Textures.treeTextures;
		margin = new Margin(1, 1, 0, 0);
		id = TREE;
		isBaseTile = false;
	}
	
	@Override
	public void render(int x, int y) {
		textures[currentTexture].draw(x, y, TILE_SIZE * Game.pixelModifier, TILE_SIZE * Game.pixelModifier);
	}
	
	public Tile createNew() {
		return new TreeTile();
	}
}
