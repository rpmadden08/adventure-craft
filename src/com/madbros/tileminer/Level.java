package com.madbros.tileminer;

import static com.madbros.tileminer.Constants.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import com.madbros.tileminer.Constants.State;
import com.madbros.tileminer.GameStates.LoadingState;
import com.madbros.tileminer.GameStates.MainState;
import com.madbros.tileminer.Items.Clothing;
import com.madbros.tileminer.Items.IronArmor;
import com.madbros.tileminer.LevelTypes.ChunkGenerator;
import com.madbros.tileminer.LevelTypes.FractalTypes.BasicNoise;
import com.madbros.tileminer.TileTypes.*;
import com.madbros.tileminer.Utils.Helpers;
import com.madbros.tileminer.Utils.Point;
import com.madbros.tileminer.Utils.Rect;
import com.madbros.tileminer.Utils.RectInt;

public class Level {
//	public long rgenseed = System.currentTimeMillis();
//	//public long rgenseed = 898490;
//		//898463 ()  
//		//898474 ()
//		//898475 (forest) 
//		//898478 ()
	public Cell[][] cells;
	public Cell[] potentialCollisionCells;	//resets to empty every frame and includes only cells that something has moved in
	
	public int currentHousing = 1;
	public int currentCollisionHousing = 0;
	
	public Block[][] activeBlocks;
	public Block[][] currentChunk;
	
	public Block[][] easternChunks;
	public Block[][] westernChunks;
	public Block[][] northernChunks;
	public Block[][] southernChunks;
	
	public Thread loadingThread = new Thread();
	public Thread savingThread = new Thread();
	public ArrayList<Block> collisionBlocks;
	public Block highlightedBlock;
	public Block highlightedBlock2;
	public Block highlightedBlock3;
	public Block highlightedBlock4;
	public Tile tileBeingAttacked = new NoTile();
	public Tile tileBeingAttacked2 = new NoTile();
	public Tile tileBeingAttacked3 = new NoTile();
	public Tile tileBeingAttacked4 = new NoTile();
	
	public int highlightedBlockX = 0;
	public int highlightedBlockY = 0;
	
	public boolean hasPlacedItemOnClick = false;
	
//	public int oceanTally, mountainTally, desertTally, grasslandTally, forestTally, jungleTally, swampTally, taigaTally, tundraTally = 0;
//	
	public ArrayList<Block> blooming = new ArrayList<Block>();
	
	//public SaveGame saveGame = new SaveGame();
	public Point test = new Point(0, 0);
	public float noise;
	public float noiseTemperature;
	public float noiseRainfall;
	public int debuggerTest = 0;
	//public String currentLevel = OVERWORLD_FOLDER;
	
	//Keeps track of what part of the activeBlocks array we're rendering. Starts off in the very center.
	//renderRect, spawnX, spawnY should all be the same
//total chunks * chunk_size * Tile_size /2 - character
	public int spawnX = TILES_PER_ROW*TILE_SIZE/2 - CHARACTER_SIZE/4;
	public int spawnY = TILES_PER_ROW*TILE_SIZE/2 - CHARACTER_SIZE/2;
	public String spawnLevel = OVERWORLD_FOLDER;
	public int masterSpawnX = spawnX;
	public int masterSpawnY = spawnY;
//	public int spawnX = TILES_PER_ROW*TILE_SIZE/2 - CHARACTER_SIZE/2+900;
	//public int spawnY = TILES_PER_ROW*TILE_SIZE/2 - CHARACTER_SIZE/2+700;
	
	public int startChunkX = spawnX /(CHUNK_SIZE*TILE_SIZE) - (CHUNKS_IN_A_ROW /2);
	public int startChunkY = spawnY /(CHUNK_SIZE*TILE_SIZE) - (CHUNKS_IN_A_ROW /2);
	public Rect chunkRect = new Rect(startChunkX, startChunkY, CHUNKS_IN_A_ROW-1, CHUNKS_IN_A_ROW-1);
//	public int offsetX = 0;	//offset gets set at the start of level if there is one
//	public int offsetY = 0;
//	public Rect renderRect = new Rect(
//			spawnX / TILE_SIZE +1-(CHUNK_SIZE*chunkRect.x) - (int)Math.ceil(Game.getCenterScreenX() * 1.0 / TILE_SIZE),
//			spawnY / TILE_SIZE +1-(CHUNK_SIZE*chunkRect.y) - (int)Math.ceil(Game.getCenterScreenY() * 1.0 / TILE_SIZE),
//			(int)Math.ceil(Game.currentScreenSizeX * 1.0 / TILE_SIZE) + RENDER_MARGIN,
//			(int)Math.ceil(Game.currentScreenSizeX * 1.0 / TILE_SIZE) + RENDER_MARGIN);
	
//	
	
	
	
	
	
