package com.madbros.adventurecraft;

import java.io.*;

import org.json.simple.*;
import org.json.simple.parser.*;
import static com.madbros.adventurecraft.Constants.*;

@SuppressWarnings("unchecked")
public class SaveGame {
	public void saveChunk(Block[][] chunk, int chunkX, int chunkY) {
		JSONObject obj = new JSONObject();
		
		for(int x = 0; x < CHUNK_SIZE; x++) {
			for(int y = 0; y < CHUNK_SIZE; y++) {
				JSONArray tiles = new JSONArray();
				for(int i = 0; i < chunk[x][y].tiles.length; i++) {
					tiles.add(chunk[x][y].tiles[i].id);
				}
				obj.put("tiles" + x + "-" + y, tiles);
				obj.put("x" + x + "-" + y, chunk[x][y].aRect.x);
				obj.put("y" + x + "-" + y, chunk[x][y].aRect.y);
			}
		}
		
		
		
		try {
			File f = new File("saves");
			if(!f.exists()) {
				f.mkdir();
			}
			FileWriter file = new FileWriter("saves/" + chunkX + "-" + chunkY + ".sv");
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
			Object f = parser.parse(new FileReader("saves/" + chunkX + "-" + chunkY + ".sv"));
			JSONObject jO = (JSONObject) f;
			
			for(int x = 0; x < CHUNK_SIZE; x++) {
				for(int y = 0; y < CHUNK_SIZE; y++) {
					JSONArray tiles = (JSONArray) jO.get("tiles" + x + "-" + y);
					Tile[] t = new Tile[tiles.size()];
					for(int i = 0; i < tiles.size(); i++) {
						long id = (Long) tiles.get(i);
						t[i] = Helpers.tileHash.get((int)id).createNew();
					}
					
					long absX = (Long) jO.get("x" + x + "-" + y);
					long absY = (Long) jO.get("y" + x + "-" + y); 
				
					chunk[x][y] = new Block(t, (int)absX, (int)absY);
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