package com.madbros.adventurecraft;

import static com.madbros.adventurecraft.Constants.*;

import java.util.Random;

public class ChunkGenerator {
	public float chunkNoiseElevation[][] = new float [CHUNK_SIZE+20][CHUNK_SIZE+20];
	public float chunkNoiseRainfall[][] = new float [CHUNK_SIZE+20][CHUNK_SIZE+20];
	public float chunkNoiseTemperature[][] = new float [CHUNK_SIZE+20][CHUNK_SIZE+20];
	public float chunkGroundLayer[][] = new float [CHUNK_SIZE+20][CHUNK_SIZE+20];
	public float chunkObjectLayer[][] = new float [CHUNK_SIZE+20][CHUNK_SIZE+20];
	
	public ChunkGenerator() {
		
	}
	
	public int getObjectLayerGeneration(int m, int n, long seed, Random rand, int x, int y) {
		if(chunkGroundLayer[m][n] == 1) {
			return AIR;
    	//MOUNTAIN
    	} else if(chunkGroundLayer[m][n] == 2) {
    		Random r = new Random();
    		int Low = 1;
    		int High = 10;
    		int R = r.nextInt(High-Low) + Low;
    		if(R >5) {
    			return DIRT_MOUNTAIN_BOTTOM;
    		} else {
    			return DIRT_MOUNTAIN_COAL_BOTTOM;
    		}
    	} else {
    		//DESERT
    		if(chunkGroundLayer[m][n] == 3) {
    			return AIR;
        		//GRASSLAND
    		} else if(chunkGroundLayer[m][n] == 4) {
    			return AIR;
	    		//GRASSLAND
    		}else if(chunkGroundLayer[m][n] == 4){
    			return AIR;
	    		//Forest
    		}else if(chunkGroundLayer[m][n] == 5){
				if(x > CHUNK_SIZE * 3 - 1 && x < CHUNKS_LENGTH_TOTAL * CHUNK_SIZE - CHUNK_SIZE * 2 + 1 && y > CHUNK_SIZE * 3 - 1 &&
				   y < CHUNKS_LENGTH_TOTAL * CHUNK_SIZE - CHUNK_SIZE * 2) {
					
	    			double a = getTree(m, n, seed, rand);
	    			if(a < 0.5) {
	    				return TREE;
	    			} else {
	    				return AIR;
	    			}
				}
    			
    			return AIR;
    			
	    		
				//RainForest
    		}else if(chunkGroundLayer[m][n] == 6){
    			return AIR;
				//Swamp
    		}else if(chunkGroundLayer[m][n] == 7){
    			return AIR;
	    		
	    		//Taiga (snowy forest)
    		}else if(chunkGroundLayer[m][n] == 8){
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
		if(chunkNoiseElevation[m][n] < -0.1) {//FIXME Should be < -0.1
			return 1;
    	//MOUNTAIN
    	} else if(chunkNoiseElevation[m][n] > 0.1) {
    		return 2;
    	} else {
    		//DESERT
    		if(chunkNoiseTemperature[m][n] < -0.1 && chunkNoiseRainfall[m][n] < -0.1) {
    			return 3;
        		//GRASSLAND
    		} else if(chunkNoiseTemperature[m][n] >= -0.1 && chunkNoiseTemperature[m][n] < 0.1 && chunkNoiseRainfall[m][n] < -0.1){
    			return 4;
	    		//GRASSLAND
    		}else if(chunkNoiseTemperature[m][n] < -0.1 && chunkNoiseRainfall[m][n] >= -0.1 && chunkNoiseRainfall[m][n] < 0.1){
    			return 4;
	    		//Forest
    		}else if(chunkNoiseTemperature[m][n] >= -0.1 && chunkNoiseTemperature[m][n] < 0.1 && chunkNoiseRainfall[m][n] >= -0.1 && chunkNoiseRainfall[m][n] < 0.1){
    			return 5;
	    		
				//RainForest
    		}else if(chunkNoiseTemperature[m][n] < -0.1 && chunkNoiseRainfall[m][n] >= 0.1){
    			return 6;
				//Swamp
    		}else if(chunkNoiseTemperature[m][n] >= -0.1 && chunkNoiseTemperature[m][n] < 0.1 && chunkNoiseRainfall[m][n] >= 0.1){
    			return 7;
	    		//Taiga (snowy forest)
    		}else if(chunkNoiseRainfall[m][n] >= -0.1 && chunkNoiseRainfall[m][n] < 0.1 && chunkNoiseTemperature[m][n] >= 0.1){
    			return 8;
				//Tundra (snowy)
    		}else if(chunkNoiseRainfall[m][n] < -0.1 && chunkNoiseTemperature[m][n] >= 0.1){
    			return 9;
	    		//HOLES
    		}else {
    			return 0;
    			
    		}
    	}
	}
	
	
	
	double getTree(int x, int y, long rgenseed, Random rand) {
	    double a;
	    float seed = chunkNoiseElevation[x][y] * 1000000000;
	    long seed2 = (long) seed;
	    Random r = new Random(seed2);
	    a = r.nextDouble();
	    return a;
	}
}
