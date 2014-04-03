package com.madbros.adventurecraft;

import static com.madbros.adventurecraft.Constants.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import com.madbros.adventurecraft.GameObjects.Hero;
import com.madbros.adventurecraft.Items.Clothing;
import com.madbros.adventurecraft.Items.IronArmor;
import com.madbros.adventurecraft.LevelTypes.ChunkGenerator;
import com.madbros.adventurecraft.LevelTypes.Overworld;
import com.madbros.adventurecraft.LevelTypes.Underground1;
import com.madbros.adventurecraft.LevelTypes.FractalTypes.BasicNoise;
import com.madbros.adventurecraft.TileTypes.*;
import com.madbros.adventurecraft.Utils.Helpers;
import com.madbros.adventurecraft.Utils.Point;
import com.madbros.adventurecraft.Utils.Rect;

public class Level {
	public Cell[][] cells;
	public Cell[] potentialCollisionCells;	//resets to empty every frame and includes only cells that something has moved in
	
	public Block[][] activeBlocks;
	public Block[][] currentChunk;
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
	public int spawnX = CHUNKS_LENGTH_TOTAL * CHUNK_SIZE * TILE_SIZE /2 - CHARACTER_SIZE/2;
	public int spawnY = CHUNKS_LENGTH_TOTAL * CHUNK_SIZE * TILE_SIZE /2 - CHARACTER_SIZE/2;
	public int masterSpawnX = spawnX;
	public int masterSpawnY = spawnY;
//	public int spawnX = TILES_PER_ROW*TILE_SIZE/2 - CHARACTER_SIZE/2+900;
	//public int spawnY = TILES_PER_ROW*TILE_SIZE/2 - CHARACTER_SIZE/2+700;
	
	int startChunkX = spawnX /(CHUNK_SIZE*TILE_SIZE) - (CHUNKS_IN_A_ROW /2);
	int startChunkY = spawnY /(CHUNK_SIZE*TILE_SIZE) - (CHUNKS_IN_A_ROW /2);
	public Rect chunkRect = new Rect(startChunkX, startChunkY, CHUNKS_IN_A_ROW-1, CHUNKS_IN_A_ROW-1);
	public int offsetX = 0;	//offset gets set at the start of level if there is one
	public int offsetY = 0;
	public Rect renderRect = new Rect(
			spawnX / TILE_SIZE +1-(CHUNK_SIZE*chunkRect.x) - (int)Math.ceil(Game.getCenterScreenX() * 1.0 / TILE_SIZE),
			spawnY / TILE_SIZE +1-(CHUNK_SIZE*chunkRect.y) - (int)Math.ceil(Game.getCenterScreenY() * 1.0 / TILE_SIZE),
			(int)Math.ceil(INITIAL_WINDOW_WIDTH * 1.0 / TILE_SIZE) + RENDER_MARGIN,
			(int)Math.ceil(INITIAL_WINDOW_HEIGHT * 1.0 / TILE_SIZE) + RENDER_MARGIN);
	
//	
	//private long rgenseed = System.currentTimeMillis();
	public long rgenseed = 1; // 6 is swamp, 2 is hole+swamp, 19 is Grass and Snow (plains)
	public PerlinGenerator perlin = new PerlinGenerator((int) rgenseed);
	public Random rand = new Random(rgenseed);
	public int randInt1 = rand.nextInt();
	public int randInt2 = rand.nextInt();
	public PerlinGenerator perlin2 = new PerlinGenerator(randInt1);
	public PerlinGenerator perlin3 = new PerlinGenerator(randInt2);
	
