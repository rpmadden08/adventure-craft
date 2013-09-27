package com.madbros.adventurecraft;

import static com.madbros.adventurecraft.Constants.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import com.madbros.adventurecraft.TileTypes.*;
import com.madbros.adventurecraft.Utils.Helpers;
import com.madbros.adventurecraft.Utils.Point;
import com.madbros.adventurecraft.Utils.Rect;

public class Level {
	public Cell[][] cells;
	public Cell[] potentialCollisionCells;	//resets to empty every frame and includes only cells that something has moved in
	
	public Block[][] activeBlocks;
	public ArrayList<Block> collisionBlocks;
	public Block highlightedBlock;
	public Tile tileBeingAttacked = new NoTile();
	public int highlightedBlockX = 0;
	public int highlightedBlockY = 0;
	
	public ArrayList<Block> blooming = new ArrayList<Block>();
	
	public SaveGame saveGame = new SaveGame();
	public Point test = new Point(0, 0);
	public float noise;
	public float noiseTemperature;
	public float noiseRainfall;
	public int debuggerTest = 0;
	
	//Keeps track of what part of the activeBlocks array we're rendering. Starts off in the very center.
	public Rect renderRect = new Rect(TILES_PER_ROW / 2 - (int)Math.ceil(Game.getCenterScreenX() * 1.0 /TILE_SIZE),
			  						  TILES_PER_ROW / 2 - (int)Math.ceil(Game.getCenterScreenY() * 1.0 / TILE_SIZE),
			  						  (int)Math.ceil(INITIAL_WINDOW_WIDTH * 1.0 / TILE_SIZE) + RENDER_MARGIN,
			  						  (int)Math.ceil(INITIAL_WINDOW_HEIGHT * 1.0 / TILE_SIZE) + RENDER_MARGIN);
	
	//private long rgenseed = System.currentTimeMillis();
	private long rgenseed = 1;
	public PerlinGenerator perlin = new PerlinGenerator((int) rgenseed);
	public Random rand = new Random(rgenseed);
	public int randInt1 = rand.nextInt();
	public int randInt2 = rand.nextInt();
	public PerlinGenerator perlin2 = new PerlinGenerator(randInt1);
	public PerlinGenerator perlin3 = new PerlinGenerator(randInt2);
	//public PerlinGenerator perlin2 = new PerlinGenerator((int) rgenseed);
	//public PerlinGenerator perlin3 = new PerlinGenerator((int) rgenseed);
	public int size = 100;
	
	public Rect chunkRect = new Rect(0, 0, CHUNKS_IN_A_ROW-1, CHUNKS_IN_A_ROW-1);	//keeps track of the chunk we're on
	public int offsetX = 0;	//offset gets set at the start of level if there is one
	public int offsetY = 0;

	public boolean isLoading = false;
	public double PTotal = 0;
	public double PTaiga = 0;
	public double PRainForest = 0;
	public double PGrass = 0;
	public double PDesert = 0;
	public double PForest = 0;
	public double PSwamp = 0;
	public double PTundra = 0;
	public double POcean = 0;
	public double PMountain = 0;
	public double PHole = 0;
	
	
	
	public Level() {
		if(Game.getCenterScreenX() % TILE_SIZE > 0) offsetX = TILE_SIZE - Game.getCenterScreenX() % TILE_SIZE;
		if(Game.getCenterScreenY() % TILE_SIZE > 0) offsetY = TILE_SIZE - Game.getCenterScreenY() % TILE_SIZE;
		
		activeBlocks = new Block[TILES_PER_ROW][TILES_PER_ROW];
		
		for(int i = 0; i < CHUNKS_IN_A_ROW; i++) {
			for(int j = 0; j < CHUNKS_IN_A_ROW; j++) {
				createNewChunk(CHUNK_SIZE*i, CHUNK_SIZE*j, chunkRect.x + i, chunkRect.y + j);
			}
		}
		bloomAll();		

		autoTileNewArea(2, 2, TILES_PER_ROW-2, TILES_PER_ROW-2);
	}
	
