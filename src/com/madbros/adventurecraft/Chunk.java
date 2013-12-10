package com.madbros.adventurecraft;

import static com.madbros.adventurecraft.Constants.*;

public class Chunk {
	public int[][][] ids = new int[CHUNK_SIZE][CHUNK_SIZE][23];
	public int[][][] currentTextures = new int[CHUNK_SIZE][CHUNK_SIZE][23];
	public int[][] furnaceInts;
	public boolean[][] furnaceBooleans;

//	public int[][][] furnaceFuel = new int[CHUNK_SIZE][CHUNK_SIZE][23];
//	public int[][][] furnaceMaxFuel = new int[CHUNK_SIZE][CHUNK_SIZE][23];
//	public int[][][] furnaceBuildTime = new int[CHUNK_SIZE][CHUNK_SIZE][23];
//	public int[][][] furnacePossiblyCraftableItemID = new int[CHUNK_SIZE][CHUNK_SIZE][23];
//	public int[][][] furnaceSlot0ID = new int[CHUNK_SIZE][CHUNK_SIZE][23];
//	public int[][][] furnaceSlot1ID = new int[CHUNK_SIZE][CHUNK_SIZE][23];
//	public int[][][] furnaceSlot0StackSize = new int[CHUNK_SIZE][CHUNK_SIZE][23];
//	public int[][][] furnaceSlot1StackSize = new int[CHUNK_SIZE][CHUNK_SIZE][23];
//	public boolean[][][] furnaceIsBurning = new boolean[CHUNK_SIZE][CHUNK_SIZE][23];
//	public boolean[][][] furnaceIsCraftableItem = new boolean[CHUNK_SIZE][CHUNK_SIZE][23];
//	
//	
	
	public int[][] absX = new int[CHUNK_SIZE][CHUNK_SIZE];
	public int[][] absY = new int[CHUNK_SIZE][CHUNK_SIZE];
	public boolean[][] isUnfinished = new boolean[CHUNK_SIZE][CHUNK_SIZE];
	
	
}