	//public PerlinGenerator perlin = new PerlinGenerator((int) rgenseed);
	public Random rand = new Random(Game.rgenseed);
	public int randInt1 = rand.nextInt();
	public int randInt2 = rand.nextInt();
	//public PerlinGenerator perlin2 = new PerlinGenerator(randInt1);
	//public PerlinGenerator perlin3 = new PerlinGenerator(randInt2);
	
	//public BasicNoise noise1 = new BasicNoise(898456);
	public BasicNoise noise1 = new BasicNoise((int) Game.rgenseed);
	public BasicNoise noise2 = new BasicNoise(randInt1);
	public BasicNoise noise3 = new BasicNoise(randInt2);
	//public PerlinGenerator perlin2 = new PerlinGenerator((int) rgenseed);
	//public PerlinGenerator perlin3 = new PerlinGenerator((int) rgenseed);
	public int size = 1000;
	public long time;
	public int hours = 8;
	public int minutes = 0;
	public boolean isDay = true;
	
	//public Rect chunkRect = new Rect(0, 0, CHUNKS_IN_A_ROW-1, CHUNKS_IN_A_ROW-1);	//keeps track of the chunk we're on


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
	
	
	
	//public int musicSelection = 0;
	//public Music music = Gdx.audio.newMusic(Gdx.files.internal("music/overworld.wav"));
	
	
	
	public Level() {	
		//initialize();
	}
	
	public void initialize() {
	}
	
	public void finishLoading() {
		Game.currentState = new MainState();
	}
	
	
	
	public void checkPercentages() {
		//double total = Game.tundraTally + Game.taigaTally + Game.forestTally + Game.jungleTally+ Game.grasslandTally + Game.mountainTally +Game.oceanTally + Game.desertTally +Game.swampTally;
	}
	
	public void respawn(int x, int y, String folder) {
		teleportHero(x,y,folder);
		
		//teleportChunkRect();
//		int bX = x - (chunkRect.x * CHUNK_SIZE);
//		int bY = y - (chunkRect.y * CHUNK_SIZE);
//		if(Game.level.activeBlocks[bX][bY].absRect.detectCollision(Game.hero.absRect)) {
//			Game.level.activeBlocks[bX][bY].layers[OBJECT_LAYER].deleteMe(bX, bY, activeBlocks);
//		}
		//activeBlocks[40][40].isHighlighted = true;
		
	}
	
	public void teleportHero(int x, int y) {
			Game.hero.absRect.x = x *TILE_SIZE-(Game.hero.absRect.w/4) ;
			Game.hero.absRect.y = y *TILE_SIZE- (Game.hero.absRect.h/2)+1; //+1
	}
	
