package com.madbros.tileminer.Slots;

import static com.madbros.tileminer.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.madbros.tileminer.*;
import com.madbros.tileminer.Items.*;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Rect;
import com.madbros.tileminer.Utils.RectInt;

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
			RectInt slotRect2 = slotRect.getRectInt();
			highlighter.draw(slotRect2.x+2,slotRect2.y+2, Z_INV_HIGHLIGHT, slotRect2.w - 6, slotRect2.h-6);
			Sprites.pixel.setColor(Color.WHITE);
		}
		//slotSprite.draw(slotRect, Z_INV_SLOTS);
//		Rect r = new Rect(slotRect.x, slotRect.y, INV_SLOT_SIZE*2, INV_SLOT_SIZE*2);
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
			//swapItems(inv);
			
			
//			FurnaceTile furnaceTile = (FurnaceTile) Game.level.activeBlocks[inv.currentInvActiveBlockX][inv.currentInvActiveBlockY].layers[OBJECT_LAYER];
//			furnaceTile.craftedSlot = new CraftedSlot[1];
			//removeRecipeItemsFromCraftingSlots(inv.heldItem.craftCost, craftingSlots);
			//craftAnotherItemIfPossible(craftingSlots, inv.invCrafted);
			Game.soundController.create("sounds/clickSelect.wav", 0.5f);
			inv.heldItem = ITEM_HASH.get(item.id).createNew();
			inv.heldItem.stackSize = item.stackSize;
			item = new NoItem();
			//item.stackSize = 0;
			
		} else if (inv.heldItem.id == this.item.id) {
			int total = inv.heldItem.stackSize + this.item.stackSize;
			if(total <= inv.heldItem.maxStackSize) {
				inv.heldItem.stackSize = total;
				this.item = new NoItem();
				removeRecipeItemsFromCraftingSlots(inv.heldItem.craftCost, craftingSlots);
				craftAnotherItemIfPossible(craftingSlots, inv.invCrafted);
				
			}
			Game.soundController.create("sounds/clickSelect.wav", 0.5f);
		}
	}
	
	@Override
	public void handleRightClick(Inventory inv) {
		handleLeftClick(inv);	//because it is effectively handled the same
	}
}
