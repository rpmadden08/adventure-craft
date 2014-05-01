package com.madbros.adventurecraft.Slots;

import static com.madbros.adventurecraft.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Constants.State;
import com.madbros.adventurecraft.Items.*;
import com.madbros.adventurecraft.Sprites.*;
import com.madbros.adventurecraft.TileTypes.CauldronTile;
import com.madbros.adventurecraft.TileTypes.FurnaceTile;
import com.madbros.adventurecraft.Utils.*;

public class Slot {
	public StaticSprite slotSprite = Sprites.sprites.get(Sprites.INVENTORY_MENU_SLOT);
	public StaticSprite highlighter = Sprites.sprites.get(Sprites.PIXEL);
	
	public Color highlightColor = new Color(1.0f, 1.0f, 1.0f, 0.2f);
	public boolean isInactive = false;
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
		if(Game.currentState.type == State.FURNACE) {
			FurnaceTile furnaceTile = (FurnaceTile) Game.level.activeBlocks[inv.currentInvActiveBlockX][inv.currentInvActiveBlockY].layers[OBJECT_LAYER];
			burnAnotherItemIfPossible(furnaceTile, furnaceTile.furnaceSlots, furnaceTile.craftedSlot);
		} else if(Game.currentState.type == State.CAULDRON) {
			CauldronTile cauldronTile = (CauldronTile) Game.level.activeBlocks[inv.currentInvActiveBlockX][inv.currentInvActiveBlockY].layers[OBJECT_LAYER];
			handleAdditionalCauldron(cauldronTile, cauldronTile.cauldronSlots, cauldronTile.craftedSlot);
		} else {
			handleAdditional(inv.invCrafting, inv.invCrafted);
		}
	}
	
	public void handleLeftClickCrafting(Inventory inv) {
		//System.out.println("Need to code what happens when clicked!");
		if(this.item.id == EMPTY) {
			inv.craftingMenu.currentPage = 0;
			inv.craftingMenu.currentCraftableList = inv.craftingMenu.craftableList;
		} else {
			inv.craftingMenu.currentPage = 0;
			inv.craftingMenu.currentCraftableList = this.item.itemsPossiblyCraftable;
			
		}
	}
	
	public void checkIfItemHasIngredients() {
		
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
		
		if(Game.currentState.type == State.FURNACE) {
			FurnaceTile furnaceTile = (FurnaceTile) Game.level.activeBlocks[inv.currentInvActiveBlockX][inv.currentInvActiveBlockY].layers[OBJECT_LAYER];
			burnAnotherItemIfPossible(furnaceTile, furnaceTile.furnaceSlots, furnaceTile.craftedSlot);
		} else if(Game.currentState.type == State.CAULDRON) {
			CauldronTile cauldronTile = (CauldronTile) Game.level.activeBlocks[inv.currentInvActiveBlockX][inv.currentInvActiveBlockY].layers[OBJECT_LAYER];
			handleAdditionalCauldron(cauldronTile, cauldronTile.cauldronSlots, cauldronTile.craftedSlot);
		} else {
			handleAdditional(inv.invCrafting, inv.invCrafted);
		}
	}
	
	public void handleAdditional(Slot[] invCrafting, Slot[] invCrafted) { }
	
	public void handleAdditional2(FurnaceTile furnace, Slot[] invCrafting, Slot[] invCrafted) { 
		
	}
	
	public void handleAdditionalCauldron(CauldronTile cauldron, Slot[] invCrafting, Slot[] invCrafted) { 
		craftAnotherItemIfPossibleCauldron(cauldron, invCrafting, invCrafted);
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
//		for(int i = 0; i < invCrafting.length; i++) {
//			if(invCrafting[i].item.id != EMPTY) {
//				craftAnItemFromThisListIfPossible(invCrafting, invCrafted, invCrafting[i].item.itemsPossiblyCraftable);
//				return;
//			}
//		}
		if(Game.currentState.type == State.FURNACE) {
			invCrafted[0].item = new NoItem();
		}
	}
	
	public void burnAnotherItemIfPossible(FurnaceTile furnace, Slot[] invCrafting, Slot[] invCrafted) {
		if(invCrafting[0].item.id != EMPTY) {
			craftAnItemFromThisListIfPossible2(furnace, invCrafting, invCrafted, invCrafting[0].item.itemsPossiblyBurnable);
			return;
		} else {
			furnace.isCraftableItem = false;
		}
	}
	
	public void craftAnotherItemIfPossibleCauldron(CauldronTile cauldron, Slot[] invCrafting, Slot[] invCrafted) {
		for(int i = 0; i < invCrafting.length-1; i++) {
			if(invCrafting[i].item.id != EMPTY) {
				craftAnItemFromThisListIfPossibleCauldron(cauldron, invCrafting, invCrafted, invCrafting[i].item.itemsPossiblyBrewable);
				return;
			} else {
				cauldron.isCraftableItem = false;
			}
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
	
	public void craftAnItemFromThisListIfPossible2(FurnaceTile furnace, Slot[] invCrafting, Slot[] invCrafted, int[] itemsPossiblyCraftable) {
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
	public void craftAnItemFromThisListIfPossibleCauldron(CauldronTile cauldron, Slot[] invCrafting, Slot[] invCrafted, int[] itemsPossiblyCraftable) {
		for(int i = 0; i < itemsPossiblyCraftable.length; i++) {
			Item possiblyCraftableItem = ITEM_HASH.get(itemsPossiblyCraftable[i]);
			if(possiblyCraftableItem.isValidCauldronRecipe(cauldron.cauldronSlots)) {
				cauldron.isCraftableItem = true;
				cauldron.possiblyCraftableItem = possiblyCraftableItem.createNew();
				
				if(cauldron.cauldronIsBurning == false) {
					checkCauldronFuel(cauldron, invCrafting, invCrafted);
				}
				return;
			} else {
				cauldron.isCraftableItem = false;
				//cauldron.cauldronBuildTime = 10;
			}
		}
	}
	public void checkFuel(FurnaceTile furnace, Slot[] invCrafting, Slot[] invCrafted) { 
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
	public void checkCauldronFuel(CauldronTile cauldron, Slot[] invCrafting, Slot[] invCrafted) { 
		if(invCrafting[3].item.isFuelSource) {
			cauldron.cauldronIsBurning = true;
			cauldron.cauldronFuel = cauldron.cauldronSlots[3].item.fuelAmount;
			cauldron.cauldronMaxFuel = cauldron.cauldronSlots[3].item.fuelAmount;
			
			
			cauldron.cauldronSlots[3].item.stackSize = cauldron.cauldronSlots[3].item.stackSize - 1;
			if(cauldron.cauldronSlots[3].item.stackSize <= 0) {
				cauldron.cauldronSlots[3].item = new NoItem();
			}
		} else {
			cauldron.cauldronIsBurning = false;
		}
	}
	
}