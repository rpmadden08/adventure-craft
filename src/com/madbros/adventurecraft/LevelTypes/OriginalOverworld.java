package com.madbros.adventurecraft.LevelTypes;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.TileTypes.BarrelTile;
import com.madbros.adventurecraft.TileTypes.BarrelTopTile;
import com.madbros.adventurecraft.TileTypes.DarkDirtTile;
import com.madbros.adventurecraft.TileTypes.DarkGrassTile;
import com.madbros.adventurecraft.TileTypes.DirtTile;
import com.madbros.adventurecraft.TileTypes.GrassTile;
import com.madbros.adventurecraft.TileTypes.HoleTile;
import com.madbros.adventurecraft.TileTypes.NoTile;
import com.madbros.adventurecraft.TileTypes.SandTile;
import com.madbros.adventurecraft.TileTypes.SnowTile;
import com.madbros.adventurecraft.TileTypes.SpaceTile;
import com.madbros.adventurecraft.TileTypes.StoneMountainBottomTile;
import com.madbros.adventurecraft.TileTypes.StoneMountainCoalBottomTile;
import com.madbros.adventurecraft.TileTypes.StoneMountainCopperBottomTile;
import com.madbros.adventurecraft.TileTypes.StoneMountainTinBottomTile;
import com.madbros.adventurecraft.TileTypes.Tile;
import com.madbros.adventurecraft.TileTypes.TreeLeafPineTile;
import com.madbros.adventurecraft.TileTypes.TreeLeafRainTile;
import com.madbros.adventurecraft.TileTypes.TreeLeafTile;
import com.madbros.adventurecraft.TileTypes.TreePineTile;
import com.madbros.adventurecraft.TileTypes.TreeRainTile;
import com.madbros.adventurecraft.TileTypes.TreeTile;
import com.madbros.adventurecraft.TileTypes.WaterTile;

public class OriginalOverworld extends Level{
	public OriginalOverworld() {
		//currentLevel = OVERWORLD_FOLDER;
		Game.currentLevel = OVERWORLD_FOLDER;
		initialize();
		
	}
	
