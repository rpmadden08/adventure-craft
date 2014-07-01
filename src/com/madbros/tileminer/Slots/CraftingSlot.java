package com.madbros.tileminer.Slots;

import static com.madbros.tileminer.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.madbros.tileminer.Game;
import com.madbros.tileminer.Inventory;
import com.madbros.tileminer.Items.Item;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Rect;
public class CraftingSlot extends Slot{
	public boolean hasIngedients = false;
	private int maxToolTipDelay = 30;
	private int toolTipDelay = maxToolTipDelay;
	
	public CraftingSlot(int x, int y) {
		super(x, y);
		type = CRAFTING_SLOT;
	}
	
	@Override
	public void handleAdditional(Slot[] invCrafting, Slot[] invCrafted) {
		craftAnotherItemIfPossible(invCrafting, invCrafted);
	}
	public void handleLeftClickCrafting(Inventory inv) {
	}
	
	public void render() {
		super.render();
		if(isInactive) {
			if(this.item.sprite != null) {
				item.sprite.setColor(0.2f,0.2f,0.2f,0.7f);
				item.render(slotRect);
				item.sprite.setColor(1f,1f,1f,1f);
			}
		}
		if(isHighlighted) {
			if(toolTipDelay <=0) {
				renderCraftingInfo();
				//toolTipDelay = maxToolTipDelay;
			} else {
				toolTipDelay = toolTipDelay -1;
			}
			
		} else {
			toolTipDelay = maxToolTipDelay;
		}
	}
	
	public void renderCraftingInfo() {
		if(item.id != 0) {
			Rect craftingInfoRect = new Rect(501, 446, 226, 143);
			Sprites.pixel.setColor(0.349f, 0.337f, 0.322f, 1.0f);
			Sprites.pixel.draw(craftingInfoRect.x, craftingInfoRect.y, 0, craftingInfoRect.w, craftingInfoRect.h);
//			Game.inventory.menuSprites[0].draw(craftingInfoRect.x, craftingInfoRect.y, Z_INV_BACKDROP);	//top left
//			Game.inventory.menuSprites[6].draw(craftingInfoRect.x2(), craftingInfoRect.y, Z_INV_BACKDROP); //top right
//			Game.inventory.menuSprites[2].draw(craftingInfoRect.x, craftingInfoRect.y2(), Z_INV_BACKDROP);	//bottom left
//			Game.inventory.menuSprites[8].draw(craftingInfoRect.x2(), craftingInfoRect.y2(), Z_INV_BACKDROP);	//bottom right
//			
//			Game.inventory.menuSprites[3].draw(craftingInfoRect.x+INV_MENU_TILE_SIZE, craftingInfoRect.y, Z_INV_BACKDROP, craftingInfoRect.w-INV_MENU_TILE_SIZE, INV_MENU_TILE_SIZE);	//top
//			Game.inventory.menuSprites[5].draw(craftingInfoRect.x+INV_MENU_TILE_SIZE, craftingInfoRect.y2(), Z_INV_BACKDROP, craftingInfoRect.w-INV_MENU_TILE_SIZE, INV_MENU_TILE_SIZE);	//bottom
//			Game.inventory.menuSprites[1].draw(craftingInfoRect.x, craftingInfoRect.y+INV_MENU_TILE_SIZE, Z_INV_BACKDROP, INV_MENU_TILE_SIZE, craftingInfoRect.h-INV_MENU_TILE_SIZE);	//left
//			Game.inventory.menuSprites[7].draw(craftingInfoRect.x2(), craftingInfoRect.y+INV_MENU_TILE_SIZE, Z_INV_BACKDROP, INV_MENU_TILE_SIZE, craftingInfoRect.h-INV_MENU_TILE_SIZE);	//right
//			
//			Game.inventory.menuSprites[4].draw(craftingInfoRect.x+INV_MENU_TILE_SIZE, craftingInfoRect.y+INV_MENU_TILE_SIZE, Z_INV_BACKDROP, craftingInfoRect.w-INV_MENU_TILE_SIZE, craftingInfoRect.h-INV_MENU_TILE_SIZE);
//		
			int slotYCoord = 464;
			
			for(int a = 0; a < item.craftCost.length; a++) {
				
				
				Item recipeItem = ITEM_HASH.get(item.craftCost[a]).createNew();
				recipeItem.stackSize = item.craftCostAmount[a];
				Rect recipeSlotRect = new Rect(504,slotYCoord, slotRect.w, slotRect.h);
				if(recipeItem.isInInventory()) {
				} else {
					//slotSprite.setColor(Color.RED);
				}
				slotSprite.draw(504,slotYCoord, Z_INV_SLOTS);
				slotSprite.setColor(Color.WHITE);
				recipeItem.render(recipeSlotRect);
				recipeItem.renderFont(recipeSlotRect.x2()-INV_SLOT_SIZE/2,recipeSlotRect.y2()-INV_SLOT_SIZE/2, Game.batch);
				Sprites.arial10.draw(Game.batch, recipeItem.name, recipeSlotRect.x+ 44, recipeSlotRect.y+ 12);
				
				slotYCoord = slotYCoord + 42;
				
			}
			
			
			if(item.workSpaceNeeded[0] != 0) {
				CraftedSlot craftedSlot = new CraftedSlot(645, 507); //625, 484
				
				if(item.workSpaceNeeded[0] == 1) {
					
					craftedSlot.item = ITEM_HASH.get(TABLE).createNew();
					
				}
				if(Game.inventory.currentWorkSpace != 1) {
					//craftedSlot.slotSprite.setColor(Color.RED);
				} 
				
				craftedSlot.render();
				craftedSlot.slotSprite.setColor(Color.WHITE);
				
				Sprites.font.draw(Game.batch, "Workspace", 642, 473);
				Sprites.font.draw(Game.batch, "Needed", 658, 488);
			}
			Sprites.font.draw(Game.batch, "Recipe for "+item.name, 504, 445);
			
		}
	
	}
	
	public void handleRightClick(Inventory inv) {
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
