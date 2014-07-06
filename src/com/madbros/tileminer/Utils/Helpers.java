package com.madbros.tileminer.Utils;

import static com.madbros.tileminer.Constants.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.madbros.tileminer.*;
import com.madbros.tileminer.GameObjects.Hero;
import com.madbros.tileminer.GameStates.GameState;
import com.madbros.tileminer.Items.Item;
import com.madbros.tileminer.Slots.*;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.TileTypes.CauldronTile;
import com.madbros.tileminer.TileTypes.FurnaceTile;
import com.madbros.tileminer.TileTypes.Tile;

public class Helpers {		
	//wrappers for mouse stuff since display thinks y starts at the bottom...
	public static int getX() {
		return GameState.input.mouseX;
	}
	
	public static int getY() {
		return GameState.input.mouseY; //Game.currentScreenSizeY - Mouse.getY();
	}
	
	public static Rect getMouseRect() {
		return new Rect(getX(), getY(), 1, 1);
	}
	
	public static Point getOffsetPoint(Hero hero, Block firstBlockInActiveBlocks) {
		return new Point(((hero.absRect.x - Game.currentScreenSizeX / 2) - firstBlockInActiveBlocks.absRect.x) % TILE_SIZE,
						 ((hero.absRect.y - Game.currentScreenSizeY / 2) - firstBlockInActiveBlocks.absRect.y) % TILE_SIZE);
	}
	
	public static Rect getRenderRect(Hero hero, Block firstBlockInActiveBlocks) {
		return new Rect((int)Math.ceil(((hero.absRect.x - Game.currentScreenSizeX / 2) - firstBlockInActiveBlocks.absRect.x) / TILE_SIZE) + RENDER_MARGIN,
						(int) Math.ceil(((hero.absRect.y - Game.currentScreenSizeY / 2) - firstBlockInActiveBlocks.absRect.y) / TILE_SIZE) + RENDER_MARGIN,
						(int)Math.ceil(Game.currentScreenSizeX / TILE_SIZE) + RENDER_MARGIN,
						(int)Math.ceil(Game.currentScreenSizeY / TILE_SIZE) + RENDER_MARGIN + 1);
	}
	
	public static boolean containsXNumberOfItemsInSlots(int x, int itemId, Slot[] slots) {
		int count = 0;
		for(int i = 0; i < slots.length; i++) {
			if(slots[i].item.id == itemId) count++;
			//System.out.println("ITEM ID: "+itemId+" = "+slots[i].item.id);
		}
		//System.out.println("ITEM ID: "+itemId+" = "+slots[i].item.id);
		if(count == x) {
			return true;
		}
		return false;
	}
	
	public static boolean arrayDoesContainInt(int[] a, int b) {
		for(int i = 0; i < a.length; i++) {
			if(a[i] == b) return true;
		}
		return false;
	}

	public static void drawRect(Rect r, float zLayer) {
		Sprites.pixel.draw(r.x, r.y, zLayer, r.w, 1);
		Sprites.pixel.draw(r.x, r.y, zLayer, 1, r.h);
		Sprites.pixel.draw(r.x2()-1, r.y, zLayer, 1, r.h);
		Sprites.pixel.draw(r.x, r.y2()-1, zLayer, r.w, 1);
	}
	
