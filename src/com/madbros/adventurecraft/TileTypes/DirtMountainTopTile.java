package com.madbros.adventurecraft.TileTypes;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Utils.Margin;

public class DirtMountainTopTile extends CollisionTile {
	public DirtMountainTopTile() {
		super();
		currentSpriteId = 0;
		sprites = Sprites.spriteCollections.get(Sprites.DIRT_MOUNTAIN_TOP_NEW);
		margin = new Margin(9, 9, 12, 11);
		id = DIRT_MOUNTAIN_TOP;
		autoTileID = id;
		layer = ABOVE_LAYER_2;
		z = Z_ABOVE_LAYER;
		isDiggable = false;
		isPickable = true;
	}
	
	public void render(int x, int y) {
		int size = TILE_SIZE/2;

		//This one if statement often increases render time by about 300-400 ms.
		if(topLeftAutoTile == MIDDLE_TILE && topRightAutoTile == MIDDLE_TILE &&
		   bottomLeftAutoTile == MIDDLE_TILE && bottomRightAutoTile == MIDDLE_TILE) {
			sprites[MIDDLE_TILE].draw(x, y, z);
		} else {
			//sprites[topLeftAutoTile].draw(x, y, z, size, size);
			sprites[topLeftAutoTile].draw(x, y, z);
			sprites[topRightAutoTile].draw(x+size, y, z);
			sprites[bottomLeftAutoTile].draw(x, y+size, z);
			sprites[bottomRightAutoTile].draw(x+size, y+size, z);
		}
	}
	
	public void update(int x,int y) {
		//if(bottomLeftAutoTile == 0 ||bottomLeftAutoTile == 8 ||bottomLeftAutoTile == 32 )  {
			//Game.level.activeBlocks[x][y].layers[TREE_RIGHT_2].isVisible = false;//96 //104
		//} else {
			//isVisible = true;
		//}
		//System.out.println("yo");
	}
	
	public Tile createNew() {
		return new DirtMountainTopTile();
	}
}