	public void bloomAll() {
		for(int i = 0; i < blooming.size(); i++) {
			Block b = blooming.get(i);
			int x = b.getX(activeBlocks);
			int y = b.getY(activeBlocks);
			if(x > 2 && y > 2 && x < TILES_PER_ROW-2 && y < TILES_PER_ROW-2) {
				b.layers[OBJECT_LAYER].bloom(x,y, activeBlocks);
				//b.isUnfinished = false;
				
				
			}
		}
	}
	
	public void saveChunk(int startX, int startY, int chunkX, int chunkY) {
		//bloomChunk(CHUNK_SIZE*i, CHUNK_SIZE*j, chunkRect.x + i, chunkRect.y + j);
		Block[][] chunk = new Block[CHUNK_SIZE][CHUNK_SIZE];
		int x2 = 0;
		int y2 = 0;
		for(int x = startX; x < startX+CHUNK_SIZE; x++) {
			for(int y = startY; y < startY+CHUNK_SIZE; y++) {
				chunk[x2][y2] = activeBlocks[x][y];
            	y2++;
			}
			x2++; y2 = 0;
		}
		
		saveGame.saveChunk(chunk, chunkX, chunkY);
	}
	
	private void highlight64() {
		if(highlightedBlock != null) {
			highlightedBlock.isHighlighted = false;
			activeBlocks[highlightedBlockX+1][highlightedBlockY].isHighlighted = false;
			activeBlocks[highlightedBlockX][highlightedBlockY+1].isHighlighted = false;
			activeBlocks[highlightedBlockX+1][highlightedBlockY+1].isHighlighted = false;
		}
		Rect mRect = Helpers.getMouseRect();
		highlightedBlockX = renderRect.x + (mRect.x + offsetX) / TILE_SIZE;
		
		highlightedBlockY = renderRect.y + (mRect.y + offsetY) / TILE_SIZE;
		
		if(highlightedBlockX % 2 == 0 && highlightedBlockY % 2 == 0) {
			
		} else if (highlightedBlockX % 2 == 1 && highlightedBlockY % 2 == 0) {
			highlightedBlockX--;
		} else if (highlightedBlockX % 2 == 0 && highlightedBlockY % 2 == 1) {
			highlightedBlockY--;
		} else {
			highlightedBlockX--;
			highlightedBlockY--;
		}
		
		highlightedBlock = activeBlocks[highlightedBlockX][highlightedBlockY];
		
		if(tileBeingAttacked != highlightedBlock.getTopTile()) {
			tileBeingAttacked.currentHp = tileBeingAttacked.maxHp;
			tileBeingAttacked = highlightedBlock.getTopTile();
		}
		highlightedBlock.isHighlighted = true;
		activeBlocks[highlightedBlockX+1][highlightedBlockY].isHighlighted = true;
		activeBlocks[highlightedBlockX][highlightedBlockY+1].isHighlighted = true;
		activeBlocks[highlightedBlockX+1][highlightedBlockY+1].isHighlighted = true;
	}
	
	private void highlight32() {
		if(highlightedBlock != null) {
			highlightedBlock.isHighlighted = false;
			activeBlocks[highlightedBlockX+1][highlightedBlockY].isHighlighted = false;
			activeBlocks[highlightedBlockX][highlightedBlockY+1].isHighlighted = false;
			activeBlocks[highlightedBlockX+1][highlightedBlockY+1].isHighlighted = false;
		}
		
		
		Rect mRect = Helpers.getMouseRect();
		highlightedBlockX = renderRect.x + (mRect.x + offsetX) / TILE_SIZE;
		highlightedBlockY = renderRect.y + (mRect.y + offsetY) / TILE_SIZE;
		
		highlightedBlock = activeBlocks[highlightedBlockX][highlightedBlockY];
		
		if(tileBeingAttacked != highlightedBlock.getTopTile()) {
			tileBeingAttacked.currentHp = tileBeingAttacked.maxHp;
			tileBeingAttacked = highlightedBlock.getTopTile();
		}
		highlightedBlock.isHighlighted = true;
	}
	
	private void highlightBlock() {
		if(Game.inventory.invBar[Game.inventory.itemSelected].item.is32 == true) {
			highlight32();
		} else {
			highlight64();
		}
		
	}
	
