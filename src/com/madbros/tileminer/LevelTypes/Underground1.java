package com.madbros.tileminer.LevelTypes;

import static com.madbros.tileminer.Constants.*;

import java.io.File;

import com.madbros.tileminer.*;
import com.madbros.tileminer.GameStates.MainState;
import com.madbros.tileminer.Items.Item;
import com.madbros.tileminer.LevelTypes.FractalTypes.CaveNoise;
import com.madbros.tileminer.Slots.Slot;
import com.madbros.tileminer.TileTypes.*;
import com.madbros.tileminer.Utils.Helpers;

public class Underground1 extends Level{
	public Underground1() {
		//currentLevel = UNDERGROUND_1_FOLDER;
		Game.currentLevel = UNDERGROUND_1_FOLDER;
		noise1 = new CaveNoise((int) Game.rgenseed);
//		noise1 = new CaveNoise(898456);
//		noise1 = new CaveNoise(898456);
		initialize();
		
		
	}
	
	public void finishLoading() {
		Game.currentState = new MainState();
		//System.out.println("FINISHLOADING:  "+chunkRect.x);
		if(Game.replaceableX > 0 && Game.replaceableY > 0) {
			int x = getXFromAbs(Game.replaceableX);
			int y = getYFromAbs(Game.replaceableY);
			if(Game.level.activeBlocks[x][y].layers[OBJECT_LAYER].id != STAIRS_UP_TILE) {
				Game.level.activeBlocks[x][y].layers[OBJECT_LAYER].deleteThisTile(x, y, Game.level.activeBlocks);
				if(Game.level.activeBlocks[x][y].layers[GRASS_LAYER].id != AIR) {
					Game.level.activeBlocks[x][y].layers[GRASS_LAYER].deleteMe(x, y, Game.level.activeBlocks);
				}
			
				if(Game.level.activeBlocks[x][y].layers[WATER_LAYER].id != AIR) {
					Game.level.activeBlocks[x][y].layers[WATER_LAYER].deleteMe(x, y, Game.level.activeBlocks);
				}
			
				if(Game.level.activeBlocks[x][y].layers[LIGHT_DIRT_LAYER].id == AIR) {
					Game.level.activeBlocks[x][y].layers[LIGHT_DIRT_LAYER] = new DirtTile();
				}
				Game.level.activeBlocks[x][y].layers[OBJECT_LAYER] = new StairsUpTile();
				
				Game.level.activeBlocks[x][y].layers[OBJECT_LAYER].cRect.x = Game.level.activeBlocks[x][y].getAbsX()* TILE_SIZE;
				Game.level.activeBlocks[x][y].layers[OBJECT_LAYER].cRect.y = Game.level.activeBlocks[x][y].getAbsY()* TILE_SIZE;
				
			}
			Game.level.autoTileBlock(x, y+1);
			Game.level.autoTileBlock(x, y);
			Game.level.autoTileBlock(x, y-1);
			Game.level.autoTileBlock(x, y-2);
			Game.level.autoTileBlock(x, y-3);
			
			Game.level.autoTileBlock(x-1, y+1);
			Game.level.autoTileBlock(x-1, y);
			Game.level.autoTileBlock(x-1, y-1);
			Game.level.autoTileBlock(x-1, y-2);
			Game.level.autoTileBlock(x-1, y-3);
			
			Game.level.autoTileBlock(x+1, y+1);
			Game.level.autoTileBlock(x+1, y);
			Game.level.autoTileBlock(x+1, y-1);
			Game.level.autoTileBlock(x+1, y-2);
			Game.level.autoTileBlock(x+1, y-3);
			
			Game.replaceableX = 0;
			Game.replaceableY = 0;
		}
		
	}
	
	
	public void createNewBlock(int i, int j, int chunkX, int chunkY, int x, int y, Underground1ChunkGenerator chunkGenerator) {
		Block block;  //top left

		int absX = i*TILE_SIZE+chunkX*CHUNK_SIZE*TILE_SIZE;
		int absY = j*TILE_SIZE+chunkY*CHUNK_SIZE*TILE_SIZE;
		int m = i+CHUNK_BLOOM_MARGIN;
		int n = j+CHUNK_BLOOM_MARGIN;

		//BELOW SEA LEVEL
		if(chunkGenerator.chunkGroundLayer[m][n] == 10) {
				Tile[] grassTile = {new DarkDirtTile(), new DirtTile(), new NoTile(), new SpaceTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
				block = new Block(grassTile, absX, absY, false);
	
		} else if(chunkGenerator.chunkGroundLayer[m][n] == 1) {
			POcean++;
			Tile[] waterTile = {new DarkDirtTile(),  new DirtTile(), new NoTile(), new WaterTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
			block = new Block(waterTile, absX, absY, false);

    	//MOUNTAIN
    	} else if(chunkGenerator.chunkGroundLayer[m][n] == 2) {
    		PMountain++;
    		Tile[] stoneMountainTile = {new DarkDirtTile(), new DirtTile(), new NoTile(), new NoTile(), new DirtMountainBottomTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
    		block = new Block(stoneMountainTile, absX, absY, true);
    	} else {
    		//DESERT
    		if(chunkGenerator.chunkGroundLayer[m][n] == 3) {
    			PDesert++;
    			Tile[] sandTile = {new DarkDirtTile(), new DirtTile(), new SandTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
    			block = new Block(sandTile, absX, absY, false);

        		//GRASSLAND
    		} else if(chunkGenerator.chunkGroundLayer[m][n] == 4) {
    			PGrass++;
    			Tile[] grassTile = {new DarkDirtTile(), new DirtTile(), new GrassTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
    			block = new Block(grassTile, absX, absY, false);

	    		//GRASSLAND
    		}else if(chunkGenerator.chunkGroundLayer[m][n] == 4){
    			PGrass++;
    			Tile[] grassTile = {new DarkDirtTile(), new DirtTile(), new GrassTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
    			block = new Block(grassTile, absX, absY, false);

	    		//Forest
    		}else if(chunkGenerator.chunkGroundLayer[m][n] == 5){
    			PForest++;
    			Tile[] grassTile = {new DarkDirtTile(), new DirtTile(), new GrassTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
    			block = new Block(grassTile, absX, absY, false);

	    		
				//RainForest
    		}else if(chunkGenerator.chunkGroundLayer[m][n] == 6){
    			PRainForest++;
    			Tile[] grassTile = {new DarkDirtTile(), new DirtTile(), new GrassTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
    			block = new Block(grassTile, absX, absY, false);
	    	    //Swamp
    		}else if(chunkGenerator.chunkGroundLayer[m][n] == 7){
    			PSwamp++;
    			Tile[] grassTile = {new DarkDirtTile(), new DirtTile(), new DarkGrassTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
    			block = new Block(grassTile, absX, absY, false);

	    		//Taiga (snowy forest)
    		}else if(chunkGenerator.chunkGroundLayer[m][n] == 8){
    			PTaiga++;
    			Tile[] grassTile = {new DarkDirtTile(), new DirtTile(), new SnowTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
    			block = new Block(grassTile, absX, absY, false);


				//Tundra (snowy)
    		}else if(chunkGenerator.chunkGroundLayer[m][n] == 9){
    			PTundra++;
    			Tile[] grassTile = {new DarkDirtTile(), new DirtTile(), new SnowTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
				block = new Block(grassTile, absX, absY, false);
	    		//DIRT
    		}else if(chunkGenerator.chunkGroundLayer[m][n] == 11){
    			PTundra++;
    			Tile[] grassTile = {new DarkDirtTile(), new DirtTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
				block = new Block(grassTile, absX, absY, false);
	    		//HOLES
    		} else {
    			PHole++;
				Tile[] grassTile = {new DarkDirtTile(), new DirtTile(), new NoTile(), new HoleTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
				block = new Block(grassTile, absX, absY, false);
	    	}
    		
    	}
		//Mountains
//		if(chunkGenerator.chunkObjectLayer[m][n+2] == STONE_MOUNTAIN_BOTTOM) {
//			block.layers[ABOVE_LAYER_1] = new StoneMountainTopTile();
//			block2.layers[ABOVE_LAYER_1] = new StoneMountainTopTile();
//			block3.layers[ABOVE_LAYER_1] = new StoneMountainTopTile();
//			block4.layers[ABOVE_LAYER_1] = new StoneMountainTopTile();
//		}
		

		
		if(chunkGenerator.chunkObjectLayer[m][n+1] == DIRT_MOUNTAIN_BOTTOM) {
			block.layers[ABOVE_LAYER_1] = new DirtMountainMiddleTile();
		}
		
		if(chunkGenerator.chunkObjectLayer[m][n+2] == DIRT_MOUNTAIN_BOTTOM ||
			chunkGenerator.chunkObjectLayer[m][n+2] == DIRT_MOUNTAIN_COPPER_BOTTOM ||
			chunkGenerator.chunkObjectLayer[m][n+2] == DIRT_MOUNTAIN_TIN_BOTTOM ||
			chunkGenerator.chunkObjectLayer[m][n+2] == DIRT_MOUNTAIN_COAL_BOTTOM) {
			block.layers[ABOVE_LAYER_2] = new DirtMountainTopTile();
		}
		

		
		if(chunkGenerator.chunkObjectLayer[m][n] == DIRT_MOUNTAIN_COAL_BOTTOM) {
			block.layers[OBJECT_LAYER] = new DirtMountainCoalBottomTile();
		}
		if(chunkGenerator.chunkObjectLayer[m][n+1] == DIRT_MOUNTAIN_COAL_BOTTOM) {
		block.layers[ABOVE_LAYER_1] = new DirtMountainCoalMiddleTile();
		}
		
		if(chunkGenerator.chunkObjectLayer[m][n] == DIRT_MOUNTAIN_COPPER_BOTTOM) {
			block.layers[OBJECT_LAYER] = new DirtMountainCopperBottomTile();
		}
		if(chunkGenerator.chunkObjectLayer[m][n+1] == DIRT_MOUNTAIN_COPPER_BOTTOM) {
			block.layers[ABOVE_LAYER_1] = new DirtMountainCopperMiddleTile();
		}
		
//		
		if(chunkGenerator.chunkObjectLayer[m][n] == DIRT_MOUNTAIN_TIN_BOTTOM) {
			block.layers[OBJECT_LAYER] = new DirtMountainTinBottomTile();
		}
		
		if(chunkGenerator.chunkObjectLayer[m][n+1] == DIRT_MOUNTAIN_TIN_BOTTOM) {
			block.layers[ABOVE_LAYER_1] = new DirtMountainTinMiddleTile();
		}
		
		
//		Block[] blockGrid = {block, block3, block2, block4};
//		int cycle = 0;
//		for(int s = m; s < m+2; s++) {
//			for(int t = n; t < n+2; t++) {
			int s = m;
			int t = n;
				//Barrels
			if(chunkGenerator.chunkObjectLayer[s][t] == CHEST) { //BARREL
				//block.layers[OBJECT_LAYER] = new ChestTile();
				//System.out.println(Game.locOfSavedGame);
				int absXNew = absX / TILE_SIZE;
				int absYNew = absY / TILE_SIZE;
				File f = new File(Game.locOfSavedGame + CHESTS_FOLDER + absXNew + "-" + absYNew + ".sv");
				if(f.exists()) { 
					f.delete();
				}
				Tile tile = new ChestTile();
				block.layers[OBJECT_LAYER] = tile;
				block.setCollisionTile((CollisionTile)tile);
				//public Slot[] invChest= new Slot[INV_LENGTH * INV_HEIGHT];
				Slot[] slot = new Slot[INV_LENGTH * INV_HEIGHT];
				
				
				int c = 0;
				for(int g = 0; g < INV_LENGTH; g++) {
					for(int h = 0; h < INV_HEIGHT; h++) {
						slot[c] = new Slot(INV_BAG_RECT.x + (INV_SLOT_SIZE + INV_MENU_SLOT_MARGIN.right) * h+200,
											 INV_BAG_RECT.y + (INV_SLOT_SIZE + INV_MENU_SLOT_MARGIN.bottom) * g);
						c++;
					}
				}
				Item items[] = Helpers.getRandomLoot(
						  new int[]{TIN_BAR, BAT_WING, WORM_GUTS, SLIME_BALL, HONEY, COOKED_STEAK,
								  PEPPER_SPROUT, HEALTH_POTION, ARTICHOKE_SPROUT, TIN_SWORD, TIN_PICK, TIN_AXE, TIN_HOE, TIN_SHOVEL, TIN_HELMET, TIN_LEGGINGS, TIN_ARMOR, TIN_BOOTS,
								  COPPER_SWORD, COPPER_PICK, PEPPER_SPROUT}, 
						  new int[]{1,1,1,1,1,1,  2,2,2,2,2,2,2,2,2,2,2,2, 3,3}, //Probabilities
						  new int[]{1,1,1,1,1,1,  1,1,1,1,1,1,1,1,1,1,1,1, 1,1}, //min stack size
						  new int[]{5,5,5,5,5,5,  5,3,5,1,1,1,1,1,1,1,1,1, 1,1}, //maxstacksize
						  1, //min items in chest
						  6); //max items in chest
				for(int g = 0; g < items.length; g++) {
					slot[g].item = items[g];
					slot[g].item.stackSize = items[g].stackSize;
				}
				Game.saveGame.saveChest(slot, absXNew, absYNew);
				
				//Game.level.hasPlacedItemOnClick = true;
			}
				if(chunkGenerator.chunkObjectLayer[s][t] == BARREL) {
					block.layers[OBJECT_LAYER] = new BarrelTile();
				}
				if(chunkGenerator.chunkObjectLayer[s][t+1] == BARREL) {
					block.layers[ABOVE_LAYER_1] = new BarrelTopTile();
				}
				
				//RainForestTree
				if(chunkGenerator.chunkObjectLayer[s][t] == TREE_RAIN) {
	    			block.layers[OBJECT_LAYER] = new TreeRainTile();
	    		}
				if(chunkGenerator.chunkObjectLayer[s+1][t+3] == TREE_RAIN) {
					block.layers[TREE_LEFT_3] = new TreeLeafRainTile();
					block.layers[TREE_LEFT_3].currentSpriteId = 0;
				}
				if(chunkGenerator.chunkObjectLayer[s+1][t+2] == TREE_RAIN) {
					block.layers[TREE_LEFT_2] = new TreeLeafRainTile();
					block.layers[TREE_LEFT_2].currentSpriteId = 1;
				}
				if(chunkGenerator.chunkObjectLayer[s+1][t+1] == TREE_RAIN) {
					block.layers[TREE_LEFT_1] = new TreeLeafRainTile();
					block.layers[TREE_LEFT_1].currentSpriteId = 2;
				}
				
				if(chunkGenerator.chunkObjectLayer[s][t+3] == TREE_RAIN) {
					block.layers[TREE_CENTER_3] = new TreeLeafRainTile();
					block.layers[TREE_CENTER_3].currentSpriteId = 3;
				}
				if(chunkGenerator.chunkObjectLayer[s][t+2] == TREE_RAIN) {
					block.layers[TREE_CENTER_2] = new TreeLeafRainTile();
					block.layers[TREE_CENTER_2].currentSpriteId = 4;
				}
				if(chunkGenerator.chunkObjectLayer[s][t+1] == TREE_RAIN) {
					block.layers[TREE_CENTER_1] = new TreeLeafRainTile();
					block.layers[TREE_CENTER_1].currentSpriteId = 5;
				}
//				if(chunkGenerator.chunkObjectLayer[s][t] == TREE_RAIN) {
//					block.layers[TREE_CENTER_1] = new TreeLeafTile();
//					block.layers[TREE_CENTER_1].currentSpriteId = 5;
//				}
				if(chunkGenerator.chunkObjectLayer[s-1][t+3] == TREE_RAIN) {
					block.layers[TREE_RIGHT_2] = new TreeLeafRainTile();
					block.layers[TREE_RIGHT_2].currentSpriteId = 7;
				}
				if(chunkGenerator.chunkObjectLayer[s-1][t+2] == TREE_RAIN) {
					block.layers[TREE_RIGHT_1] = new TreeLeafRainTile();
					block.layers[TREE_RIGHT_1].currentSpriteId = 8;
				}
				if(chunkGenerator.chunkObjectLayer[s-1][t+1] == TREE_RAIN) {
					block.layers[TREE_RIGHT_0] = new TreeLeafRainTile();
					block.layers[TREE_RIGHT_0].currentSpriteId = 9;
				}
				
				
				
				   //Forest Tree
				if(chunkGenerator.chunkObjectLayer[s][t] == TREE) {
	    			block.layers[OBJECT_LAYER] = new TreeTile();
	    		}
				if(chunkGenerator.chunkObjectLayer[s+1][t+2] == TREE) {
					block.layers[TREE_LEFT_2] = new TreeLeafTile();
					block.layers[TREE_LEFT_2].currentSpriteId = 0;
				}
				if(chunkGenerator.chunkObjectLayer[s+1][t+1] == TREE) {
					block.layers[TREE_LEFT_1] = new TreeLeafTile();
					block.layers[TREE_LEFT_1].currentSpriteId = 1;
				}
				if(chunkGenerator.chunkObjectLayer[s+1][t] == TREE) {
					block.layers[TREE_LEFT_0] = new TreeLeafTile();
					block.layers[TREE_LEFT_0].currentSpriteId = 2;
				}
				if(chunkGenerator.chunkObjectLayer[s][t+2] == TREE) {
					block.layers[TREE_CENTER_2] = new TreeLeafTile();
					block.layers[TREE_CENTER_2].currentSpriteId = 3;
				}
				if(chunkGenerator.chunkObjectLayer[s][t+1] == TREE) {
					block.layers[TREE_CENTER_1] = new TreeLeafTile();
					block.layers[TREE_CENTER_1].currentSpriteId = 4;
				}
//				if(chunkGenerator.chunkObjectLayer[s][t] == TREE) {
//					block.layers[TREE_CENTER_0] = new TreeLeafTile();
//					block.layers[TREE_CENTER_0].currentSpriteId = 5;
//				}
				if(chunkGenerator.chunkObjectLayer[s-1][t+2] == TREE) {
					block.layers[TREE_RIGHT_2] = new TreeLeafTile();
					block.layers[TREE_RIGHT_2].currentSpriteId = 6;
				}
				if(chunkGenerator.chunkObjectLayer[s-1][t+1] == TREE) {
					block.layers[TREE_RIGHT_1] = new TreeLeafTile();
					block.layers[TREE_RIGHT_1].currentSpriteId = 7;
				}
				if(chunkGenerator.chunkObjectLayer[s-1][t] == TREE) {
					block.layers[TREE_RIGHT_0] = new TreeLeafTile();
					block.layers[TREE_RIGHT_0].currentSpriteId = 8;
				}
				
				//PINE TREE
				if(chunkGenerator.chunkObjectLayer[s][t] == TREE_PINE) {
	    			block.layers[OBJECT_LAYER] = new TreePineTile();
	    		}
				if(chunkGenerator.chunkObjectLayer[s+1][t+2] == TREE_PINE) {
					block.layers[TREE_LEFT_2] = new TreeLeafPineTile();
					block.layers[TREE_LEFT_2].currentSpriteId = 0;
				}
				if(chunkGenerator.chunkObjectLayer[s+1][t+1] == TREE_PINE) {
					block.layers[TREE_LEFT_1] = new TreeLeafPineTile();
					block.layers[TREE_LEFT_1].currentSpriteId = 1;
				}
				if(chunkGenerator.chunkObjectLayer[s+1][t] == TREE_PINE) {
					block.layers[TREE_LEFT_0] = new TreeLeafPineTile();
					block.layers[TREE_LEFT_0].currentSpriteId = 2;
				}
				if(chunkGenerator.chunkObjectLayer[s][t+2] == TREE_PINE) {
					block.layers[TREE_CENTER_2] = new TreeLeafPineTile();
					block.layers[TREE_CENTER_2].currentSpriteId = 3;
				}
				if(chunkGenerator.chunkObjectLayer[s][t+1] == TREE_PINE) {
					block.layers[TREE_CENTER_1] = new TreeLeafPineTile();
					block.layers[TREE_CENTER_1].currentSpriteId = 4;
				}
//				if(chunkGenerator.chunkObjectLayer[s][t] == TREE) {
//					block.layers[TREE_CENTER_0] = new TreeLeafTile();
//					block.layers[TREE_CENTER_0].currentSpriteId = 5;
//				}
				if(chunkGenerator.chunkObjectLayer[s-1][t+2] == TREE_PINE) {
					block.layers[TREE_RIGHT_2] = new TreeLeafPineTile();
					block.layers[TREE_RIGHT_2].currentSpriteId = 6;
				}
				if(chunkGenerator.chunkObjectLayer[s-1][t+1] == TREE_PINE) {
					block.layers[TREE_RIGHT_1] = new TreeLeafPineTile();
					block.layers[TREE_RIGHT_1].currentSpriteId = 7;
				}
				if(chunkGenerator.chunkObjectLayer[s-1][t] == TREE_PINE) {
					block.layers[TREE_RIGHT_0] = new TreeLeafPineTile();
					block.layers[TREE_RIGHT_0].currentSpriteId = 8;
				}
				
//				cycle++;
//			}
//			
//
//    	}
		
		block.noise = noise;

		
		currentChunk[i][j] = block;
		
//		block.mapHeight = noise;
	}
	
	
	
	
	@Override
	public void createNewChunk(int startX, int startY, int chunkX, int chunkY) {
		
//		Block[][] chunk = new Block[CHUNK_SIZE][CHUNK_SIZE];
		int i = 0; int j = 0;
		Underground1ChunkGenerator chunkGenerator = new Underground1ChunkGenerator();
		for(int x = startX-CHUNK_BLOOM_MARGIN; x < startX+CHUNK_SIZE+CHUNK_BLOOM_MARGIN; x++) {
			for(int y = startY-CHUNK_BLOOM_MARGIN; y < startY+CHUNK_SIZE+CHUNK_BLOOM_MARGIN; y++) {
				chunkGenerator.chunkNoiseRainfall[i][j] = (float) noise3.noise.get(4*((chunkX*CHUNK_SIZE)+i-CHUNK_BLOOM_MARGIN) / (float)size, 4* ((chunkY*CHUNK_SIZE)+j-CHUNK_BLOOM_MARGIN) / (float)size, 0);
				
				chunkGenerator.chunkNoiseElevation[i][j] = (float) noise1.noise.get(4*((chunkX*CHUNK_SIZE)+i-CHUNK_BLOOM_MARGIN) / (float)size, 4 * ((chunkY*CHUNK_SIZE)+j-CHUNK_BLOOM_MARGIN) / (float)size, 0);
				chunkGenerator.chunkGroundLayer[i][j] = chunkGenerator.getGroundLayerGeneration(i, j, rand, x, y);
				chunkGenerator.chunkObjectLayer[i][j] = chunkGenerator.getObjectLayerGeneration(i, j, Game.rgenseed, rand, x, y);
				j++;
			}
			i++; j = 0;
		}
		i=0; j=0;
		
//		for(int x = startX; x < startX+CHUNK_SIZE; x++) {
//			for(int y = startY; y < startY+CHUNK_SIZE; y++) {
		for(int x = startX-CHUNK_BLOOM_MARGIN; x < startX+CHUNK_SIZE+CHUNK_BLOOM_MARGIN; x++) {
			for(int y = startY-CHUNK_BLOOM_MARGIN; y < startY+CHUNK_SIZE+CHUNK_BLOOM_MARGIN; y++) {
				chunkGenerator.secondIteration(i, j);
				j++;
			}
			i++; j = 0;
		}
		i=0; j=0;
		//First Iteration (creates all the blocks)
		for(int x = startX; x < startX+CHUNK_SIZE; x++) {
			for(int y = startY; y < startY+CHUNK_SIZE; y++) {
//				if(i % 2 == 0 && j % 2 == 0) {
					createNewBlock(i, j, chunkX, chunkY, x, y, chunkGenerator);
				//}
            	j++;
			}
			i++; j = 0;
		}
		Game.saveGame.saveChunk(currentChunk, chunkX, chunkY);
	}
}
