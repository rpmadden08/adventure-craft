package com.madbros.tileminer.LevelTypes;

import static com.madbros.tileminer.Constants.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.madbros.tileminer.Game;

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
 				
    			if(getRandom(2.5, m, n)) {
    				return COAL_MARK; 
    			} else if(getRandom(4, m, n)) {
    				return TIN_MARK;
//    			} else if(getRandom(0.6, m, n)) {//0.3
//        			return COPPER_MARK;
        		} else {
    				return DIRT_MOUNTAIN_BOTTOM;
    			}
    	} else {
    		
    		//DESERT
    		if(chunkGroundLayer[m][n] == 3) {
    				if(getRandom(0.3, m, n)) {
	    				return CACTUS;
	    			}
    			return AIR;
    			
        		//GRASSLAND
    		} else if(chunkGroundLayer[m][n] == 4) {
    		    			if(getRandom(0.5, m, n)) {
    		    				return TREE;
    		    			} else if(getRandom(0.55, m, n)){ 
        		    			return BARREL; 
    		    			} else if(getRandom(2, m, n)){ 
            		    			return RED_FLOWERS_TILE; 
    		    			} else if(getRandom(6, m, n)){ 
        		    			return GRASS_MARK; 
    		    			} else {
    		    				return AIR;
    		    			}
    			
	    		//Forest
    		}else if(chunkGroundLayer[m][n] == 5){
					
	    			if(getRandom(20.0, m, n)) { //20.0
	    				return TREE;
	    			} else if(getRandom(20.05, m, n)){ 
		    			return BARREL; 
	    			}else {
	    				return AIR;
	    			}
    			
	    		
				//RainForest
    		}else if(chunkGroundLayer[m][n] == 6){
    						
    		    			if(getRandom(85.0, m, n)) {
    		    				return TREE_RAIN;
    		    			} else {
    		    				return AIR;
    		    			}
				//Swamp
    		}else if(chunkGroundLayer[m][n] == 7){
    			return AIR;
	    		
	    		//Taiga (snowy forest)
    		}else if(chunkGroundLayer[m][n] == 8){

    				if(getRandom(85.0, m, n)) {
 		    				return TREE_PINE;
 		    			} else {
 		    				return AIR;
 		    			}
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

	public int getGroundLayerGeneration(int m, int n, Random rand, int x, int y) {
		//BELOW SEA LEVEL
		if(x > CHUNK_SIZE * 3 - 1 && x < CHUNKS_LENGTH_TOTAL * CHUNK_SIZE - CHUNK_SIZE * 2 - 1 && y > CHUNK_SIZE * 3 - 1 &&
				   y < CHUNKS_LENGTH_TOTAL * CHUNK_SIZE - CHUNK_SIZE * 2) {
			if(chunkNoiseElevation[m][n] < 0.5) {
				Game.oceanTally = Game.oceanTally +1;
				return 1;
	    	//MOUNTAIN
	    	} else if(chunkNoiseElevation[m][n] > 0.87) {
	    		Game.mountainTally = Game.mountainTally +1;
	    		return 2;
	    	} else {
	    		//DESERT
	    		if(chunkNoiseTemperature[m][n] < 0.5 && chunkNoiseRainfall[m][n] < 0.5) {
	    			Game.desertTally = Game.desertTally +1;
	    			return 3;
	        		//GRASSLAND
	    		} else if(chunkNoiseTemperature[m][n] >= 0.5 && chunkNoiseTemperature[m][n] < 0.87 && chunkNoiseRainfall[m][n] < 0.5){
	    			Game.grasslandTally = Game.grasslandTally +1;
	    			return 4;
		    		//GRASSLAND
	    		}else if(chunkNoiseTemperature[m][n] < 0.5 && chunkNoiseRainfall[m][n] >= 0.5 && chunkNoiseRainfall[m][n] < 0.87){
	    			Game.grasslandTally = Game.grasslandTally +1;
	    			return 4;
		    		//Forest
	    		}else if(chunkNoiseTemperature[m][n] >= 0.5 && chunkNoiseTemperature[m][n] < 0.87 && chunkNoiseRainfall[m][n] >= 0.5 && chunkNoiseRainfall[m][n] < 0.87){
	    			Game.forestTally = Game.forestTally +1;
	    			return 5;
		    		
					//RainForest
	    		}else if(chunkNoiseTemperature[m][n] < 0.5 && chunkNoiseRainfall[m][n] >= 0.87){
	    			Game.desertTally = Game.desertTally +1;
	    			return 3;
					//Swamp
	    		}else if(chunkNoiseTemperature[m][n] >= 0.5 && chunkNoiseTemperature[m][n] < 0.87 && chunkNoiseRainfall[m][n] >= 0.87){
	    			Game.desertTally = Game.desertTally +1;
	    			return 3;
		    		//Taiga (snowy forest)
	    		}else if(chunkNoiseRainfall[m][n] >= 0.5 && chunkNoiseRainfall[m][n] < 0.87 && chunkNoiseTemperature[m][n] >= 0.87){
	    			Game.forestTally = Game.forestTally +1;
	    			return 5;
					//Tundra (snowy)
	    		}else if(chunkNoiseRainfall[m][n] < 0.5 && chunkNoiseTemperature[m][n] >= 0.87){
	    			Game.desertTally = Game.desertTally +1;
	    			return 3;
		    		//HOLES
	    		}else {
	    			Game.desertTally = Game.desertTally +1;
	    			return 3;
	    			
	    		}
	    	}
	
		} else {
			return 10;
		}
	}
	
	
	
	public boolean getRandom(double chance, int x, int y) {
	    double a;
	    float seed = chunkNoiseRainfall[x][y] * 1000000000;
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
	
	public void oreGenerator(int oreAmount, int x, int y, int itemType, int is32, int[] possibleBiomes) {
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
			for(int b = 0; b < possibleBiomes.length; b++){
				if(chunkGroundLayer[oreX.get(a)][oreY.get(a)] == possibleBiomes[b]) {
					chunkObjectLayer[oreX.get(a)][oreY.get(a)] = itemType;
					break;
				}
			}
				
			//}
		}
		
	}
	
	public void secondIteration(int m, int n) {
		if(chunkGroundLayer[m][n] != 10) { //Checks to see if it's in space or not... 
			if(chunkObjectLayer[m][n] == COAL_MARK) {
				float seed = chunkNoiseElevation[m][n] * 1000000000;
			    long seed2 = (long) seed;
				Random rand = new Random(seed2); 
				int oreAmount = rand.nextInt(7) + 3;
				oreGenerator(oreAmount,m,n, DIRT_MOUNTAIN_COAL_BOTTOM, 1, new int[] {2});
			} else if(chunkObjectLayer[m][n] == 1000) {
				//System.out.println("CHECK");
				//System.out.println(m);
				oreGenerator(22,m,n,BARREL, 1, new int[] {4});
			} else if(chunkObjectLayer[m][n] == TIN_MARK) {
				float seed = chunkNoiseElevation[m][n] * 1000000000;
			    long seed2 = (long) seed;
				Random rand = new Random(seed2); 
				int oreAmount = rand.nextInt(7) + 3;
				oreGenerator(oreAmount,m,n, DIRT_MOUNTAIN_TIN_BOTTOM, 1, new int[] {2});
			} else if(chunkObjectLayer[m][n] == COPPER_MARK) {
				float seed = chunkNoiseElevation[m][n] * 1000000000;
			    long seed2 = (long) seed;
				Random rand = new Random(seed2); 
				int oreAmount = rand.nextInt(7) + 3;
				oreGenerator(oreAmount,m,n, DIRT_MOUNTAIN_COPPER_BOTTOM, 1, new int[] {2});
			} else if(chunkObjectLayer[m][n] == GRASS_MARK) {
				float seed = chunkNoiseElevation[m][n] * 1000000000;
			    long seed2 = (long) seed;
				Random rand = new Random(seed2); 
				int oreAmount = rand.nextInt(7) + 3;
				oreGenerator(oreAmount,m,n, TALL_GRASS_A_TILE, 1, new int[] {4});
			} 
		}
	}
}
