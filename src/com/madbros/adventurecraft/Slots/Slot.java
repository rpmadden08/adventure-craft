package com.madbros.adventurecraft.Slots;

import static com.madbros.adventurecraft.Constants.*;


import com.badlogic.gdx.graphics.Color;
import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Items.*;
import com.madbros.adventurecraft.Sprites.*;
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
		
		handleAdditional(inv.invCrafting, inv.invCrafted);
	}
	
	public void handleAdditional(Slot[] invCrafting, Slot[] invCrafted) { }
	
	/* Helpers */
	public void swapItems(Inventory inv) {
		Item temp = inv.heldItem;
		inv.heldItem = this.item;
		this.item = temp;
	}
	
	
}