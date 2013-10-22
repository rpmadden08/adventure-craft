package com.madbros.adventurecraft;

import java.io.*;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.madbros.adventurecraft.Utils.Helpers;

import static com.madbros.adventurecraft.Constants.*;

//@SuppressWarnings("unchecked")
public class SaveGame {
	public void saveChunk(Block[][] chunk, int chunkX, int chunkY) {
		Chunk chunkData = new Chunk();
		
		int[][][] ids = new int[chunk.length][chunk.length][chunk[0][0].layers.length];
		int[][][] currentTextures = new int[chunk.length][chunk.length][chunk[0][0].layers.length];
		int[][] absX = new int[CHUNK_SIZE][CHUNK_SIZE];
		int[][] absY = new int[CHUNK_SIZE][CHUNK_SIZE];
		boolean[][] isUnfinished = new boolean[CHUNK_SIZE][CHUNK_SIZE];
		
		for(int x = 0; x < chunk.length; x++) {
			for(int y = 0; y < chunk.length; y++) {
				for(int i = 0; i < chunk[x][y].layers.length; i++) {
					ids[x][y][i] = chunk[x][y].layers[i].id;
					currentTextures[x][y][i] = chunk[x][y].layers[i].currentSpriteId;
				}
				absX[x][y] = chunk[x][y].absRect.x;
				absY[x][y] = chunk[x][y].absRect.y;
				isUnfinished[x][y] = chunk[x][y].isUnfinished;
			}
		}
		
		chunkData.ids = ids;
		chunkData.currentTextures = currentTextures;
		chunkData.absX = absX;
		chunkData.absY = absY;
		chunkData.isUnfinished = isUnfinished;
		
		Kryo kryo = new Kryo();

		try {
			Output output = new Output(new FileOutputStream(Game.locOfSavedGame + CHUNKS_FOLDER + chunkX + "-" + chunkY + ".sv"));
			
			kryo.writeObject(output, chunkData);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public Block[][] loadChunk(int chunkX, int chunkY) {
		Chunk chunk = new Chunk();
		
		Kryo kryo = new Kryo();

		try {
			Input input = new Input(new FileInputStream(Game.locOfSavedGame + CHUNKS_FOLDER + chunkX + "-" + chunkY + ".sv"));
			//System.out.println(chunkX + "-" + chunkY + ".sv");
			chunk = kryo.readObject(input, Chunk.class);
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Helpers.chunkToBlockArray(chunk);
	}
}