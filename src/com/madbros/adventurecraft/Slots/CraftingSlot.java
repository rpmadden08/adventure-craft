package com.madbros.adventurecraft.Slots;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Inventory;
import com.madbros.adventurecraft.Items.Item;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Utils.Rect;
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
			renderCraftingInfo();
			
		}
	}
	
	public void renderCraftingInfo() {
		Rect craftingInfoRect = new Rect(396, 262, 204, 180);
		Game.inventory.menuSprites[0].draw(craftingInfoRect.x, craftingInfoRect.y, Z_INV_BACKDROP);	//top left
		Game.inventory.menuSprites[6].draw(craftingInfoRect.x2(), craftingInfoRect.y, Z_INV_BACKDROP); //top right
		Game.inventory.menuSprites[2].draw(craftingInfoRect.x, craftingInfoRect.y2(), Z_INV_BACKDROP);	//bottom left
		Game.inventory.menuSprites[8].draw(craftingInfoRect.x2(), craftingInfoRect.y2(), Z_INV_BACKDROP);	//bottom right
		
		Game.inventory.menuSprites[3].draw(craftingInfoRect.x+INV_MENU_TILE_SIZE, craftingInfoRect.y, Z_INV_BACKDROP, craftingInfoRect.w-INV_MENU_TILE_SIZE, INV_MENU_TILE_SIZE);	//top
		Game.inventory.menuSprites[5].draw(craftingInfoRect.x+INV_MENU_TILE_SIZE, craftingInfoRect.y2(), Z_INV_BACKDROP, craftingInfoRect.w-INV_MENU_TILE_SIZE, INV_MENU_TILE_SIZE);	//bottom
		Game.inventory.menuSprites[1].draw(craftingInfoRect.x, craftingInfoRect.y+INV_MENU_TILE_SIZE, Z_INV_BACKDROP, INV_MENU_TILE_SIZE, craftingInfoRect.h-INV_MENU_TILE_SIZE);	//left
		Game.inventory.menuSprites[7].draw(craftingInfoRect.x2(), craftingInfoRect.y+INV_MENU_TILE_SIZE, Z_INV_BACKDROP, INV_MENU_TILE_SIZE, craftingInfoRect.h-INV_MENU_TILE_SIZE);	//right
		
		Game.inventory.menuSprites[4].draw(craftingInfoRect.x+INV_MENU_TILE_SIZE, craftingInfoRect.y+INV_MENU_TILE_SIZE, Z_INV_BACKDROP, craftingInfoRect.w-INV_MENU_TILE_SIZE, craftingInfoRect.h-INV_MENU_TILE_SIZE);
	
		int slotYCoord = 288;
		
		for(int a = 0; a < item.craftCost.length; a++) {
			
			Rect recipeSlotRect = new Rect(402,slotYCoord, slotRect.w, slotRect.h);
			slotSprite.draw(402,slotYCoord, Z_INV_SLOTS);
			Item recipeItem = ITEM_HASH.get(item.craftCost[a]).createNew();
			recipeItem.stackSize = item.craftCostAmount[a];
			recipeItem.render(recipeSlotRect);
			recipeItem.renderFont(recipeSlotRect.x2()-INV_SLOT_SIZE/2,recipeSlotRect.y2()-INV_SLOT_SIZE/2, Game.batch);
			Sprites.arial10.draw(Game.batch, recipeItem.name, recipeSlotRect.x+ 44, recipeSlotRect.y+ 12);
			slotYCoord = slotYCoord + 44;
			
		}
		Sprites.font.draw(Game.batch, "Recipe", 402, 268);
	
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
