//package com.madbros.adventurecraft.Chunks;
//
//import static com.madbros.adventurecraft.Constants.*;
//
//import com.madbros.adventurecraft.*;
//
//public class BottomCenterChunk extends Chunk{
//	@Override
//	public void renderPrep(Rect renderRect) {
//		extraX = CHUNK_SIZE;
//		extraY = CHUNK_SIZE*2;
//		if(renderRect.y2() >= CHUNK_SIZE*2 && renderRect.x2() >= CHUNK_SIZE && renderRect.x < CHUNK_SIZE*2) {
//			isOnScreen = true;
//		} else {
//			isOnScreen = false;
//		}
//		arrayX = Math.max(renderRect.x - CHUNK_SIZE, 0);
//		arrayY = 0;
//		arrayX2 = Math.min(renderRect.x2() - CHUNK_SIZE, CHUNK_SIZE);
//		arrayY2 = renderRect.y2() - CHUNK_SIZE*2;
//		
//	}
////	@Override
////	public void render(Rect renderRect, int offsetX, int offsetY) {
////		if(renderRect.y2() >= CHUNK_SIZE*2 && renderRect.x2() >= CHUNK_SIZE && renderRect.x < CHUNK_SIZE*2) {
////			int arrayX = Math.max(renderRect.x - CHUNK_SIZE, 0);
////			int arrayY = 0;
////			int arrayX2 = Math.min(renderRect.x2() - CHUNK_SIZE, CHUNK_SIZE);
////			int arrayY2 = renderRect.y2() - CHUNK_SIZE*2;
////
////			for(int x = arrayX; x < arrayX2; x++) {
////				for(int y = arrayY; y < arrayY2; y++) {
////					blocks[x][y].renderDebug(TILE_SIZE * (x+CHUNK_SIZE-renderRect.x) - offsetX - TILE_SIZE, 
////					  TILE_SIZE * (y+CHUNK_SIZE*2-renderRect.y) - offsetY - TILE_SIZE);
////				}
////			}
////		}
////	}
//}