	public void teleportHero(int x, int y, String folder) {
		if(folder == Game.currentLevel) {
			Game.hero.absRect.x = x *TILE_SIZE-(Game.hero.absRect.w/4) ;
			Game.hero.absRect.y = y *TILE_SIZE- (Game.hero.absRect.h/2)+1; //+1
			Game.saveGame.saveCurrentLevel();
			Game.collectibleController = new CollectibleController();
			Game.switchLevel(false);
			//Game.currentState = new MainState();
		} else {
			Game.currentState = new LoadingState(Game.batch);
			Game.musicController.music.stop();
			Game.hero.stop();
			Game.saveGame.saveCurrentLevel();
			Game.collectibleController = new CollectibleController();
			Game.switchLevel(true);
			Game.hero.absRect.x = x *TILE_SIZE-(Game.hero.absRect.w/4) ;
			Game.hero.absRect.y = y *TILE_SIZE- (Game.hero.absRect.h/2)+1; //+1
		}		
	}
	
//	public void teleportChunkRect() {
//		startChunkX = spawnX /(CHUNK_SIZE*TILE_SIZE) - (CHUNKS_IN_A_ROW /2);
//		startChunkY = spawnY /(CHUNK_SIZE*TILE_SIZE) - (CHUNKS_IN_A_ROW /2);
//		
//		chunkRect = new Rect(startChunkX, startChunkY, CHUNKS_IN_A_ROW-1, CHUNKS_IN_A_ROW-1);
//		for(int i = 0; i < CHUNKS_IN_A_ROW; i++) {
//			for(int j = 0; j < CHUNKS_IN_A_ROW; j++) {
//				loadChunk(CHUNK_SIZE*i, CHUNK_SIZE*j, chunkRect.x + i, chunkRect.y + j);	
//			}	
//		}
//		autoTileNewArea(2, 2, TILES_PER_ROW-2, TILES_PER_ROW-2);
//	}
	
	
	public void loadGame() {
		if(Game.isNewGame) {
			Game.saveGame.saveGame();
			Game.isNewGame = false;
			//NO NEED to saveCurrentChunks();  It's already been done...
		} else {
			SaveGameData saveData = Game.saveGame.saveData();
			Game.hero.hP = saveData.hP;
			Game.hero.maxHP = saveData.maxHP;
			Game.hero.mP = saveData.mP;
			Game.hero.maxMP = saveData.maxMP;
			Game.hero.eP = saveData.eP;
			Game.hero.maxEP = saveData.maxEP;
			for(int x = 0; x < saveData.invBarID.length; x++) {
				int id = saveData.invBarID[x];
				Game.inventory.invBar[x].item = ITEM_HASH.get(id).createNew();
				Game.inventory.invBar[x].item.stackSize = saveData.invBarStackSize[x];
				Game.inventory.invBar[x].item.uses = saveData.invBarUsage[x];
			}
			
			for(int x = saveData.invClothingID.length-1; x >= 0; x--) {
				int id = saveData.invClothingID[x];
				Game.inventory.invClothing[x].item = ITEM_HASH.get(id).createNew();
				Clothing clothingItem = new IronArmor();
				if(Game.inventory.invClothing[x].item.id != 0) {
					clothingItem = (Clothing)Game.inventory.invClothing[x].item;
					Game.hero.addClothingItem(clothingItem);
					
					//Game.hero.sprite.addSprite(clothingItem.animatedSprite);
				} 
				Game.inventory.invClothing[x].item.uses = saveData.invClothingUsage[x];
			}
			Game.hero.calcArmor();
			
			for(int x = 0; x < saveData.invCraftingID.length; x++) {
				int id = saveData.invCraftingID[x];
				Game.inventory.invCrafting[x].item = ITEM_HASH.get(id).createNew();
				Game.inventory.invCrafting[x].item.stackSize = saveData.invCraftingStackSize[x];
			}
				int id = saveData.invCraftedID;
				Game.inventory.invCrafted[0].item = ITEM_HASH.get(id).createNew();
				Game.inventory.invCrafted[0].item.stackSize = saveData.invCraftedStackSize;
			
			for(int x = 0; x < saveData.invBagID.length; x++) {
				id = saveData.invBagID[x];
				Game.inventory.invBag[x].item = ITEM_HASH.get(id).createNew();
				Game.inventory.invBag[x].item.stackSize = saveData.invBagStackSize[x];
				Game.inventory.invBag[x].item.uses = saveData.invBagUsage[x];
			}
			
			
			Game.timeSpentInPreviousSaves = saveData.gameTime;
			
		}
		loadEasternChunks();
		loadWesternChunks();
		loadNorthernChunks();
		loadSouthernChunks();
	}
	
	public int getXFromAbs(int abs) {
		RectInt chunkRect2 = chunkRect.getRectInt();
		return abs - (chunkRect2.x * CHUNK_SIZE);
	}
	
	public int getYFromAbs(int abs) {
		RectInt chunkRect2 = chunkRect.getRectInt();
		return abs - (chunkRect2.y * CHUNK_SIZE);
	}
	
	public void saveCurrentChunks() {
		RectInt chunkRect2 = chunkRect.getRectInt();
		for(int i = 0; i < CHUNKS_IN_A_ROW; i++) {
			for(int j = 0; j < CHUNKS_IN_A_ROW; j++) {
				saveChunk(CHUNK_SIZE*i, CHUNK_SIZE*j, chunkRect2.x + i, chunkRect2.y + j);
			}
		}
	}
	

	
	public void saveChunk(int startX, int startY, int chunkX, int chunkY) {
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
		
		Game.saveGame.saveChunk(chunk, chunkX, chunkY);
	}
	
