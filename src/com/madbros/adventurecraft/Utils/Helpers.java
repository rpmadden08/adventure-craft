package com.madbros.adventurecraft.Utils;

import static com.madbros.adventurecraft.Constants.*;
import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Slots.*;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.TileTypes.Cauldron;
import com.madbros.adventurecraft.TileTypes.Furnace;
import com.madbros.adventurecraft.TileTypes.Tile;

public class Helpers {		
	//wrappers for mouse stuff since display thinks y starts at the bottom...
	public static int getX() {
		return Game.currentState.input.mouseX;
	}
	
	public static int getY() {
		return Game.currentState.input.mouseY; //Game.currentScreenSizeY - Mouse.getY();
	}
	
	public static Rect getMouseRect() {
		return new Rect(getX(), getY(), 1, 1);
	}
	
	public static boolean containsXNumberOfItemsInSlots(int x, int itemId, Slot[] slots) {
		int count = 0;
		for(int i = 0; i < slots.length; i++) {
			if(slots[i].item.id == itemId) count++;
		}
		
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
		for(int x = 0; x < CHUNK_SIZE; x++) {
			for(int y = 0; y < CHUNK_SIZE; y++) {
				Tile[] t = new Tile[23];
				for(int i = 0; i < 23; i++) {
					int id = chunk.ids[x][y][i];	
					t[i] = TILE_HASH.get(id).createNew();
					t[i].currentSpriteId = chunk.currentTextures[x][y][i];
					t[i].timeCreated = chunk.timeCreated[x][y][i];
					if(chunk.ids[x][y][i] == FURNACE) {
						int id0 = chunk.furnaceInts[furnInt][4];
						int id1 = chunk.furnaceInts[furnInt][5];
						//t[i] = (Furnace) t[i];
						Furnace tempTile = (Furnace) t[i];
						
						tempTile.furnaceSlots[0].item = ITEM_HASH.get(id0).createNew();						
						tempTile.furnaceSlots[1].item = ITEM_HASH.get(id1).createNew();
						tempTile.furnaceSlots[0].item.stackSize = chunk.furnaceInts[furnInt][6];
						tempTile.furnaceSlots[1].item.stackSize = chunk.furnaceInts[furnInt][7];
						
						tempTile.furnaceFuel = chunk.furnaceInts[furnInt][0];
						tempTile.furnaceMaxFuel = chunk.furnaceInts[furnInt][1];
						tempTile.furnaceBuildTime = chunk.furnaceInts[furnInt][2];
						tempTile.furnaceIsBurning = chunk.furnaceBooleans[furnInt][0];
						tempTile.isCraftableItem = chunk.furnaceBooleans[furnInt][1];
						int possiblyCraftableId = chunk.furnaceInts[furnInt][3];
						tempTile.possiblyCraftableItem = ITEM_HASH.get(possiblyCraftableId).createNew();
						t[i] = tempTile;
						furnInt = furnInt +1;
					} else if(chunk.ids[x][y][i] == CAULDRON) {
						int id0 = chunk.cauldronInts[furnInt][4];
						int id1 = chunk.cauldronInts[furnInt][5];
						int id2 = chunk.cauldronInts[furnInt][6];
						int id3 = chunk.cauldronInts[furnInt][7];
						//t[i] = (Furnace) t[i];
						Cauldron tempTile = (Cauldron) t[i];
						
						tempTile.cauldronSlots[0].item = ITEM_HASH.get(id0).createNew();						
						tempTile.cauldronSlots[1].item = ITEM_HASH.get(id1).createNew();
						tempTile.cauldronSlots[2].item = ITEM_HASH.get(id2).createNew();						
						tempTile.cauldronSlots[3].item = ITEM_HASH.get(id3).createNew();
						tempTile.cauldronSlots[0].item.stackSize = chunk.cauldronInts[furnInt][8];
						tempTile.cauldronSlots[1].item.stackSize = chunk.cauldronInts[furnInt][9];
						tempTile.cauldronSlots[2].item.stackSize = chunk.cauldronInts[furnInt][10];
						tempTile.cauldronSlots[3].item.stackSize = chunk.cauldronInts[furnInt][11];
						
						tempTile.cauldronFuel = chunk.cauldronInts[furnInt][0];
						tempTile.cauldronMaxFuel = chunk.cauldronInts[furnInt][1];
						tempTile.cauldronBuildTime = chunk.cauldronInts[furnInt][2];
						tempTile.cauldronIsBurning = chunk.cauldronBooleans[furnInt][0];
						tempTile.isCraftableItem = chunk.cauldronBooleans[furnInt][1];
						int possiblyCraftableId = chunk.cauldronInts[furnInt][3];
						tempTile.possiblyCraftableItem = ITEM_HASH.get(possiblyCraftableId).createNew();
						t[i] = tempTile;
						furnInt = furnInt +1;
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
		System.out.println("Helpers Item Slot 1 has an ID of:  "+chestData.itemIds[0]);
		for(int x = 0; x < Game.inventory.invChest.length; x++) {
			Game.inventory.invChest[x].item = ITEM_HASH.get(chestData.itemIds[x]).createNew();
			Game.inventory.invChest[x].item.stackSize = chestData.itemStackSizes[x];
			Game.inventory.invChest[x].item.uses = chestData.itemUses[x];
		}
	}
	
	public static void println(String s) {
		Game.debugger.displayedExtra = s;
	}
//	public Rect sRectToARect(Rect sRect) {
//		return new Rect(Game.hero.aRect.x - (Game.hero.sRect.x - sRect.x), Game.hero.aRect.y - (Game.hero.sRect.y - sRect.y), sRect.w, sRect.h);
//	}
}