	public void update() {
		//System.out.println(blooming.size());
		System.out.println("GRASS: "+ PGrass/PTotal *100);
		System.out.println("FOREST: "+ PForest/PTotal *100);
		System.out.println("RAINFOREST: "+ PRainForest/PTotal *100);
		System.out.println("DESERT: "+ PDesert/PTotal *100);
		System.out.println("TAIGA: "+ PTaiga/PTotal *100);
		System.out.println("SWAMP: "+ PSwamp/PTotal *100);
		System.out.println("TUNDRA: "+ PTundra/PTotal *100);
		System.out.println("MOUNTAIN: "+ PMountain/PTotal *100);
		System.out.println("HOLE: "+ PHole/PTotal *100);
		System.out.println("OCEAN: "+ POcean/PTotal *100);
		if(Game.currentState.type == State.MAIN) highlightBlock();
		
		if(renderRect.y <= CHUNK_SIZE/2) {
			getNorthernChunks();
		} else if(renderRect.y2() >= TILES_PER_ROW-CHUNK_SIZE/2 - 1) {
			getSouthernChunks();
		}
		
		if(renderRect.x <= CHUNK_SIZE/2) {
			getWesternChunks();
		} else if(renderRect.x2() >= TILES_PER_ROW-CHUNK_SIZE/2 - 1) {
			getEasternChunks();
		}
		for(int x = renderRect.x; x < renderRect.x2(); x++) {
			for(int y = renderRect.y; y < renderRect.y2(); y++) {
				activeBlocks[x][y].layers[OBJECT_LAYER].update(x, y);
			}
		}
	}
	
	//removes the unfinished blocks from the blooming arraylist
	
	public void removeUnfinished(int startX, int startY) {
		Iterator<Block> iterator = blooming.iterator();
		while (iterator.hasNext()) {
			
				Block b = iterator.next();
				int x = b.getX(activeBlocks);
				int y = b.getY(activeBlocks);
				if(x >= startX && y >= startY && x <= startX + CHUNK_SIZE+1 && y <= startY + CHUNK_SIZE+1) {
					iterator.remove();
				}
				
			}
	}
	
	public void getNorthernChunks() {
		for(int i = 0; i < CHUNKS_IN_A_ROW; i++) {
			removeUnfinished(CHUNK_SIZE*i, TILES_PER_ROW- CHUNK_SIZE);
		}
		renderRect.y += CHUNK_SIZE;
		
		shiftActiveBlocksArray(DOWN);
		chunkRect.y--;
		
		for(int i = 0; i < CHUNKS_IN_A_ROW; i++) {
			createNewChunk(CHUNK_SIZE*i, 0, chunkRect.x + i, chunkRect.y);
		}
		bloomAll();
		autoTileNewArea(2, 2, TILES_PER_ROW-2, TILES_PER_ROW-2);
		//autoTileNewArea(2, 2, TILES_PER_ROW-2, CHUNK_SIZE+2);
		
	}
	
	public void getSouthernChunks() {
		for(int i = 0; i < CHUNKS_IN_A_ROW; i++) {
			removeUnfinished(CHUNK_SIZE*i, 0);
		}
		renderRect.y -= CHUNK_SIZE;
		
		shiftActiveBlocksArray(UP);
		chunkRect.y++;
		
		for(int i = 0; i < CHUNKS_IN_A_ROW; i++) {
			createNewChunk(CHUNK_SIZE*i, TILES_PER_ROW-CHUNK_SIZE, chunkRect.x + i, chunkRect.y2());
		}
		bloomAll();
		autoTileNewArea(2, 2, TILES_PER_ROW-2, TILES_PER_ROW-2);
		//autoTileNewArea(2, TILES_PER_ROW-CHUNK_SIZE-2, TILES_PER_ROW-2, TILES_PER_ROW-2);
		
	}
	
	public void getEasternChunks() {
		for(int i = 0; i < CHUNKS_IN_A_ROW; i++) {
			removeUnfinished(0, CHUNK_SIZE*i);
		}
		renderRect.x -= CHUNK_SIZE;
		
		shiftActiveBlocksArray(LEFT);
		chunkRect.x++;
		
		for(int i = 0; i < CHUNKS_IN_A_ROW; i++) {
			createNewChunk(TILES_PER_ROW-CHUNK_SIZE, CHUNK_SIZE*i, chunkRect.x2(), chunkRect.y + i);
		}
		bloomAll();
		autoTileNewArea(2, 2, TILES_PER_ROW-2, TILES_PER_ROW-2);
		//autoTileNewArea(TILES_PER_ROW-CHUNK_SIZE-2, 2, TILES_PER_ROW-2, TILES_PER_ROW-2);
	
	}

