package com.madbros.adventurecraft.TileTypes;

import static com.madbros.adventurecraft.Constants.*;

import java.util.Random;

import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Items.Item;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Utils.Margin;
import com.madbros.adventurecraft.Utils.Rect;

public class DirtMountainBottomTile extends CollisionTile {
	public DirtMountainBottomTile() {
		super();
		currentSpriteId = 0;
		sprites = Sprites.spriteCollections.get(Sprites.DIRT_MOUNTAIN_BOTTOM_NEW);
		margin = new Margin(3, 3, 0, 12);
		id = DIRT_MOUNTAIN_BOTTOM;
		autoTileID = id;
		layer = OBJECT_LAYER;
		z = Z_OBJECT;
		isDiggable = false;
		isPickable = true;
		is32 = true;
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
		return new DirtMountainBottomTile();
	}
	
	@Override
	public void bloom(int x, int y, Block[][] activeBlocks) {
		//Block b = activeBlocks[x][y-1];
		//b = activeBlocks[xs[i]][ys[i]];
//		b.layers[ABOVE_LAYER_1] = new DirtMountainTopTile(); 
//		b.layers[ABOVE_LAYER_1].currentSpriteId = 0;
//		b.layers[ABOVE_LAYER_1].z = Z_ABOVE_LAYER;
		
		
	}
	
	public void deleteMe(int x, int y, Block[][] activeBlocks) {
		activeBlocks[x][y].layers[OBJECT_LAYER] = new NoTile();
		activeBlocks[x][y-1].layers[ABOVE_LAYER_1] = new NoTile();
		activeBlocks[x][y-2].layers[ABOVE_LAYER_2] = new NoTile();
		activeBlocks[x][y].collisionTile = null;
		
		Random rnd = new Random();
		int dropAmount1 = rnd.nextInt(2)+2;
			for(int i = dropAmount1; i >-1 ; i--) {
				Rect collectibleRect = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
				Item item = ITEM_HASH.get(DIRT_MOUNTAIN_CLUMP).createNew();
				Game.collectibleController.add(DIRT_MOUNTAIN_CLUMP, Sprites.sprites.get(Sprites.DIRT_MOUNTAIN_CLUMP), collectibleRect, 1, item.maxUses);
			}

			Rect collectibleRect = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
			Item item = ITEM_HASH.get(STONE).createNew();
			Game.collectibleController.add(STONE, Sprites.sprites.get(Sprites.STONE), collectibleRect, 1, item.maxUses);
	
	}
}
