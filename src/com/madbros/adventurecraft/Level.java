package com.madbros.adventurecraft;

import static com.madbros.adventurecraft.Constants.*;

import java.io.File;

import com.madbros.adventurecraft.TileTypes.*;

public class Level {
	public Block[][] activeBlocks;
	public String gameName;
	public SaveGame saveGame = new SaveGame();
	
	//Keeps track of what part of the activeBlocks array we're rendering. Starts off in the very center.
	public Rect renderRect = new Rect(TILES_PER_ROW/2- Game.centerScreenX/TILE_SIZE - 2,
			  						  TILES_PER_ROW/2 - Game.centerScreenY/TILE_SIZE - 2,
			  						  (int)Math.ceil(INITIAL_WINDOW_WIDTH * 1.0 / TILE_SIZE) + RENDER_MARGIN,
			  						  (int)Math.ceil(INITIAL_WINDOW_HEIGHT * 1.0 / TILE_SIZE) + RENDER_MARGIN);
	
	private long rgenseed = System.currentTimeMillis();
	public PerlinGenerator perlin = new PerlinGenerator((int) rgenseed);
	public int size = 100;
	
	public Rect chunkRect = new Rect(0, 0, CHUNKS_IN_A_ROW-1, CHUNKS_IN_A_ROW-1);	//keeps track of the chunk we're on
	public int offsetX = (TILE_SIZE * 2 - CHARACTER_SIZE) / 2;
	public int offsetY = (TILE_SIZE * 2 - CHARACTER_SIZE) / 2;

	public boolean isLoading = false;
	
	public Level() {
		activeBlocks = new Block[TILES_PER_ROW][TILES_PER_ROW];
		
		for(int i = 0; i < CHUNKS_IN_A_ROW; i++) {
			for(int j = 0; j < CHUNKS_IN_A_ROW; j++) {
				createNewChunk(CHUNK_SIZE*i, CHUNK_SIZE*j, chunkRect.x + i, chunkRect.y + j);
			}
		}
		
		autoTileNewArea(1, 1, TILES_PER_ROW-1, TILES_PER_ROW-1);
	}
	
	public void update() {
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
		for(int x = renderRect.x; x < renderRect.x2(); x++) {
			for(int y = renderRect.y; y < renderRect.y2(); y++) {
				if(x < activeBlocks.length && y < activeBlocks[0].length && x >= 0 && y >= 0) {
					activeBlocks[x][y].render(TILE_SIZE * (x-renderRect.x) - offsetX - TILE_SIZE, 
								  TILE_SIZE * (y-renderRect.y) - offsetY - TILE_SIZE);
					if(Game.debugger.chunkBoundariesAreOn) {
						if(x % CHUNK_SIZE == 0) Textures.pixel.draw(TILE_SIZE * (x-renderRect.x) - offsetX - TILE_SIZE,
												TILE_SIZE * (y-renderRect.y) - offsetY - TILE_SIZE, 1, TILE_SIZE);
						if(y % CHUNK_SIZE == 0) Textures.pixel.draw(TILE_SIZE * (x-renderRect.x) - offsetX - TILE_SIZE,
												TILE_SIZE * (y-renderRect.y) - offsetY - TILE_SIZE, TILE_SIZE, 1);
					}
				}
			}
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
    		block = new Block(new WaterTile(), absX, absY);
    	} else if(noise > 0.1 && noise < 0.105) {
    		Tile[] treeTile = {new GrassTile(), new TreeTile()};
    		block = new Block(treeTile, absX, absY);
    	} else {
    		block = new Block(new GrassTile(), absX, absY);
    	}
		block.mapHeight = noise;
		
		return block;
	}
	
	public Block autoTile(Block[][] blocks, Block block, int x, int y) {
		if(block.baseTile.id != GRASS) {
			int left = 0, topLeft = 0, top = 0, topRight = 0, right = 0, bottomRight = 0, bottom = 0, bottomLeft = 0;
			if(blocks[x-1][y-1].baseTile.id == block.baseTile.id) topLeft = 1;
			if(blocks[x][y-1].baseTile.id == block.baseTile.id) top = 2;
			if(blocks[x+1][y-1].baseTile.id == block.baseTile.id) topRight = 4;
			if(blocks[x-1][y].baseTile.id == block.baseTile.id) left = 8;
			if(blocks[x+1][y].baseTile.id == block.baseTile.id) right = 16;
			if(blocks[x-1][y+1].baseTile.id == block.baseTile.id) bottomLeft = 32;
			if(blocks[x][y+1].baseTile.id == block.baseTile.id) bottom = 64;
			if(blocks[x+1][y+1].baseTile.id == block.baseTile.id) bottomRight = 128;
			
			block.baseTile.topLeftAutoTile = Helpers.topLeftAutoTileHash.get(left + topLeft + top);
			block.baseTile.topRightAutoTile = Helpers.topRightAutoTileHash.get(right + topRight + top);
			block.baseTile.bottomLeftAutoTile = Helpers.bottomLeftAutoTileHash.get(left + bottomLeft + bottom);
			block.baseTile.bottomRightAutoTile = Helpers.bottomRightAutoTileHash.get(right + bottomRight + bottom);
		}
		return block;
	}
	
	public void createNewChunk(int startX, int startY, int chunkX, int chunkY) {
		isLoading = true;
		File f = new File("saves/" + chunkX + "-" + chunkY + ".sv");
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
}
