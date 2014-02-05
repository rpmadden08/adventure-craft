package com.madbros.adventurecraft;

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
 					
	    		Random r = new Random();
	    		int Low = 1;
	    		int High = 10;
	    		int R = r.nextInt(High-Low) + Low;
	    		if(R >5) {
	    			return DIRT_MOUNTAIN_BOTTOM;
	    		} else {
	    			return DIRT_MOUNTAIN_COAL_BOTTOM;
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
    						
    		    			double a = getTree(m, n, seed, rand);
    		    			if(a < 0.005) {
    		    				return TREE;
    		    			} else {
    		    				a = getTree(m, n, seed, rand);
        		    			if(a < 0.01 && a > 0.005) {
        		    				return 1000;
        		    			} else {
        		    				return AIR;
        		    			}
    		    			}
    		    			
    		    			
    					}
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
    			if(x > CHUNK_SIZE * 3 - 1 && x < CHUNKS_LENGTH_TOTAL * CHUNK_SIZE - CHUNK_SIZE * 2 + 1 && y > CHUNK_SIZE * 3 - 1 &&
    					   y < CHUNKS_LENGTH_TOTAL * CHUNK_SIZE - CHUNK_SIZE * 2) {
    						
    		    			double a = getTree(m, n, seed, rand);
    		    			if(a < 0.5) {
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
 						
 		    			double a = getTree(m, n, seed, rand);
 		    			if(a < 0.5) {
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
	
	
	
	double getTree(int x, int y, long rgenseed, Random rand) {
	    double a;
	    float seed = chunkNoiseElevation[x][y] * 1000000000;
	    long seed2 = (long) seed;
	    Random r = new Random(seed2);
	    a = r.nextDouble();
	    return a;
	}
	
	public void oreGenerator(int oreAmount, int x, int y, int itemType) {
		List<Integer> oreX = new ArrayList<Integer>();
		List<Integer> oreY = new ArrayList<Integer>();
		List<Integer> oreCheckX = new ArrayList<Integer>();
		List<Integer> oreCheckY = new ArrayList<Integer>();
		//System.out.println("TEST: "+x);
		oreX.add(x); oreY.add(y);
		//System.out.println((int) oreX.get(0));
		while(oreX.size() < oreAmount) {
			//Add possible ores or possible ores array...
			System.out.println(oreX.size());
			int totalOres = oreX.size();
			for(int a = 0; a < totalOres; a++) {
				//Check Up
				int i = oreX.get(a);
				int j = oreY.get(a)-1; 
				int totalCheckOres = oreCheckX.size();
				if(totalCheckOres > 0) {
					for(int b = 0; b < totalCheckOres; b++) {
						if(i != oreCheckX.get(b) && j != oreCheckY.get(b)) {
							oreCheckX.add(i); oreCheckY.add(j);
							break;
						}
					}
				} else { 
					oreCheckX.add(i); oreCheckY.add(j);
				}
				//Check Down
				i = oreX.get(a);
				j = oreY.get(a)+1;
				totalCheckOres = oreCheckX.size();
				for(int b = 0; b < totalCheckOres; b++) {
					if(i != oreCheckX.get(b) && j != oreCheckY.get(b)) {
							oreCheckX.add(i); oreCheckY.add(j);
							break;
					}
				}
				//Check Left
				i = oreX.get(a)-1;
				j = oreY.get(a);
				totalCheckOres = oreCheckX.size();
				for(int b = 0; b < totalCheckOres; b++) {
					if(i != oreCheckX.get(b) && j != oreCheckY.get(b)) {
							oreCheckX.add(i); oreCheckY.add(j);
							break;
					}
				}
				//Check Right
				i = oreX.get(a)+1;
				j = oreY.get(a);
				totalCheckOres = oreCheckX.size();
				for(int b = 0; b < totalCheckOres; b++) {
					if(i != oreCheckX.get(b) && j != oreCheckY.get(b)) {
							oreCheckX.add(i); oreCheckY.add(j);
							break;
					}
				}
			}
		
			//Randomly select one of the possible ore areas and add it to the generated ores.  
			Random rand = new Random(); 
			//int randSelection = rand.nextInt(oreCheckX.size()); 
			int test = oreCheckX.size();
			int randSelection = rand.nextInt(test); 
			totalOres = oreX.size();
			oreX.add(oreCheckX.get(randSelection)); oreY.add(oreCheckY.get(randSelection));
			oreCheckX.remove(randSelection); oreCheckY.remove(randSelection);

		
		}
		//Place the ores
		int totalOres = oreX.size();
		//System.out.println(totalOres);
		for(int a = 0; a < totalOres; a++) {
			//System.out.println(oreX.indexOf(a)+" - "+oreY.indexOf(a));
			if(oreX.get(a) > 0 && oreY.get(a) > 0 && oreX.get(a) < 36 && oreY.get(a) < 36) {
				
				chunkObjectLayer[oreX.get(a)][oreY.get(a)] = itemType;
			}
		}
		
	}
	
	public void secondIteration(int m, int n) {
		if(chunkObjectLayer[m][n] == DIRT_MOUNTAIN_COAL_BOTTOM) {
			//oreGenerator(3,m,n, DIRT_MOUNTAIN_COAL_BOTTOM);
		} else if(chunkObjectLayer[m][n] == 1000) {
			//System.out.println("CHECK");
			//System.out.println(m);
			oreGenerator(6,m,n,BARREL);
		}
	}
}
