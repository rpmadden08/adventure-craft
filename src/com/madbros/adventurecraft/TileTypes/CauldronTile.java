package com.madbros.adventurecraft.TileTypes;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Block;
import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Items.Item;
import com.madbros.adventurecraft.Items.NoItem;
import com.madbros.adventurecraft.Slots.CraftedSlot;
import com.madbros.adventurecraft.Slots.CraftingSlot;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Utils.Margin;
import com.madbros.adventurecraft.Utils.Rect;

public class CauldronTile extends CollisionTile {
	//public int test = 0;
	public int cauldronFuel = 0;
	public int cauldronMaxFuel = 0;
	public int cauldronBuildTime = 10;
	public Item possiblyCraftableItem = new NoItem();
	public boolean cauldronIsBurning = false;
	public int timeCheck = 0;
	
	public CraftingSlot[] cauldronSlots = new CraftingSlot[4];
	public CraftedSlot[] craftedSlot = new CraftedSlot[1];
	
	public boolean isCraftableItem = false;
	
	public CauldronTile() {
		super();
		currentSpriteId = 0;
		sprites = Sprites.cauldronStatic;
		margin = new Margin(0, 0, 0, 0);
		id = CAULDRON;
		layer = OBJECT_LAYER;
		z = Z_OBJECT;
		isDiggable = false;
		isAutoTileable = false;
		autoTile = 0;
		isBreakable = true;
		isUseable = true;
		currentHp = 1;
		maxHp = 1;
		

			cauldronSlots[0] = new CraftingSlot(INV_CRAFTING_RECT.x + (INV_SLOT_SIZE)- (INV_SLOT_SIZE/2), INV_CRAFTING_RECT.y + (INV_SLOT_SIZE)-INV_SLOT_SIZE);
			cauldronSlots[1] = new CraftingSlot(INV_CRAFTING_RECT.x + (INV_SLOT_SIZE), INV_CRAFTING_RECT.y + (INV_SLOT_SIZE)-INV_SLOT_SIZE- (INV_SLOT_SIZE));
			cauldronSlots[2] = new CraftingSlot(INV_CRAFTING_RECT.x + (INV_SLOT_SIZE)+ (INV_SLOT_SIZE/2), INV_CRAFTING_RECT.y + (INV_SLOT_SIZE)-INV_SLOT_SIZE);
			
			cauldronSlots[3] = new CraftingSlot(INV_CRAFTING_RECT.x + (INV_SLOT_SIZE), INV_CRAFTING_RECT.y + (INV_SLOT_SIZE)+INV_SLOT_SIZE);
			
		
		craftedSlot[0] = new CraftedSlot(INV_CRAFTING_RECT.x2() + 75, INV_CRAFTING_RECT.y+INV_SLOT_SIZE);
		
	}
	
	@Override
	public void update(int x, int y) {
//		System.out.println("FUEL:  "+furnaceFuel);
//		System.out.println("MAX FUEL:  "+furnaceMaxFuel);
		if(cauldronIsBurning == true) {
			sprites = Sprites.cauldronAnimation;
			if(timeCheck <= 0) {
				timeCheck = 30; //Resets the half a second loop...
//				System.out.println("furnaceFuel Left: "+furnaceFuel);
//				System.out.println("furnaceBuildTime Left: "+furnaceBuildTime);
				cauldronFuel = cauldronFuel - 1;
				if(isCraftableItem) {
					cauldronBuildTime = cauldronBuildTime - 1;
				}
				if(cauldronFuel <= 0) {
					cauldronIsBurning = false;
					if(isCraftableItem) {
						cauldronSlots[3].checkCauldronFuel(this, cauldronSlots, craftedSlot);
					}
					
				} else if(cauldronBuildTime <= 0 && isCraftableItem == true) { 
					cauldronBuildTime = 10;
					if(craftedSlot[0].item.id == 0) {
						craftedSlot[0].item = possiblyCraftableItem.createNew();						
					} else {
						craftedSlot[0].item.stackSize = craftedSlot[0].item.stackSize + craftedSlot[0].item.numberProducedByCrafting;
					}
					//Remove the items from the ingredients area
					craftedSlot[0].removeRecipeItemsFromCraftingSlots(craftedSlot[0].item.craftCost, cauldronSlots);
					cauldronSlots[0].handleAdditionalCauldron(this, cauldronSlots, craftedSlot);
					cauldronSlots[1].handleAdditionalCauldron(this, cauldronSlots, craftedSlot);
					cauldronSlots[2].handleAdditionalCauldron(this, cauldronSlots, craftedSlot);
					
				} else if(isCraftableItem == false) {
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
			//Game.inventory.furnace = this;
			//Game.inventory.invFurnace = furnaceSlots;
			Game.inventory.currentInvActiveBlockX = x;
			Game.inventory.currentInvActiveBlockY = y;
//			File f = new File(Game.locOfSavedGame + CHESTS_FOLDER + x + "-" + y + ".sv");
//			if(f.exists()) {
//				Game.level.saveGame.loadChest(x, y);
//			}
			Game.toggleInventoryState();
			
		}
	}
	
	public Tile createNew() {
		return new CauldronTile();
	}
	
	public void deleteMe(int x, int y, Block[][] activeBlocks) {
		Block b = activeBlocks[x][y];
		b.layers[OBJECT_LAYER] = new NoTile();
		Rect collectibleRect = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
		Game.collectibleController.add(CAULDRON_ITEM, Sprites.sprites.get(Sprites.CAULDRON_SINGLE), collectibleRect, 1);
		for(int i = 0; i < cauldronSlots.length; i++) {
			if(cauldronSlots[i].item.id != 0) {
				Rect collectibleRect2 = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
				Game.collectibleController.add(cauldronSlots[i].item.id, cauldronSlots[i].item.sprite, collectibleRect2, 1);
			}
		}
		if(craftedSlot[0].item.id != 0) {
			Rect collectibleRect3 = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
			Game.collectibleController.add(craftedSlot[0].item.id, craftedSlot[0].item.sprite, collectibleRect3, 1);
			
		}
	}
}
