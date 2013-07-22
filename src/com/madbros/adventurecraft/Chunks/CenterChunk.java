//package com.madbros.adventurecraft.Chunks;
//
//import static com.madbros.adventurecraft.Constants.*;
//
//import com.madbros.adventurecraft.*;
//import com.madbros.adventurecraft.TileTypes.*;
//
//public class CenterChunk extends Chunk {
//	public CenterChunk() {
//		for (int row = 0; row < CHUNK_SIZE; row ++) {
//            for (int col = 0; col < CHUNK_SIZE; col++) {
//            	blocks[row][col] = new Block(new GrassTile(), row*TILE_SIZE, col*TILE_SIZE);
//            }
//		}
//	}
//	
//	@Override
//	public void renderPrep(Rect renderRect) {
//		extraX = CHUNK_SIZE;
//		extraY = CHUNK_SIZE;
//		isOnScreen = true;
//		
//		arrayX = Math.max(renderRect.x - CHUNK_SIZE, 0);
//		arrayY = Math.max(renderRect.y - CHUNK_SIZE, 0);
//		arrayX2 = Math.min(renderRect.x2() - CHUNK_SIZE, CHUNK_SIZE);
//		arrayY2 = Math.min(renderRect.y2() - CHUNK_SIZE, CHUNK_SIZE);
//	}
////	@Override
////	public void render(Rect renderRect, int offsetX, int offsetY) {
////		int arrayX = Math.max(renderRect.x - CHUNK_SIZE, 0);
////		int arrayY = Math.max(renderRect.y - CHUNK_SIZE, 0);
////		int arrayX2 = Math.min(renderRect.x2() - CHUNK_SIZE, CHUNK_SIZE);
////		int arrayY2 = Math.min(renderRect.y2() - CHUNK_SIZE, CHUNK_SIZE);
////
////		for(int x = arrayX; x < arrayX2; x++) {
////			for(int y = arrayY; y < arrayY2; y++) {
////				blocks[x][y].renderDebug(TILE_SIZE * (x+CHUNK_SIZE-renderRect.x) - offsetX - TILE_SIZE, 
////				  TILE_SIZE * (y+CHUNK_SIZE-renderRect.y) - offsetY - TILE_SIZE);
////			}
////		}
////	}
//}