	public void saveEdgeChunk(int startX, int startY, int chunkX, int chunkY, Block[][] nsewChunks) {
		Block[][] chunk = new Block[CHUNK_SIZE][CHUNK_SIZE];
		int x2 = 0;
		int y2 = 0;
		for(int x = startX; x < startX + CHUNK_SIZE; x++) {
			for(int y = startY; y < startY + CHUNK_SIZE; y++) {
				chunk[x2][y2] = nsewChunks[x][y];
            	y2++;
			}
			x2++; y2 = 0;
		}
		
		Game.saveGame.saveChunk(chunk, chunkX, chunkY);
	}
	
	
	private void highlight32(Rect renderRect, Point offsetPoint) {
		if(highlightedBlock != null) {
			highlightedBlock.isHighlighted = false;
			activeBlocks[highlightedBlockX+1][highlightedBlockY].isHighlighted = false;
			activeBlocks[highlightedBlockX][highlightedBlockY+1].isHighlighted = false;
			activeBlocks[highlightedBlockX+1][highlightedBlockY+1].isHighlighted = false;
		}
		
		
		Rect mRect = Helpers.getMouseRect();
		RectInt mRect2 = mRect.getRectInt();
		Rect itemRange = Game.inventory.invBar[Game.inventory.itemSelected].item.range;
		itemRange.x = Game.hero.sRect.x - (itemRange.w / 2) + (Game.hero.sRect.w /2);
		itemRange.y = Game.hero.sRect.y- (itemRange.h / 2) + (Game.hero.sRect.h/2);
		if(mRect.detectCollision(itemRange)) {
			Game.inventory.invBar[Game.inventory.itemSelected].item.isInRange = true;
			RectInt renderRect2 = renderRect.getRectInt();
			highlightedBlockX = renderRect2.x + (mRect2.x + offsetPoint.x) / TILE_SIZE;
			highlightedBlockY = renderRect2.y + (mRect2.y + offsetPoint.y) / TILE_SIZE;
			highlightedBlock = activeBlocks[highlightedBlockX][highlightedBlockY];
			//This is so that chests know their coordinates...
			tileBeingAttacked.absX = highlightedBlock.getAbsX();
			tileBeingAttacked.absY = highlightedBlock.getAbsY();
			//This is so that furnaces know their activeBlocks...
			tileBeingAttacked.activeBlocksX = highlightedBlockX;
			tileBeingAttacked.activeBlocksY = highlightedBlockY;
			
			//if(tileBeingAttacked != highlightedBlock.getObjectTile()) {
			if(tileBeingAttacked != highlightedBlock.getTopTile()) {	
				
				tileBeingAttacked.currentHp = tileBeingAttacked.maxHp;
				tileBeingAttacked = highlightedBlock.getTopTile();
				
			}
			highlightedBlock.isHighlighted = true;
		} else {
			highlightedBlock = null;
			Game.inventory.invBar[Game.inventory.itemSelected].item.isInRange = false;
			
		}
	}
	
	private void highlightBlock(Rect renderRect, Point offsetPoint) {
		if(Game.inventory.invBar[Game.inventory.itemSelected].item.is32 == true) {
			highlight32(renderRect, offsetPoint);
		} else {
			highlight32(renderRect, offsetPoint);
		}
		
	}
	
	public void update() {
		Time.checkTime();
		currentCollisionHousing = 0;
		Rect renderRect = Helpers.getRenderRect(Game.hero, activeBlocks[0][0]);
		Point offsetPoint = Helpers.getOffsetPoint(Game.hero, activeBlocks[0][0]);
		if(Game.currentState.type == State.MAIN) highlightBlock(renderRect, offsetPoint);
		
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
		RectInt renderRect2 = renderRect.getRectInt();
		for(int x = renderRect2.x; x < renderRect2.x2(); x++) {
			for(int y = renderRect2.y; y < renderRect2.y2(); y++) {
				activeBlocks[x][y].layers[WATER_LAYER].update(x, y);
				activeBlocks[x][y].layers[GRASS_LAYER].update(x, y);
				activeBlocks[x][y].layers[OBJECT_LAYER].update(x, y);
				//This check will kill the loop if a staircase was hit.  (it also runs the function)
				if(activeBlocks[x][y].layers[OBJECT_LAYER].updateStairs(x, y) == true) {
					
				} else {
					return;
				}
				activeBlocks[x][y].layers[TREE_LEFT_0].update(x, y);
				activeBlocks[x][y].layers[ABOVE_LAYER_2].update(x, y);
				//activeBlocks[x][y].layers[TREE_RIGHT_1].update(x, y);
				//activeBlocks[x][y].layers[TREE_RIGHT_0].update(x, y);
				activeBlocks[x][y].layers[TREE_LEFT_2].update(x, y);
				activeBlocks[x][y].layers[TREE_RIGHT_2].update(x, y);
			}
		}
		if(currentCollisionHousing >0) {
			for(int x = renderRect2.x; x < renderRect2.x2(); x++) {
				for(int y = renderRect2.y; y < renderRect2.y2(); y++) {
					activeBlocks[x][y].layers[GRASS_LAYER].update(x, y);	
					activeBlocks[x][y].layers[ABOVE_LAYER_4].update(x, y);
				}
			}
		}
		
		for(int i = 0; i < Game.level.activeBlocks.length; i++) {
			for(int j = 0; j < Game.level.activeBlocks.length; j++) {
				if(Game.level.activeBlocks[i][j].layers[OBJECT_LAYER].isLightSource) {
					Game.renderSystem.lightTiles.add(Game.level.activeBlocks[i][j]);
				}
			}
		}
		
	}
		
