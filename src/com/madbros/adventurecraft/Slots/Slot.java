package com.madbros.adventurecraft.Slots;

import static com.madbros.adventurecraft.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Constants.State;
import com.madbros.adventurecraft.Items.*;
import com.madbros.adventurecraft.Sprites.*;
import com.madbros.adventurecraft.TileTypes.Furnace;
import com.madbros.adventurecraft.Utils.*;

public class Slot {
	private StaticSprite slotSprite = Sprites.sprites.get(Sprites.INVENTORY_MENU_SLOT);
	private StaticSprite highlighter = Sprites.sprites.get(Sprites.PIXEL);
	
	private Color highlightColor = new Color(1.0f, 1.0f, 1.0f, 0.2f);
	
	public Rect slotRect;
	
	public Item item;
	public int type;
	
	public boolean isHighlighted = false;
	
	public Slot(int x, int y) {
		item = new NoItem();
		Rect r = new Rect(x, y, INV_SLOT_SIZE, INV_SLOT_SIZE);
		slotRect = r;
	}
	
	public void render() {
		slotSprite.draw(slotRect, Z_INV_SLOTS);
		
		if(isHighlighted) {
			Sprites.pixel.setColor(highlightColor);
			highlighter.draw(slotRect, Z_INV_HIGHLIGHT);
			Sprites.pixel.setColor(Color.WHITE);
		}
		
		item.render(slotRect);
	}
	
	/* Handle Events */
	public void handleLeftClick(Inventory inv) {//Item heldItem, Slot[] invCrafting, Slot[]invCrafted, Inventory inv) {
		if(inv.heldItem.id == this.item.id && this.item.id != EMPTY) {
			int total = inv.heldItem.stackSize + this.item.stackSize;
			if(total > inv.heldItem.maxStackSize) {
				this.item.stackSize = this.item.maxStackSize;
				inv.heldItem.stackSize = total - inv.heldItem.maxStackSize;
			} else {
				this.item.stackSize = total;
				inv.heldItem = new NoItem();
			}
		} else {
			swapItems(inv);
		}
		if(inv.craftingTableOn) {
			handleAdditional(inv.invTable, inv.invCrafted);
		} else if(inv.furnaceOn) {
			handleAdditional2((Furnace)Game.level.activeBlocks[inv.currentInvActiveBlockX][inv.currentInvActiveBlockY].layers[OBJECT_LAYER], Game.level.activeBlocks[inv.currentInvActiveBlockX][inv.currentInvActiveBlockY].layers[OBJECT_LAYER].furnaceSlots, Game.level.activeBlocks[inv.currentInvActiveBlockX][inv.currentInvActiveBlockY].layers[OBJECT_LAYER].craftedSlot);
		} else {
			handleAdditional(inv.invCrafting, inv.invCrafted);
		}
	}
	
	public void handleRightClick(Inventory inv) {
		if(inv.heldItem.id != EMPTY && this.item.id == EMPTY) {
			inv.heldItem.stackSize -= 1;
			this.item = ITEM_HASH.get(inv.heldItem.id).createNew();
			if(inv.heldItem.stackSize < 1) inv.heldItem = new NoItem();
		} else if(inv.heldItem.id != EMPTY && inv.heldItem.id == this.item.id) {
			if(this.item.stackSize < this.item.maxStackSize) {
				inv.heldItem.stackSize -= 1;
				this.item.stackSize += 1;
				if(inv.heldItem.stackSize < 1) inv.heldItem = new NoItem();
			}
		} else if(inv.heldItem.id == EMPTY && this.item.stackSize > 1) {
			inv.heldItem = ITEM_HASH.get(this.item.id).createNew();
			inv.heldItem.stackSize = (int)Math.ceil(this.item.stackSize / 2f);
			this.item.stackSize = this.item.stackSize / 2;
		} else {
			swapItems(inv);
		}
		
		if(inv.craftingTableOn) {
			handleAdditional(inv.invTable, inv.invCrafted);
		} else if(inv.furnaceOn) {
			handleAdditional2((Furnace)Game.level.activeBlocks[inv.currentInvActiveBlockX][inv.currentInvActiveBlockY].layers[OBJECT_LAYER], Game.level.activeBlocks[inv.currentInvActiveBlockX][inv.currentInvActiveBlockY].layers[OBJECT_LAYER].furnaceSlots, Game.level.activeBlocks[inv.currentInvActiveBlockX][inv.currentInvActiveBlockY].layers[OBJECT_LAYER].craftedSlot);
		} else {
			handleAdditional(inv.invCrafting, inv.invCrafted);
		}
	}
	
	public void handleAdditional(Slot[] invCrafting, Slot[] invCrafted) { }
	
