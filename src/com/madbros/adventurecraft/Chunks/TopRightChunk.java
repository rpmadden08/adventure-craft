//package com.madbros.adventurecraft.Chunks;
//
//import static com.madbros.adventurecraft.Constants.*;
//
//import com.madbros.adventurecraft.*;
//
//public class TopRightChunk extends Chunk{
//	@Override
//	public void renderPrep(Rect renderRect) {
//		extraX = CHUNK_SIZE*2;
//		extraY = 0;
//		if(renderRect.y < CHUNK_SIZE && renderRect.x2() >= CHUNK_SIZE*2) {
//			isOnScreen = true;
//		} else {
//			isOnScreen = false;
//		}
//			arrayX = 0;
//			arrayY = renderRect.y;
//			arrayX2 = renderRect.x2() - CHUNK_SIZE*2;
//			arrayY2 = CHUNK_SIZE;
//		
//	}
//	
////	public void render(Rect renderRect, int offsetX, int offsetY) {
////		if(renderRect.y < CHUNK_SIZE && renderRect.x2() >= CHUNK_SIZE*2) {
////			int arrayX = 0;
////			int arrayY = renderRect.y;
////			int arrayX2 = renderRect.x2() - CHUNK_SIZE*2;
////			int arrayY2 = CHUNK_SIZE;
////	
////			for(int x = arrayX; x < arrayX2; x++) {
////				for(int y = arrayY; y < arrayY2; y++) {
////					blocks[x][y].renderDebug(TILE_SIZE * (x+CHUNK_SIZE*2-renderRect.x) - offsetX - TILE_SIZE, 
////					  TILE_SIZE * (y-renderRect.y) - offsetY - TILE_SIZE);
////				}
////			}
////		}
////	}
//}