	public void createNewBlock(int i, int j, int chunkX, int chunkY, int x, int y, ChunkGenerator chunkGenerator) {
		Block block;  //top left
		Block block2; //top right
		Block block3; //bottom left
		Block block4; //bottom right
		PTotal++;
		noise = perlin.Noise(4 * ((chunkX*CHUNK_SIZE)+i) / (float)size, 4 * ((chunkY*CHUNK_SIZE)+j) / (float)size, 0);
		noiseTemperature = perlin2.Noise(4 * ((chunkX*CHUNK_SIZE)+i) / (float)size, 4 * ((chunkY*CHUNK_SIZE)+j) / (float)size, 0);
		noiseRainfall = perlin3.Noise(4 * ((chunkX*CHUNK_SIZE)+i) / (float)size, 4 * ((chunkY*CHUNK_SIZE)+j) / (float)size, 0);
		
		int absX = i*TILE_SIZE+chunkX*CHUNK_SIZE*TILE_SIZE;
		int absY = j*TILE_SIZE+chunkY*CHUNK_SIZE*TILE_SIZE;
		int m = i+CHUNK_BLOOM_MARGIN;
		int n = j+CHUNK_BLOOM_MARGIN;

		//BELOW SEA LEVEL
		if(chunkX < 3 || chunkY < 3 || chunkX > CHUNKS_LENGTH_TOTAL - 3 || chunkY > CHUNKS_LENGTH_TOTAL - 3) {
				Tile[] grassTile = {new DarkDirtTile(), new DirtTile(), new NoTile(), new SpaceTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
				Tile[] grassTile2 = {new DarkDirtTile(), new DirtTile(), new NoTile(), new SpaceTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
				Tile[] grassTile3 = {new DarkDirtTile(), new DirtTile(), new NoTile(), new SpaceTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
				Tile[] grassTile4 = {new DarkDirtTile(), new DirtTile(), new NoTile(), new SpaceTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
	    		block = new Block(grassTile, absX, absY, false);
	    		block2 = new Block(grassTile2, absX+TILE_SIZE, absY, false);
	    		block3 = new Block(grassTile3, absX, absY+TILE_SIZE, false);
	    		block4 = new Block(grassTile4, absX+TILE_SIZE, absY+TILE_SIZE, false);			
		} else if(chunkGenerator.chunkGroundLayer[m][n] == 1) {
			POcean++;
			Tile[] waterTile = {new DarkDirtTile(),  new DirtTile(), new NoTile(), new WaterTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
			Tile[] waterTile2 = {new DarkDirtTile(),  new DirtTile(), new NoTile(), new WaterTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
			Tile[] waterTile3 = {new DarkDirtTile(),  new DirtTile(), new NoTile(), new WaterTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
			Tile[] waterTile4 = {new DarkDirtTile(),  new DirtTile(), new NoTile(), new WaterTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
    		block = new Block(waterTile, absX, absY, false);
    		block2 = new Block(waterTile2, absX+TILE_SIZE, absY, false);
    		block3 = new Block(waterTile3, absX, absY+TILE_SIZE, false);
    		block4 = new Block(waterTile4, absX+TILE_SIZE, absY+TILE_SIZE, false);
    	//MOUNTAIN
    	} else if(chunkGenerator.chunkGroundLayer[m][n] == 2) {
    		PMountain++;
    		Tile[] stoneMountainTile = {new DarkDirtTile(), new DirtTile(), new NoTile(), new NoTile(), new StoneMountainBottomTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
    		Tile[] stoneMountainTile2 = {new DarkDirtTile(), new DirtTile(), new NoTile(), new NoTile(), new StoneMountainBottomTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
    		Tile[] stoneMountainTile3 = {new DarkDirtTile(), new DirtTile(), new NoTile(), new NoTile(), new StoneMountainBottomTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
    		Tile[] stoneMountainTile4 = {new DarkDirtTile(), new DirtTile(), new NoTile(), new NoTile(), new StoneMountainBottomTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
	    	block = new Block(stoneMountainTile, absX, absY, true);
	    	block2 = new Block(stoneMountainTile2, absX+TILE_SIZE, absY, true);
	    	block3 = new Block(stoneMountainTile3, absX, absY+TILE_SIZE, true);
	    	block4 = new Block(stoneMountainTile4, absX+TILE_SIZE, absY+TILE_SIZE, true);
    	} else {
    		//DESERT
    		if(chunkGenerator.chunkGroundLayer[m][n] == 3) {
    			PDesert++;
    			Tile[] sandTile = {new DarkDirtTile(), new DirtTile(), new SandTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
    			Tile[] sandTile2 = {new DarkDirtTile(), new DirtTile(), new SandTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
    			Tile[] sandTile3 = {new DarkDirtTile(), new DirtTile(), new SandTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
    			Tile[] sandTile4 = {new DarkDirtTile(), new DirtTile(), new SandTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
        		block = new Block(sandTile, absX, absY, false);
        		block2 = new Block(sandTile2, absX+TILE_SIZE, absY, false);
        		block3 = new Block(sandTile3, absX, absY+TILE_SIZE, false);
        		block4 = new Block(sandTile4, absX+TILE_SIZE, absY+TILE_SIZE, false);
        		//GRASSLAND
    		} else if(chunkGenerator.chunkGroundLayer[m][n] == 4) {
    			PGrass++;
    			Tile[] grassTile = {new DarkDirtTile(), new DirtTile(), new GrassTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
				Tile[] grassTile2 = {new DarkDirtTile(), new DirtTile(), new GrassTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
				Tile[] grassTile3 = {new DarkDirtTile(), new DirtTile(), new GrassTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
				Tile[] grassTile4 = {new DarkDirtTile(), new DirtTile(), new GrassTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
	    		block = new Block(grassTile, absX, absY, false);
	    		block2 = new Block(grassTile2, absX+TILE_SIZE, absY, false);
	    		block3 = new Block(grassTile3, absX, absY+TILE_SIZE, false);
	    		block4 = new Block(grassTile4, absX+TILE_SIZE, absY+TILE_SIZE, false);
	    		//GRASSLAND
    		}else if(chunkGenerator.chunkGroundLayer[m][n] == 4){
    			PGrass++;
    			Tile[] grassTile = {new DarkDirtTile(), new DirtTile(), new GrassTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
				Tile[] grassTile2 = {new DarkDirtTile(), new DirtTile(), new GrassTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
				Tile[] grassTile3 = {new DarkDirtTile(), new DirtTile(), new GrassTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
				Tile[] grassTile4 = {new DarkDirtTile(), new DirtTile(), new GrassTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
	    		block = new Block(grassTile, absX, absY, false);
	    		block2 = new Block(grassTile2, absX+TILE_SIZE, absY, false);
	    		block3 = new Block(grassTile3, absX, absY+TILE_SIZE, false);
	    		block4 = new Block(grassTile4, absX+TILE_SIZE, absY+TILE_SIZE, false);
	    		//Forest
    		}else if(chunkGenerator.chunkGroundLayer[m][n] == 5){
    			PForest++;
    			Tile[] grassTile = {new DarkDirtTile(), new DirtTile(), new GrassTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
				Tile[] grassTile2 = {new DarkDirtTile(), new DirtTile(), new GrassTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
				Tile[] grassTile3 = {new DarkDirtTile(), new DirtTile(), new GrassTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
				Tile[] grassTile4 = {new DarkDirtTile(), new DirtTile(), new GrassTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
	    		block = new Block(grassTile, absX, absY, false);
	    		block2 = new Block(grassTile2, absX+TILE_SIZE, absY, false);
	    		block3 = new Block(grassTile3, absX, absY+TILE_SIZE, false);
	    		block4 = new Block(grassTile4, absX+TILE_SIZE, absY+TILE_SIZE, false);
	    		
	    		
				//RainForest
    		}else if(chunkGenerator.chunkGroundLayer[m][n] == 6){
    			PRainForest++;
    			Tile[] grassTile = {new DarkDirtTile(), new DirtTile(), new GrassTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
				Tile[] grassTile2 = {new DarkDirtTile(), new DirtTile(), new GrassTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
				Tile[] grassTile3 = {new DarkDirtTile(), new DirtTile(), new GrassTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
				Tile[] grassTile4 = {new DarkDirtTile(), new DirtTile(), new GrassTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
	    		block = new Block(grassTile, absX, absY, false);
	    		block2 = new Block(grassTile2, absX+TILE_SIZE, absY, false);
	    		block3 = new Block(grassTile3, absX, absY+TILE_SIZE, false);
	    		block4 = new Block(grassTile4, absX+TILE_SIZE, absY+TILE_SIZE, false);
				//Swamp
    		}else if(chunkGenerator.chunkGroundLayer[m][n] == 7){
    			PSwamp++;
    			Tile[] grassTile = {new DarkDirtTile(), new DirtTile(), new DarkGrassTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
				Tile[] grassTile2 = {new DarkDirtTile(), new DirtTile(), new DarkGrassTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
				Tile[] grassTile3 = {new DarkDirtTile(), new DirtTile(), new DarkGrassTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
				Tile[] grassTile4 = {new DarkDirtTile(), new DirtTile(), new DarkGrassTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
	    		block = new Block(grassTile, absX, absY, false);
	    		block2 = new Block(grassTile2, absX+TILE_SIZE, absY, false);
	    		block3 = new Block(grassTile3, absX, absY+TILE_SIZE, false);
	    		block4 = new Block(grassTile4, absX+TILE_SIZE, absY+TILE_SIZE, false);
	    		
	    		//Taiga (snowy forest)
    		}else if(chunkGenerator.chunkGroundLayer[m][n] == 8){
    			PTaiga++;
    			Tile[] grassTile = {new DarkDirtTile(), new DirtTile(), new SnowTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
				Tile[] grassTile2 = {new DarkDirtTile(), new DirtTile(), new SnowTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
				Tile[] grassTile3 = {new DarkDirtTile(), new DirtTile(), new SnowTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
				Tile[] grassTile4 = {new DarkDirtTile(), new DirtTile(), new SnowTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
	    		block = new Block(grassTile, absX, absY, false);
	    		block2 = new Block(grassTile2, absX+TILE_SIZE, absY, false);
	    		block3 = new Block(grassTile3, absX, absY+TILE_SIZE, false);
	    		block4 = new Block(grassTile4, absX+TILE_SIZE, absY+TILE_SIZE, false);

				//Tundra (snowy)
    		}else if(chunkGenerator.chunkGroundLayer[m][n] == 9){
    			PTundra++;
    			Tile[] grassTile = {new DarkDirtTile(), new DirtTile(), new SnowTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
				Tile[] grassTile2 = {new DarkDirtTile(), new DirtTile(), new SnowTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
				Tile[] grassTile3 = {new DarkDirtTile(), new DirtTile(), new SnowTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
				Tile[] grassTile4 = {new DarkDirtTile(), new DirtTile(), new SnowTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
	    		block = new Block(grassTile, absX, absY, false);
	    		block2 = new Block(grassTile2, absX+TILE_SIZE, absY, false);
	    		block3 = new Block(grassTile3, absX, absY+TILE_SIZE, false);
	    		block4 = new Block(grassTile4, absX+TILE_SIZE, absY+TILE_SIZE, false);
	    		//HOLES
    		} else {
    			PHole++;
				Tile[] grassTile = {new DarkDirtTile(), new DirtTile(), new NoTile(), new HoleTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
				Tile[] grassTile2 = {new DarkDirtTile(), new DirtTile(), new NoTile(), new HoleTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
				Tile[] grassTile3 = {new DarkDirtTile(), new DirtTile(), new NoTile(), new HoleTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
				Tile[] grassTile4 = {new DarkDirtTile(), new DirtTile(), new NoTile(), new HoleTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
	    		block = new Block(grassTile, absX, absY, false);
	    		block2 = new Block(grassTile2, absX+TILE_SIZE, absY, false);
	    		block3 = new Block(grassTile3, absX, absY+TILE_SIZE, false);
	    		block4 = new Block(grassTile4, absX+TILE_SIZE, absY+TILE_SIZE, false);
    		}
    		
    	}
		//Mountains
//		if(chunkGenerator.chunkObjectLayer[m][n+2] == STONE_MOUNTAIN_BOTTOM) {
//			block.layers[ABOVE_LAYER_1] = new StoneMountainTopTile();
//			block2.layers[ABOVE_LAYER_1] = new StoneMountainTopTile();
//			block3.layers[ABOVE_LAYER_1] = new StoneMountainTopTile();
//			block4.layers[ABOVE_LAYER_1] = new StoneMountainTopTile();
//		}
		

		
		if(chunkGenerator.chunkObjectLayer[m][n] == STONE_MOUNTAIN_COAL_BOTTOM) {
			block.layers[OBJECT_LAYER] = new StoneMountainCoalBottomTile();
			block2.layers[OBJECT_LAYER] = new StoneMountainCoalBottomTile();
			block3.layers[OBJECT_LAYER] = new StoneMountainCoalBottomTile();
			block4.layers[OBJECT_LAYER] = new StoneMountainCoalBottomTile();
		}
//		if(chunkGenerator.chunkObjectLayer[m][n+2] == STONE_MOUNTAIN_COAL_BOTTOM) {
//		block.layers[ABOVE_LAYER_1] = new StoneMountainTopTile();
//		block2.layers[ABOVE_LAYER_1] = new StoneMountainTopTile();
//		block3.layers[ABOVE_LAYER_1] = new StoneMountainTopTile();
//		block4.layers[ABOVE_LAYER_1] = new StoneMountainTopTile();
//	}
		if(chunkGenerator.chunkObjectLayer[m][n] == STONE_MOUNTAIN_COPPER_BOTTOM) {
			block.layers[OBJECT_LAYER] = new StoneMountainCopperBottomTile();
			block2.layers[OBJECT_LAYER] = new StoneMountainCopperBottomTile();
			block3.layers[OBJECT_LAYER] = new StoneMountainCopperBottomTile();
			block4.layers[OBJECT_LAYER] = new StoneMountainCopperBottomTile();
		}
		
		if(chunkGenerator.chunkObjectLayer[m][n] == STONE_MOUNTAIN_TIN_BOTTOM) {
			block.layers[OBJECT_LAYER] = new StoneMountainTinBottomTile();
			block2.layers[OBJECT_LAYER] = new StoneMountainTinBottomTile();
			block3.layers[OBJECT_LAYER] = new StoneMountainTinBottomTile();
			block4.layers[OBJECT_LAYER] = new StoneMountainTinBottomTile();
		}
		
		
		
		Block[] blockGrid = {block, block3, block2, block4};
		int cycle = 0;
		for(int s = m; s < m+2; s++) {
			for(int t = n; t < n+2; t++) {
				//Barrels
				if(chunkGenerator.chunkObjectLayer[s][t] == BARREL) { //BARREL
					blockGrid[cycle].layers[OBJECT_LAYER] = new BarrelTile();
				}
				if(chunkGenerator.chunkObjectLayer[s][t+1] == BARREL) {
					blockGrid[cycle].layers[ABOVE_LAYER_1] = new BarrelTopTile();
				}
				
				//RainForestTree
				if(chunkGenerator.chunkObjectLayer[s][t] == TREE_RAIN) {
	    			blockGrid[cycle].layers[OBJECT_LAYER] = new TreeRainTile();
	    		}
				if(chunkGenerator.chunkObjectLayer[s+1][t+3] == TREE_RAIN) {
					blockGrid[cycle].layers[TREE_LEFT_3] = new TreeLeafRainTile();
					blockGrid[cycle].layers[TREE_LEFT_3].currentSpriteId = 0;
				}
				if(chunkGenerator.chunkObjectLayer[s+1][t+2] == TREE_RAIN) {
					blockGrid[cycle].layers[TREE_LEFT_2] = new TreeLeafRainTile();
					blockGrid[cycle].layers[TREE_LEFT_2].currentSpriteId = 1;
				}
				if(chunkGenerator.chunkObjectLayer[s+1][t+1] == TREE_RAIN) {
					blockGrid[cycle].layers[TREE_LEFT_1] = new TreeLeafRainTile();
					blockGrid[cycle].layers[TREE_LEFT_1].currentSpriteId = 2;
				}
				
				if(chunkGenerator.chunkObjectLayer[s][t+3] == TREE_RAIN) {
					blockGrid[cycle].layers[TREE_CENTER_3] = new TreeLeafRainTile();
					blockGrid[cycle].layers[TREE_CENTER_3].currentSpriteId = 3;
				}
				if(chunkGenerator.chunkObjectLayer[s][t+2] == TREE_RAIN) {
					blockGrid[cycle].layers[TREE_CENTER_2] = new TreeLeafRainTile();
					blockGrid[cycle].layers[TREE_CENTER_2].currentSpriteId = 4;
				}
				if(chunkGenerator.chunkObjectLayer[s][t+1] == TREE_RAIN) {
					blockGrid[cycle].layers[TREE_CENTER_1] = new TreeLeafRainTile();
					blockGrid[cycle].layers[TREE_CENTER_1].currentSpriteId = 5;
				}
//				if(chunkGenerator.chunkObjectLayer[s][t] == TREE_RAIN) {
//					blockGrid[cycle].layers[TREE_CENTER_1] = new TreeLeafTile();
//					blockGrid[cycle].layers[TREE_CENTER_1].currentSpriteId = 5;
//				}
				if(chunkGenerator.chunkObjectLayer[s-1][t+3] == TREE_RAIN) {
					blockGrid[cycle].layers[TREE_RIGHT_2] = new TreeLeafRainTile();
					blockGrid[cycle].layers[TREE_RIGHT_2].currentSpriteId = 7;
				}
				if(chunkGenerator.chunkObjectLayer[s-1][t+2] == TREE_RAIN) {
					blockGrid[cycle].layers[TREE_RIGHT_1] = new TreeLeafRainTile();
					blockGrid[cycle].layers[TREE_RIGHT_1].currentSpriteId = 8;
				}
				if(chunkGenerator.chunkObjectLayer[s-1][t+1] == TREE_RAIN) {
					blockGrid[cycle].layers[TREE_RIGHT_0] = new TreeLeafRainTile();
					blockGrid[cycle].layers[TREE_RIGHT_0].currentSpriteId = 9;
				}
				
				
				
				   //Forest Tree
				if(chunkGenerator.chunkObjectLayer[s][t] == TREE) {
	    			blockGrid[cycle].layers[OBJECT_LAYER] = new TreeTile();
	    		}
				if(chunkGenerator.chunkObjectLayer[s+1][t+2] == TREE) {
					blockGrid[cycle].layers[TREE_LEFT_2] = new TreeLeafTile();
					blockGrid[cycle].layers[TREE_LEFT_2].currentSpriteId = 0;
				}
				if(chunkGenerator.chunkObjectLayer[s+1][t+1] == TREE) {
					blockGrid[cycle].layers[TREE_LEFT_1] = new TreeLeafTile();
					blockGrid[cycle].layers[TREE_LEFT_1].currentSpriteId = 1;
				}
				if(chunkGenerator.chunkObjectLayer[s+1][t] == TREE) {
					blockGrid[cycle].layers[TREE_LEFT_0] = new TreeLeafTile();
					blockGrid[cycle].layers[TREE_LEFT_0].currentSpriteId = 2;
				}
				if(chunkGenerator.chunkObjectLayer[s][t+2] == TREE) {
					blockGrid[cycle].layers[TREE_CENTER_2] = new TreeLeafTile();
					blockGrid[cycle].layers[TREE_CENTER_2].currentSpriteId = 3;
				}
				if(chunkGenerator.chunkObjectLayer[s][t+1] == TREE) {
					blockGrid[cycle].layers[TREE_CENTER_1] = new TreeLeafTile();
					blockGrid[cycle].layers[TREE_CENTER_1].currentSpriteId = 4;
				}
//				if(chunkGenerator.chunkObjectLayer[s][t] == TREE) {
//					blockGrid[cycle].layers[TREE_CENTER_0] = new TreeLeafTile();
//					blockGrid[cycle].layers[TREE_CENTER_0].currentSpriteId = 5;
//				}
				if(chunkGenerator.chunkObjectLayer[s-1][t+2] == TREE) {
					blockGrid[cycle].layers[TREE_RIGHT_2] = new TreeLeafTile();
					blockGrid[cycle].layers[TREE_RIGHT_2].currentSpriteId = 6;
				}
				if(chunkGenerator.chunkObjectLayer[s-1][t+1] == TREE) {
					blockGrid[cycle].layers[TREE_RIGHT_1] = new TreeLeafTile();
					blockGrid[cycle].layers[TREE_RIGHT_1].currentSpriteId = 7;
				}
				if(chunkGenerator.chunkObjectLayer[s-1][t] == TREE) {
					blockGrid[cycle].layers[TREE_RIGHT_0] = new TreeLeafTile();
					blockGrid[cycle].layers[TREE_RIGHT_0].currentSpriteId = 8;
				}
				
				//PINE TREE
				if(chunkGenerator.chunkObjectLayer[s][t] == TREE_PINE) {
	    			blockGrid[cycle].layers[OBJECT_LAYER] = new TreePineTile();
	    		}
				if(chunkGenerator.chunkObjectLayer[s+1][t+2] == TREE_PINE) {
					blockGrid[cycle].layers[TREE_LEFT_2] = new TreeLeafPineTile();
					blockGrid[cycle].layers[TREE_LEFT_2].currentSpriteId = 0;
				}
				if(chunkGenerator.chunkObjectLayer[s+1][t+1] == TREE_PINE) {
					blockGrid[cycle].layers[TREE_LEFT_1] = new TreeLeafPineTile();
					blockGrid[cycle].layers[TREE_LEFT_1].currentSpriteId = 1;
				}
				if(chunkGenerator.chunkObjectLayer[s+1][t] == TREE_PINE) {
					blockGrid[cycle].layers[TREE_LEFT_0] = new TreeLeafPineTile();
					blockGrid[cycle].layers[TREE_LEFT_0].currentSpriteId = 2;
				}
				if(chunkGenerator.chunkObjectLayer[s][t+2] == TREE_PINE) {
					blockGrid[cycle].layers[TREE_CENTER_2] = new TreeLeafPineTile();
					blockGrid[cycle].layers[TREE_CENTER_2].currentSpriteId = 3;
				}
				if(chunkGenerator.chunkObjectLayer[s][t+1] == TREE_PINE) {
					blockGrid[cycle].layers[TREE_CENTER_1] = new TreeLeafPineTile();
					blockGrid[cycle].layers[TREE_CENTER_1].currentSpriteId = 4;
				}
//				if(chunkGenerator.chunkObjectLayer[s][t] == TREE) {
//					blockGrid[cycle].layers[TREE_CENTER_0] = new TreeLeafTile();
//					blockGrid[cycle].layers[TREE_CENTER_0].currentSpriteId = 5;
//				}
				if(chunkGenerator.chunkObjectLayer[s-1][t+2] == TREE_PINE) {
					blockGrid[cycle].layers[TREE_RIGHT_2] = new TreeLeafPineTile();
					blockGrid[cycle].layers[TREE_RIGHT_2].currentSpriteId = 6;
				}
				if(chunkGenerator.chunkObjectLayer[s-1][t+1] == TREE_PINE) {
					blockGrid[cycle].layers[TREE_RIGHT_1] = new TreeLeafPineTile();
					blockGrid[cycle].layers[TREE_RIGHT_1].currentSpriteId = 7;
				}
				if(chunkGenerator.chunkObjectLayer[s-1][t] == TREE_PINE) {
					blockGrid[cycle].layers[TREE_RIGHT_0] = new TreeLeafPineTile();
					blockGrid[cycle].layers[TREE_RIGHT_0].currentSpriteId = 8;
				}
				
				cycle++;
			}
			

    	}
		
		block.noise = noise;
		block2.noise = noise;
		block3.noise = noise;
		block4.noise = noise;

		
		currentChunk[i][j] = block;
		currentChunk[i+1][j] = block2;
		currentChunk[i][j+1] = block3;
		currentChunk[i+1][j+1] = block4;
		
//		block.mapHeight = noise;
	}
	
	public void createNewChunk(int startX, int startY, int chunkX, int chunkY) {
//		Block[][] chunk = new Block[CHUNK_SIZE][CHUNK_SIZE];
		int i = 0; int j = 0;
		ChunkGenerator chunkGenerator = new ChunkGenerator();
		for(int x = startX-CHUNK_BLOOM_MARGIN; x < startX+CHUNK_SIZE+CHUNK_BLOOM_MARGIN; x++) {
			for(int y = startY-CHUNK_BLOOM_MARGIN; y < startY+CHUNK_SIZE+CHUNK_BLOOM_MARGIN; y++) {
				chunkGenerator.chunkNoiseElevation[i][j] = perlin.Noise(4 * ((chunkX*CHUNK_SIZE)+i-CHUNK_BLOOM_MARGIN) / (float)size, 4 * ((chunkY*CHUNK_SIZE)+j-CHUNK_BLOOM_MARGIN) / (float)size, 0);
				chunkGenerator.chunkNoiseTemperature[i][j] = perlin2.Noise(4 * ((chunkX*CHUNK_SIZE)+i-CHUNK_BLOOM_MARGIN) / (float)size, 4 * ((chunkY*CHUNK_SIZE)+j-CHUNK_BLOOM_MARGIN) / (float)size, 0);
				chunkGenerator.chunkNoiseRainfall[i][j] = perlin3.Noise(4 * ((chunkX*CHUNK_SIZE)+i-CHUNK_BLOOM_MARGIN) / (float)size, 4 * ((chunkY*CHUNK_SIZE)+j-CHUNK_BLOOM_MARGIN) / (float)size, 0);
				chunkGenerator.chunkGroundLayer[i][j] = chunkGenerator.getGroundLayerGeneration(i, j, rand);
				chunkGenerator.chunkObjectLayer[i][j] = chunkGenerator.getObjectLayerGeneration(i, j, rgenseed, rand, x, y);
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
				if(i % 2 == 0 && j % 2 == 0) {
					createNewBlock(i, j, chunkX, chunkY, x, y, chunkGenerator);
				}
            	j++;
			}
			i++; j = 0;
		}
		Game.saveGame.saveChunk(currentChunk, chunkX, chunkY);
	}
}