	public static Block[][] chunkToBlockArray(Chunk chunk) {
		Block[][] blockArray = new Block[CHUNK_SIZE][CHUNK_SIZE];
		int furnInt = 0;
		int cauldInt = 0;
		for(int x = 0; x < CHUNK_SIZE; x++) {
			for(int y = 0; y < CHUNK_SIZE; y++) {
				Tile[] t = new Tile[23];
				for(int i = 0; i < 23; i++) {
					int id = chunk.ids[x][y][i];	
					t[i] = TILE_HASH.get(id).createNew();
					t[i].currentSpriteId = chunk.currentTextures[x][y][i];
					t[i].timeCreated = chunk.timeCreated[x][y][i];
					if(chunk.ids[x][y][i] == FURNACE_TILE) {
						int id0 = chunk.furnaceInts[furnInt][4];
						int id1 = chunk.furnaceInts[furnInt][5];
						int id2 = chunk.furnaceInts[furnInt][8];
						//t[i] = (Furnace) t[i];
						FurnaceTile tempTile = (FurnaceTile) t[i];
						
						tempTile.furnaceSlots[0].item = ITEM_HASH.get(id0).createNew();						
						tempTile.fuelSlot[0].item = ITEM_HASH.get(id1).createNew();
						tempTile.furnaceSlots[0].item.stackSize = chunk.furnaceInts[furnInt][6];
						tempTile.fuelSlot[0].item.stackSize = chunk.furnaceInts[furnInt][7];
						tempTile.craftedSlot[0].item = ITEM_HASH.get(id2).createNew();
						tempTile.craftedSlot[0].item.stackSize = chunk.furnaceInts[furnInt][9];
						
						tempTile.furnaceFuel = chunk.furnaceInts[furnInt][0];
						tempTile.furnaceMaxFuel = chunk.furnaceInts[furnInt][1];
						tempTile.furnaceBuildTime = chunk.furnaceInts[furnInt][2];
						tempTile.furnaceIsBurning = chunk.furnaceBooleans[furnInt][0];
						//tempTile.isCraftableItem = chunk.furnaceBooleans[furnInt][1];
						int possiblyCraftableId = chunk.furnaceInts[furnInt][3];
						tempTile.possiblyCraftableItem = ITEM_HASH.get(possiblyCraftableId).createNew();
						t[i] = tempTile;
						furnInt = furnInt +1;
					} else if(chunk.ids[x][y][i] == CAULDRON_TILE) {
						int id0 = chunk.cauldronInts[cauldInt][4];
//						int id1 = chunk.cauldronInts[cauldInt][5];
//						int id2 = chunk.cauldronInts[cauldInt][6];
						int id3 = chunk.cauldronInts[cauldInt][5];
						int id4 = chunk.cauldronInts[cauldInt][8];
						//t[i] = (Furnace) t[i];
						CauldronTile tempTile = (CauldronTile) t[i];
						
						tempTile.cauldronSlots[0].item = ITEM_HASH.get(id0).createNew();						
//						tempTile.cauldronSlots[1].item = ITEM_HASH.get(id1).createNew();
//						tempTile.cauldronSlots[2].item = ITEM_HASH.get(id2).createNew();						
						tempTile.fuelSlot[0].item = ITEM_HASH.get(id3).createNew();
						tempTile.cauldronSlots[0].item.stackSize = chunk.cauldronInts[cauldInt][6];
//						tempTile.cauldronSlots[1].item.stackSize = chunk.cauldronInts[cauldInt][9];
//						tempTile.cauldronSlots[2].item.stackSize = chunk.cauldronInts[cauldInt][10];
						tempTile.fuelSlot[0].item.stackSize = chunk.cauldronInts[cauldInt][7];
						tempTile.craftedSlot[0].item = ITEM_HASH.get(id4).createNew();
						tempTile.craftedSlot[0].item.stackSize = chunk.cauldronInts[cauldInt][9];
						
						tempTile.cauldronFuel = chunk.cauldronInts[cauldInt][0];
						tempTile.cauldronMaxFuel = chunk.cauldronInts[cauldInt][1];
						tempTile.cauldronBuildTime = chunk.cauldronInts[cauldInt][2];
						tempTile.cauldronIsBurning = chunk.cauldronBooleans[cauldInt][0];
						//tempTile.isCraftableItem = chunk.cauldronBooleans[cauldInt][1];
						int possiblyCraftableId = chunk.cauldronInts[cauldInt][3];
						tempTile.possiblyCraftableItem = ITEM_HASH.get(possiblyCraftableId).createNew();
						t[i] = tempTile;
						cauldInt = cauldInt +1;
					}
				}
				
				long absX = chunk.absX[x][y];
				long absY = chunk.absY[x][y]; 
				boolean isUnfinished = chunk.isUnfinished[x][y];
				blockArray[x][y] = new Block(t, (int)absX, (int)absY, isUnfinished);
				blockArray[x][y].isUnfinished = isUnfinished;
			}
		}
		return blockArray;
	}
	
