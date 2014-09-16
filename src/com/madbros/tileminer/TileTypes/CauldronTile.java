package com.madbros.tileminer.TileTypes;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Block;
import com.madbros.tileminer.Game;
import com.madbros.tileminer.Items.Item;
import com.madbros.tileminer.Items.NoItem;
import com.madbros.tileminer.Slots.CauldronSlot;
import com.madbros.tileminer.Slots.CraftedSlot;
import com.madbros.tileminer.Slots.FuelSlot;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Margin;
import com.madbros.tileminer.Utils.Rect;

public class CauldronTile extends CollisionTile {
	//public int test = 0;
	public int cauldronFuel = 0;
	public int cauldronMaxFuel = 0;
	public int cauldronBuildTime = 10;
	public Item possiblyCraftableItem = new NoItem();
	public boolean cauldronIsBurning = false;
	public int timeCheck = 0;
	
	public CauldronSlot[] cauldronSlots = new CauldronSlot[1];
	public FuelSlot[] fuelSlot = new FuelSlot[1];
	public CraftedSlot[] craftedSlot = new CraftedSlot[1];
	
	public boolean isCraftableItem() {
		if(cauldronSlots[0].item.id == 0) {
			return false;
		} else if(craftedSlot[0].item.id == 0 || craftedSlot[0].item.id == cauldronSlots[0].item.id){
			return true;
		} else {
			return false;
		}
	}
	
	public CauldronTile() {
		super();
		currentSpriteId = 0;
		sprites = Sprites.cauldronStatic;
		margin = new Margin(1, 0, 1, 0);
		id = CAULDRON_TILE;
		layer = OBJECT_LAYER;
		z = Z_OBJECT;
		isDiggable = false;
		isAutoTileable = false;
		autoTile = 0;
		isBreakable = true;
		isUseable = true;
		currentHp = 1;
		maxHp = 1;
		

		cauldronSlots[0] = new CauldronSlot(540, 448);
		
		fuelSlot[0] = new FuelSlot(540, 546);
		
		
		craftedSlot[0] = new CraftedSlot(645, 493);
		
	}
	
	@Override
	public void update(int x, int y) {
//		TODO need to account for when crafted slot does not = the new item being created...
		//cauldronSlots[0].boilAnotherItemIfPossible(this, this.cauldronSlots, this.craftedSlot);
		if(this.cauldronIsBurning == false && isCraftableItem()) {
			fuelSlot[0].checkCauldronFuel(this, cauldronSlots, craftedSlot);
		}
		if(cauldronIsBurning == true) {
			sprites = Sprites.cauldronAnimation;
			if(timeCheck <= 0) {
				timeCheck = 30; //Resets the half a second loop...
				cauldronFuel = cauldronFuel - 1;
				if(isCraftableItem()) {
					cauldronBuildTime = cauldronBuildTime - 1;
				}
				if(cauldronFuel <= 0) {
					cauldronIsBurning = false;
					if(isCraftableItem()) {
						fuelSlot[0].checkCauldronFuel(this, cauldronSlots, craftedSlot);
					}
					
				} else if(cauldronBuildTime <= 0 && isCraftableItem()) { 
					cauldronBuildTime = 10;
					if(craftedSlot[0].item.id == 0) {
						craftedSlot[0].item = ITEM_HASH.get(cauldronSlots[0].item.id).createNew();
						craftedSlot[0].item.stackSize = 1;
					} else {
						craftedSlot[0].item.stackSize ++;
								//craftedSlot[0].item.stackSize + 1;
					}
					//Remove the items from the ingredients area
					cauldronSlots[0].item.stackSize = cauldronSlots[0].item.stackSize - 1;
					if(cauldronSlots[0].item.stackSize < 1) {
						cauldronSlots[0].item = new NoItem();
					}
				//cauldronSlots[0].boilAnotherItemIfPossible(this, cauldronSlots, craftedSlot);
					
				} else if(!isCraftableItem()) {
					cauldronBuildTime = 10;
				}
				
			} else {
				timeCheck = timeCheck -1;
			}
			
		}else {
			sprites = Sprites.cauldronStatic;
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
			Game.inventory.cauldronOn = true;
			Game.inventory.currentWorkSpace = CAULDRON_WORKSPACE;
			Game.inventory.craftingMenu.changeToBrewingSlots();
			Game.inventory.craftingMenu.currentCraftableList = Game.inventory.craftingMenu.brewableList;
			//Game.inventory.furnace = this;
			//Game.inventory.invFurnace = furnaceSlots;
			Game.inventory.currentInvActiveBlockX = x;
			Game.inventory.currentInvActiveBlockY = y;
//			File f = new File(Game.locOfSavedGame + CHESTS_FOLDER + x + "-" + y + ".sv");
//			if(f.exists()) {
//				Game.level.saveGame.loadChest(x, y);
//			}
			Game.toggleCauldronState();
			
		}
	}
	
	public Tile createNew() {
		return new CauldronTile();
	}
	
	public void deleteMe(int x, int y, Block[][] activeBlocks) {
		this.cauldronSlots[0].deleteQueue2();
		Block b = activeBlocks[x][y];
		b.layers[OBJECT_LAYER] = new NoTile();
		b.layers[OBJECT_LAYER].setCollisionRect(b.absRect);
		b.collisionTile = null;
		Rect collectibleRect = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
		Item item = ITEM_HASH.get(CAULDRON).createNew();
		Game.collectibleController.add(CAULDRON, Sprites.sprites.get(Sprites.CAULDRON_SINGLE), collectibleRect, 1, item.maxUses);
		
		for(int i = 0; i < fuelSlot.length; i++) {
			if(fuelSlot[i].item.id != 0) {
				Rect collectibleRect2 = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
				item = ITEM_HASH.get(fuelSlot[i].item.id).createNew();
				Game.collectibleController.add(fuelSlot[i].item.id, fuelSlot[i].item.sprite, collectibleRect2, fuelSlot[i].item.stackSize, item.maxUses);
			}
		}
		if(craftedSlot[0].item.id != 0) {
			Rect collectibleRect3 = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
			item = ITEM_HASH.get(craftedSlot[0].item.id).createNew();
			Game.collectibleController.add(craftedSlot[0].item.id, craftedSlot[0].item.sprite, collectibleRect3, craftedSlot[0].item.stackSize, item.maxUses);
			
		}
	}
}
