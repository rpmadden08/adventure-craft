package com.madbros.adventurecraft;

import static com.madbros.adventurecraft.Constants.*;

public class Chunk {
	public int[][][] ids = new int[CHUNK_SIZE][CHUNK_SIZE][23];
	public int[][][] currentTextures = new int[CHUNK_SIZE][CHUNK_SIZE][23];
	public int[][] absX = new int[CHUNK_SIZE][CHUNK_SIZE];
	public int[][] absY = new int[CHUNK_SIZE][CHUNK_SIZE];
	public boolean[][] isUnfinished = new boolean[CHUNK_SIZE][CHUNK_SIZE];
}