	public static void chestDataToSlotArray(ChestData chestData) {
		//Slot[] slots = new Slot[Game.inventory.invChest.length];
		//System.out.println("Helpers Item Slot 1 has an ID of:  "+chestData.itemIds[0]);
		for(int x = 0; x < Game.inventory.invChest.length; x++) {
			Game.inventory.invChest[x].item = ITEM_HASH.get(chestData.itemIds[x]).createNew();
			Game.inventory.invChest[x].item.stackSize = chestData.itemStackSizes[x];
			Game.inventory.invChest[x].item.uses = chestData.itemUses[x];
		}
	}
	
	public static void collectibleDataToCollectibleController(CurrentLevelData c) {
		System.out.println("Loading:  "+c.collectibleItemIds.length);
		
		for(int x = 0; x < c.collectibleItemIds.length; x++) {
			Item i = ITEM_HASH.get(c.collectibleItemIds[x]).createNew();
			Game.collectibleController.add(c.collectibleItemIds[x], i.sprite, new Rect(c.collectibleItemX[x], c.collectibleItemY[x], 32, 32), c.collectibleItemStackSize[x], c.collectibleItemUses[x]);
			
			
			//Make sure the collectibles bounce and bounced and bounced2 == true
			Game.collectibleController.collectibles.get(Game.collectibleController.collectibles.size()-1).bounced = true;
			Game.collectibleController.collectibles.get(Game.collectibleController.collectibles.size()-1).bounced2 = true;
		}
	}
	
	public static void println(String s) {
		Game.debugger.displayedExtra = s;
		//getRandomLoot(new int[]{SWORD}, new int[]{1},new int[]{0},new int[]{0}, 1, 5);
		//public int[] itemsPossiblyCraftable = {};
	}
	//getRandomLoot(new Item[])
	
	public static int getRandomMaxMin(int min, int max) {
		if(max == 1) {
			return 1;
		} else {
			int totalItems = max - min;
			Random rand = new Random();
			int totalItems2 = rand.nextInt(totalItems);
			totalItems = min +totalItems2;
			//System.out.println(totalItems);
			return totalItems;
		}
	}
	
