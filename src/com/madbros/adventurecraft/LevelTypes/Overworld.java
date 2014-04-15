package com.madbros.adventurecraft.LevelTypes;

import static com.madbros.adventurecraft.Constants.*;

import java.io.File;
import java.util.Random;

import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Items.IronSword;
import com.madbros.adventurecraft.Slots.Slot;
import com.madbros.adventurecraft.TileTypes.*;

public class Overworld extends Level{
	public Overworld() {
		//currentLevel = OVERWORLD_FOLDER;
		Game.currentLevel = OVERWORLD_FOLDER;
		initialize();
		
	}
	
	public void createNewBlock(int i, int j, int chunkX, int chunkY, int x, int y, ChunkGenerator chunkGenerator) {
		Block block;  
		PTotal++;

		int absX = i*TILE_SIZE+chunkX*CHUNK_SIZE*TILE_SIZE;
		int absY = j*TILE_SIZE+chunkY*CHUNK_SIZE*TILE_SIZE;
		int m = i+CHUNK_BLOOM_MARGIN;
		int n = j+CHUNK_BLOOM_MARGIN;

		//BELOW SEA LEVEL
		if(chunkX < 3 || chunkY < 3 || chunkX > CHUNKS_LENGTH_TOTAL - 3 || chunkY > CHUNKS_LENGTH_TOTAL - 3) {
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
    		block = new Block(stoneMountainTile, absX, absY, false);

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
	    		//HOLES
    		} else {
    			PHole++;
				Tile[] grassTile = {new DarkDirtTile(), new DirtTile(), new NoTile(), new HoleTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
				block = new Block(grassTile, absX, absY, false);
    		}
    		
    	}
		//Mountains
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
		if(chunkGenerator.chunkObjectLayer[m][n] == STONE_MOUNTAIN_TIN_BOTTOM) {
			block.layers[OBJECT_LAYER] = new DirtMountainTinBottomTile();
		}
		
		if(chunkGenerator.chunkObjectLayer[m][n+1] == STONE_MOUNTAIN_TIN_BOTTOM) {
			block.layers[ABOVE_LAYER_1] = new DirtMountainTinMiddleTile();
		}
		
		
				int s = m;
				int t = n;
				
				if(chunkGenerator.chunkObjectLayer[s][t] == RED_FLOWERS_TILE) { 
					block.layers[OBJECT_LAYER] = new RedFlowersTile();
				}
				if(chunkGenerator.chunkObjectLayer[s][t] == TALL_GRASS_A_TILE) { 
					Random rand = new Random();
					int tileID = rand.nextInt(3) + 1;
					if(tileID == 1) {
						block.layers[OBJECT_LAYER]  = new TallGrassATile();
					} else if(tileID == 2) {
						block.layers[OBJECT_LAYER]  = new TallGrassBTile();
					} else if(tileID == 3) {
						block.layers[OBJECT_LAYER]  = new TallGrassCTile();
					} else {
						
					}
				}
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
					slot[0].item = new IronSword();
					Game.saveGame.saveChest(slot, absXNew, absYNew);
					
					//Game.level.hasPlacedItemOnClick = true;
				}
				
				
				if(chunkGenerator.chunkObjectLayer[s][t] == BARREL) {
					block.layers[OBJECT_LAYER] = new BarrelTile();
				}
				if(chunkGenerator.chunkObjectLayer[s][t+1] == BARREL) {
					block.layers[ABOVE_LAYER_1] = new BarrelTopTile();
				}
				
				
//				if(Time.getGameTime() - timeCreated > 300) {// 10000
//					Block b = Game.level.activeBlocks[x][y];
//					b.layers[OBJECT_LAYER] = new CactusTile();
//					b.setCollisionTile(new CactusTile());
//					int[] xs = {x-1, x-1, x, x, x+1, x+1};
//					int[] ys = {y-1, y, y-1, y, y-1, y};
//					int[] tileLayer = {TREE_LEFT_1, TREE_LEFT_0, TREE_CENTER_1, TREE_CENTER_0, TREE_RIGHT_1, TREE_RIGHT_0};
//					
//					for(int i = 0; i < 6; i++) {
//						b = Game.level.activeBlocks[xs[i]][ys[i]];
//						b.layers[tileLayer[i]] = new CactusLeafTile(); 
//						b.layers[tileLayer[i]].currentSpriteId = i;
//						b.layers[tileLayer[i]].z = Z_ABOVE_LAYER;
//					}
//				}
				//Cactus
				if(chunkGenerator.chunkObjectLayer[s+1][t+1] == CACTUS) {
					block.layers[TREE_LEFT_1] = new CactusLeafTile();
					block.layers[TREE_LEFT_1].currentSpriteId = 0;
				}
				if(chunkGenerator.chunkObjectLayer[s+1][t] == CACTUS) {
					block.layers[TREE_LEFT_0] = new CactusLeafTile();
					block.layers[TREE_LEFT_0].currentSpriteId = 1;
				}
				if(chunkGenerator.chunkObjectLayer[s][t+1] == CACTUS) {
					block.layers[TREE_CENTER_1] = new CactusLeafTile();
					block.layers[TREE_CENTER_1].currentSpriteId = 2;
				}
				if(chunkGenerator.chunkObjectLayer[s][t] == CACTUS) {
					block.layers[OBJECT_LAYER] = new CactusTile();
				}
				if(chunkGenerator.chunkObjectLayer[s-1][t+1] == CACTUS) {
					block.layers[TREE_RIGHT_1] = new CactusLeafTile();
					block.layers[TREE_RIGHT_1].currentSpriteId = 4;
				}
				if(chunkGenerator.chunkObjectLayer[s-1][t] == CACTUS) {
					block.layers[TREE_RIGHT_0] = new CactusLeafTile();
					block.layers[TREE_RIGHT_0].currentSpriteId = 5;
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
				
				//cycle++;
//			}
//			
//
//    	}
		
		block.noise = noise;
//		block2.noise = noise;
//		block3.noise = noise;
//		block4.noise = noise;

		
		currentChunk[i][j] = block;
//		currentChunk[i+1][j] = block2;
//		currentChunk[i][j+1] = block3;
//		currentChunk[i+1][j+1] = block4;
	}
	
