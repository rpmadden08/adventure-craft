package com.madbros.tileminer.TileTypes;

import static com.madbros.tileminer.Constants.*;

import java.util.Random;

import com.madbros.tileminer.Block;
import com.madbros.tileminer.Game;
import com.madbros.tileminer.Items.Item;
import com.madbros.tileminer.Sprites.*;
import com.madbros.tileminer.Utils.Rect;

public class RoofTile extends Tile {
	public RoofTile() {
		maxHp = 1;
		currentHp = 1;
		layer = ABOVE_LAYER_3;
		z = Z_GRASS;
		isCollidable = false;
		currentSpriteId = 0;
		sprites = Sprites.spriteCollections.get(Sprites.ROOF_TILE);
		id = ROOF_TILE;
		autoTileID = id;
		isTillable = true;
		isBreakable = true;
		is32 = false;
		particleEffect = "grassChunks.p";
		

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
		return new RoofTile();
	}
	
//	public void update(int x, int y) {
//		if(Game.currentLevel == OVERWORLD_FOLDER) {
//			Random rand = new Random();
//			
//			Block[] b = new Block[] {Game.level.activeBlocks[x+1][y],Game.level.activeBlocks[x-1][y],Game.level.activeBlocks[x][y+1],Game.level.activeBlocks[x][y-1]};
//			for(int a = 0; a<b.length;a++) {
//				if(b[a].layers[GRASS_LAYER].id == AIR && b[a].layers[WATER_LAYER].id == AIR && b[a].layers[OBJECT_LAYER].id == AIR && b[a].layers[LIGHT_DIRT_LAYER].id != AIR) {
//						if(rand.nextInt(10000)== 0) { //10000
//							
//							b[a].layers[GRASS_LAYER] = new RoofTile();
//							Game.level.autoTileBlock(b[a].getX(Game.level.activeBlocks), b[a].getY(Game.level.activeBlocks));
//							
//						}
//				}
//			}
//		}
//		
//	}
	
	public void deleteMe(int x, int y, Block[][] activeBlocks) {
		activeBlocks[x][y].layers[ABOVE_LAYER_3] = new WallBorderTile();
		Rect collectibleRect = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
		Item item = ITEM_HASH.get(ROOF).createNew();
		Game.collectibleController.add(ROOF, Sprites.sprites.get(Sprites.ROOF), collectibleRect, 1, item.maxUses);
		
	}
}
