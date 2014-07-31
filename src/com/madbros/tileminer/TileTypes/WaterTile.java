package com.madbros.tileminer.TileTypes;

import java.util.Random;

import com.madbros.tileminer.Block;
import com.madbros.tileminer.Game;
import com.madbros.tileminer.GameObjects.Actor;
import com.madbros.tileminer.GameObjects.Bee;
import com.madbros.tileminer.GameObjects.Mob;
import com.madbros.tileminer.GameObjects.QueenBee;
import com.madbros.tileminer.Sprites.*;
import com.madbros.tileminer.Utils.Margin;
import com.madbros.tileminer.Utils.Rect;

import static com.madbros.tileminer.Constants.*;

public class WaterTile extends CollisionTile {
	public WaterTile() {
		super();
		currentSpriteId = 0;
		layer = WATER_LAYER;
		z = Z_WATER;
		margin = new Margin(14, 14, 14, 14);
		id = WATER;
		autoTileID = id;
		isDiggable = false;
		//isCollidable = true;
		//sprites = Sprites.waterSprites;
		sprites = Sprites.spriteCollections.get(Sprites.WATER_NEW);
		is32 = false;
	}
	
	public void heroDidCollide(Actor actor, int dir, double move, Rect charCRect, Rect tileRect) {
		if(actor instanceof Mob && !(actor instanceof Bee) && !(actor instanceof QueenBee)) {
			super.heroDidCollide(actor, dir, move, charCRect, tileRect);
		} 
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
	
	public void update(int x, int y) {
		Random rand = new Random();
		Block[] b = new Block[] {Game.level.activeBlocks[x+1][y],Game.level.activeBlocks[x-1][y],Game.level.activeBlocks[x][y+1],Game.level.activeBlocks[x][y-1]};
		for(int a = 0; a<b.length;a++) {
			if(b[a].layers[WATER_LAYER].id == HOLE) {
				if(rand.nextInt(100)== 0) {
					b[a].layers[WATER_LAYER] = new WaterTile();
					b[a].collisionTile = null;
					b[a].setCollisionTile(this);
					//
					Game.level.autoTileBlock(b[a].getX(Game.level.activeBlocks), b[a].getY(Game.level.activeBlocks));
				}
			}
		}
		
		
	}
	public void deleteMe(int x, int y, Block[][] activeBlocks) {
		activeBlocks[x][y].layers[WATER_LAYER] = new NoTile();
	}
	
	public Tile createNew() {
		return new WaterTile();
	}
}
