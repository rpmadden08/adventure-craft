package com.madbros.tileminer.TileTypes;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Margin;

public class WallBorderTile extends CollisionTile {
	public WallBorderTile() {
		super();
		currentSpriteId = 0;
		sprites = Sprites.spriteCollections.get(Sprites.WALL_BORDER);
		margin = new Margin(9, 9, 12, 11);
		id = WALL_BORDER;
		autoTileID = id;
		layer = ABOVE_LAYER_3;
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
		} else if(isTransparent) {
			sprites[topLeftAutoTile].setColor(1,1,1,0.3f);
			sprites[topRightAutoTile].setColor(1,1,1,0.3f);
			sprites[bottomLeftAutoTile].setColor(1,1,1,0.3f);
			sprites[bottomRightAutoTile].setColor(1,1,1,0.3f);
			sprites[topLeftAutoTile].draw(x, y, z);
			sprites[topRightAutoTile].draw(x+size, y, z);
			sprites[bottomLeftAutoTile].draw(x, y+size, z);
			sprites[bottomRightAutoTile].draw(x+size, y+size, z);
		} else {
			sprites[topLeftAutoTile].setColor(1,1,1,1f);
			sprites[topRightAutoTile].setColor(1,1,1,1f);
			sprites[bottomLeftAutoTile].setColor(1,1,1,1f);
			sprites[bottomRightAutoTile].setColor(1,1,1,1f);
			sprites[topLeftAutoTile].draw(x, y, z);
			sprites[topRightAutoTile].draw(x+size, y, z);
			sprites[bottomLeftAutoTile].draw(x, y+size, z);
			sprites[bottomRightAutoTile].draw(x+size, y+size, z);
		}
	}
	
	public void update(int x,int y) {
	}
	
	public Tile createNew() {
		return new WallBorderTile();
	}
}
