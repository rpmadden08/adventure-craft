package com.madbros.adventurecraft;

import static com.madbros.adventurecraft.Constants.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.madbros.adventurecraft.Slots.Slot;
import com.madbros.adventurecraft.TileTypes.Cauldron;
import com.madbros.adventurecraft.TileTypes.Furnace;
import com.madbros.adventurecraft.Utils.Helpers;

//@SuppressWarnings("unchecked")
public class SaveGame {
	public void saveGame() {
		SaveGameData saveData = new SaveGameData();
		saveData.heroX = Game.hero.absRect.x;
		saveData.heroY = Game.hero.absRect.y;
		saveData.offsetX = Game.level.offsetX;
		saveData.offsetY = Game.level.offsetY;
		
		Kryo kryo = new Kryo();

		try {
			Output output = new Output(new FileOutputStream(Game.locOfSavedGame+"main.sv"));
			
			kryo.writeObject(output, saveData);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public SaveGameData saveData() {
		SaveGameData saveData = new SaveGameData();
		
		Kryo kryo = new Kryo();
		
		try {
			Input input = new Input(new FileInputStream(Game.locOfSavedGame + "main.sv"));
			saveData = kryo.readObject(input, SaveGameData.class);
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return saveData;
		
	}
	
//	public void loadGame() {
//		SaveGameData saveData = new SaveGameData();
//		
//		Kryo kryo = new Kryo();
//		
//		try {
//			Input input = new Input(new FileInputStream(Game.locOfSavedGame + "main.sv"));
//			saveData = kryo.readObject(input, SaveGameData.class);
//			input.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		//Set everything here
//		Game.level.spawnX = saveData.heroX;
//		Game.level.spawnY = saveData.heroY;
//		
//		
//	}
	public void saveChunk(Block[][] chunk, int chunkX, int chunkY) {
		Chunk chunkData = new Chunk();
		
		int[][][] ids = new int[chunk.length][chunk.length][chunk[0][0].layers.length];
		int[][][] currentTextures = new int[chunk.length][chunk.length][chunk[0][0].layers.length];
		ArrayList<int[]> furnaceListInt = new ArrayList<int[]>();
		ArrayList<boolean[]> furnaceListBoolean = new ArrayList<boolean[]>();
		int[][] furnaceInts;
		boolean[][] furnaceBooleans;
		
		ArrayList<int[]> cauldronListInt = new ArrayList<int[]>();
		ArrayList<boolean[]> cauldronListBoolean = new ArrayList<boolean[]>();
		int[][] cauldronInts;
		boolean[][] cauldronBooleans;
		
		int[][] absX = new int[CHUNK_SIZE][CHUNK_SIZE];
		int[][] absY = new int[CHUNK_SIZE][CHUNK_SIZE];
		boolean[][] isUnfinished = new boolean[CHUNK_SIZE][CHUNK_SIZE];
		
		
		for(int x = 0; x < chunk.length; x++) {
			for(int y = 0; y < chunk.length; y++) {
				for(int i = 0; i < chunk[x][y].layers.length; i++) {
					ids[x][y][i] = chunk[x][y].layers[i].id;
					currentTextures[x][y][i] = chunk[x][y].layers[i].currentSpriteId;
					
					if(chunk[x][y].layers[i].id == FURNACE) {
						Furnace furnace = (Furnace) chunk[x][y].layers[i];
						int a1[]={furnace.furnaceFuel,
								furnace.furnaceMaxFuel,
								furnace.furnaceBuildTime,
								furnace.possiblyCraftableItem.id,
								furnace.furnaceSlots[0].item.id,
								furnace.furnaceSlots[1].item.id,
								furnace.furnaceSlots[0].item.stackSize,
								furnace.furnaceSlots[1].item.stackSize,
								};
						boolean[] a2={furnace.furnaceIsBurning,
								furnace.isCraftableItem};
						furnaceListInt.add(a1);
						furnaceListBoolean.add(a2);						
					}
					if(chunk[x][y].layers[i].id == CAULDRON) {
						Cauldron cauldron = (Cauldron) chunk[x][y].layers[i];
						int a1[]={cauldron.cauldronFuel,
								cauldron.cauldronMaxFuel,
								cauldron.cauldronBuildTime,
								cauldron.possiblyCraftableItem.id,
								cauldron.cauldronSlots[0].item.id,
								cauldron.cauldronSlots[1].item.id,
								cauldron.cauldronSlots[2].item.id,
								cauldron.cauldronSlots[3].item.id,
								cauldron.cauldronSlots[0].item.stackSize,
								cauldron.cauldronSlots[1].item.stackSize,
								cauldron.cauldronSlots[2].item.stackSize,
								cauldron.cauldronSlots[3].item.stackSize,
								};
						boolean[] a2={cauldron.cauldronIsBurning,
								cauldron.isCraftableItem};
						cauldronListInt.add(a1);
						cauldronListBoolean.add(a2);						
					}
					
				}
				absX[x][y] = chunk[x][y].absRect.x;
				absY[x][y] = chunk[x][y].absRect.y;
				isUnfinished[x][y] = chunk[x][y].isUnfinished;
			}
		}
		
		furnaceInts = new int[furnaceListInt.size()][];
		for (int i = 0; i < furnaceListInt.size(); i++) {
		    furnaceInts[i] = furnaceListInt.get(i);
		}		
		
		furnaceBooleans = new boolean[furnaceListBoolean.size()][];
		for (int i = 0; i < furnaceListBoolean.size(); i++) {
		    furnaceBooleans[i] = furnaceListBoolean.get(i);
		}
		
		cauldronInts = new int[cauldronListInt.size()][];
		for (int i = 0; i < cauldronListInt.size(); i++) {
		    cauldronInts[i] = cauldronListInt.get(i);
		}		
		
		cauldronBooleans = new boolean[cauldronListBoolean.size()][];
		for (int i = 0; i < cauldronListBoolean.size(); i++) {
		    cauldronBooleans[i] = cauldronListBoolean.get(i);
		}
		
		chunkData.ids = ids;
		chunkData.currentTextures = currentTextures;
		chunkData.absX = absX;
		chunkData.absY = absY;
		chunkData.isUnfinished = isUnfinished;
		chunkData.furnaceInts = new int[furnaceInts.length][8];
		chunkData.furnaceInts = furnaceInts;
		chunkData.furnaceBooleans = new boolean[furnaceBooleans.length][8];
		chunkData.furnaceBooleans = furnaceBooleans;
		chunkData.cauldronInts = new int[cauldronInts.length][8];
		chunkData.cauldronInts = cauldronInts;
		chunkData.cauldronBooleans = new boolean[cauldronBooleans.length][8];
		chunkData.cauldronBooleans = cauldronBooleans;
		
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
			chunk = kryo.readObject(input, Chunk.class);
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Helpers.chunkToBlockArray(chunk);
	}
	
	public void saveChest(Slot[] slot, int absX, int absY) {
		ChestData chestData = new ChestData();
		
		int[] itemIds = new int[slot.length];
		int[] itemStackSizes = new int[slot.length];
		int[] itemUses = new int[slot.length];
		
		for(int x = 0; x < slot.length; x++) {
			itemIds[x] = chestData.itemIds[x];
			itemStackSizes[x] = chestData.itemStackSizes[x];
			itemUses[x] = chestData.itemUses[x];
		}
		
		Kryo kryo = new Kryo();

		try {
			Output output = new Output(new FileOutputStream(Game.locOfSavedGame + CHESTS_FOLDER + absX + "-" + absY + ".sv"));
			
			kryo.writeObject(output, chestData);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadChest(int absX, int absY) {
		ChestData chestData = new ChestData();
		
		Kryo kryo = new Kryo();
		
		try {
			Input input = new Input(new FileInputStream(Game.locOfSavedGame + CHESTS_FOLDER + absX + "-" + absY + ".sv"));
			chestData = kryo.readObject(input, ChestData.class);
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Helpers.chestDataToSlotArray(chestData);
	}
}