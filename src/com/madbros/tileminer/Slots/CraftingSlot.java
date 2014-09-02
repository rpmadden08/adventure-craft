package com.madbros.tileminer.Slots;

import static com.madbros.tileminer.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.madbros.tileminer.Game;
import com.madbros.tileminer.Inventory;
import com.madbros.tileminer.Items.Item;
import com.madbros.tileminer.Sprites.Sprite;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Rect;
import com.madbros.tileminer.Utils.RectInt;
public class CraftingSlot extends Slot{
	public boolean hasIngedients = false;
	private int maxToolTipDelay = 60;
	private int toolTipDelay = maxToolTipDelay;
	public boolean isSoundLooping = false;
	
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
		RectInt slotRect2 = slotRect.getRectInt();
		if(isHighlighted) {
			Sprites.pixel.setColor(highlightColor);

			
			highlighter.draw(slotRect2.x+2,slotRect2.y+2, Z_INV_HIGHLIGHT, slotRect2.w - 4, slotRect2.h-4);
			Sprites.pixel.setColor(Color.WHITE);
		}
		slotSprite.draw(slotRect, Z_INV_SLOTS);
		if(this.item.sprite != null) {
			if(isInactive) {
				
					item.sprite.setColor(0.2f,0.2f,0.2f,0.7f);
					item.render(slotRect);
					item.sprite.setColor(1f,1f,1f,1f);
	//				Sprite sprite = Sprites.sprites.get(Sprites.GRAY_BUTTON);
	//				sprite.draw(slotRect.x, slotRect.y, 0);
			} else { 
				item.render(slotRect);
					if(isHighlighted) {
						Sprite sprite = Sprites.sprites.get(Sprites.GREEN_BUTTON_HOVER);
						sprite.draw(slotRect2.x, slotRect2.y, 0);
					} else {
						Sprite sprite = Sprites.sprites.get(Sprites.GREEN_BUTTON);
						sprite.draw(slotRect2.x, slotRect2.y, 0);
					}
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
			RectInt craftingInfoRect = new RectInt(501, 446, 226, 143);
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
				RectInt slotRect2 = slotRect.getRectInt();
				
				Rect recipeSlotRect = new Rect(504,slotYCoord, slotRect2.w, slotRect2.h);
				RectInt recipeSlotRect2 = recipeSlotRect.getRectInt();
				if(recipeItem.isInInventory()) {
				} else {
					recipeItem.sprite.setColor(0.2f,0.2f,0.2f,0.7f);
					Sprites.arial10.setColor(0.675f, 0.196f, 0.196f, 1f);
				}
				slotSprite.draw(504,slotYCoord, Z_INV_SLOTS);
				slotSprite.setColor(Color.WHITE);
				recipeItem.render(recipeSlotRect);
				
				Sprites.arial10.draw(Game.batch, recipeItem.name, recipeSlotRect2.x+ 44, recipeSlotRect2.y+ 12);
				recipeItem.sprite.setColor(Color.WHITE);
				Sprites.arial10.setColor(Color.WHITE);
				recipeItem.renderFont(recipeSlotRect2.x2()-INV_SLOT_SIZE/2,recipeSlotRect2.y2()-INV_SLOT_SIZE/2, Game.batch);
				slotYCoord = slotYCoord + 42;
				
			}
			
			
			if(item.workSpaceNeeded[0] != 0) {
				CraftedSlot craftedSlot = new CraftedSlot(645, 507); //625, 484
				
				if(item.workSpaceNeeded[0] == 1) {
					craftedSlot.item = ITEM_HASH.get(TABLE).createNew();
				} else if(item.workSpaceNeeded[0] == 2) {
					craftedSlot.item = ITEM_HASH.get(CAULDRON).createNew();
				} else if(item.workSpaceNeeded[0] == 3) {
					craftedSlot.item = ITEM_HASH.get(FURNACE).createNew();
				}
				if(Game.inventory.currentWorkSpace != item.workSpaceNeeded[0]) {
					craftedSlot.item.sprite.setColor(0.2f,0.2f,0.2f,0.7f);
					Sprites.font.setColor(0.675f, 0.196f, 0.196f, 1f);
					//craftedSlot.slotSprite.setColor(Color.RED);
				} 
				
				craftedSlot.render();
				craftedSlot.slotSprite.setColor(Color.WHITE);
				
				Sprites.font.draw(Game.batch, "Workspace", 642, 473);
				Sprites.font.draw(Game.batch, "Needed", 658, 488);
				Sprites.font.setColor(Color.WHITE);
				craftedSlot.item.sprite.setColor(Color.WHITE);
			}
			Sprites.font.draw(Game.batch, "Recipe for "+item.name, 504, 445);
			
		}
	
	}
	
	public void handleRightClick(Inventory inv) {
	}
	public void handleLeftClickShift(Inventory inv) {
		if(inv.heldItem.id == 0 || inv.heldItem.id == this.item.id) {
			while(inv.heldItem.stackSize + this.item.stackSize <= inv.heldItem.maxStackSize) {
				if(isInactive) {
					return;
				} else {
					handleLeftClick(inv);
					isSoundLooping = true;
				}
			}
		}
		isSoundLooping = false;
	}
	public void handleLeftClick(Inventory inv) {
		if(this.item.id == EMPTY || this.isInactive == true) {
		} else {
			Boolean needToRemoveItem = false;
			if(inv.heldItem.id == 0) {
				inv.heldItem = ITEM_HASH.get(this.item.id).createNew();
				inv.heldItem.stackSize = this.item.stackSize;
				inv.heldItem.maxUses = this.item.maxUses;
				needToRemoveItem = true;
			} else if (inv.heldItem.id == this.item.id) {
				inv.heldItem.stackSize = inv.heldItem.stackSize + this.item.stackSize;
				needToRemoveItem = true;
			}
			if(needToRemoveItem) {
				for(int x = 0; x < this.item.craftCost.length; x++) {
					Item removedItem = ITEM_HASH.get(this.item.craftCost[x]).createNew();
					inv.remove(removedItem, this.item.craftCostAmount[x]);
				}
			}
			if(this.item.areIngredientsInInventory()) {
				this.isInactive = false;
			} else {
				this.isInactive = true;
			}
			if(!isSoundLooping) {
				Game.soundController.create("sounds/clickSelect.wav", 0.5f);
			}
			//Game.inventory.add(this.item, this.item.stackSize, this.item.maxUses);
		}
	}
}
