package com.madbros.tileminer.TileTypes;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.*;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Margin;

public class WallBottomTile extends CollisionTile {
	public WallBottomTile() {
		super();
		currentSpriteId = 0;
		sprites = Sprites.spriteCollections.get(Sprites.WOOD_WALL_BOTTOM);
		margin = new Margin(4, 5, 0, 5);
		id = WOOD_WALL_BOTTOM_TILE;
		autoTileID = id;
		layer = OBJECT_LAYER;
		z = Z_OBJECT;
		isDiggable = false;
		isChoppable = true;
		is32 = true;
		maxHp = 15;
		currentHp = 15;
		particleEffect = "mountainChunks.p";
		sound = "sounds/axeChop.wav";
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
	
	public Tile createNew() {
		return new WallBottomTile();
	}

	public void deleteMe(int x, int y, Block[][] activeBlocks) {
		activeBlocks[x][y].layers[OBJECT_LAYER] = new NoTile();
		activeBlocks[x][y-1].layers[ABOVE_LAYER_1] = new NoTile();
		activeBlocks[x][y-2].layers[ABOVE_LAYER_2] = new NoTile();
		activeBlocks[x][y-3].layers[ABOVE_LAYER_3] = new NoTile();
		activeBlocks[x][y-3].layers[ABOVE_LAYER_4].deleteMe(x, y-3, activeBlocks);
		activeBlocks[x][y].collisionTile = null;
		Game.level.autoTileBlock(x, y);
		Game.level.autoTileBlock(x, y-1);
		Game.level.autoTileBlock(x, y-2);
		Game.level.autoTileBlock(x, y-3);
			
	}
	
	public void highlightEntireObject(int x, int y, int drawX, int drawY) {
		Block b = Game.level.activeBlocks[x][y];
		b.layers[OBJECT_LAYER].sprites[b.layers[OBJECT_LAYER].topLeftAutoTile].setColor(HIGHLIGHT_COLOR);
		b.layers[OBJECT_LAYER].sprites[b.layers[OBJECT_LAYER].topLeftAutoTile].draw(drawX, drawY, z);
		b.layers[OBJECT_LAYER].sprites[b.layers[OBJECT_LAYER].topLeftAutoTile].setColor(1f,1f,1f,1f);
		
		b.layers[OBJECT_LAYER].sprites[b.layers[OBJECT_LAYER].topRightAutoTile].setColor(HIGHLIGHT_COLOR);
		b.layers[OBJECT_LAYER].sprites[b.layers[OBJECT_LAYER].topRightAutoTile].draw(drawX+(TILE_SIZE/2), drawY, z);
		b.layers[OBJECT_LAYER].sprites[b.layers[OBJECT_LAYER].topRightAutoTile].setColor(1f,1f,1f,1f);
		
		b.layers[OBJECT_LAYER].sprites[b.layers[OBJECT_LAYER].bottomLeftAutoTile].setColor(HIGHLIGHT_COLOR);
		b.layers[OBJECT_LAYER].sprites[b.layers[OBJECT_LAYER].bottomLeftAutoTile].draw(drawX, drawY+(TILE_SIZE/2), z);
		b.layers[OBJECT_LAYER].sprites[b.layers[OBJECT_LAYER].bottomLeftAutoTile].setColor(1f,1f,1f,1f);
		
		b.layers[OBJECT_LAYER].sprites[b.layers[OBJECT_LAYER].bottomRightAutoTile].setColor(HIGHLIGHT_COLOR);
		b.layers[OBJECT_LAYER].sprites[b.layers[OBJECT_LAYER].bottomRightAutoTile].draw(drawX+(TILE_SIZE/2), drawY+(TILE_SIZE/2), z);
		b.layers[OBJECT_LAYER].sprites[b.layers[OBJECT_LAYER].bottomRightAutoTile].setColor(1f,1f,1f,1f);
		
		b = Game.level.activeBlocks[x][y-1];
		drawY = drawY -TILE_SIZE;
		b.layers[ABOVE_LAYER_1].sprites[b.layers[ABOVE_LAYER_1].topLeftAutoTile].setColor(HIGHLIGHT_COLOR);
		b.layers[ABOVE_LAYER_1].sprites[b.layers[ABOVE_LAYER_1].topLeftAutoTile].draw(drawX, drawY, z, TILE_SIZE/2, TILE_SIZE/2);
		b.layers[ABOVE_LAYER_1].sprites[b.layers[ABOVE_LAYER_1].topLeftAutoTile].setColor(1f,1f,1f,1f);
		
		b.layers[ABOVE_LAYER_1].sprites[b.layers[ABOVE_LAYER_1].topRightAutoTile].setColor(HIGHLIGHT_COLOR);
		b.layers[ABOVE_LAYER_1].sprites[b.layers[ABOVE_LAYER_1].topRightAutoTile].draw(drawX+(TILE_SIZE/2), drawY, z);
		b.layers[ABOVE_LAYER_1].sprites[b.layers[ABOVE_LAYER_1].topRightAutoTile].setColor(1f,1f,1f,1f);
		
		b.layers[ABOVE_LAYER_1].sprites[b.layers[ABOVE_LAYER_1].bottomLeftAutoTile].setColor(HIGHLIGHT_COLOR);
		b.layers[ABOVE_LAYER_1].sprites[b.layers[ABOVE_LAYER_1].bottomLeftAutoTile].draw(drawX, drawY+(TILE_SIZE/2), z);
		b.layers[ABOVE_LAYER_1].sprites[b.layers[ABOVE_LAYER_1].bottomLeftAutoTile].setColor(1f,1f,1f,1f);
		
		b.layers[ABOVE_LAYER_1].sprites[b.layers[ABOVE_LAYER_1].bottomRightAutoTile].setColor(HIGHLIGHT_COLOR);
		b.layers[ABOVE_LAYER_1].sprites[b.layers[ABOVE_LAYER_1].bottomRightAutoTile].draw(drawX+(TILE_SIZE/2), drawY+(TILE_SIZE/2), z);
		b.layers[ABOVE_LAYER_1].sprites[b.layers[ABOVE_LAYER_1].bottomRightAutoTile].setColor(1f,1f,1f,1f);
		
		b = Game.level.activeBlocks[x][y-2];
		drawY = drawY -TILE_SIZE;
		b.layers[ABOVE_LAYER_2].sprites[b.layers[ABOVE_LAYER_2].topLeftAutoTile].setColor(HIGHLIGHT_COLOR);
		b.layers[ABOVE_LAYER_2].sprites[b.layers[ABOVE_LAYER_2].topLeftAutoTile].draw(drawX, drawY, z);
		b.layers[ABOVE_LAYER_2].sprites[b.layers[ABOVE_LAYER_2].topLeftAutoTile].setColor(1f,1f,1f,1f);
		
		b.layers[ABOVE_LAYER_2].sprites[b.layers[ABOVE_LAYER_2].topRightAutoTile].setColor(HIGHLIGHT_COLOR);
		b.layers[ABOVE_LAYER_2].sprites[b.layers[ABOVE_LAYER_2].topRightAutoTile].draw(drawX+(TILE_SIZE/2), drawY, z);
		b.layers[ABOVE_LAYER_2].sprites[b.layers[ABOVE_LAYER_2].topRightAutoTile].setColor(1f,1f,1f,1f);
		
		b.layers[ABOVE_LAYER_2].sprites[b.layers[ABOVE_LAYER_2].bottomLeftAutoTile].setColor(HIGHLIGHT_COLOR);
		b.layers[ABOVE_LAYER_2].sprites[b.layers[ABOVE_LAYER_2].bottomLeftAutoTile].draw(drawX, drawY+(TILE_SIZE/2), z);
		b.layers[ABOVE_LAYER_2].sprites[b.layers[ABOVE_LAYER_2].bottomLeftAutoTile].setColor(1f,1f,1f,1f);
		
		b.layers[ABOVE_LAYER_2].sprites[b.layers[ABOVE_LAYER_2].bottomRightAutoTile].setColor(HIGHLIGHT_COLOR);
		b.layers[ABOVE_LAYER_2].sprites[b.layers[ABOVE_LAYER_2].bottomRightAutoTile].draw(drawX+(TILE_SIZE/2), drawY+(TILE_SIZE/2), z);
		b.layers[ABOVE_LAYER_2].sprites[b.layers[ABOVE_LAYER_2].bottomRightAutoTile].setColor(1f,1f,1f,1f);
	}
}
