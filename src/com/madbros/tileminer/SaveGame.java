package com.madbros.tileminer;

import static com.madbros.tileminer.Constants.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.madbros.tileminer.GameObjects.Collectible;
import com.madbros.tileminer.Slots.Slot;
import com.madbros.tileminer.TileTypes.CauldronTile;
import com.madbros.tileminer.TileTypes.FurnaceTile;
import com.madbros.tileminer.Utils.Helpers;
import com.madbros.tileminer.Utils.RectInt;

//@SuppressWarnings("unchecked")
public class SaveGame {
	public void saveGame() {
		SaveGameData saveData = new SaveGameData();
		RectInt absRect = Game.hero.absRect.getRectInt();
		saveData.heroX = absRect.x;
		saveData.heroY = absRect.y;
		saveData.spawnX = Game.level.masterSpawnX;
		saveData.spawnY = Game.level.masterSpawnY;
		saveData.currentLevel = Game.currentLevel;
		//System.out.println("SavedOffset: "+Game.level.offsetX+"-"+Game.level.offsetY);
		//System.out.println("SavedSpawn: "+Game.level.spawnX+"-"+Game.level.spawnY);
		
		saveData.hP = Game.hero.hP;
		saveData.maxHP = Game.hero.maxHP;
		saveData.mP = Game.hero.mP;
		saveData.maxMP = Game.hero.maxMP;
		saveData.eP = Game.hero.eP;
		saveData.maxEP = Game.hero.maxEP;
		saveData.seed = Game.rgenseed;

//		public int[] invClothingID = new int[4];
		
		for(int x = 0; x < saveData.invBarID.length; x++) {
			saveData.invBarID[x] = Game.inventory.invBar[x].item.id;
			saveData.invBarStackSize[x] = Game.inventory.invBar[x].item.stackSize;
			saveData.invBarUsage[x] = Game.inventory.invBar[x].item.uses;
		}
		
		for(int x = 0; x < saveData.invBagID.length; x++) {
			saveData.invBagID[x] = Game.inventory.invBag[x].item.id;
			saveData.invBagStackSize[x] = Game.inventory.invBag[x].item.stackSize;
			saveData.invBagUsage[x] = Game.inventory.invBag[x].item.uses;
		}
		
		saveData.invCraftedID = Game.inventory.invCrafted[0].item.id;
		saveData.invCraftedStackSize = Game.inventory.invCrafted[0].item.stackSize;
		
		for(int x = 0; x < saveData.invCraftingID.length; x++) {
			saveData.invCraftingID[x] = Game.inventory.invCrafting[x].item.id;
			saveData.invCraftingStackSize[x] = Game.inventory.invCrafting[x].item.stackSize;
		}
		
		for(int x = 0; x < saveData.invClothingID.length; x++) {
			saveData.invClothingID[x] = Game.inventory.invClothing[x].item.id;
			//saveData.invCraftingStackSize[x] = Game.inventory.invCrafting[x].item.stackSize;
		}
		
		for(int x = 0; x < saveData.invClothingUsage.length; x++) {
			saveData.invClothingUsage[x] = Game.inventory.invClothing[x].item.uses;
			//saveData.invCraftingStackSize[x] = Game.inventory.invCrafting[x].item.stackSize;
		}
		
		saveData.gameTime = Time.getGameTime();
		
		Kryo kryo = new Kryo();

		try {
			Output output = new Output(new FileOutputStream(Game.locOfSavedGame+"main.sv"));
			
			kryo.writeObject(output, saveData);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		//saveCurrentLevel();
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
	
	public void saveChunk(Block[][] chunk, int chunkX, int chunkY) {
		Chunk chunkData = new Chunk();
		
		int[][][] ids = new int[chunk.length][chunk.length][chunk[0][0].layers.length];
		int[][][] currentTextures = new int[chunk.length][chunk.length][chunk[0][0].layers.length];
		long[][][] timeCreated = new long [chunk.length][chunk.length][chunk[0][0].layers.length];
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
					timeCreated[x][y][i] = chunk[x][y].layers[i].timeCreated;
					if(chunk[x][y].layers[i].id == FURNACE_TILE) {
						FurnaceTile furnace = (FurnaceTile) chunk[x][y].layers[i];
						int a1[]={furnace.furnaceFuel,
								furnace.furnaceMaxFuel,
								furnace.furnaceBuildTime,
								furnace.possiblyCraftableItem.id,
								furnace.furnaceSlots[0].item.id,
								furnace.fuelSlot[0].item.id,
								furnace.furnaceSlots[0].item.stackSize,
								furnace.fuelSlot[0].item.stackSize,
								furnace.craftedSlot[0].item.id,
								furnace.craftedSlot[0].item.stackSize,
								};
						boolean[] a2={furnace.furnaceIsBurning,
								furnace.isCraftableItem()};
						furnaceListInt.add(a1);
						furnaceListBoolean.add(a2);						
					}
					if(chunk[x][y].layers[i].id == CAULDRON_TILE) {
						CauldronTile cauldron = (CauldronTile) chunk[x][y].layers[i];
						int a1[]={cauldron.cauldronFuel,
								cauldron.cauldronMaxFuel,
								cauldron.cauldronBuildTime,
								cauldron.possiblyCraftableItem.id,
								cauldron.cauldronSlots[0].item.id,
//								cauldron.cauldronSlots[1].item.id,
//								cauldron.cauldronSlots[2].item.id,
								cauldron.fuelSlot[0].item.id,
								cauldron.cauldronSlots[0].item.stackSize,
//								cauldron.cauldronSlots[1].item.stackSize,
//								cauldron.cauldronSlots[2].item.stackSize,
								cauldron.fuelSlot[0].item.stackSize,
								cauldron.craftedSlot[0].item.id,
								cauldron.craftedSlot[0].item.stackSize,
								};
						boolean[] a2={cauldron.cauldronIsBurning,
								cauldron.isCraftableItem()};
						cauldronListInt.add(a1);
						cauldronListBoolean.add(a2);						
					}
					
				}
				RectInt absRect2 = chunk[x][y].absRect.getRectInt();
				absX[x][y] = absRect2.x;
				absY[x][y] = absRect2.y;
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
		chunkData.timeCreated = timeCreated;
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
			Output output = new Output(new FileOutputStream(Game.locOfSavedGame +CHUNKS_FOLDER + Game.currentLevel + chunkX + "-" + chunkY + ".sv"));
			
			kryo.writeObject(output, chunkData);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public void saveCurrentLevel() {
		CurrentLevelData currentLevelData = new CurrentLevelData();
		System.out.println("Saving:  "+Game.collectibleController.collectibles.size());
		for(int x = 0; x < Game.collectibleController.collectibles.size(); x++) {
			Collectible c = Game.collectibleController.collectibles.get(x);
			currentLevelData.collectibleItemIds[x] = c.item.id;
			currentLevelData.collectibleItemStackSize[x] = c.stackSize;
			currentLevelData.collectibleItemUses[x] = c.uses;
			RectInt cAbsRect = c.absRect.getRectInt();
			currentLevelData.collectibleItemX[x] = cAbsRect.x;
			currentLevelData.collectibleItemY[x] = cAbsRect.y;
		}
		Kryo kryo = new Kryo();
		try {
			Output output = new Output(new FileOutputStream(Game.locOfSavedGame +CHUNKS_FOLDER + Game.currentLevel +"Level.sv"));
			
			kryo.writeObject(output, currentLevelData);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public void loadCurrentLevel() {
		CurrentLevelData currentLevelData = new CurrentLevelData();
		
		Kryo kryo = new Kryo();
		
		try {
			Input input = new Input(new FileInputStream(Game.locOfSavedGame + CHUNKS_FOLDER + Game.currentLevel + "Level.sv"));
			currentLevelData = kryo.readObject(input, CurrentLevelData.class);
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Helpers.collectibleDataToCollectibleController(currentLevelData);
	}
	
	public Block[][] loadChunk(int chunkX, int chunkY) {
		Chunk chunk = new Chunk();
		
		Kryo kryo = new Kryo();
		
		try {
			Input input = new Input(new FileInputStream(Game.locOfSavedGame + CHUNKS_FOLDER + Game.currentLevel +chunkX + "-" + chunkY + ".sv"));
			chunk = kryo.readObject(input, Chunk.class);
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Helpers.chunkToBlockArray(chunk);
	}
	
	public void saveChest(Slot[] slot, int absX, int absY) {
		ChestData chestData = new ChestData();
		
		
		for(int x = 0; x < slot.length; x++) {
			chestData.itemIds[x] = slot[x].item.id;
			chestData.itemStackSizes[x] = slot[x].item.stackSize;
			chestData.itemUses[x] = slot[x].item.uses;
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