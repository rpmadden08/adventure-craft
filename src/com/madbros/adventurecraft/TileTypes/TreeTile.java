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
		z = Z_WATER;
		isDiggable = false;
	}
	
	@Override
	public void render(int x, int y) {
		float z;
		if(y > Game.hero.sRect.y) z = this.z + 0.3f;	//FIXME: this is a random float increase and not an ideal conditional
		else z = this.z;
		textures[currentTexture].draw(x, y, z, TILE_SIZE * Game.pixelModifier, TILE_SIZE * Game.pixelModifier);
	}
	
	public Tile createNew() {
		return new TreeTile();
	}
}
