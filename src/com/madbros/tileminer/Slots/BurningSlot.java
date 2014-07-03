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
public class BurningSlot extends CraftingSlot{
	
	public BurningSlot(int x, int y) {
		super(x, y);
	}
	
	public void handleLeftClick(Inventory inv) {
		
		if(this.item.id == EMPTY || this.isInactive == true) {
		} else {
			
			FurnaceTile furnace = (FurnaceTile) Game.level.activeBlocks[inv.currentInvActiveBlockX][inv.currentInvActiveBlockY].layers[OBJECT_LAYER];
			if(furnace.furnaceSlots[0].item.id == 0) {
				furnace.furnaceSlots[0].item = this.item;
			}else if(furnace.furnaceSlots[0].item.id == this.item.id) {
				furnace.furnaceSlots[0].item.stackSize = furnace.furnaceSlots[0].item.stackSize +1;
			} else { 
				return;
			}
			
			for(int x = 0; x < this.item.craftCost.length; x++) {
				Item removedItem = ITEM_HASH.get(this.item.craftCost[x]).createNew();
				Game.inventory.remove(removedItem, this.item.craftCostAmount[x]);
			}
			
		}
	}
}