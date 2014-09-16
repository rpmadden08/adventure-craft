package com.madbros.tileminer.TileTypes;

import static com.madbros.tileminer.Constants.*;

import java.util.Random;

import com.madbros.tileminer.*;
import com.madbros.tileminer.Items.Item;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Margin;
import com.madbros.tileminer.Utils.Rect;

public class DirtMountainBottomTile extends CollisionTile {
	public DirtMountainBottomTile() {
		super();
		currentSpriteId = 0;
		sprites = Sprites.spriteCollections.get(Sprites.DIRT_MOUNTAIN_BOTTOM_NEW);
		margin = new Margin(4, 5, 0, 5);
		id = DIRT_MOUNTAIN_BOTTOM;
		autoTileID = id;
		layer = OBJECT_LAYER;
		z = Z_OBJECT;
		isDiggable = false;
		isPickable = true;
		is32 = true;
		maxHp = 20;
		currentHp = 20;
		particleEffect = "mountainChunks.p";
		sound = "sounds/pickSound.wav";
		breakingSound = "sounds/stoneRubble.wav";
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
		activeBlocks[x][y].layers[OBJECT_LAYER].setCollisionRect(activeBlocks[x][y].absRect);
		activeBlocks[x][y-1].layers[ABOVE_LAYER_1] = new NoTile();
		activeBlocks[x][y-2].layers[ABOVE_LAYER_2] = new NoTile();
		activeBlocks[x][y].collisionTile = null;
		
		Random rnd = new Random();
		int dropAmount1 = rnd.nextInt(2)-1;
			for(int i = dropAmount1; i >-1 ; i--) {
				Rect collectibleRect = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
				Item item = ITEM_HASH.get(DIRT_MOUNTAIN_CLUMP).createNew();
				Game.collectibleController.add(DIRT_MOUNTAIN_CLUMP, Sprites.sprites.get(Sprites.DIRT_MOUNTAIN_CLUMP), collectibleRect, 1, item.maxUses);
			}

			Rect collectibleRect = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
			Item item = ITEM_HASH.get(STONE).createNew();
			Game.collectibleController.add(STONE, Sprites.sprites.get(Sprites.STONE), collectibleRect, 1, item.maxUses);
	
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
//		b = Game.level.activeBlocks[x][y-1];
//		b.layers[ABOVE_LAYER_1].sprites[b.layers[ABOVE_LAYER_1].currentSpriteId].setColor(HIGHLIGHT_COLOR);
//		b.layers[ABOVE_LAYER_1].sprites[b.layers[ABOVE_LAYER_1].currentSpriteId].draw(drawX, drawY, z, TILE_SIZE * Game.pixelModifier, TILE_SIZE * Game.pixelModifier);
//		b.layers[ABOVE_LAYER_1].sprites[b.layers[ABOVE_LAYER_1].currentSpriteId].setColor(1f,1f,1f,1f);
//		
//		b = Game.level.activeBlocks[x][y-2];
//		b.layers[ABOVE_LAYER_2].sprites[b.layers[ABOVE_LAYER_2].currentSpriteId].setColor(HIGHLIGHT_COLOR);
//		b.layers[ABOVE_LAYER_2].sprites[b.layers[ABOVE_LAYER_2].currentSpriteId].draw(drawX, drawY, z, TILE_SIZE * Game.pixelModifier, TILE_SIZE * Game.pixelModifier);
//		b.layers[ABOVE_LAYER_2].sprites[b.layers[ABOVE_LAYER_2].currentSpriteId].setColor(1f,1f,1f,1f);
//	
	}
}
