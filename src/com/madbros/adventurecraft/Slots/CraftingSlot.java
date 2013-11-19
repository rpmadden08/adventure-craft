package com.madbros.adventurecraft.Slots;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Inventory;
import com.madbros.adventurecraft.Items.Item;
import com.madbros.adventurecraft.Items.NoItem;
import com.madbros.adventurecraft.Utils.Helpers;

public class CraftingSlot extends Slot{
	public CraftingSlot(int x, int y) {
		super(x, y);
		type = CRAFTING_SLOT;
	}
	
	@Override
	public void handleLeftClick(Inventory inv) {//Item heldItem, Slot[] invCrafting, Slot[]invCrafted, Inventory inv) {
		super.handleLeftClick(inv);
		handleAdditional(Game.currentState.standardInventory.invCrafting, Game.currentState.standardInventory.invCrafted);
	}
	
	@Override
	public void handleRightClick(Inventory inv) {
		super.handleRightClick(inv);
		handleAdditional(Game.currentState.standardInventory.invCrafting, Game.currentState.standardInventory.invCrafted);
	}

	public void removeRecipeItemsFromCraftingSlots(int[] recipeCost, Slot[] invCrafting) {
		int[] itemPositionsRemovedAlready = new int[recipeCost.length];	//so you don't remove two planks from the same stack, for example
		for(int t = 0; t < itemPositionsRemovedAlready.length; t++) {  //MUST START AT -1 BECAUSE 0 is equal to the first crafting slot
			itemPositionsRemovedAlready[t] = -1;
		}
		for(int i = 0; i < recipeCost.length; i++) { 
			for(int j = 0; j < invCrafting.length; j++) { 
				//System.out.println(invCrafting[j].item.id+"-"+recipeCost[i]);
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
		invCrafted[0].item = new NoItem();
	}
	
	public void craftAnItemFromThisListIfPossible(Slot[] invCrafting, Slot[] invCrafted, int[] itemsPossiblyCraftable) {
		for(int i = 0; i < itemsPossiblyCraftable.length; i++) {
			Item possiblyCraftableItem = ITEM_HASH.get(itemsPossiblyCraftable[i]);
			if(possiblyCraftableItem.isValidRecipe(invCrafting)|| possiblyCraftableItem.isValidTableRecipe(invCrafting)) {
				invCrafted[0].item = possiblyCraftableItem.createNew();
				invCrafted[0].item.stackSize = invCrafted[0].item.numberProducedByCrafting;
				return;
			}
		}
		invCrafted[0].item = new NoItem();
	}
	
	@Override
	public void handleAdditional(Slot[] invCrafting, Slot[] invCrafted) {
		craftAnotherItemIfPossible(invCrafting, invCrafted);
	}
}