	public void handleAdditional2(Furnace furnace, Slot[] invCrafting, Slot[] invCrafted) { 
		craftAnotherItemIfPossible2(furnace, invCrafting, invCrafted);
	}
	/* Helpers */
	public void swapItems(Inventory inv) {
		Item temp = inv.heldItem;
		inv.heldItem = this.item;
		this.item = temp;
	}
	
	public void removeRecipeItemsFromCraftingSlots(int[] recipeCost, Slot[] invCrafting) {
		int[] itemPositionsRemovedAlready = new int[recipeCost.length];	//so you don't remove two planks from the same stack, for example
		for(int t = 0; t < itemPositionsRemovedAlready.length; t++) {  //MUST START AT -1 BECAUSE 0 is equal to the first crafting slot
			itemPositionsRemovedAlready[t] = -1;
		}
		for(int i = 0; i < recipeCost.length; i++) { 
			for(int j = 0; j < invCrafting.length; j++) { 
				if(invCrafting[j].item.id == recipeCost[i] && !Helpers.arrayDoesContainInt(itemPositionsRemovedAlready, j)) {
					invCrafting[j].item.stackSize -= 1;
					if(invCrafting[j].item.stackSize < 1) invCrafting[j].item = new NoItem();
					itemPositionsRemovedAlready[i] = j;
					break;
				}
			}
		}
	}
	
	public void craftAnotherItemIfPossible(Slot[] invCrafting, Slot[] invCrafted) {
		for(int i = 0; i < invCrafting.length; i++) {
			if(invCrafting[i].item.id != EMPTY) {
				craftAnItemFromThisListIfPossible(invCrafting, invCrafted, invCrafting[i].item.itemsPossiblyCraftable);
				return;
			}
		}
		if(Game.currentState.type == State.INVENTORY && Game.inventory.furnaceOn == false) {
			invCrafted[0].item = new NoItem();
		}
	}
	
	public void craftAnotherItemIfPossible2(Furnace furnace, Slot[] invCrafting, Slot[] invCrafted) {
		if(invCrafting[0].item.id != EMPTY) {
			craftAnItemFromThisListIfPossible2(furnace, invCrafting, invCrafted, invCrafting[0].item.itemsPossiblyCraftable);
			return;
		} else {
			furnace.isCraftableItem = false;
		}
	}
	
	public void craftAnItemFromThisListIfPossible(Slot[] invCrafting, Slot[] invCrafted, int[] itemsPossiblyCraftable) {
		for(int i = 0; i < itemsPossiblyCraftable.length; i++) {
			Item possiblyCraftableItem = ITEM_HASH.get(itemsPossiblyCraftable[i]);
			if(possiblyCraftableItem.isValidRecipe(Game.inventory.invCrafting)|| possiblyCraftableItem.isValidTableRecipe(Game.inventory.invTable)) {
				invCrafted[0].item = possiblyCraftableItem.createNew();
				invCrafted[0].item.stackSize = invCrafted[0].item.numberProducedByCrafting;
				return;
			}
		}
			invCrafted[0].item = new NoItem();
		
	}
	
	public void craftAnItemFromThisListIfPossible2(Furnace furnace, Slot[] invCrafting, Slot[] invCrafted, int[] itemsPossiblyCraftable) {
		for(int i = 0; i < itemsPossiblyCraftable.length; i++) {
			//Furnace furnace = (Furnace) Game.level.activeBlocks[Game.inventory.currentInvActiveBlockX][Game.inventory.currentInvActiveBlockY].layers[OBJECT_LAYER];
			Item possiblyCraftableItem = ITEM_HASH.get(itemsPossiblyCraftable[i]);
			if(possiblyCraftableItem.isValidFurnaceRecipe(furnace.furnaceSlots)) {
				furnace.isCraftableItem = true;
				furnace.possiblyCraftableItem = possiblyCraftableItem.createNew();
				
				if(furnace.furnaceIsBurning == false) {
					checkFuel(furnace, invCrafting, invCrafted);
				}
				//System.out.println("DID IT!");
				//furnace.furnaceBuildTime = 10;
				return;
			} else {
				furnace.isCraftableItem = false;
				//furnace.furnaceBuildTime = 10;
			}
		}
	}
	public void checkFuel(Furnace furnace, Slot[] invCrafting, Slot[] invCrafted) { 
		if(invCrafting[1].item.isFuelSource) {
			furnace.furnaceIsBurning = true;
			furnace.furnaceFuel = furnace.furnaceSlots[1].item.fuelAmount;
			furnace.furnaceMaxFuel = furnace.furnaceSlots[1].item.fuelAmount;
			
			
			furnace.furnaceSlots[1].item.stackSize = furnace.furnaceSlots[1].item.stackSize - 1;
			if(furnace.furnaceSlots[1].item.stackSize <= 0) {
				furnace.furnaceSlots[1].item = new NoItem();
			}
		} else {
			furnace.furnaceIsBurning = false;
		}
	}
	
}