	public void getEasternChunks() {
		//Game.currentState = new LoadingState(Game.batch);
		try {
			loadingThread.join();
			savingThread.join();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		//Convert activeBlocks to westernChunks
		for(int x = 0; x < CHUNK_SIZE; x++) {
			for(int y = 0; y < activeBlocks.length; y++) {
				westernChunks[x][y] = activeBlocks[x][y];
			}
		}
		shiftActiveBlocksArray(LEFT);
		
		for(int x = activeBlocks.length-CHUNK_SIZE; x <= activeBlocks.length-1; x++) {
			activeBlocks[x] = easternChunks[x-(CHUNK_SIZE*4)].clone();
		}
		chunkRect.x++;
		
		autoTileNewArea(1, 1, TILES_PER_ROW-1, TILES_PER_ROW-1);
		
		savingThread = new Thread(new Runnable() {
			RectInt chunkRect2 = chunkRect.getRectInt();
			@Override
			public void run() {
				for(int i = 0; i < CHUNKS_IN_A_ROW; i++) {
					saveEdgeChunk(0, CHUNK_SIZE*i, chunkRect2.x-1, chunkRect2.y + i, westernChunks);
				}
			}
		});	
		savingThread.start();
		loadingThread = new Thread(new Runnable() {
			@Override
			public void run() {

				shiftLoadChunksEast();
			}
		});	
		loadingThread.start();
		
	}
	
	public void getWesternChunks() {
		try {
			loadingThread.join();
			savingThread.join();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		
		
		for(int x = activeBlocks.length-CHUNK_SIZE; x <= activeBlocks.length-1; x++) {
			for(int y = 0; y < activeBlocks.length; y++) {
				easternChunks[x-(CHUNK_SIZE*4)][y] = activeBlocks[x][y];
			}
		}
		shiftActiveBlocksArray(RIGHT);
			
		for(int x = 0; x < CHUNK_SIZE; x++) {
			for(int y = 0; y < activeBlocks.length; y++) {
				activeBlocks[x][y] = westernChunks[x][y];
			}
		}
		chunkRect.x--;
		autoTileNewArea(1, 1, TILES_PER_ROW-1, TILES_PER_ROW-1);

		savingThread = new Thread(new Runnable() {

			RectInt chunkRect2 = chunkRect.getRectInt();
			@Override
			public void run() {
				for(int i = 0; i < CHUNKS_IN_A_ROW; i++) {
					saveEdgeChunk(0, CHUNK_SIZE*i, chunkRect2.x2()+1, chunkRect2.y + i, easternChunks);
				}
			}
		});	
		savingThread.start();
		loadingThread = new Thread(new Runnable() {
			@Override
			public void run() {
				shiftLoadChunksWest();
			}
		});	
		loadingThread.start();
			
		//NEW CODE END			
	}
	
	
	public void getNorthernChunks() {
		
		try {
			savingThread.join();
			loadingThread.join();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		
		for(int x = 0; x < activeBlocks.length; x++) {
			for(int y = activeBlocks.length-CHUNK_SIZE; y < activeBlocks.length; y++) {
				southernChunks[x][y-(CHUNK_SIZE*4)] = activeBlocks[x][y];
			}
		}
		shiftActiveBlocksArray(DOWN);
		
		for(int x = 0; x < activeBlocks.length; x++) {
			for(int y = 0; y < CHUNK_SIZE; y++) {
				activeBlocks[x][y] = northernChunks[x][y];
			}
		}
		chunkRect.y--;
		autoTileNewArea(1, 1, TILES_PER_ROW-1, TILES_PER_ROW-1);
		
		savingThread = new Thread(new Runnable() {
			RectInt chunkRect2 = chunkRect.getRectInt();
			@Override
			public void run() {
				for(int i = 0; i < CHUNKS_IN_A_ROW; i++) {
					saveEdgeChunk(CHUNK_SIZE*i, 0, chunkRect2.x + i, chunkRect2.y2()+1, southernChunks);
				}
			}
		});	
		savingThread.start();
		loadingThread = new Thread(new Runnable() {
			@Override
			public void run() {
				shiftLoadChunksNorth();
			}
		});	
		loadingThread.start();
		
	}
	
	
	
	public void getSouthernChunks() {	
		try {
			savingThread.join();
			loadingThread.join();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		//Convert activeBlocks to northernChunks
		for(int x = 0; x < activeBlocks.length; x++) {
			for(int y = 0; y < CHUNK_SIZE; y++) {
				northernChunks[x][y] = activeBlocks[x][y] ;
			}
		}
		shiftActiveBlocksArray(UP);
		for(int x = 0; x < activeBlocks.length; x++) {
			for(int y = activeBlocks.length-CHUNK_SIZE; y < activeBlocks.length; y++) {
				activeBlocks[x][y] = southernChunks[x][y-(CHUNK_SIZE*4)];
			}
		}
		chunkRect.y++;
		autoTileNewArea(1, 1, TILES_PER_ROW-1, TILES_PER_ROW-1);
		
		savingThread = new Thread(new Runnable() {
			RectInt chunkRect2 = chunkRect.getRectInt();
			@Override
			public void run() {
				for(int i = 0; i < CHUNKS_IN_A_ROW; i++) {
					saveEdgeChunk(CHUNK_SIZE*i, 0, chunkRect2.x + i, chunkRect2.y-1, northernChunks);
				}
			}
		});	
		savingThread.start();
		loadingThread = new Thread(new Runnable() {
			@Override
			public void run() {
				shiftLoadChunksSouth();
			}
		});	
		loadingThread.start();
	}
	
	public void shiftLoadChunksEast() {
		//shift northernChunks Left
		for(int x = CHUNK_SIZE; x < activeBlocks.length; x++) {
			northernChunks[x-CHUNK_SIZE] = northernChunks[x].clone();
		}
		//shift sourthernChunks Left
		for(int x = CHUNK_SIZE; x < activeBlocks.length; x++) {
			southernChunks[x-CHUNK_SIZE] = southernChunks[x].clone();
		}
		//load northern Chunk
		RectInt chunkRect2 = chunkRect.getRectInt();
		loadChunk(CHUNK_SIZE*4,0, chunkRect2.x+4, chunkRect2.y-1, northernChunks);
		//load southern Chunk
		loadChunk(CHUNK_SIZE*4,0, chunkRect2.x+4, chunkRect2.y2()+1, southernChunks);
		loadEasternChunks();
	}
	
	public void shiftLoadChunksWest() {
		//shift northernChunks Left
		for(int x = activeBlocks.length-1-CHUNK_SIZE; x >= 0; x--) {
			northernChunks[x+CHUNK_SIZE] = northernChunks[x].clone();
		}
		//shift sourthernChunks Left
		for(int x = activeBlocks.length-1-CHUNK_SIZE; x >= 0; x--) {
			southernChunks[x+CHUNK_SIZE] = southernChunks[x].clone();
		}
		RectInt chunkRect2 = chunkRect.getRectInt();
		//load northern Chunk
		loadChunk(CHUNK_SIZE*0,0, chunkRect2.x+0, chunkRect2.y-1, northernChunks);
		//load southern Chunk
		loadChunk(CHUNK_SIZE*0,0, chunkRect2.x+0, chunkRect2.y2()+1, southernChunks);
		loadWesternChunks();
	}
	
	public void shiftLoadChunksNorth() {
		//shift easternChunks Left
		for(int y = 0; y < CHUNK_SIZE; y++) {
			System.arraycopy(easternChunks[y], 0, easternChunks[y], CHUNK_SIZE, activeBlocks.length-CHUNK_SIZE);
		}
		//shift southernChunks Left
		for(int y = 0; y < CHUNK_SIZE; y++) {
			System.arraycopy(westernChunks[y], 0, westernChunks[y], CHUNK_SIZE, activeBlocks.length-CHUNK_SIZE);
		}
		RectInt chunkRect2 = chunkRect.getRectInt();
		//load eastern Chunk
		loadChunk(0, CHUNK_SIZE*0, chunkRect2.x2()+1, chunkRect2.y + 0, easternChunks);
		//load western Chunk
		loadChunk(0, CHUNK_SIZE*0, chunkRect2.x-1, chunkRect2.y + 0, westernChunks);
		loadNorthernChunks();
	}
	
	public void shiftLoadChunksSouth() {
		//shift easternChunks Left
		for(int y = 0; y < CHUNK_SIZE; y++) {
			System.arraycopy(easternChunks[y], CHUNK_SIZE, easternChunks[y], 0, activeBlocks.length-CHUNK_SIZE);
		}
		//shift southernChunks Left
		for(int y = 0; y < CHUNK_SIZE; y++) {
			System.arraycopy(westernChunks[y], CHUNK_SIZE, westernChunks[y], 0, activeBlocks.length-CHUNK_SIZE);
		}
		RectInt chunkRect2 = chunkRect.getRectInt();
		//load eastern Chunk
		loadChunk(0, CHUNK_SIZE*4, chunkRect2.x2()+1, chunkRect2.y + 4, easternChunks);
		//load western Chunk
		loadChunk(0, CHUNK_SIZE*4, chunkRect2.x-1, chunkRect2.y + 4, westernChunks);
		loadSouthernChunks();
	}
	
	public void loadSouthernChunks() {
		RectInt chunkRect2 = chunkRect.getRectInt();
		for(int i = 0; i < CHUNKS_IN_A_ROW; i++) {
			loadChunk(CHUNK_SIZE*i,0, chunkRect2.x+i, chunkRect2.y2()+1, southernChunks);
		}
	}
	
	public void loadNorthernChunks() {
		RectInt chunkRect2 = chunkRect.getRectInt();
		for(int i = 0; i < CHUNKS_IN_A_ROW; i++) {
			loadChunk(CHUNK_SIZE*i,0, chunkRect2.x+i, chunkRect2.y-1, northernChunks);
		}
	}
	
	public void loadWesternChunks() {
		RectInt chunkRect2 = chunkRect.getRectInt();
		for(int i = 0; i < CHUNKS_IN_A_ROW; i++) {
			loadChunk(0, CHUNK_SIZE*i, chunkRect2.x-1, chunkRect2.y + i, westernChunks);
		}
	}
	
	public void loadEasternChunks() {	
		RectInt chunkRect2 = chunkRect.getRectInt();
		for(int i = 0; i < CHUNKS_IN_A_ROW; i++) {
			loadChunk(0, CHUNK_SIZE*i, chunkRect2.x2()+1, chunkRect2.y + i, easternChunks);
		}
	}
	
	public void loadChunk(int startX, int startY, int chunkX, int chunkY, Block[][] nsewChunks) {
		//64,0,
		
		isLoading = true;
		File f = new File(Game.locOfSavedGame + CHUNKS_FOLDER + Game.currentLevel + chunkX + "-" + chunkY + ".sv");
		if(!f.exists()) { 
			createNewChunk(startX,startY,chunkX,chunkY);
		}
			
		Block[][] chunk = Game.saveGame.loadChunk(chunkX, chunkY);
		int i = 0; int j = 0;
		for(int x = startX; x < startX+CHUNK_SIZE; x++) {
			for(int y = startY; y < startY+CHUNK_SIZE; y++) {
				nsewChunks[x][y] = chunk[i][j];
				
				j++;
			}
			i++; j =0;
		}
		
		isLoading = false;
		
	}
	
	public void loadInitialChunks(int startX, int startY, int chunkX, int chunkY) {
		isLoading = true;
		File f = new File(Game.locOfSavedGame + CHUNKS_FOLDER + Game.currentLevel +  chunkX + "-" + chunkY + ".sv");
		if(!f.exists()) { 
			createNewChunk(startX,startY,chunkX,chunkY);
		}
		
		Block[][] chunk = Game.saveGame.loadChunk(chunkX, chunkY);
		int i = 0; int j = 0;
		for(int x = startX; x < startX+CHUNK_SIZE; x++) {
			for(int y = startY; y < startY+CHUNK_SIZE; y++) {
				activeBlocks[x][y] = chunk[i][j];
				j++;
			}
			i++; j =0;
		}

		
		isLoading = false;
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
	
	public void createNewBlock(int i, int j, int chunkX, int chunkY, int x, int y, ChunkGenerator chunkGenerator) {}

	
	public Block autoTile(Block[][] blocks, Block block, int x, int y) {
		for(int i = LIGHT_DIRT_LAYER; i < blocks[x][y].layers.length; i++) {
			if(block.layers[i].isAutoTileable) {
				int left = 0, topLeft = 0, top = 0, topRight = 0, right = 0, bottomRight = 0, bottom = 0, bottomLeft = 0;
				if(blocks[x-1][y-1].layers[i].autoTileID == block.layers[i].autoTileID) topLeft = 1;
				if(blocks[x][y-1].layers[i].autoTileID == block.layers[i].autoTileID) top = 2;
				if(blocks[x+1][y-1].layers[i].autoTileID == block.layers[i].autoTileID) topRight = 4;
				if(blocks[x-1][y].layers[i].autoTileID == block.layers[i].autoTileID) left = 8;
				if(blocks[x+1][y].layers[i].autoTileID == block.layers[i].autoTileID) right = 16;
				if(blocks[x-1][y+1].layers[i].autoTileID == block.layers[i].autoTileID) bottomLeft = 32;
				if(blocks[x][y+1].layers[i].autoTileID == block.layers[i].autoTileID) bottom = 64;
				if(blocks[x+1][y+1].layers[i].autoTileID == block.layers[i].autoTileID) bottomRight = 128;
				
				block.layers[i].topLeftAutoTile = TOP_LEFT_AUTO_TILE_HASH.get(left + topLeft + top);
				block.layers[i].topRightAutoTile = TOP_RIGHT_AUTO_TILE_HASH.get(right + topRight + top);
				block.layers[i].bottomLeftAutoTile = BOTTOM_LEFT_AUTO_TILE_HASH.get(left + bottomLeft + bottom);
				block.layers[i].bottomRightAutoTile = BOTTOM_RIGHT_AUTO_TILE_HASH.get(right + bottomRight + bottom);
				
				
				if(block.layers[i].topLeftAutoTile != MIDDLE_TILE || block.layers[i].topRightAutoTile != MIDDLE_TILE ||
				   block.layers[i].bottomLeftAutoTile != MIDDLE_TILE || block.layers[i].bottomRightAutoTile != MIDDLE_TILE) {
					block.layers[i].isMiddleTile = false;
				} else {
					block.layers[i].isMiddleTile = true;
				}
			}
		}
		return block;
	}
	
	
	
	public void createNewChunk(int startX, int startY, int chunkX, int chunkY) {}

	
	public void autoTileNewArea(int startX, int startY, int endX, int endY) {
		for(int x = startX; x < endX; x++) {
			for(int y = startY; y < endY; y++) {
				activeBlocks[x][y] = autoTile(activeBlocks, activeBlocks[x][y], x, y);
			}
		}
	}
	
	public void autoTileHighlightedBlock() {
		autoTile(activeBlocks, activeBlocks[highlightedBlockX][highlightedBlockY], highlightedBlockX, highlightedBlockY);
		
		autoTile(activeBlocks, activeBlocks[highlightedBlockX-1][highlightedBlockY-1], highlightedBlockX-1, highlightedBlockY-1);
		autoTile(activeBlocks, activeBlocks[highlightedBlockX][highlightedBlockY-1], highlightedBlockX, highlightedBlockY-1);
		autoTile(activeBlocks, activeBlocks[highlightedBlockX+1][highlightedBlockY-1], highlightedBlockX+1, highlightedBlockY-1);
		autoTile(activeBlocks, activeBlocks[highlightedBlockX-1][highlightedBlockY], highlightedBlockX-1, highlightedBlockY);
		autoTile(activeBlocks, activeBlocks[highlightedBlockX+1][highlightedBlockY], highlightedBlockX+1, highlightedBlockY);
		autoTile(activeBlocks, activeBlocks[highlightedBlockX-1][highlightedBlockY+1], highlightedBlockX-1, highlightedBlockY+1);
		autoTile(activeBlocks, activeBlocks[highlightedBlockX][highlightedBlockY+1], highlightedBlockX, highlightedBlockY+1);
		autoTile(activeBlocks, activeBlocks[highlightedBlockX+1][highlightedBlockY+1], highlightedBlockX+1, highlightedBlockY+1);
	}
	
	public void autoTileBlock(int x, int y) {
		autoTile(activeBlocks, activeBlocks[x][y], x, y);
		
		autoTile(activeBlocks, activeBlocks[x-1][y-1], x-1, y-1);
		autoTile(activeBlocks, activeBlocks[x][y-1], x, y-1);
		autoTile(activeBlocks, activeBlocks[x+1][y-1], x+1, y-1);
		autoTile(activeBlocks, activeBlocks[x-1][y], x-1, y);
		autoTile(activeBlocks, activeBlocks[x+1][y], x+1, y);
		autoTile(activeBlocks, activeBlocks[x-1][y+1], x-1, y+1);
		autoTile(activeBlocks, activeBlocks[x][y+1], x, y+1);
		autoTile(activeBlocks, activeBlocks[x+1][y+1], x+1, y+1);
	}
	
	public int getAbsChunkX(int x) {
		return (x / TILE_SIZE) / CHUNK_SIZE;
		
	}
	
	public int getAbsChunkY(int y) {
		return (y / TILE_SIZE) / CHUNK_SIZE;
	}
	
	public void handleCollisions() {
		
	}
}
