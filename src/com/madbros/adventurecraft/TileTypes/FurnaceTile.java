package com.madbros.adventurecraft.TileTypes;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Block;
import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Items.Item;
import com.madbros.adventurecraft.Items.NoItem;
import com.madbros.adventurecraft.Slots.CraftedSlot;
import com.madbros.adventurecraft.Slots.CraftingSlot;
import com.madbros.adventurecraft.Slots.FurnaceSlot;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Utils.Margin;
import com.madbros.adventurecraft.Utils.Rect;

public class FurnaceTile extends CollisionTile {
	//public int test = 0;
	public int furnaceFuel = 0;
	public int furnaceMaxFuel = 0;
	public int furnaceBuildTime = 10;
	public Item possiblyCraftableItem = new NoItem();
	public boolean furnaceIsBurning = false;
	public int timeCheck = 0;	
	
	public FurnaceSlot[] furnaceSlots = new FurnaceSlot[2];
	public CraftedSlot[] craftedSlot = new CraftedSlot[1];
	
	public boolean isCraftableItem = false;
	
	public FurnaceTile() {
		super();
		currentSpriteId = 0;
		sprites = Sprites.furnaceStatic;
		margin = new Margin(0, 0, 0, 0);
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
		furnaceSlots[0] = new FurnaceSlot(INV_CRAFTING_RECT.x + (INV_SLOT_SIZE)+170, INV_CRAFTING_RECT.y + (INV_SLOT_SIZE)-INV_SLOT_SIZE);
		furnaceSlots[1] = new FurnaceSlot(INV_CRAFTING_RECT.x + (INV_SLOT_SIZE)+170, INV_CRAFTING_RECT.y + (INV_SLOT_SIZE)+INV_SLOT_SIZE);
		
		craftedSlot[0] = new CraftedSlot(INV_CRAFTING_RECT.x2() + 192, INV_CRAFTING_RECT.y+INV_SLOT_SIZE-20);
		
	}
	
	@Override
	public void update(int x, int y) {
	
//		System.out.println("FUEL:  "+furnaceFuel);
//		System.out.println("MAX FUEL:  "+furnaceMaxFuel);
		if(furnaceIsBurning == true) {
			sprites = Sprites.furnaceAnimation;
			if(timeCheck <= 0) {
				timeCheck = 30; //Resets the half a second loop...
//				System.out.println("furnaceFuel Left: "+furnaceFuel);
//				System.out.println("furnaceBuildTime Left: "+furnaceBuildTime);
				furnaceFuel = furnaceFuel - 1;
				if(isCraftableItem) {
					furnaceBuildTime = furnaceBuildTime - 1;
				}
				if(furnaceFuel <= 0) {
					furnaceIsBurning = false;
					if(isCraftableItem) {
						furnaceSlots[1].checkFuel(this, furnaceSlots, craftedSlot);
					}
					
				} else if(furnaceBuildTime <= 0 && isCraftableItem == true) {
					furnaceBuildTime = 10;
					if(craftedSlot[0].item.id == 0) {
						craftedSlot[0].item = possiblyCraftableItem.createNew();
						//craftedSlot[0].item.stackSize = 0;						
					} else {
						//System.out.println(furnaceIsBurning);
						craftedSlot[0].item.stackSize = craftedSlot[0].item.stackSize + craftedSlot[0].item.numberProducedByCrafting;
					}
					//
					if(furnaceSlots[0].item.stackSize >1) {
						furnaceSlots[0].item.stackSize = furnaceSlots[0].item.stackSize -1;
					} else {
						furnaceSlots[0].item = new NoItem();
					}
					furnaceSlots[0].handleAdditional2(this, furnaceSlots, craftedSlot);
					
				} else if(isCraftableItem == false) {
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
		Block b = activeBlocks[x][y];
		b.collisionTile = null;
		Block b2 = activeBlocks[x][y-1];
		b.layers[OBJECT_LAYER] = new NoTile();
		b2.layers[ABOVE_LAYER_1] = new NoTile();
		
		Rect collectibleRect = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
		Item item = ITEM_HASH.get(FURNACE).createNew();
		Game.collectibleController.add(FURNACE, Sprites.sprites.get(Sprites.FURNACE_SINGLE), collectibleRect, 1, item.maxUses);
		
		for(int i = 0; i < furnaceSlots.length; i++) {
			if(furnaceSlots[i].item.id != 0) {
				Rect collectibleRect2 = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
				item = ITEM_HASH.get(furnaceSlots[i].item.id).createNew();
				Game.collectibleController.add(furnaceSlots[i].item.id, furnaceSlots[i].item.sprite, collectibleRect2, furnaceSlots[0].item.stackSize, item.maxUses);
			}
		}
		if(craftedSlot[0].item.id != 0) {
			Rect collectibleRect3 = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
			item = ITEM_HASH.get(craftedSlot[0].item.id).createNew();
			Game.collectibleController.add(craftedSlot[0].item.id, craftedSlot[0].item.sprite, collectibleRect3, craftedSlot[0].item.stackSize, item.maxUses);
			
		}
	}
}
