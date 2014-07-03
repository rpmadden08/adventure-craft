package com.madbros.tileminer.Slots;

import static com.madbros.tileminer.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.madbros.tileminer.*;
import com.madbros.tileminer.Items.*;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Rect;

public class CraftedSlot extends Slot{
	public CraftedSlot(int x, int y) {
		super(x, y);
		type = CRAFTED_SLOT;
		slotRect = new Rect(x, y, INV_SLOT_SIZE*2, INV_SLOT_SIZE*2);
	}
	
	public void render() {
		if(isHighlighted) {
			Sprites.pixel.setColor(highlightColor);
			//Rect r = new Rect(slotRect.x, slotRect.y, INV_SLOT_SIZE+100, INV_SLOT_SIZE+100);
			//highlighter.draw(slotRect.x,slotRect.y, Z_INV_HIGHLIGHT, INV_SLOT_SIZE+10, INV_SLOT_SIZE+10);
			highlighter.draw(slotRect.x+2,slotRect.y+2, Z_INV_HIGHLIGHT, slotRect.w - 6, slotRect.h-6);
			Sprites.pixel.setColor(Color.WHITE);
		}
		//slotSprite.draw(slotRect, Z_INV_SLOTS);
		Rect r = new Rect(slotRect.x, slotRect.y, INV_SLOT_SIZE*2, INV_SLOT_SIZE*2);
		//highlighter.draw(slotRect.x,slotRect.y, Z_INV_HIGHLIGHT, INV_SLOT_SIZE+10, INV_SLOT_SIZE+10);
		slotSprite.draw(slotRect, Z_INV_HIGHLIGHT);
		
		item.renderLarge(slotRect);
	}
	
	@Override
	public void handleLeftClick(Inventory inv) { //Item inv.heldItem, Slot[] inv.invCrafting, Slot[]invCrafted) {
		CraftingSlot[] craftingSlots;
		if(inv.craftingTableOn == true) {
			craftingSlots = inv.invTable;
		} else if(inv.furnaceOn) {
			//FurnaceTile furnaceTile = (FurnaceTile) Game.level.activeBlocks[inv.currentInvActiveBlockX][inv.currentInvActiveBlockY].layers[OBJECT_LAYER];
			craftingSlots = inv.invCrafting;
		} else {
			craftingSlots = inv.invCrafting;
		}
		if(inv.heldItem.id == EMPTY) {
			swapItems(inv);
			removeRecipeItemsFromCraftingSlots(inv.heldItem.craftCost, craftingSlots);
			craftAnotherItemIfPossible(craftingSlots, inv.invCrafted);
		} else if (inv.heldItem.id == this.item.id) {
			int total = inv.heldItem.stackSize + this.item.stackSize;
			if(total <= inv.heldItem.maxStackSize) {
				inv.heldItem.stackSize = total;
				this.item = new NoItem();
				removeRecipeItemsFromCraftingSlots(inv.heldItem.craftCost, craftingSlots);
				craftAnotherItemIfPossible(craftingSlots, inv.invCrafted);
			}
		}
	}
	
	@Override
	public void handleRightClick(Inventory inv) {
		handleLeftClick(inv);	//because it is effectively handled the same
	}
}
