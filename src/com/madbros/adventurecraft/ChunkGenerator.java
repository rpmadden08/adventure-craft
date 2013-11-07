package com.madbros.adventurecraft;

import static com.madbros.adventurecraft.Constants.*;

public class ChunkGenerator {
	public float chunkNoiseElevation[][] = new float [CHUNK_SIZE+20][CHUNK_SIZE+20];
	public float chunkNoiseRainfall[][] = new float [CHUNK_SIZE+20][CHUNK_SIZE+20];
	public float chunkNoiseTemperature[][] = new float [CHUNK_SIZE+20][CHUNK_SIZE+20];
	public ChunkGenerator() {
		
	}
}
