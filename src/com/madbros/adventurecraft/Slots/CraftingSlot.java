package com.madbros.adventurecraft.Slots;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Inventory;
import com.madbros.adventurecraft.Items.Item;
public class CraftingSlot extends Slot{
	public boolean hasIngedients = false;
	public CraftingSlot(int x, int y) {
		super(x, y);
		type = CRAFTING_SLOT;
	}
	
	@Override
	public void handleAdditional(Slot[] invCrafting, Slot[] invCrafted) {
		craftAnotherItemIfPossible(invCrafting, invCrafted);
	}
	public void handleLeftClickCrafting(Inventory inv) {
//		if(this.item.id == EMPTY || this.isInactive == true) {
//		} else {
//			//Add items to inventory.  
//			for(int x = 0; x < this.item.craftCost.length; x++) {
//				Item removedItem = ITEM_HASH.get(this.item.craftCost[x]).createNew();
//				Game.inventory.remove(removedItem, this.item.craftCostAmount[x]);
//			}
//			Game.inventory.add(this.item, this.item.stackSize, this.item.maxUses);
//		}
	}
	public void render() {
		super.render();
		if(isInactive) {
			if(this.item.sprite != null) {
				item.sprite.setColor(0.2f,0.2f,0.2f,0.7f);
				item.render(slotRect);
				item.sprite.setColor(1f,1f,1f,1f);
			}
			//Rect newSlotRect = new Rect(slotRect.x+5, slotRect.y+5, 32, 32);
			//highlighter.draw(newSlotRect, Z_INV_HIGHLIGHT);
			//Sprites.pixel.setColor(Color.WHITE);
		}
	}
	public void handleLeftClick(Inventory inv) {
		if(this.item.id == EMPTY || this.isInactive == true) {
		} else {
			//Add items to inventory.  
			for(int x = 0; x < this.item.craftCost.length; x++) {
				Item removedItem = ITEM_HASH.get(this.item.craftCost[x]).createNew();
				Game.inventory.remove(removedItem, this.item.craftCostAmount[x]);
			}
			Game.inventory.add(this.item, this.item.stackSize, this.item.maxUses);
		}
	}
}
