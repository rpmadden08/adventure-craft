package com.madbros.adventurecraft.TileTypes;

import java.util.Random;

import com.madbros.adventurecraft.Block;
import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.GameObjects.Actor;
import com.madbros.adventurecraft.Sprites.*;
import com.madbros.adventurecraft.Utils.Margin;
import com.madbros.adventurecraft.Utils.Rect;

import static com.madbros.adventurecraft.Constants.*;

public class WaterTile extends CollisionTile {
	public WaterTile() {
		super();
		currentSpriteId = 0;
		layer = WATER_LAYER;
		z = Z_WATER;
		margin = new Margin(1, 0, 0, 1);
		id = WATER;
		autoTileID = id;
		isDiggable = false;
		//sprites = Sprites.waterSprites;
		sprites = Sprites.spriteCollections.get(Sprites.WATER_NEW);
		is32 = false;
	}
	
	public void heroDidCollide(Actor actor, int dir, int move, Rect charCRect, Rect tileRect) {
		if(actor != Game.hero) {
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
		//System.out.println("YESSS");
		Random rand = new Random();
		Block[] b = new Block[] {Game.level.activeBlocks[x+1][y],Game.level.activeBlocks[x-1][y],Game.level.activeBlocks[x][y+1],Game.level.activeBlocks[x][y-1]};
		for(int a = 0; a<b.length;a++) {
			if(b[a].layers[WATER_LAYER].id == HOLE) {
				if(rand.nextInt(100)== 0) {
					b[a].layers[WATER_LAYER] = new WaterTile();
					Game.level.autoTileBlock(b[a].getX(Game.level.activeBlocks), b[a].getY(Game.level.activeBlocks));
				}
			}
		}
		
		
	}
	
	
	public Tile createNew() {
		return new WaterTile();
	}
}
