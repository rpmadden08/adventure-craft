package com.madbros.adventurecraft;

import static com.madbros.adventurecraft.Constants.*;

import java.io.File;

import com.madbros.adventurecraft.TileTypes.*;
import com.madbros.adventurecraft.Utils.Helpers;
import com.madbros.adventurecraft.Utils.Rect;

public class Level {
	public Block[][] activeBlocks;
	public Block highlightedBlock;
	public int highlightedBlockX = 0;
	public int highlightedBlockY = 0;
	public String gameName;
	public SaveGame saveGame = new SaveGame();
	
	//Keeps track of what part of the activeBlocks array we're rendering. Starts off in the very center.
	public Rect renderRect = new Rect(TILES_PER_ROW / 2 - (int)Math.ceil(Game.centerScreenX * 1.0 /TILE_SIZE),
			  						  TILES_PER_ROW / 2 - (int)Math.ceil(Game.centerScreenY * 1.0 / TILE_SIZE),
			  						  (int)Math.ceil(INITIAL_WINDOW_WIDTH * 1.0 / TILE_SIZE) + RENDER_MARGIN,
			  						  (int)Math.ceil(INITIAL_WINDOW_HEIGHT * 1.0 / TILE_SIZE) + RENDER_MARGIN);
	
	private long rgenseed = System.currentTimeMillis();
	public PerlinGenerator perlin = new PerlinGenerator((int) rgenseed);
	public int size = 100;
	
	public Rect chunkRect = new Rect(0, 0, CHUNKS_IN_A_ROW-1, CHUNKS_IN_A_ROW-1);	//keeps track of the chunk we're on
	public int offsetX = 0;	//offset gets set at the start of level if there is one
	public int offsetY = 0;

	public boolean isLoading = false;
	
	public Level() {
		if(Game.centerScreenX % TILE_SIZE > 0) offsetX = TILE_SIZE - Game.centerScreenX % TILE_SIZE;
		if(Game.centerScreenY % TILE_SIZE > 0) offsetY = TILE_SIZE - Game.centerScreenY % TILE_SIZE;
		
		activeBlocks = new Block[TILES_PER_ROW][TILES_PER_ROW];
		
		for(int i = 0; i < CHUNKS_IN_A_ROW; i++) {
			for(int j = 0; j < CHUNKS_IN_A_ROW; j++) {
				createNewChunk(CHUNK_SIZE*i, CHUNK_SIZE*j, chunkRect.x + i, chunkRect.y + j);
			}
		}
		
		autoTileNewArea(1, 1, TILES_PER_ROW-1, TILES_PER_ROW-1);
	}
	
	private void highlightBlock() {
		if(highlightedBlock != null) highlightedBlock.isHighlighted = false;
		Rect mRect = Helpers.getMouseRect();
		highlightedBlockX = renderRect.x + (mRect.x + offsetX) / TILE_SIZE;
		
		highlightedBlockY = renderRect.y + (mRect.y + offsetY) / TILE_SIZE;
		highlightedBlock = activeBlocks[highlightedBlockX][highlightedBlockY];
		highlightedBlock.isHighlighted = true;
	}
	
	public void update() {
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
	}
	
	public void render() {
		int i = 0; int j = 0;
		for(int x = renderRect.x; x < renderRect.x2(); x++) {
			for(int y = renderRect.y; y < renderRect.y2(); y++) {
				if(x < activeBlocks.length && y < activeBlocks[0].length && x >= 0 && y >= 0) {
					activeBlocks[x][y].render(TILE_SIZE * i - offsetX, TILE_SIZE * j - offsetY);
					if(Game.debugMenu.chunkBoundariesAreOn) {
						if(x % CHUNK_SIZE == 0) Textures.pixel.draw(TILE_SIZE * i - offsetX,
												TILE_SIZE * j - offsetY, 1, TILE_SIZE);
						if(y % CHUNK_SIZE == 0) Textures.pixel.draw(TILE_SIZE * i - offsetX,
												TILE_SIZE * j - offsetY, TILE_SIZE, 1);
					}
				}
				j++;
			}
			i++; j = 0;
		}
	}

	public void getNorthernChunks() {
		renderRect.y += CHUNK_SIZE;
		
		shiftActiveBlocksArray(DOWN);
		chunkRect.y--;
		
		for(int i = 0; i < CHUNKS_IN_A_ROW; i++) {
			createNewChunk(CHUNK_SIZE*i, 0, chunkRect.x + i, chunkRect.y);
		}
		
		autoTileNewArea(1, 1, TILES_PER_ROW-1, CHUNK_SIZE+1);
	}
	
