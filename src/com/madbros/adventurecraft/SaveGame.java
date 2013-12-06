package com.madbros.adventurecraft;

import static com.madbros.adventurecraft.Constants.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.madbros.adventurecraft.Items.Item;
import com.madbros.adventurecraft.Items.NoItem;
import com.madbros.adventurecraft.Slots.Slot;
import com.madbros.adventurecraft.TileTypes.Furnace;
import com.madbros.adventurecraft.Utils.Helpers;

//@SuppressWarnings("unchecked")
public class SaveGame {
	public void saveChunk(Block[][] chunk, int chunkX, int chunkY) {
		Chunk chunkData = new Chunk();
		
		int[][][] ids = new int[chunk.length][chunk.length][chunk[0][0].layers.length];
		int[][][] currentTextures = new int[chunk.length][chunk.length][chunk[0][0].layers.length];
		
		int[][][] furnaceFuel =  new int[chunk.length][chunk.length][chunk[0][0].layers.length];
		int[][][] furnaceMaxFuel =  new int[chunk.length][chunk.length][chunk[0][0].layers.length];
		int[][][] furnaceBuildTime =  new int[chunk.length][chunk.length][chunk[0][0].layers.length];
		int[][][] furnacePossiblyCraftableItemID =  new int[chunk.length][chunk.length][chunk[0][0].layers.length];
		int[][][] furnaceSlot0ID =  new int[chunk.length][chunk.length][chunk[0][0].layers.length];
		int[][][] furnaceSlot1ID =  new int[chunk.length][chunk.length][chunk[0][0].layers.length];
		int[][][] furnaceSlot0StackSize =  new int[chunk.length][chunk.length][chunk[0][0].layers.length];
		int[][][] furnaceSlot1StackSize =  new int[chunk.length][chunk.length][chunk[0][0].layers.length];
		boolean[][][] furnaceIsBurning =  new boolean[chunk.length][chunk.length][chunk[0][0].layers.length];
		boolean[][][] furnaceIsCraftableItem =  new boolean[chunk.length][chunk.length][chunk[0][0].layers.length];
		
		
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
						furnaceFuel[x][y][i] = furnace.furnaceFuel;
						furnaceMaxFuel[x][y][i] = furnace.furnaceMaxFuel;
						//System.out.println(furnace.furnaceFuel);
						furnaceBuildTime[x][y][i] = furnace.furnaceBuildTime;
						furnacePossiblyCraftableItemID[x][y][i] = furnace.possiblyCraftableItem.id;
						furnaceSlot0ID[x][y][i] = furnace.furnaceSlots[0].item.id;
						furnaceSlot1ID[x][y][i] = furnace.furnaceSlots[1].item.id;
						furnaceSlot0StackSize[x][y][i] = furnace.furnaceSlots[0].item.stackSize;
						furnaceSlot1StackSize[x][y][i] = furnace.furnaceSlots[1].item.stackSize;
						furnaceIsBurning[x][y][i] = furnace.furnaceIsBurning;
						furnaceIsCraftableItem[x][y][i] = furnace.isCraftableItem;
						
						

					}
					//If branch for if the id is a furnace
					//  -save the item id for the "what is cooking" slot
					//  - save the item id for the fuel slot
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
		//TODO should section all of this out in its own function and class (furnaceData) in order to increase load speeds
		chunkData.furnaceFuel = furnaceFuel;
		chunkData.furnaceMaxFuel = furnaceMaxFuel;
		chunkData.furnaceBuildTime = furnaceBuildTime;
		chunkData.furnacePossiblyCraftableItemID = furnacePossiblyCraftableItemID;
		chunkData.furnaceIsBurning = furnaceIsBurning;
		chunkData.furnaceIsCraftableItem = furnaceIsCraftableItem;
		chunkData.furnaceSlot0ID = furnaceSlot0ID;
		chunkData.furnaceSlot1ID = furnaceSlot1ID;
		chunkData.furnaceSlot0StackSize = furnaceSlot0StackSize;
		chunkData.furnaceSlot1StackSize = furnaceSlot1StackSize;
		
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