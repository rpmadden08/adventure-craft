package com.madbros.tileminer.TileTypes;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Game;
import com.madbros.tileminer.Items.RoofBase;
import com.madbros.tileminer.Sprites.*;

public class RoofBaseTile extends Tile {
	public RoofBaseTile() {
		maxHp = 1;
		currentHp = 1;
		layer = ABOVE_LAYER_4;
		z = Z_GRASS;
		isCollidable = false;
		currentSpriteId = 0;
		sprites = Sprites.spriteCollections.get(Sprites.ROOF_TILE);
		id = ROOF_TILE;
		autoTileID = id;
		isBreakable = true;
		is32 = false;
		particleEffect = "grassChunks.p";
		

	}
	
	

	public void render(int x, int y) {
		int size = TILE_SIZE/2;
			if(isTransparent) {
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
		isVisible = true;
	}
	
	public Tile createNew() {
		return new RoofBaseTile();
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
	public void update(int x, int y) {
		if(Game.level.currentCollisionHousing == this.housingNumber) {
			//TODO check if item currently selected is a roof item.  If it is, then make it transparent.  Otherwise invisible
			if(RoofBase.class.isAssignableFrom(Game.inventory.invBar[Game.inventory.itemSelected].item.getClass())) {
				Game.level.activeBlocks[x][y].layers[ABOVE_LAYER_4].isTransparent = true;
				Game.level.activeBlocks[x][y].layers[ABOVE_LAYER_4].isVisible = true;
			} else {
				Game.level.activeBlocks[x][y].layers[ABOVE_LAYER_4].isTransparent = true;
				Game.level.activeBlocks[x][y].layers[ABOVE_LAYER_4].isVisible = false;
			}
		} 
	}
}
