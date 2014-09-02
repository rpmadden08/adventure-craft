package com.madbros.tileminer.TileTypes;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Block;
import com.madbros.tileminer.Game;
import com.madbros.tileminer.Items.Item;
import com.madbros.tileminer.Items.NoItem;
import com.madbros.tileminer.Slots.CraftedSlot;
import com.madbros.tileminer.Slots.FuelSlot;
import com.madbros.tileminer.Slots.FurnaceSlot;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Margin;
import com.madbros.tileminer.Utils.Rect;

public class FurnaceTile extends CollisionTile {
	//public int test = 0;
	public int furnaceFuel = 0;
	public int furnaceMaxFuel = 0;
	public int furnaceBuildTime = 10;
	public Item possiblyCraftableItem = new NoItem();
	public boolean furnaceIsBurning = false;
	public int timeCheck = 0;	
	
	public FurnaceSlot[] furnaceSlots = new FurnaceSlot[1];
	public FuelSlot[] fuelSlot = new FuelSlot[1];
	public CraftedSlot[] craftedSlot = new CraftedSlot[1];
	
	//public boolean isCraftableItem = false;
	
	public boolean isCraftableItem() {
		if(furnaceSlots[0].item.id == 0) {
			return false;
		} else if(craftedSlot[0].item.id == 0 || craftedSlot[0].item.id == furnaceSlots[0].item.id){
			return true;
		} else {
			return false;
		}
	}
	
	public FurnaceTile() {
		super();
		currentSpriteId = 0;
		sprites = Sprites.furnaceStatic;
		margin = new Margin(0, 0, 0, 5);
		id = FURNACE_TILE;
		layer = OBJECT_LAYER;
		z = Z_OBJECT;
		isDiggable = false;
		isAutoTileable = false;
		autoTile = 0;
		isBreakable = true;
		isUseable = true;
		currentHp = 1;
		maxHp = 1;
		furnaceSlots[0] = new FurnaceSlot(540, 448);
		fuelSlot[0] = new FuelSlot(540, 546);
		
		craftedSlot[0] = new CraftedSlot(645, 493);
		
	}
	
	@Override
	public void update(int x, int y) {
		if(this.furnaceIsBurning == false && isCraftableItem()) {
			fuelSlot[0].checkFuel(this, furnaceSlots, craftedSlot);
		}
		if(furnaceIsBurning == true) {
			sprites = Sprites.furnaceAnimation;
			if(timeCheck <= 0) {
				timeCheck = 30; //Resets the half a second loop...
				furnaceFuel = furnaceFuel - 1;
				if(isCraftableItem()) {
					furnaceBuildTime = furnaceBuildTime - 1;
				}
				if(furnaceFuel <= 0) {
					furnaceIsBurning = false;
					if(isCraftableItem()) {
						fuelSlot[0].checkFuel(this, furnaceSlots, craftedSlot);
					}
					
				} else if(furnaceBuildTime <= 0 && isCraftableItem()) {
					furnaceBuildTime = 10;
					if(craftedSlot[0].item.id == EMPTY) {
						craftedSlot[0].item = ITEM_HASH.get(furnaceSlots[0].item.id).createNew();
						craftedSlot[0].item.stackSize = 1;						
					} else {
						craftedSlot[0].item.stackSize +=1;
					}
					
					furnaceSlots[0].item.stackSize = furnaceSlots[0].item.stackSize - 1;
					if(furnaceSlots[0].item.stackSize < 1) {
						furnaceSlots[0].item = new NoItem();
					}
				} else if(!isCraftableItem()) {
					furnaceBuildTime = 10;
				}
				
			} else {
				timeCheck = timeCheck -1;
			}
			
		}else {
			sprites = Sprites.furnaceStatic;
			//furnaceBuildTime = 10;
		}
	}
	
	@Override
	public void render(int x, int y) {
		sprites[0].draw(x, y, z);
	}
	
	@Override
	public void rightClicked() {
		if(Game.currentState.type == State.MAIN && Game.level.hasPlacedItemOnClick == false) {
			int x = activeBlocksX;
			int y = activeBlocksY;
			Game.inventory.furnaceOn = true;
			Game.inventory.currentWorkSpace = FURNACE_WORKSPACE;
			Game.inventory.craftingMenu.changeToBurningSlots();
			Game.inventory.craftingMenu.currentCraftableList = Game.inventory.craftingMenu.burnableList;
			//Game.inventory.furnace = this;
			//Game.inventory.invFurnace = furnaceSlots;
			Game.inventory.currentInvActiveBlockX = x;
			Game.inventory.currentInvActiveBlockY = y;
//			File f = new File(Game.locOfSavedGame + CHESTS_FOLDER + x + "-" + y + ".sv");
//			if(f.exists()) {
//				Game.level.saveGame.loadChest(x, y);
//			}
			Game.toggleFurnaceState();
			
		}
	}
	
	public Tile createNew() {
		return new FurnaceTile();
	}
	
	public void deleteMe(int x, int y, Block[][] activeBlocks) {
		this.furnaceSlots[0].deleteQueue2();
		Block b = activeBlocks[x][y];
		b.collisionTile = null;
		Block b2 = activeBlocks[x][y-1];
		b.layers[OBJECT_LAYER] = new NoTile();
		b2.layers[ABOVE_LAYER_1] = new NoTile();
		
		Rect collectibleRect = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
		Item item = ITEM_HASH.get(FURNACE).createNew();
		Game.collectibleController.add(FURNACE, Sprites.sprites.get(Sprites.FURNACE_SINGLE), collectibleRect, 1, item.maxUses);
		
		for(int i = 0; i < fuelSlot.length; i++) {
			if(fuelSlot[i].item.id != 0) {
				Rect collectibleRect2 = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
				item = ITEM_HASH.get(furnaceSlots[i].item.id).createNew();
				Game.collectibleController.add(fuelSlot[i].item.id, fuelSlot[i].item.sprite, collectibleRect2, fuelSlot[0].item.stackSize, item.maxUses);
			}
		}
		if(craftedSlot[0].item.id != 0) {
			Rect collectibleRect3 = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
			item = ITEM_HASH.get(craftedSlot[0].item.id).createNew();
			Game.collectibleController.add(craftedSlot[0].item.id, craftedSlot[0].item.sprite, collectibleRect3, craftedSlot[0].item.stackSize, item.maxUses);
			
		}
	}
}