	public void getSouthernChunks() {
		renderRect.y -= CHUNK_SIZE;
		
		shiftActiveBlocksArray(UP);
		chunkRect.y++;
		
		for(int i = 0; i < CHUNKS_IN_A_ROW; i++) {
			createNewChunk(CHUNK_SIZE*i, TILES_PER_ROW-CHUNK_SIZE, chunkRect.x + i, chunkRect.y2());
		}
		autoTileNewArea(1, TILES_PER_ROW-CHUNK_SIZE-1, TILES_PER_ROW-1, TILES_PER_ROW-1);
	}
	
	public void getEasternChunks() {
		renderRect.x -= CHUNK_SIZE;
		
		shiftActiveBlocksArray(LEFT);
		chunkRect.x++;
		
		for(int i = 0; i < CHUNKS_IN_A_ROW; i++) {
			createNewChunk(TILES_PER_ROW-CHUNK_SIZE, CHUNK_SIZE*i, chunkRect.x2(), chunkRect.y + i);
		}
		autoTileNewArea(TILES_PER_ROW-CHUNK_SIZE-1, 1, TILES_PER_ROW-1, TILES_PER_ROW-1);
	}

	public void getWesternChunks() {
		renderRect.x += CHUNK_SIZE;
		
		shiftActiveBlocksArray(RIGHT);
		chunkRect.x--;
		
		for(int i = 0; i < CHUNKS_IN_A_ROW; i++) {
			createNewChunk(0, CHUNK_SIZE*i, chunkRect.x, chunkRect.y + i);
		}
		autoTileNewArea(1, 1, CHUNK_SIZE+1, TILES_PER_ROW-1);
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
	
	public Block createNewBlock(int x, int y, int chunkX, int chunkY) {
		Block block;
		float noise = perlin.Noise(4 * ((chunkX*CHUNK_SIZE)+x) / (float)size, 4 * ((chunkY*CHUNK_SIZE)+y) / (float)size, 0);
		int absX = x*TILE_SIZE+chunkX*CHUNK_SIZE*TILE_SIZE;
		int absY = y*TILE_SIZE+chunkY*CHUNK_SIZE*TILE_SIZE;
		
		if(noise < -0.1) {
			Tile[] waterTile = {new DarkDirtTile(),  new DirtTile(), new NoTile(), new WaterTile()};
    		block = new Block(waterTile, absX, absY);
    	} else if(noise > 0.1 && noise < 0.105) {
    		Tile[] treeTile = {new DarkDirtTile(), new DirtTile(), new GrassTile(), new TreeTile()};
    		block = new Block(treeTile, absX, absY);
    	} else {
    		Tile[] grassTile = {new DarkDirtTile(), new DirtTile(), new GrassTile(), new NoTile()};
    		block = new Block(grassTile, absX, absY);
    	}
//		block.mapHeight = noise;
		
		return block;
	}
	
	public Block autoTile(Block[][] blocks, Block block, int x, int y) {
		for(int i = 0; i < blocks[x][y].layers.length; i++) {
			int left = 0, topLeft = 0, top = 0, topRight = 0, right = 0, bottomRight = 0, bottom = 0, bottomLeft = 0;
			if(blocks[x-1][y-1].layers[i].id == block.layers[i].id) topLeft = 1;
			if(blocks[x][y-1].layers[i].id == block.layers[i].id) top = 2;
			if(blocks[x+1][y-1].layers[i].id == block.layers[i].id) topRight = 4;
			if(blocks[x-1][y].layers[i].id == block.layers[i].id) left = 8;
			if(blocks[x+1][y].layers[i].id == block.layers[i].id) right = 16;
			if(blocks[x-1][y+1].layers[i].id == block.layers[i].id) bottomLeft = 32;
			if(blocks[x][y+1].layers[i].id == block.layers[i].id) bottom = 64;
			if(blocks[x+1][y+1].layers[i].id == block.layers[i].id) bottomRight = 128;
			
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
			for(int x = startX; x < startX+CHUNK_SIZE; x++) {
				for(int y = startY; y < startY+CHUNK_SIZE; y++) {
					Block block = createNewBlock(i, j, chunkX, chunkY);
					chunk[i][j] = block;
	            	activeBlocks[x][y] = block;
	            	j++;
				}
				i++; j = 0;
			}
			saveGame.saveChunk(chunk, chunkX, chunkY);
		}
		isLoading = false;
	}
	
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
}
