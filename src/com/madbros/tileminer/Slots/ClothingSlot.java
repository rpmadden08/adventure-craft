package com.madbros.tileminer.Slots;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Game;
import com.madbros.tileminer.Inventory;
import com.madbros.tileminer.Items.NoItem;
import com.madbros.tileminer.Items.Clothing.Clothing;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Sprites.StaticSprite;
import com.madbros.tileminer.Utils.RectInt;

public class ClothingSlot extends Slot{
	public ClothingSlot(int x, int y, int type) {
		super(x, y);
		this.type = type;
	}
	
	@Override
	public void render() {
		
		StaticSprite sprite;
		if(this.item.id == 0) {
			RectInt slotRect2 = slotRect.getRectInt();
			if(type == 5) {
				sprite = Sprites.sprites.get(Sprites.GRAY_HELMET);
				sprite.draw(slotRect2.x+4, slotRect2.y+4, 0);
			} else if(type == 6) {
				sprite = Sprites.sprites.get(Sprites.GRAY_ARMOR);
				sprite.draw(slotRect2.x+4, slotRect2.y+4, 0);
			} else if(type == 7) {
				sprite = Sprites.sprites.get(Sprites.GRAY_LEGGINGS);
				sprite.draw(slotRect2.x+4, slotRect2.y+4, 0);
			} else {
				sprite = Sprites.sprites.get(Sprites.GRAY_BOOTS);
				//sprite.draw(slotRect.x+(slotRect.w/2)-(sprite.getWidth()/2),slotRect.y+(slotRect.h/2)-(sprite.getHeight()/2),0);
				sprite.draw(slotRect2.x+4, slotRect2.y+4, 0);
			}
		
		
			//sprite.draw(slotRect.x+(slotRect.w/2)-(sprite.getWidth()/2),slotRect.y+(slotRect.h/2)-(sprite.getHeight()/2),0);
		}
		super.render();
		
	}
	
	@Override
	public void handleLeftClick(Inventory inv) {
		if (inv.heldItem instanceof Clothing) {
			Clothing heldItem = (Clothing) inv.heldItem;
			if (heldItem.slotType == this.type) {
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
					Game.hero.addClothingItem(heldItem);
					if(item instanceof Clothing) Game.hero.removeClothingItem((Clothing)item);
					swapItems(inv);
					Game.hero.calcArmor();
				}
			}
			Game.soundController.create("sounds/clickSelect.wav", 0.5f);
		} else if(inv.heldItem.id == EMPTY && item.id != EMPTY) {
			Clothing item = (Clothing) this.item;
			Game.hero.removeClothingItem(item);
			swapItems(inv);
			Game.hero.calcArmor();
			Game.soundController.create("sounds/clickSelect.wav", 0.5f);
		}
		
		handleAdditional(inv.invCrafting, inv.invCrafted);
	}
	
	public void handleLeftClickShift(Inventory inv) {
			//inv.add(this.item,this.item.stackSize, this.item.uses);
			if(Game.inventory.add(this.item,this.item.stackSize, this.item.uses)) {
				if(this.item instanceof Clothing) Game.hero.removeClothingItem((Clothing)this.item);
				inv.removeSlot(this);
				Game.hero.calcArmor();
				Game.soundController.create("sounds/clickSelect.wav", 0.5f);
			}
			
			
	}
	
	public void handleLeftClickShiftChest(Inventory inv) {
		handleLeftClickShift(inv);
	}
}