	public static Item[] getRandomLoot(int possibleItems[], int[] probabilities, int[] stackSizeMin, int stackSizeMax[], int min, int max) {
		//figure out how many items will be in loot randomly
		int totalItems = max - min;
		Random rand = new Random();
		int totalItems2 = rand.nextInt(totalItems);
		totalItems = min +totalItems2;
		//System.out.println("TOTAL ITEMS:  "+totalItems);
		
	     Item[] items = new Item[totalItems];
	     
	     boolean prob1= false, prob2 = false, prob3 = false, prob4 = false;
	     for(int i = 0; i<probabilities.length; i++) {
	    	 if(probabilities[i] == 1) {
					prob1 = true;
	    	 } else  if(probabilities[i] == 2) {
					prob2 = true;
	    	 } else  if(probabilities[i] == 3) {
					prob3 = true;
	    	 } else  if(probabilities[i] == 4) {
					prob4 = true;
	    	 }
	     }
		for(int x = 0; x < totalItems; x++) {
			double randomPercent = rand.nextDouble();
			//double randomPercent = 0.323;
			//System.out.println(randomPercent);
			
			if(randomPercent < 0.001 && prob4 == true) {
				List<Item> itemList = new ArrayList<Item>();
				for(int i = 0; i<probabilities.length; i++) {
					if(probabilities[i] == 4) {
						itemList.add(ITEM_HASH.get(possibleItems[i]).createNew());
						itemList.get(itemList.size() - 1).stackSize = getRandomMaxMin(stackSizeMin[i], stackSizeMax[i]);
					}
				}
				int itemNumber = rand.nextInt(itemList.size());
				items[x] = itemList.get(itemNumber);
				items[x].stackSize = itemList.get(itemNumber).stackSize;
			}else if(randomPercent < 0.01 && prob3 == true) {
				List<Item> itemList = new ArrayList<Item>();
				for(int i = 0; i<probabilities.length; i++) {
					if(probabilities[i] == 3) {
						itemList.add(ITEM_HASH.get(possibleItems[i]).createNew());
						itemList.get(itemList.size() - 1).stackSize = getRandomMaxMin(stackSizeMin[i], stackSizeMax[i]);
					}
				}
				int itemNumber = rand.nextInt(itemList.size());
				items[x] = itemList.get(itemNumber);
				items[x].stackSize = itemList.get(itemNumber).stackSize;
			} else if(randomPercent < 0.3 && prob2 == true) {
				List<Item> itemList = new ArrayList<Item>();
				for(int i = 0; i<probabilities.length; i++) {
					if(probabilities[i] == 2) {
						itemList.add(ITEM_HASH.get(possibleItems[i]).createNew());
						itemList.get(itemList.size() - 1).stackSize = getRandomMaxMin(stackSizeMin[i], stackSizeMax[i]);
					}
				}
				int itemNumber = rand.nextInt(itemList.size());
				items[x] = itemList.get(itemNumber);
				items[x].stackSize = itemList.get(itemNumber).stackSize;
			} else if(prob1 == true && randomPercent >= 0.3) {
				List<Item> itemList = new ArrayList<Item>();
				for(int i = 0; i<probabilities.length; i++) {
					if(probabilities[i] == 1) {
						itemList.add(ITEM_HASH.get(possibleItems[i]).createNew());
						itemList.get(itemList.size() - 1).stackSize = getRandomMaxMin(stackSizeMin[i], stackSizeMax[i]);
					}
				}
				int itemNumber = rand.nextInt(itemList.size());
				items[x] = itemList.get(itemNumber);
				items[x].stackSize = itemList.get(itemNumber).stackSize;
			} else if(prob1 == true) {
				List<Item> itemList = new ArrayList<Item>();
				for(int i = 0; i<probabilities.length; i++) {
					if(probabilities[i] == 1) {
						itemList.add(ITEM_HASH.get(possibleItems[i]).createNew());
						itemList.get(itemList.size() - 1).stackSize = getRandomMaxMin(stackSizeMin[i], stackSizeMax[i]);
					}
				}
				int itemNumber = rand.nextInt(itemList.size());
				items[x] = itemList.get(itemNumber);
				items[x].stackSize = itemList.get(itemNumber).stackSize;
			} else if(prob2 == true) {
				List<Item> itemList = new ArrayList<Item>();
				for(int i = 0; i<probabilities.length; i++) {
					if(probabilities[i] == 2) {
						itemList.add(ITEM_HASH.get(possibleItems[i]).createNew());
						itemList.get(itemList.size() - 1).stackSize = getRandomMaxMin(stackSizeMin[i], stackSizeMax[i]);
					}
				}
				int itemNumber = rand.nextInt(itemList.size());
				items[x] = itemList.get(itemNumber);
				items[x].stackSize = itemList.get(itemNumber).stackSize;
			} else if(prob3 == true) {
				//System.out.println("Just as I thought...");
				List<Item> itemList = new ArrayList<Item>();
				for(int i = 0; i<probabilities.length; i++) {
					if(probabilities[i] == 3) {
						itemList.add(ITEM_HASH.get(possibleItems[i]).createNew());
						itemList.get(itemList.size() - 1).stackSize = getRandomMaxMin(stackSizeMin[i], stackSizeMax[i]);
					}
				}
				//System.out.println(itemList.size());
				int itemNumber = rand.nextInt(itemList.size());
				items[x] = itemList.get(itemNumber);
				items[x].stackSize = itemList.get(itemNumber).stackSize;
			} else {
				List<Item> itemList = new ArrayList<Item>();
				for(int i = 0; i<probabilities.length; i++) {
					if(probabilities[i] == 4) {
						itemList.add(ITEM_HASH.get(possibleItems[i]).createNew());
						itemList.get(itemList.size() - 1).stackSize = getRandomMaxMin(stackSizeMin[i], stackSizeMax[i]);
					}
				}
				int itemNumber = rand.nextInt(itemList.size());
				items[x] = itemList.get(itemNumber);
				items[x].stackSize = itemList.get(itemNumber).stackSize;
			}
		}
//		for(int i = 0; i<items.length; i++) {
//			//System.out.println(items[i].stackSize);
//		}
		return items;
	}
}
