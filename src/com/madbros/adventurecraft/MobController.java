package com.madbros.adventurecraft;

import java.util.ArrayList;

import static com.madbros.adventurecraft.Constants.*;

import java.util.Random;

import com.madbros.adventurecraft.GameObjects.*;
import com.madbros.adventurecraft.TileTypes.Tile;
import com.madbros.adventurecraft.Utils.Rect;

public class MobController {
	public ArrayList<Mob> mobs = new ArrayList<Mob>();
	
	
	public Block[] getTileArea(int x, int y) {
		Block[] finalBlockArray = new Block[24];
		//x= x-2;
		//y = y -2;
		
//		for(int a = 0; a < finalBlockArray.length; a++) {
		int a = 0;
			for(int i=x-2;i<x+3;i++) {
				for(int j=y-2;j<y+3;j++) {
					if(i == x && j == y) {
						
					} else {
					finalBlockArray[a] = Game.level.activeBlocks[i][j];
					a++;
					}
				}
			}
		return finalBlockArray;
	}
	
	public Boolean checkTileArea(Block[] tileArea, int typeLayer, int typeID, int totalNeeded) {
		int tileCount = 0;
		for(int a = 0; a< tileArea.length; a++) {
			if(tileArea[a].layers[typeLayer].id == typeID) {
				tileCount = tileCount +1;
			}
		}
		if(tileCount >= totalNeeded) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public boolean isDark(Rect possibleSpawnPoint) {
		if(Game.level.isDay == true) {
			return false;
		} else {
			for(int a = 0; a < Game.renderSystem.lightTiles.size(); a++) {
				Rect tempRect = Game.renderSystem.lightTiles.get(a).absRect;
				Rect lightTileRect = new Rect(tempRect.x-250, tempRect.y-250, tempRect.w+500, tempRect.h+500);
				if(lightTileRect.detectCollision(possibleSpawnPoint)) {
					return false;
				}
				
			}
			return true;
			
		}
	}
	
	
	public void update() {
		
		Random rand = new Random();
		int x = rand.nextInt(CHUNK_SIZE * 3)+ CHUNK_SIZE;
		int y = rand.nextInt(CHUNK_SIZE * 3)+ CHUNK_SIZE;
		
		
		
		
		int absX = x* TILE_SIZE + Game.level.activeBlocks[0][0].absRect.x;
		int absY = y* TILE_SIZE + Game.level.activeBlocks[0][0].absRect.y;
		Rect possibleSpawnPoint = new Rect(absX, absY, 1,1);
		Rect heroRect = new Rect(Game.hero.absRect.x-250, Game.hero.absRect.y-250, Game.hero.absRect.w +500, Game.hero.absRect.h+500);
		//if(x > CHUNK_SIZE*2 && x< CHUNK_SIZE *3+1 && y > CHUNK_SIZE*2 && y< CHUNK_SIZE *3+1 ) {
		int topTile = Game.level.activeBlocks[x][y].getTopTile().id;
		//System.out.println(topTile);
		Block[] tileArea = getTileArea(x,y);
		int num = rand.nextInt(1);//100  //10 is quick
		if(num == 0 && mobs.size() < 10) { //10
			if(possibleSpawnPoint.detectCollision(heroRect)) {
			//    ***************IF THE WORLD IS DARK	
			} else if(isDark(possibleSpawnPoint)){
				if(Game.currentLevel == OVERWORLD_FOLDER) {
					if(topTile == GRASS) {
						if(checkTileArea(tileArea, OBJECT_LAYER, TREE, 2)) {
							mobs.add(new Slime(this, x, y));
						} else {
							int num2 = rand.nextInt(2);//100
							if(num2 == 0) {
								mobs.add(new BeeEvening(this, x, y));
							}
						}
					}
				} else if(Game.currentLevel == UNDERGROUND_1_FOLDER) {
					if(topTile== DIRT) {
						if(checkTileArea(tileArea, OBJECT_LAYER, DIRT_MOUNTAIN_BOTTOM, 2)) {
							mobs.add(new Bat(this, x, y));
						} else {
							mobs.add(new Worm(this, x, y));
						}
					} 
				}
			} else { 
				// **************** IF THE WORLD IS LIGHT
				if(Game.currentLevel == OVERWORLD_FOLDER) {
					if(topTile == GRASS) {
							int num2 = rand.nextInt(6);
							if(num2 == 0) {
								mobs.add(new Worm(this, x, y));
							} else if(canLargeMobSpawn(x,y)) {
								mobs.add(new Worm(this, x, y));  //Should be cow
							}
					}
				}
			}
		}
		
		//for every mob in this list do...
		for(int i = 0; i < mobs.size(); i++) {
			mobs.get(i).updateAI();
			mobs.get(i).update();
			//DO NOT remove the mob before checkCollisions...  Causes a crash
			mobs.get(i).checkCollisions();
		}
	}
	
	public boolean canLargeMobSpawn(int x, int y) {
		Tile[] t = new Tile[9];
		t[0] = Game.level.activeBlocks[x][y].layers[OBJECT_LAYER];
		t[1] = Game.level.activeBlocks[x+1][y].layers[OBJECT_LAYER];
		t[2] = Game.level.activeBlocks[x][y+1].layers[OBJECT_LAYER];
		t[3] = Game.level.activeBlocks[x+1][y+1].layers[OBJECT_LAYER];
		t[4] = Game.level.activeBlocks[x+2][y].layers[OBJECT_LAYER];
		t[5] = Game.level.activeBlocks[x+2][y+1].layers[OBJECT_LAYER];
		t[6] = Game.level.activeBlocks[x+2][y+2].layers[OBJECT_LAYER];
		t[7] = Game.level.activeBlocks[x+1][y+2].layers[OBJECT_LAYER];
		t[8] = Game.level.activeBlocks[x][y+2].layers[OBJECT_LAYER];
		
		for(int i = 0; i< t.length; i++) {
			if(t[i].id != AIR && t[i].id != TALL_GRASS_A && t[i].id != RED_FLOWERS) {
				return false; 
			}
		}
		
		t[0] = Game.level.activeBlocks[x][y].layers[WATER_LAYER];
		t[1] = Game.level.activeBlocks[x+1][y].layers[WATER_LAYER];
		t[2] = Game.level.activeBlocks[x][y+1].layers[WATER_LAYER];
		t[3] = Game.level.activeBlocks[x+1][y+1].layers[WATER_LAYER];
		t[4] = Game.level.activeBlocks[x+2][y].layers[WATER_LAYER];
		t[5] = Game.level.activeBlocks[x+2][y+1].layers[WATER_LAYER];
		t[6] = Game.level.activeBlocks[x+2][y+2].layers[WATER_LAYER];
		t[7] = Game.level.activeBlocks[x+1][y+2].layers[WATER_LAYER];
		t[8] = Game.level.activeBlocks[x][y+2].layers[WATER_LAYER];
		
		for(int i = 0; i< t.length; i++) {
			if(t[i].id != AIR) {
				return false; 
			}
		}
		
		//if(t.id == AIR && t2.id == AIR && t3.id == AIR && t4.id == AIR && t5.id == AIR && t6.id == AIR && t7.id == AIR && t8.id == AIR && t9.id == AIR) {
			return true;
		//} else {
		//	return false;
		//}
	}
	
	public void remove(Mob mob) {
		mobs.remove(mob);
	}
}