	public void getWesternChunks() {
		for(int i = 0; i < CHUNKS_IN_A_ROW; i++) {
			removeUnfinished(TILES_PER_ROW-CHUNK_SIZE, CHUNK_SIZE*i);
		}
		renderRect.x += CHUNK_SIZE;
		
		shiftActiveBlocksArray(RIGHT);
		chunkRect.x--;
		
		for(int i = 0; i < CHUNKS_IN_A_ROW; i++) {
			createNewChunk(0, CHUNK_SIZE*i, chunkRect.x, chunkRect.y + i);
		}
		bloomAll();
		autoTileNewArea(2, 2, TILES_PER_ROW-2, TILES_PER_ROW-2);
		//autoTileNewArea(2, 2, CHUNK_SIZE+2, TILES_PER_ROW-2);
		
	}
	
	public void shiftActiveBlocksArray(int dir) {
		if(dir == DOWN) { 
			for(int y = 0; y < activeBlocks.length; y++) {
				System.arraycopy(activeBlocks[y], 0, activeBlocks[y], CHUNK_SIZE, activeBlocks.length-CHUNK_SIZE);
			} 
		} else if(dir == UP) {
			for(int y = 0; y < activeBlocks.length; y++) {
				System.arraycopy(activeBlocks[y], CHUNK_SIZE, activeBlocks[y], 0, activeBlocks.length-CHUNK_SIZE);
			}	
		} else if(dir == RIGHT) {
			for(int x = activeBlocks.length-1-CHUNK_SIZE; x >= 0; x--) {
				activeBlocks[x+CHUNK_SIZE] = activeBlocks[x].clone();
			}
		} else if(dir == LEFT){
			for(int x = CHUNK_SIZE; x < activeBlocks.length; x++) {
				activeBlocks[x-CHUNK_SIZE] = activeBlocks[x].clone();
			}
		}
	}
	