	public void createNewChunk(int startX, int startY, int chunkX, int chunkY) {
		int i = 0; int j = 0;
		ChunkGenerator chunkGenerator = new ChunkGenerator();
		for(int x = startX-CHUNK_BLOOM_MARGIN; x < startX+CHUNK_SIZE+CHUNK_BLOOM_MARGIN; x++) {
			for(int y = startY-CHUNK_BLOOM_MARGIN; y < startY+CHUNK_SIZE+CHUNK_BLOOM_MARGIN; y++) {
				chunkGenerator.chunkNoiseElevation[i][j] = (float) noise1.noise.get(4*((chunkX*CHUNK_SIZE)+i-CHUNK_BLOOM_MARGIN) / (float)size, 4 * ((chunkY*CHUNK_SIZE)+j-CHUNK_BLOOM_MARGIN) / (float)size, 0);
				chunkGenerator.chunkNoiseTemperature[i][j] = (float) noise2.noise.get(4*((chunkX*CHUNK_SIZE)+i-CHUNK_BLOOM_MARGIN) / (float)size, 4* ((chunkY*CHUNK_SIZE)+j-CHUNK_BLOOM_MARGIN) / (float)size, 0);
				chunkGenerator.chunkNoiseRainfall[i][j] = (float) noise3.noise.get(4*((chunkX*CHUNK_SIZE)+i-CHUNK_BLOOM_MARGIN) / (float)size, 4* ((chunkY*CHUNK_SIZE)+j-CHUNK_BLOOM_MARGIN) / (float)size, 0);
				chunkGenerator.chunkGroundLayer[i][j] = chunkGenerator.getGroundLayerGeneration(i, j, rand);
				chunkGenerator.chunkObjectLayer[i][j] = chunkGenerator.getObjectLayerGeneration(i, j, rgenseed, rand, x, y);
				j++;
			}
			i++; j = 0;
		}
		i=0; j=0;
	
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
				//if(i % 2 == 0 && j % 2 == 0) {
					createNewBlock(i, j, chunkX, chunkY, x, y, chunkGenerator);
				//}
            	j++;
			}
			i++; j = 0;
		}
		Game.saveGame.saveChunk(currentChunk, chunkX, chunkY);
	}
}