	public BasicNoise noise1 = new BasicNoise(898456);
	public BasicNoise noise2 = new BasicNoise(randInt1);
	public BasicNoise noise3 = new BasicNoise(randInt2);
	//public PerlinGenerator perlin2 = new PerlinGenerator((int) rgenseed);
	//public PerlinGenerator perlin3 = new PerlinGenerator((int) rgenseed);
	public int size = 1000;
	public long time;
	public long gameStartTime;
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
		initialize();
	}
	
	public void initialize() {
		Game.gameStartTime = Time.getTime();
		if(Game.isNewGame) {
			if(Game.getCenterScreenX() % TILE_SIZE > 0) offsetX = TILE_SIZE - Game.getCenterScreenX() % TILE_SIZE;
			if(Game.getCenterScreenY() % TILE_SIZE > 0) offsetY = TILE_SIZE - Game.getCenterScreenY() % TILE_SIZE;
		} else {
			SaveGameData saveData = Game.saveGame.saveData();
			//Game.currentLevel = saveData.currentLevel;
			spawnX = saveData.heroX;
			spawnY = saveData.heroY;
			masterSpawnX = spawnX;
			masterSpawnY = spawnY;
			startChunkX = spawnX /(CHUNK_SIZE*TILE_SIZE) - (CHUNKS_IN_A_ROW /2);
			startChunkY = spawnY /(CHUNK_SIZE*TILE_SIZE) - (CHUNKS_IN_A_ROW /2);
			chunkRect = new Rect(startChunkX, startChunkY, CHUNKS_IN_A_ROW-1, CHUNKS_IN_A_ROW-1);
			
			offsetX = saveData.offsetX;
			offsetY = saveData.offsetY;
			//System.out.println("SPAWNLOAD: "+spawnX+"-"+spawnY);
			//System.out.println("OFFSETLOAD: "+offsetX+"-"+offsetY);
			int renderRectX;
			if(offsetX > 15) {
				renderRectX = (spawnX+(offsetX)) / TILE_SIZE -(CHUNK_SIZE*chunkRect.x) - (int)Math.ceil(Game.getCenterScreenX() * 1.0 / TILE_SIZE);
			} else {
				renderRectX = (spawnX+(offsetX)) / TILE_SIZE +1-(CHUNK_SIZE*chunkRect.x) - (int)Math.ceil(Game.getCenterScreenX() * 1.0 / TILE_SIZE);
			}
			renderRect = new Rect(
					renderRectX,
					(spawnY+(TILE_SIZE -offsetY)) / TILE_SIZE +1-(CHUNK_SIZE*chunkRect.y) - (int)Math.ceil(Game.getCenterScreenY() * 1.0 / TILE_SIZE),
					(int)Math.ceil(INITIAL_WINDOW_WIDTH * 1.0 / TILE_SIZE) + RENDER_MARGIN,
					(int)Math.ceil(INITIAL_WINDOW_HEIGHT * 1.0 / TILE_SIZE) + RENDER_MARGIN);
			
		}
		
		
		
		activeBlocks = new Block[TILES_PER_ROW][TILES_PER_ROW];
		currentChunk = new Block[CHUNK_SIZE][CHUNK_SIZE];
		
		File f = new File(Game.locOfSavedGame + CHUNKS_FOLDER + Game.currentLevel);
		if(!f.exists()) {
			f.mkdir();
			for(int i = 0; i < CHUNKS_LENGTH_TOTAL; i++) {
				for(int j = 0; j < CHUNKS_LENGTH_TOTAL; j++) {
					//System.out.println("Chunk being created..."+i+"-"+j);
					createNewChunk(CHUNK_SIZE*i, CHUNK_SIZE*j, i, j);
				}
			}
			
		} 
		
		
		
		for(int i = 0; i < CHUNKS_IN_A_ROW; i++) {
			for(int j = 0; j < CHUNKS_IN_A_ROW; j++) {
				loadChunk(CHUNK_SIZE*i, CHUNK_SIZE*j, chunkRect.x + i, chunkRect.y + j);
				
			}
			
		}
		gameStartTime = Time.getTime();		

		autoTileNewArea(2, 2, TILES_PER_ROW-2, TILES_PER_ROW-2);
		
	}
	
	public void checkPercentages() {
		double total = Game.tundraTally + Game.taigaTally + Game.forestTally + Game.jungleTally+ Game.grasslandTally + Game.mountainTally +Game.oceanTally + Game.desertTally +Game.swampTally;
		System.out.println(Game.tundraTally);
		System.out.println(Game.tundraTally /total *100);
		System.out.println("Tundra = " + Game.tundraTally /total *100+"%");
		System.out.println("Taiga = " + Game.taigaTally /total *100+"%");
		System.out.println("Forest = " + Game.forestTally /total *100+"%");
		System.out.println("Jungle = " + Game.jungleTally /total *100+"%");
		System.out.println("Grassland= " + Game.grasslandTally /total *100+"%");
		System.out.println("Mountain = " + Game.mountainTally /total *100+"%");
		System.out.println("Ocean = " + Game.oceanTally /total *100+"%");
		System.out.println("Desert = " + Game.desertTally /total *100+"%");
		System.out.println("Swamp = " + Game.swampTally /total *100+"%");
	}
	
	
	public void teleportHero(int x, int y) {
		//THIS FUNCTION IS INCOMPLETE (in particular it doesn't address switching between levels)
		Game.hero.absRect.x = x *TILE_SIZE;
		Game.hero.absRect.y = y *TILE_SIZE;
		
		offsetX = 15;
		offsetY = 16;
		Game.saveGame.saveGame();
		saveCurrentChunks();
		Game.level = new Level();
		Game.hero = new Hero();
		Game.hero.absRect.x = Game.hero.absRect.x + 15;
		//offsetX = 15;
		
		
		
	}
	
	
	public void loadGame() {
		if(Game.isNewGame) {
			Game.saveGame.saveGame();
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
			}
			
			for(int x = saveData.invClothingID.length-1; x >= 0; x--) {
				int id = saveData.invClothingID[x];
				Game.inventory.invClothing[x].item = ITEM_HASH.get(id).createNew();
				Clothing clothingItem = new IronArmor();
				if(Game.inventory.invClothing[x].item.id != 0) {
					clothingItem = (Clothing)Game.inventory.invClothing[x].item;
					Game.hero.sprite.addSprite(clothingItem.animatedSprite);
				}
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
			}
			Game.timeSpentInPreviousSaves = saveData.gameTime;
		}
	}
	
	public int getXFromAbs(int abs) {
		return abs - (chunkRect.x * CHUNK_SIZE);
	}
	
	public int getYFromAbs(int abs) {
		return abs - (chunkRect.y * CHUNK_SIZE);
	}
	
	public void saveCurrentChunks() {
		for(int i = 0; i < CHUNKS_IN_A_ROW; i++) {
			for(int j = 0; j < CHUNKS_IN_A_ROW; j++) {
				saveChunk(CHUNK_SIZE*i, CHUNK_SIZE*j, chunkRect.x + i, chunkRect.y + j);
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
	
	private void highlight64() {
		if(highlightedBlock != null) {
			highlightedBlock.isHighlighted = false;
			activeBlocks[highlightedBlockX+1][highlightedBlockY].isHighlighted = false;
			activeBlocks[highlightedBlockX][highlightedBlockY+1].isHighlighted = false;
			activeBlocks[highlightedBlockX+1][highlightedBlockY+1].isHighlighted = false;
		}
		
		Rect mRect = Helpers.getMouseRect();
		Rect itemRange = Game.inventory.invBar[Game.inventory.itemSelected].item.range;
		itemRange.x = Game.hero.sRect.x - (itemRange.w / 2) + (Game.hero.sRect.w /2);
		itemRange.y = Game.hero.sRect.y- (itemRange.h / 2) + (Game.hero.sRect.h/2);
		if(mRect.detectCollision(itemRange)) {
			Game.inventory.invBar[Game.inventory.itemSelected].item.isInRange = true;
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
			highlightedBlock2 = activeBlocks[highlightedBlockX+1][highlightedBlockY];
			highlightedBlock3 = activeBlocks[highlightedBlockX][highlightedBlockY+1];
			highlightedBlock4 = activeBlocks[highlightedBlockX+1][highlightedBlockY+1];
		
			Game.inventory.invBar[Game.inventory.itemSelected].item.getTopTile();
//			if(tileBeingAttacked != highlightedBlock.getTopTile()) {
//				tileBeingAttacked.currentHp = tileBeingAttacked.maxHp;
//				tileBeingAttacked = highlightedBlock.getTopTile();
//				tileBeingAttacked2 = highlightedBlock2.getTopTile();
//				tileBeingAttacked3 = highlightedBlock3.getTopTile();
//				tileBeingAttacked4 = highlightedBlock4.getTopTile();
//			}
			
//			if(tileBeingAttacked != highlightedBlock.getDiggableTopTile()) {
//				tileBeingAttacked.currentHp = tileBeingAttacked.maxHp;
//				tileBeingAttacked = highlightedBlock.getDiggableTopTile();
//				tileBeingAttacked2 = highlightedBlock2.getDiggableTopTile();
//				tileBeingAttacked3 = highlightedBlock3.getDiggableTopTile();
//				tileBeingAttacked4 = highlightedBlock4.getDiggableTopTile();
//			}
			
			highlightedBlock.isHighlighted = true;
			activeBlocks[highlightedBlockX+1][highlightedBlockY].isHighlighted = true;
			activeBlocks[highlightedBlockX][highlightedBlockY+1].isHighlighted = true;
			activeBlocks[highlightedBlockX+1][highlightedBlockY+1].isHighlighted = true;
		} else {
			highlightedBlock = null;
			highlightedBlock2 = null;
			highlightedBlock3 = null;
			highlightedBlock4 = null;
			Game.inventory.invBar[Game.inventory.itemSelected].item.isInRange = false;
		}
		
	}
	
	private void highlight32() {
		if(highlightedBlock != null) {
			highlightedBlock.isHighlighted = false;
			activeBlocks[highlightedBlockX+1][highlightedBlockY].isHighlighted = false;
			activeBlocks[highlightedBlockX][highlightedBlockY+1].isHighlighted = false;
			activeBlocks[highlightedBlockX+1][highlightedBlockY+1].isHighlighted = false;
		}
		
		
		Rect mRect = Helpers.getMouseRect();
		Rect itemRange = Game.inventory.invBar[Game.inventory.itemSelected].item.range;
		itemRange.x = Game.hero.sRect.x - (itemRange.w / 2) + (Game.hero.sRect.w /2);
		itemRange.y = Game.hero.sRect.y- (itemRange.h / 2) + (Game.hero.sRect.h/2);
		if(mRect.detectCollision(itemRange)) {
			Game.inventory.invBar[Game.inventory.itemSelected].item.isInRange = true;
			highlightedBlockX = renderRect.x + (mRect.x + offsetX) / TILE_SIZE;
			highlightedBlockY = renderRect.y + (mRect.y + offsetY) / TILE_SIZE;
			
			highlightedBlock = activeBlocks[highlightedBlockX][highlightedBlockY];
			//This is so that chests know their coordinates...
			tileBeingAttacked.absX = highlightedBlock.getAbsX();
			tileBeingAttacked.absY = highlightedBlock.getAbsY();
			//This is so that furnaces know their activeBlocks...
			tileBeingAttacked.activeBlocksX = highlightedBlockX;
			tileBeingAttacked.activeBlocksY = highlightedBlockY;
			
			if(tileBeingAttacked != highlightedBlock.getObjectTile()) {
				
				
				tileBeingAttacked.currentHp = tileBeingAttacked.maxHp;
				tileBeingAttacked = highlightedBlock.getObjectTile();
				
			}
			highlightedBlock.isHighlighted = true;
		} else {
			highlightedBlock = null;
			Game.inventory.invBar[Game.inventory.itemSelected].item.isInRange = false;
			
		}
	}
	
	private void highlightBlock() {
		if(Game.inventory.invBar[Game.inventory.itemSelected].item.is32 == true) {
			highlight32();
		} else {
			highlight64();
		}
		
	}
	
	public void update() {
		Time.checkTime();
		
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
				activeBlocks[x][y].layers[TREE_LEFT_0].update(x, y);
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
			saveChunk(CHUNK_SIZE*i, TILES_PER_ROW-CHUNK_SIZE, chunkRect.x + i, chunkRect.y2());
		}
		
		renderRect.y += CHUNK_SIZE;
		
		shiftActiveBlocksArray(DOWN);
		chunkRect.y--;
		
		for(int i = 0; i < CHUNKS_IN_A_ROW; i++) {
			loadChunk(CHUNK_SIZE*i, 0, chunkRect.x + i, chunkRect.y);
		}
		
		autoTileNewArea(2, 2, TILES_PER_ROW-2, TILES_PER_ROW-2);
		
		
	}
	
	public void getSouthernChunks() {
		for(int i = 0; i < CHUNKS_IN_A_ROW; i++) {
			saveChunk(CHUNK_SIZE*i, 0, chunkRect.x + i, chunkRect.y);
		}
		
		renderRect.y -= CHUNK_SIZE;
		
		shiftActiveBlocksArray(UP);
		chunkRect.y++;
		
		for(int i = 0; i < CHUNKS_IN_A_ROW; i++) {
			loadChunk(CHUNK_SIZE*i, TILES_PER_ROW-CHUNK_SIZE, chunkRect.x + i, chunkRect.y2());
		}
		
		autoTileNewArea(2, 2, TILES_PER_ROW-2, TILES_PER_ROW-2);
		
		
	}
	
	public void getEasternChunks() {
		for(int i = 0; i < CHUNKS_IN_A_ROW; i++) {
			saveChunk(0, CHUNK_SIZE*i, chunkRect.x, chunkRect.y + i);
		}
		
		renderRect.x -= CHUNK_SIZE;
		
		shiftActiveBlocksArray(LEFT);
		chunkRect.x++;
		
		for(int i = 0; i < CHUNKS_IN_A_ROW; i++) {
			loadChunk(TILES_PER_ROW-CHUNK_SIZE, CHUNK_SIZE*i, chunkRect.x2(), chunkRect.y + i);
		}
		autoTileNewArea(2, 2, TILES_PER_ROW-2, TILES_PER_ROW-2);
	
	}

	public void getWesternChunks() {
		for(int i = 0; i < CHUNKS_IN_A_ROW; i++) {
			saveChunk(TILES_PER_ROW-CHUNK_SIZE, CHUNK_SIZE*i, chunkRect.x2(), chunkRect.y + i);
		}
		
		renderRect.x += CHUNK_SIZE;
		
		shiftActiveBlocksArray(RIGHT);
		chunkRect.x--;
		
		for(int i = 0; i < CHUNKS_IN_A_ROW; i++) {
			loadChunk(0, CHUNK_SIZE*i, chunkRect.x, chunkRect.y + i);
		}
		
		autoTileNewArea(2, 2, TILES_PER_ROW-2, TILES_PER_ROW-2);
		
		
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
				if(blocks[x-2][y-2].layers[i].autoTileID == block.layers[i].autoTileID) topLeft = 1;
				if(blocks[x][y-2].layers[i].autoTileID == block.layers[i].autoTileID) top = 2;
				if(blocks[x+2][y-2].layers[i].autoTileID == block.layers[i].autoTileID) topRight = 4;
				if(blocks[x-2][y].layers[i].autoTileID == block.layers[i].autoTileID) left = 8;
				if(blocks[x+2][y].layers[i].autoTileID == block.layers[i].autoTileID) right = 16;
				if(blocks[x-2][y+2].layers[i].autoTileID == block.layers[i].autoTileID) bottomLeft = 32;
				if(blocks[x][y+2].layers[i].autoTileID == block.layers[i].autoTileID) bottom = 64;
				if(blocks[x+2][y+2].layers[i].autoTileID == block.layers[i].autoTileID) bottomRight = 128;
				
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
	
	public void loadChunk(int startX, int startY, int chunkX, int chunkY) {
		isLoading = true;
		
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
	
	public void createNewChunk(int startX, int startY, int chunkX, int chunkY) {}

	
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
	
	public void autoTileBlock(int x, int y) {
		autoTile(activeBlocks, activeBlocks[x][y], x, y);
		
		autoTile(activeBlocks, activeBlocks[x-2][y-2], x-2, y-2);
		autoTile(activeBlocks, activeBlocks[x][y-2], x, y-2);
		autoTile(activeBlocks, activeBlocks[x+2][y-2], x+2, y-2);
		autoTile(activeBlocks, activeBlocks[x-2][y], x-2, y);
		autoTile(activeBlocks, activeBlocks[x+2][y], x+2, y);
		autoTile(activeBlocks, activeBlocks[x-2][y+2], x-2, y+2);
		autoTile(activeBlocks, activeBlocks[x][y+2], x, y+2);
		autoTile(activeBlocks, activeBlocks[x+2][y+2], x+2, y+2);

	}
	
	public void handleCollisions() {
		
	}
}