	public void createNewBlock(int i, int j, int chunkX, int chunkY, int x, int y) {
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
		
		//BELOW SEA LEVEL
		if(noise < -0.3) {//FIX ME Should be < -0.1
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
    	} else if(noise > 0.3) {
    		PMountain++;
    		Tile[] dirtMountainTile = {new DarkDirtTile(), new DirtTile(), new GrassTile(), new NoTile(), new DirtMountainBottomTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
    		Tile[] dirtMountainTile2 = {new DarkDirtTile(), new DirtTile(), new GrassTile(), new NoTile(), new DirtMountainBottomTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
    		Tile[] dirtMountainTile3 = {new DarkDirtTile(), new DirtTile(), new GrassTile(), new NoTile(), new DirtMountainBottomTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
    		Tile[] dirtMountainTile4 = {new DarkDirtTile(), new DirtTile(), new GrassTile(), new NoTile(), new DirtMountainBottomTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
	    	block = new Block(dirtMountainTile, absX, absY, true);
	    	block2 = new Block(dirtMountainTile2, absX+TILE_SIZE, absY, true);
	    	block3 = new Block(dirtMountainTile3, absX, absY+TILE_SIZE, true);
	    	block4 = new Block(dirtMountainTile4, absX+TILE_SIZE, absY+TILE_SIZE, true);
			blooming.add(block);
			blooming.add(block2);
			blooming.add(block3);
			blooming.add(block4);
		//GRASSLANDS
    	} else {
    		//DESERT
    		if(noiseTemperature < -0.1 && noiseRainfall < -0.1) {
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
    		} else if(noiseTemperature >= -0.1 && noiseTemperature < 0.1 &&noiseRainfall < -0.1){
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
    		}else if(noiseTemperature < -0.1 && noiseRainfall >= -0.1 &&noiseRainfall < 0.1){
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
    		}else if(noiseTemperature >= -0.1 && noiseTemperature < 0.1 &&noiseRainfall >= -0.1 && noiseRainfall < 0.1){
    			PForest++;
    			Tile[] grassTile = {new DarkDirtTile(), new DirtTile(), new GrassTile(), new NoTile(), new TreeTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
				Tile[] grassTile2 = {new DarkDirtTile(), new DirtTile(), new GrassTile(), new NoTile(), new TreeTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
				Tile[] grassTile3 = {new DarkDirtTile(), new DirtTile(), new GrassTile(), new NoTile(), new TreeTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
				Tile[] grassTile4 = {new DarkDirtTile(), new DirtTile(), new GrassTile(), new NoTile(), new TreeTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
	    		block = new Block(grassTile, absX, absY, false);
	    		block2 = new Block(grassTile2, absX+TILE_SIZE, absY, false);
	    		block3 = new Block(grassTile3, absX, absY+TILE_SIZE, false);
	    		block4 = new Block(grassTile4, absX+TILE_SIZE, absY+TILE_SIZE, false);
	    		blooming.add(block);
				blooming.add(block2);
				blooming.add(block3);
				blooming.add(block4);
				//RainForest
    		}else if(noiseTemperature < -0.1 &&noiseRainfall >= 0.1){
    			PRainForest++;
    			Tile[] grassTile = {new DarkDirtTile(), new DirtTile(), new GrassTile(), new NoTile(), new TreeRainTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
				Tile[] grassTile2 = {new DarkDirtTile(), new DirtTile(), new GrassTile(), new NoTile(), new TreeRainTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
				Tile[] grassTile3 = {new DarkDirtTile(), new DirtTile(), new GrassTile(), new NoTile(), new TreeRainTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
				Tile[] grassTile4 = {new DarkDirtTile(), new DirtTile(), new GrassTile(), new NoTile(), new TreeRainTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
	    		block = new Block(grassTile, absX, absY, false);
	    		block2 = new Block(grassTile2, absX+TILE_SIZE, absY, false);
	    		block3 = new Block(grassTile3, absX, absY+TILE_SIZE, false);
	    		block4 = new Block(grassTile4, absX+TILE_SIZE, absY+TILE_SIZE, false);
	    		blooming.add(block);
				blooming.add(block2);
				blooming.add(block3);
				blooming.add(block4);
				//Swamp
    		}else if(noiseTemperature >= -0.1 && noiseTemperature < 0.1 && noiseRainfall >= 0.1){
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
    		}else if(noiseRainfall >= -0.1 && noiseRainfall < 0.1 && noiseTemperature >= 0.1){
    			PTaiga++;
    			Tile[] grassTile = {new DarkDirtTile(), new DirtTile(), new GrassTile(), new NoTile(), new TreePineTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
				Tile[] grassTile2 = {new DarkDirtTile(), new DirtTile(), new GrassTile(), new NoTile(), new TreePineTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
				Tile[] grassTile3 = {new DarkDirtTile(), new DirtTile(), new GrassTile(), new NoTile(), new TreePineTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
				Tile[] grassTile4 = {new DarkDirtTile(), new DirtTile(), new GrassTile(), new NoTile(), new TreePineTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
	    		block = new Block(grassTile, absX, absY, false);
	    		block2 = new Block(grassTile2, absX+TILE_SIZE, absY, false);
	    		block3 = new Block(grassTile3, absX, absY+TILE_SIZE, false);
	    		block4 = new Block(grassTile4, absX+TILE_SIZE, absY+TILE_SIZE, false);
	    		blooming.add(block);
				blooming.add(block2);
				blooming.add(block3);
				blooming.add(block4);
				//Tundra (snowy)
    		}else if(noiseRainfall < -0.1 &&noiseTemperature >= 0.1){
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
    		}else {
    			PHole++;
    			System.out.println("RAIN: "+noiseRainfall);
    			System.out.println("TEMP: "+noiseTemperature);
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
		//if(x == 40 && y == 40) {
//			Tile[] grassTile = {new DarkDirtTile(), new DirtTile(), new GrassTile(), new NoTile(), new TreeTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
//			Tile[] grassTile2 = {new DarkDirtTile(), new DirtTile(), new GrassTile(), new NoTile(), new TreeTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
//			Tile[] grassTile3 = {new DarkDirtTile(), new DirtTile(), new GrassTile(), new NoTile(), new TreeTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
//			Tile[] grassTile4 = {new DarkDirtTile(), new DirtTile(), new GrassTile(), new NoTile(), new TreeTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(),new NoTile(), new NoTile()};
//			
//			block = new Block(grassTile, absX, absY, false);
//			block2 = new Block(grassTile2, absX+TILE_SIZE, absY, false);
//			block3 = new Block(grassTile3, absX, absY+TILE_SIZE, false);
//			block4 = new Block(grassTile4, absX+TILE_SIZE, absY+TILE_SIZE, false);
//			blooming.add(block);
//			blooming.add(block2);
//			blooming.add(block3);
//			blooming.add(block4);
		//}
		block.noise = noise;
		block2.noise = noise;
		block3.noise = noise;
		block4.noise = noise;

		activeBlocks[x][y] = block;
		activeBlocks[x+1][y] = block2;
		activeBlocks[x][y+1] = block3;
		activeBlocks[x+1][y+1] = block4;
		
//		block.mapHeight = noise;
	}
	
	public void secondIteration(int x, int y, int i, int j, int chunkX, int chunkY) {
		int absX = i*TILE_SIZE+chunkX*CHUNK_SIZE*TILE_SIZE;
		int absY = j*TILE_SIZE+chunkY*CHUNK_SIZE*TILE_SIZE;

		
		if (x == 0 || y == 0 || x == CHUNK_SIZE * CHUNKS_IN_A_ROW || y == CHUNK_SIZE * CHUNKS_IN_A_ROW) {
			//if(activeBlocks[x][y].noise > 0.1 && noise < 0.105) {
			
		    	//Tile[] treeTile = {new DarkDirtTile(), new DirtTile(), new GrassTile(), new NoTile(), new TreeTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile()};
		    	//activeBlocks[x][y] = new Block(treeTile, absX, absY, false);
		    	
	    	//}
	    		
	    } else {
	    	//if(activeBlocks[x][y].noise > 0.1 && noise < 0.105) {
	    	//System.out.println(x+" "+y);
		    	Tile[] treeTile = {new DarkDirtTile(), new DirtTile(), new GrassTile(), new NoTile(), new TreeTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile(), new NoTile()};
		    	activeBlocks[x][y] = new Block(treeTile, absX, absY, false);
		    	//activeBlocks[x][y].layers[OBJECT_LAYER].bloom(x,y, activeBlocks);
	    	//}
	    }
		

	}
	
	public Block autoTile(Block[][] blocks, Block block, int x, int y) {
		for(int i = LIGHT_DIRT_LAYER; i < blocks[x][y].layers.length; i++) {
			if(block.layers[i].isAutoTileable) {
				int left = 0, topLeft = 0, top = 0, topRight = 0, right = 0, bottomRight = 0, bottom = 0, bottomLeft = 0;
				if(blocks[x-2][y-2].layers[i].id == block.layers[i].id) topLeft = 1;
				if(blocks[x][y-2].layers[i].id == block.layers[i].id) top = 2;
				if(blocks[x+2][y-2].layers[i].id == block.layers[i].id) topRight = 4;
				if(blocks[x-2][y].layers[i].id == block.layers[i].id) left = 8;
				if(blocks[x+2][y].layers[i].id == block.layers[i].id) right = 16;
				if(blocks[x-2][y+2].layers[i].id == block.layers[i].id) bottomLeft = 32;
				if(blocks[x][y+2].layers[i].id == block.layers[i].id) bottom = 64;
				if(blocks[x+2][y+2].layers[i].id == block.layers[i].id) bottomRight = 128;
				
				block.layers[i].autoTile = TOP_LEFT_AUTO_TILE_HASH.get(left + topLeft + top);
				blocks[x+1][y].layers[i].autoTile = TOP_RIGHT_AUTO_TILE_HASH.get(right + topRight + top);
				blocks[x][y+1].layers[i].autoTile = BOTTOM_LEFT_AUTO_TILE_HASH.get(left + bottomLeft + bottom);
				blocks[x+1][y+1].layers[i].autoTile = BOTTOM_RIGHT_AUTO_TILE_HASH.get(right + bottomRight + bottom);
				
				if(block.layers[i].autoTile != MIDDLE_TILE || blocks[x+1][y].layers[i].autoTile != MIDDLE_TILE ||
				   blocks[x][y+1].layers[i].autoTile != MIDDLE_TILE || blocks[x+1][y+1].layers[i].autoTile != MIDDLE_TILE) {
					block.layers[i].isMiddleTile = false;
					blocks[x+1][y].layers[i].isMiddleTile = false;
					blocks[x][y+1].layers[i].isMiddleTile = false;
					blocks[x+1][y+1].layers[i].isMiddleTile = false;
				} else {
					block.layers[i].isMiddleTile = true;
					blocks[x+1][y].layers[i].isMiddleTile = true;
					blocks[x][y+1].layers[i].isMiddleTile = true;
					blocks[x+1][y+1].layers[i].isMiddleTile = true;
				}
			}
		}
		return block;
	}
	
	public void createNewChunk(int startX, int startY, int chunkX, int chunkY) {
		isLoading = true;
		
		File f = new File(Game.locOfSavedGame + CHUNKS_FOLDER + chunkX + "-" + chunkY + ".sv");
		if(f.exists()) { 
			Block[][] chunk = saveGame.loadChunk(chunkX, chunkY);
			int i = 0; int j = 0;
			for(int x = startX; x < startX+CHUNK_SIZE; x++) {
				for(int y = startY; y < startY+CHUNK_SIZE; y++) {
					activeBlocks[x][y] = chunk[i][j];
					j++;
				}
				i++; j =0;
			}
		} else {
			Block[][] chunk = new Block[CHUNK_SIZE][CHUNK_SIZE];
			int i = 0; int j = 0;
			//First Iteration (creates all the blocks)
			for(int x = startX; x < startX+CHUNK_SIZE; x++) {
				for(int y = startY; y < startY+CHUNK_SIZE; y++) {
					if(i % 2 == 0 && j % 2 == 0) {
						createNewBlock(i, j, chunkX, chunkY, x, y);
						chunk[i][j] = activeBlocks[x][y];
						chunk[i+1][j] = activeBlocks[x+1][y];
						chunk[i][j+1] = activeBlocks[x][y+1];
						chunk[i+1][j+1] = activeBlocks[x+1][y+1];
					}
	            	j++;
				}
				i++; j = 0;
			}
			//saveGame.saveChunk(chunk, chunkX, chunkY);
		}
		isLoading = false;
	}
	
	public void autoTileNewArea(int startX, int startY, int endX, int endY) {
		for(int x = startX; x < endX; x++) {
			for(int y = startY; y < endY; y++) {
				if(x % 2 == 0 && y % 2 == 0) {
					activeBlocks[x][y] = autoTile(activeBlocks, activeBlocks[x][y], x, y);
				}
			}
		}
	}
	
	public void autoTileHighlightedBlock() {
		autoTile(activeBlocks, activeBlocks[highlightedBlockX][highlightedBlockY], highlightedBlockX, highlightedBlockY);
		
		autoTile(activeBlocks, activeBlocks[highlightedBlockX-2][highlightedBlockY-2], highlightedBlockX-2, highlightedBlockY-2);
		autoTile(activeBlocks, activeBlocks[highlightedBlockX][highlightedBlockY-2], highlightedBlockX, highlightedBlockY-2);
		autoTile(activeBlocks, activeBlocks[highlightedBlockX+2][highlightedBlockY-2], highlightedBlockX+2, highlightedBlockY-2);
		autoTile(activeBlocks, activeBlocks[highlightedBlockX-2][highlightedBlockY], highlightedBlockX-2, highlightedBlockY);
		autoTile(activeBlocks, activeBlocks[highlightedBlockX+2][highlightedBlockY], highlightedBlockX+2, highlightedBlockY);
		autoTile(activeBlocks, activeBlocks[highlightedBlockX-2][highlightedBlockY+2], highlightedBlockX-2, highlightedBlockY+2);
		autoTile(activeBlocks, activeBlocks[highlightedBlockX][highlightedBlockY+2], highlightedBlockX, highlightedBlockY+2);
		autoTile(activeBlocks, activeBlocks[highlightedBlockX+2][highlightedBlockY+2], highlightedBlockX+2, highlightedBlockY+2);
	}
	
	public void handleCollisions() {
		
	}
}
