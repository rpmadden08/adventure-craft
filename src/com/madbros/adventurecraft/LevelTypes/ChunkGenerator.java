package com.madbros.adventurecraft.LevelTypes;

import static com.madbros.adventurecraft.Constants.*;

import java.util.ArrayList;
import java.util.List;
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
	
	
	
	boolean getRandom(double chance, int x, int y) {
	    double a;
	    float seed = chunkNoiseElevation[x][y] * 1000000000;
	    long seed2 = (long) seed;
	    Random r = new Random(seed2);
	    a = r.nextDouble();
	    a = a * 100;
	    if(a< chance) {
	    	return true;
	    } else {
	    	return false;
	    }
	}
	
	public void oreGenerator(int oreAmount, int x, int y, int itemType, int is32) {
		if(is32 == 2) {
			if(x % 2 != 0) {
				x = x -1;
			}
			if(y % 2 != 0) {
				y = y -1;
			}
		}
		List<Integer> oreX = new ArrayList<Integer>();
		List<Integer> oreY = new ArrayList<Integer>();
		List<Integer> oreCheckX = new ArrayList<Integer>();
		List<Integer> oreCheckY = new ArrayList<Integer>();
		//System.out.println("TEST: "+x);
		oreX.add(x); oreY.add(y);
		oreCheckX.add(x); oreCheckY.add(y);
		//System.out.println((int) oreX.get(0));
		while(oreX.size() < oreAmount) {
			//Add possible ores or possible ores array...
			//System.out.println(oreX.size());
			int totalOres = oreX.size();
			for(int a = 0; a < totalOres; a++) {
				
				//Check Up
				int i = oreX.get(a);
				int j = oreY.get(a)-is32; 
				int totalCheckOres = oreCheckX.size();
				boolean isDuplicate = false;
				for(int c = 0; c < totalOres; c++) {
					if(i == oreX.get(c) && j == oreY.get(c)) {
						isDuplicate = true;
						break;
					} 
				}
				for(int b = 0; b < totalCheckOres; b++) {
					if(i == oreCheckX.get(b) && j == oreCheckY.get(b)) {
						isDuplicate = true;
						break;
					} 
				}
				if(isDuplicate == false && i > -1 && j > -1 && i< 36 && j <36) {
					oreCheckX.add(i); oreCheckY.add(j);
				}
				
				//Check Down
				i = oreX.get(a);
				j = oreY.get(a)+is32;
				totalCheckOres = oreCheckX.size();
				isDuplicate = false;
				for(int c = 0; c < totalOres; c++) {
					if(i == oreX.get(c) && j == oreY.get(c)) {
						isDuplicate = true;
						break;
					} 
				}
				for(int b = 0; b < totalCheckOres; b++) {
					if(i == oreCheckX.get(b) && j == oreCheckY.get(b)) {
						isDuplicate = true;
						break;
					} 
				}
				if(isDuplicate == false && i > -1 && j > -1 && i< 36 && j <36) {
					oreCheckX.add(i); oreCheckY.add(j);
				}
				//Check Left
				i = oreX.get(a)-is32;
				j = oreY.get(a);
				totalCheckOres = oreCheckX.size();
				isDuplicate = false;
				for(int c = 0; c < totalOres; c++) {
					if(i == oreX.get(c) && j == oreY.get(c)) {
						isDuplicate = true;
						break;
					} 
				}
				for(int b = 0; b < totalCheckOres; b++) {
					if(i == oreCheckX.get(b) && j == oreCheckY.get(b)) {
						isDuplicate = true;
						break;
					} 
				}
				if(isDuplicate == false && i > -1 && j > -1 && i< 36 && j <36) {
					oreCheckX.add(i); oreCheckY.add(j);
				}
				//Check Right
				i = oreX.get(a)+is32;
				j = oreY.get(a);
				totalCheckOres = oreCheckX.size();
				isDuplicate = false;
				for(int c = 0; c < totalOres; c++) {
					if(i == oreX.get(c) && j == oreY.get(c)) {
						isDuplicate = true;
						break;
					} 
				}
				for(int b = 0; b < totalCheckOres; b++) {
					if(i == oreCheckX.get(b) && j == oreCheckY.get(b)) {
						isDuplicate = true;
						break;
					} 
				}
				if(isDuplicate == false && i > -1 && j > -1 && i< 36 && j <36) {
					oreCheckX.add(i); oreCheckY.add(j);
				}
			}
		
			//Randomly select one of the possible ore areas and add it to the generated ores.  
			float seed = chunkNoiseElevation[x][y] * 1000000000;
		    long seed2 = (long) seed;
			Random rand = new Random(seed2); 
			//int randSelection = rand.nextInt(oreCheckX.size()); 
			int test = oreCheckX.size();
			if(test > 0) {
				int randSelection = rand.nextInt(test); 
				totalOres = oreX.size();
				oreX.add(oreCheckX.get(randSelection)); oreY.add(oreCheckY.get(randSelection));
				oreCheckX.remove(randSelection); oreCheckY.remove(randSelection);
			}

		
		}
		//Place the ores
		int totalOres = oreX.size();
		//System.out.println(totalOres);
		for(int a = 0; a < totalOres; a++) {
			//System.out.println(oreX.indexOf(a)+" - "+oreY.indexOf(a));
			//if(oreX.get(a) > -1 && oreY.get(a) > -1 && oreX.get(a) < 36 && oreY.get(a) < 36) {
				
				chunkObjectLayer[oreX.get(a)][oreY.get(a)] = itemType;
			//}
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
			//System.out.println("CHECK");
			//System.out.println(m);
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