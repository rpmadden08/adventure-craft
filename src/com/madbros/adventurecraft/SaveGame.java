package com.madbros.adventurecraft;

import java.io.*;

import org.json.simple.*;
import org.json.simple.parser.*;

import com.madbros.adventurecraft.TileTypes.Tile;

import static com.madbros.adventurecraft.Constants.*;

@SuppressWarnings("unchecked")
public class SaveGame {
	public void saveChunk(Block[][] chunk, int chunkX, int chunkY) {
		JSONObject obj = new JSONObject();
		
		for(int x = 0; x < CHUNK_SIZE; x++) {
			for(int y = 0; y < CHUNK_SIZE; y++) {
				JSONArray tilesID = new JSONArray();
				for(int i = 0; i < chunk[x][y].layers.length; i++) {
					tilesID.add(chunk[x][y].layers[i].id);
				}
				
				JSONArray tilesCurrentTexture = new JSONArray();
				for(int i = 0; i < chunk[x][y].layers.length; i++) {
					tilesCurrentTexture.add(chunk[x][y].layers[i].currentTexture);
				}
				
				obj.put("tilesID" + x + "-" + y, tilesID);
				obj.put("tilesCurrentTexture" + x + "-" + y, tilesCurrentTexture);
				obj.put("x" + x + "-" + y, chunk[x][y].aRect.x);
				obj.put("y" + x + "-" + y, chunk[x][y].aRect.y);
				obj.put("isUnfinished" + x + "-" + y,new Boolean (chunk[x][y].isUnfinished));
			}
		}
		
		
		
		try {
			FileWriter file = new FileWriter(Game.locOfSavedGame + CHUNKS_FOLDER + chunkX + "-" + chunkY + ".sv");
			BufferedWriter bw = new BufferedWriter(file);
			bw.write(obj.toJSONString());
			
			bw.flush();
			bw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public Block[][] loadChunk(int chunkX, int chunkY) {
		JSONParser parser = new JSONParser();
		Block[][] chunk = new Block[CHUNK_SIZE][CHUNK_SIZE];
		try {
			Object f = parser.parse(new FileReader(Game.locOfSavedGame + CHUNKS_FOLDER + chunkX + "-" + chunkY + ".sv"));
			JSONObject jO = (JSONObject) f;
			
			for(int x = 0; x < CHUNK_SIZE; x++) {
				for(int y = 0; y < CHUNK_SIZE; y++) {
					JSONArray tilesID = (JSONArray) jO.get("tilesID" + x + "-" + y);
					Tile[] t = new Tile[tilesID.size()];
					for(int i = 0; i < tilesID.size(); i++) {
						long id = (Long) tilesID.get(i);						
						t[i] = TILE_HASH.get((int)id).createNew();
					}
					
					JSONArray tilesCurrentTexture = (JSONArray) jO.get("tilesCurrentTexture" + x + "-" + y);
					//Tile[] t = new Tile[tiles.size()];
					for(int i = 0; i < tilesCurrentTexture.size(); i++) {
						long currentTexture = (Long) tilesCurrentTexture.get(i);
						t[i].currentTexture = (int)currentTexture;
						
					}
					
					long absX = (Long) jO.get("x" + x + "-" + y);
					long absY = (Long) jO.get("y" + x + "-" + y); 
					boolean isUnfinished = (Boolean) jO.get("isUnfinished" + x + "-" + y);
					chunk[x][y] = new Block(t, (int)absX, (int)absY, isUnfinished);
					chunk[x][y].isUnfinished = isUnfinished;
					
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return chunk;
	}
}