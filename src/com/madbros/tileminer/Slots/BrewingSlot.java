package com.madbros.tileminer.Slots;

import static com.madbros.tileminer.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.madbros.tileminer.Game;
import com.madbros.tileminer.Inventory;
import com.madbros.tileminer.Items.Item;
import com.madbros.tileminer.Sprites.Sprite;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.TileTypes.CauldronTile;
import com.madbros.tileminer.TileTypes.FurnaceTile;
import com.madbros.tileminer.Utils.Rect;
public class BrewingSlot extends CraftingSlot{
	
	public BrewingSlot(int x, int y) {
		super(x, y);
	}
	
	public void handleLeftClick(Inventory inv) {
		
		if(this.item.id == EMPTY || this.isInactive == true) {
		} else {
			
			CauldronTile cauldron = (CauldronTile) Game.level.activeBlocks[inv.currentInvActiveBlockX][inv.currentInvActiveBlockY].layers[OBJECT_LAYER];
			if(cauldron.cauldronSlots[0].item.id == 0) {
				cauldron.cauldronSlots[0].item = ITEM_HASH.get(this.item.id).createNew();
			}else if(cauldron.cauldronSlots[0].item.id == this.item.id) {
				cauldron.cauldronSlots[0].item.stackSize = cauldron.cauldronSlots[0].item.stackSize +1;
			} else { 
				return;
			}
			
			for(int x = 0; x < this.item.craftCost.length; x++) {
				Item removedItem = ITEM_HASH.get(this.item.craftCost[x]).createNew();
				Game.inventory.remove(removedItem, this.item.craftCostAmount[x]);
			}
			
		}
	}
	public void handleLeftClickShift(Inventory inv) {
		CauldronTile cauldron = (CauldronTile) Game.level.activeBlocks[inv.currentInvActiveBlockX][inv.currentInvActiveBlockY].layers[OBJECT_LAYER];
		if(cauldron.cauldronSlots[0].item.id == 0 || cauldron.cauldronSlots[0].item.id == this.item.id) {
			while(cauldron.cauldronSlots[0].item.stackSize + this.item.stackSize <= cauldron.cauldronSlots[0].item.maxStackSize) {
				System.out.println(cauldron.cauldronSlots[0].item.stackSize);
				if(isInactive) {
					return;
				} else {
					handleLeftClick(inv);
				}
			}
		}
	}
}