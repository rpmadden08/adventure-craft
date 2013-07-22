//package com.madbros.adventurecraft.Chunks;
//
//import static com.madbros.adventurecraft.Constants.*;
//
//import com.madbros.adventurecraft.*;
//
//public class CenterLeftChunk extends Chunk{
//	@Override
//	public void renderPrep(Rect renderRect) {
//		extraX = 0;
//		extraY = CHUNK_SIZE;
//		if(renderRect.x < CHUNK_SIZE && renderRect.y2() >= CHUNK_SIZE && renderRect.y < CHUNK_SIZE*2) {
//			isOnScreen = true;
//		} else {
//			isOnScreen = false;
//		}
//		arrayX = renderRect.x;
//		arrayY = Math.max(renderRect.y - CHUNK_SIZE, 0);
//		arrayX2 = CHUNK_SIZE;
//		arrayY2 = Math.min(renderRect.y2() - CHUNK_SIZE, CHUNK_SIZE);
//	}
////	@Override
////	public void render(Rect renderRect, int offsetX, int offsetY) {
////		if(renderRect.x < CHUNK_SIZE && renderRect.y2() >= CHUNK_SIZE && renderRect.y < CHUNK_SIZE*2) {
////			int arrayX = renderRect.x;
////			int arrayY = Math.max(renderRect.y - CHUNK_SIZE, 0);
////			int arrayX2 = CHUNK_SIZE;
////			int arrayY2 = Math.min(renderRect.y2() - CHUNK_SIZE, CHUNK_SIZE);
////	
////			for(int x = arrayX; x < arrayX2; x++) {
////				for(int y = arrayY; y < arrayY2; y++) {
////					blocks[x][y].renderDebug(TILE_SIZE * (x-renderRect.x) - offsetX - TILE_SIZE, 
////					  TILE_SIZE * (y+CHUNK_SIZE-renderRect.y) - offsetY - TILE_SIZE);
////				}
////			}
////		}
////	}
//}
