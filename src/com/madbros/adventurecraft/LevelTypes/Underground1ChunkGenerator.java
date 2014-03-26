package com.madbros.adventurecraft.LevelTypes;

import static com.madbros.adventurecraft.Constants.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Underground1ChunkGenerator extends ChunkGenerator{
//	public float chunkNoiseElevation[][] = new float [CHUNK_SIZE+20][CHUNK_SIZE+20];
//	public float chunkNoiseRainfall[][] = new float [CHUNK_SIZE+20][CHUNK_SIZE+20];
//	public float chunkNoiseTemperature[][] = new float [CHUNK_SIZE+20][CHUNK_SIZE+20];
//	public float chunkGroundLayer[][] = new float [CHUNK_SIZE+20][CHUNK_SIZE+20];
//	public float chunkObjectLayer[][] = new float [CHUNK_SIZE+20][CHUNK_SIZE+20];
	
	public Underground1ChunkGenerator() {
		
	}
	
	@Override
	public int getObjectLayerGeneration(int m, int n, long seed, Random rand, int x, int y) {
		if(chunkGroundLayer[m][n] == 1) {
			return AIR;
    	//MOUNTAIN
    	} else if(chunkGroundLayer[m][n] == 2) {
    		if(x > CHUNK_SIZE * 3 - 1 && x < CHUNKS_LENGTH_TOTAL * CHUNK_SIZE - CHUNK_SIZE * 2 + 1 && y > CHUNK_SIZE * 3 - 1 &&
 				   y < CHUNKS_LENGTH_TOTAL * CHUNK_SIZE - CHUNK_SIZE * 2) {
 					
    			
    			if(getRandom(0.1, m, n)) {
    				return COAL_MARK; // Return Barrel (1001)
    			} else if(getRandom(0.2, m, n)) {
    				return TIN_MARK;
    			} else if(getRandom(0.3, m, n)) {
        			return COPPER_MARK;
        		} else {
    				return STONE_MOUNTAIN_BOTTOM;
    			}
    		}
    		return AIR;
    	} else {
    		
    		//DESERT
    		if(chunkGroundLayer[m][n] == 3) {
    			return AIR;
    			
        		//GRASSLAND
    		} else if(chunkGroundLayer[m][n] == 4) {
    			if(x > CHUNK_SIZE * 3 - 1 && x < CHUNKS_LENGTH_TOTAL * CHUNK_SIZE - CHUNK_SIZE * 2 + 1 && y > CHUNK_SIZE * 3 - 1 &&
    					   y < CHUNKS_LENGTH_TOTAL * CHUNK_SIZE - CHUNK_SIZE * 2) {
    						
    		    			if(getRandom(0.5, m, n)) {
    		    				return TREE;
    		    			} else if(getRandom(0.7, m, n)){
        		    			return BARREL; // Return Barrel (1000)
        		    				
    		    			} else {
    		    				return AIR;
    		    			}
    		    			
    		    			
    					}
    			return AIR;
    			
	    		//Forest
    		}else if(chunkGroundLayer[m][n] == 5){
				if(x > CHUNK_SIZE * 3 - 1 && x < CHUNKS_LENGTH_TOTAL * CHUNK_SIZE - CHUNK_SIZE * 2 + 1 && y > CHUNK_SIZE * 3 - 1 &&
				   y < CHUNKS_LENGTH_TOTAL * CHUNK_SIZE - CHUNK_SIZE * 2) {
					
	    			if(getRandom(85.0, m, n)) {
	    				return TREE;
	    			} else {
	    				return AIR;
	    			}
				}
    			
    			return AIR;
    			
	    		
				//RainForest
    		}else if(chunkGroundLayer[m][n] == 6){
    			if(x > CHUNK_SIZE * 3 - 1 && x < CHUNKS_LENGTH_TOTAL * CHUNK_SIZE - CHUNK_SIZE * 2 + 1 && y > CHUNK_SIZE * 3 - 1 &&
    					   y < CHUNKS_LENGTH_TOTAL * CHUNK_SIZE - CHUNK_SIZE * 2) {
    						
    		    			if(getRandom(85.0, m, n)) {
    		    				return TREE_RAIN;
    		    			} else {
    		    				return AIR;
    		    			}
    					}
    			return AIR;
				//Swamp
    		}else if(chunkGroundLayer[m][n] == 7){
    			return AIR;
	    		
	    		//Taiga (snowy forest)
    		}else if(chunkGroundLayer[m][n] == 8){
    			if(x > CHUNK_SIZE * 3 - 1 && x < CHUNKS_LENGTH_TOTAL * CHUNK_SIZE - CHUNK_SIZE * 2 + 1 && y > CHUNK_SIZE * 3 - 1 &&
 					   y < CHUNKS_LENGTH_TOTAL * CHUNK_SIZE - CHUNK_SIZE * 2) {

    				if(getRandom(85.0, m, n)) {
 		    				return TREE_PINE;
 		    			} else {
 		    				return AIR;
 		    			}
 					}
    			return AIR;
				//Tundra (snowy)
    		}else if(chunkGroundLayer[m][n] == 9){
    			return AIR;
	    		//HOLES
    		}else {
    			return AIR;
    		}
    	}
		//For every object create a function and make sure the priority goes last...
		
	}

	public int getGroundLayerGeneration(int m, int n, Random rand) {
		//BELOW SEA LEVEL
		if(chunkNoiseElevation[m][n] < -0.1) {
			return 1;
    	//MOUNTAIN
    	} else if(chunkNoiseElevation[m][n] > 0.01) {
    		return 2;
    	} else {
    		return 2;
    	}
	}
	public void secondIteration(int m, int n) {
		if(chunkObjectLayer[m][n] == COAL_MARK) {
			float seed = chunkNoiseElevation[m][n] * 1000000000;
		    long seed2 = (long) seed;
			Random rand = new Random(seed2); 
			int oreAmount = rand.nextInt(6) + 3;
			oreGenerator(oreAmount,m,n, STONE_MOUNTAIN_COAL_BOTTOM, 2);
		} else if(chunkObjectLayer[m][n] == 1000) {
			oreGenerator(22,m,n,BARREL, 1);
		} else if(chunkObjectLayer[m][n] == TIN_MARK) {
			float seed = chunkNoiseElevation[m][n] * 1000000000;
		    long seed2 = (long) seed;
			Random rand = new Random(seed2); 
			int oreAmount = rand.nextInt(6) + 3;
			oreGenerator(oreAmount,m,n, STONE_MOUNTAIN_TIN_BOTTOM, 2);
		} else if(chunkObjectLayer[m][n] == COPPER_MARK) {
			float seed = chunkNoiseElevation[m][n] * 1000000000;
		    long seed2 = (long) seed;
			Random rand = new Random(seed2); 
			int oreAmount = rand.nextInt(6) + 3;
			oreGenerator(oreAmount,m,n, STONE_MOUNTAIN_COPPER_BOTTOM, 2);
		} 
	}
}
