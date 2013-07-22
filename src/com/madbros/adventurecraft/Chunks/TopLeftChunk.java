//package com.madbros.adventurecraft.Chunks;
//
//import static com.madbros.adventurecraft.Constants.*;
//
//import com.madbros.adventurecraft.*;
//
//public class TopLeftChunk extends Chunk {
//	@Override
//	public void renderPrep(Rect renderRect) {
//		extraX = 0;
//		extraY = 0;
//		if(renderRect.x < CHUNK_SIZE && renderRect.y < CHUNK_SIZE) {
//			isOnScreen = true;
//		} else {
//			isOnScreen = false;
//		}
//			arrayX = renderRect.x;
//			arrayY = renderRect.y;
//			arrayX2 = CHUNK_SIZE;
//			arrayY2 = CHUNK_SIZE;
//		
//	}
//	
////	@Override
////	public void render(Rect renderRect, int offsetX, int offsetY) {
////		if(renderRect.x < CHUNK_SIZE && renderRect.y < CHUNK_SIZE) {
////			for(int x = renderRect.x; x < CHUNK_SIZE; x++) {
////				for(int y = renderRect.y; y < CHUNK_SIZE; y++) {
////					blocks[x][y].renderDebug(TILE_SIZE * (x-renderRect.x) - offsetX - TILE_SIZE, 
////					  TILE_SIZE * (y-renderRect.y) - offsetY - TILE_SIZE);
////				}
////			}
////		}
////	}
//}
