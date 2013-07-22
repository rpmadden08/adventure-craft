//package com.madbros.adventurecraft.Chunks;
//
//import static com.madbros.adventurecraft.Constants.*;
//
//import com.madbros.adventurecraft.*;
//
//public class BottomLeftChunk extends Chunk{
//	@Override
//	public void renderPrep(Rect renderRect) {
//		extraX = 0;
//		extraY = CHUNK_SIZE*2;
//		if(renderRect.x < CHUNK_SIZE && renderRect.y2() > CHUNK_SIZE*2) {
//			isOnScreen = true;
//		} else {
//			isOnScreen = false;
//		}
//		arrayX = renderRect.x;
//		arrayY = 0;
//		arrayX2 = CHUNK_SIZE;
//		arrayY2 = renderRect.y2() - CHUNK_SIZE*2;
//		
//	}
////	@Override
////	public void render(Rect renderRect, int offsetX, int offsetY) {
////		if(renderRect.x < CHUNK_SIZE && renderRect.y2() > CHUNK_SIZE*2) {
////			int arrayX = renderRect.x;
////			int arrayY = 0;
////			int arrayX2 = CHUNK_SIZE;
////			int arrayY2 = renderRect.y2() - CHUNK_SIZE*2;
////
////			for(int x = arrayX; x < arrayX2; x++) {
////				for(int y = arrayY; y < arrayY2; y++) {
////					blocks[x][y].renderDebug(TILE_SIZE * (x-renderRect.x) - offsetX - TILE_SIZE, 
////					  TILE_SIZE * (y+CHUNK_SIZE*2-renderRect.y) - offsetY - TILE_SIZE);
////				}
////			}
////		}
////	}
//}
