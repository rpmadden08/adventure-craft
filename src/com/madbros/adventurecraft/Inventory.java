package com.madbros.adventurecraft;

import static com.madbros.adventurecraft.Constants.*;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.madbros.adventurecraft.Slots.*;
import com.madbros.adventurecraft.Sprites.*;
import com.madbros.adventurecraft.Utils.Rect;
import com.madbros.adventurecraft.GameObjects.Hero;
import com.madbros.adventurecraft.Items.*;

public class Inventory {
	public StaticSprite selectSprite = Sprites.sprites.get(Sprites.INVENTORY_MENU_SELECTOR);
	public StaticSprite[] menuSprites = Sprites.spriteCollections.get(Sprites.INVENTORY_MENU); // {Sprites.menuSprites1, Sprites.menuSprites2, Sprites.menuSprites3};
	public int formerHeroAnimation;
	
	public Slot[] invBar= new Slot[INV_LENGTH];
	public Slot[] invBag= new Slot[INV_LENGTH * INV_HEIGHT];
	public boolean craftingTableOn = false;
	public CraftingSlot[] invCrafting = new CraftingSlot[2 * 2];
	public CraftingSlot[] invTable = new CraftingSlot[3 * 3];
	public CraftedSlot[] invCrafted = new CraftedSlot[1];
	public ClothingSlot[] invClothing = new ClothingSlot[4];

	
	public Item heldItem = new NoItem();
	public int itemSelected = 0;
	public boolean isUsingRightItem = false;
	public boolean isUsingLeftItem = false;
	
	public Inventory() {
		for(int i = 0; i < INV_LENGTH; i++) {
			invBar[i] = new Slot(INV_BAR_RECT.x, INV_BAR_RECT.y + (INV_SLOT_SIZE + INV_SLOT_MARGIN.right) * i);
		}
		
		int k = 0;
		for(int x = 0; x < INV_LENGTH; x++) {
			for(int y = 0; y < INV_HEIGHT; y++) {
				invBag[k] = new Slot(INV_BAG_RECT.x + (INV_SLOT_SIZE + INV_MENU_SLOT_MARGIN.right) * y,
									 INV_BAG_RECT.y + (INV_SLOT_SIZE + INV_MENU_SLOT_MARGIN.bottom) * x);
				k++;
			}
		}
		
		k = 0;
		for(int x = 0; x < 2; x++) {	
			for(int y = 0; y < 2; y++) {
				invCrafting[k] = new CraftingSlot(INV_CRAFTING_RECT.x + (INV_SLOT_SIZE) * x,
										  INV_CRAFTING_RECT.y + (INV_SLOT_SIZE) * y);
				k++;
			}
		}
		k=0;
		for(int x = 0; x < 3; x++) {	
			for(int y = 0; y < 3; y++) {
				invTable[k] = new CraftingSlot(INV_CRAFTING_RECT.x + (INV_SLOT_SIZE) * x,
										  INV_CRAFTING_RECT.y + (INV_SLOT_SIZE) * y);
				k++;
			}
		}
		
		invCrafted[0] = new CraftedSlot(INV_CRAFTING_RECT.x2() + 75, INV_CRAFTING_RECT.y);

		invClothing[0] = new ClothingSlot(INV_CHAR_RECT.x +150,INV_CHAR_RECT.y+40, HELMET_SLOT);
		invClothing[1] = new ClothingSlot(INV_CHAR_RECT.x +150,INV_CHAR_RECT.y +80,ARMOR_SLOT);
		invClothing[2] = new ClothingSlot(INV_CHAR_RECT.x +150,INV_CHAR_RECT.y +120,LEGGINGS_SLOT);
		invClothing[3] = new ClothingSlot(INV_CHAR_RECT.x +150,INV_CHAR_RECT.y +160,BOOTS_SLOT);
		
		invBar[0].item = new Log();
		invBar[0].item.stackSize =99;
		invBar[1].item = new Sword();
		invBar[1].item.stackSize = 1;
		invBar[2].item = new TableItem();
		invBar[2].item.stackSize = 1;
		invBar[3].item = new SandClump();
		invBar[3].item.stackSize = 99;
		invBar[4].item = new GrassSeed();
		invBar[4].item.stackSize = 99;
		invBar[5].item = new Hoe();
		invBar[5].item.stackSize = 1;
		invBar[6].item = new SeedPotato();
		invBar[6].item.stackSize = 99;
		invBar[7].item = new CampfireItem();
		invBar[7].item.stackSize = 99;
		invBar[8].item = new Potatoes();
		invBar[8].item.stackSize = 99;
		invBar[9].item = new Axe();
		invBar[9].item.stackSize = 1;

//		invClothing[0].item = new IronHelmet();
//		Game.hero.addClothingItem((ClothingItem)invClothing[0].item);
		

	}
	
	public void dropAll() {
		for(int i = 0; i < invBar.length; i++) {
			if(invBar[i].item.id != 0) {
				Rect collectibleRect = new Rect(Game.hero.absRect.x, Game.hero.absRect.y, 16, 16);
				Game.collectibleController.add(invBar[i].item.id, invBar[i].item.sprite, collectibleRect, invBar[i].item.stackSize);
				invBar[i].item.stackSize = 0;
				invBar[i].item = new NoItem();
			}
			
		}
		
		for(int i = 0; i < invBar.length; i++) {
			if(invBar[i].item.id != 0) {
				Rect collectibleRect = new Rect(Game.hero.absRect.x, Game.hero.absRect.y, 16, 16);
				Game.collectibleController.add(invBar[i].item.id, invBar[i].item.sprite, collectibleRect, invBar[i].item.stackSize);
				invBar[i].item.stackSize = 0;
				invBar[i].item = new NoItem();
			}
		}
	}
	public void add(Item addedItem, int stackSize) {
		for(int i = 0; i < invBar.length; i++) {
			if(invBar[i].item.id == addedItem.id ) {
				if(invBar[i].item.stackSize != addedItem.maxStackSize ) {
					if(invBar[i].item.stackSize + stackSize > addedItem.maxStackSize) {
						int diff = addedItem.maxStackSize - invBar[i].item.stackSize;
						stackSize = stackSize - diff;
						invBar[i].item.stackSize = invBar[i].item.stackSize + diff;
					} else {
						invBar[i].item.stackSize = invBar[i].item.stackSize + stackSize;
						return;
					}
					
					
				}
				
			}
		}
		for(int i = 0; i < invBag.length; i++) {
			if(invBag[i].item.id == addedItem.id ) {
				if(invBag[i].item.stackSize != addedItem.maxStackSize ) {
					if(invBag[i].item.stackSize + stackSize > addedItem.maxStackSize) {
						int diff = addedItem.maxStackSize - invBag[i].item.stackSize;
						stackSize = stackSize - diff;
						invBag[i].item.stackSize = invBag[i].item.stackSize + diff;
					} else {
						invBag[i].item.stackSize = invBag[i].item.stackSize + stackSize;
						return;
					}
				}
						
			}
		}
		for(int i = 0; i < invBar.length; i++) {
			if(invBar[i].item.id == 0 ) {
				invBar[i].item = addedItem;
				invBar[i].item.stackSize = stackSize;
				return;
			}
		}
		for(int i = 0; i < invBag.length; i++) {
			if(invBag[i].item.id == 0) {
				invBag[i].item = addedItem;
				invBag[i].item.stackSize = stackSize;
				return;
			}
		}
	}
	public void update() {
		if(isUsingLeftItem) invBar[itemSelected].item.useLeft();
		if(isUsingRightItem) invBar[itemSelected].item.useRight();
	}
	
	public void useItem(int button) {
		if(button == Input.Buttons.LEFT) isUsingLeftItem = true;
		if(button == Input.Buttons.RIGHT) isUsingRightItem = true;
	}
	
	public void stopUsingItem(int button) {
		if(button == Input.Buttons.LEFT) isUsingLeftItem = false;
		if(button == Input.Buttons.RIGHT) isUsingRightItem = false;
	}
	
	public void mouseWheelDidIncrement() {
		if(itemSelected < invBar.length - 1) itemSelected += 1;
		else itemSelected = 0;
	}
	
	public void mouseWheelDidDecrement() {
		if(itemSelected > 0) itemSelected -= 1;
		else itemSelected = invBar.length - 1;
	}
	
	public void changeSelectedItemTo(int key) {
		if(key == Keys.NUM_1) itemSelected = 0;
		else if(key == Keys.NUM_2) itemSelected = 1;
		else if(key == Keys.NUM_3) itemSelected = 2;
		else if(key == Keys.NUM_4) itemSelected = 3;
		else if(key == Keys.NUM_5) itemSelected = 4;
		else if(key == Keys.NUM_6) itemSelected = 5;
		else if(key == Keys.NUM_7) itemSelected = 6;
		else if(key == Keys.NUM_8) itemSelected = 7;
		else if(key == Keys.NUM_9) itemSelected = 8;
		else if(key == Keys.NUM_0) itemSelected = 9;
	}
	
	public void deleteItemIfNecessary() {
		if(invBar[itemSelected].item.stackSize < 1) invBar[itemSelected].item = new NoItem();
	}
	
	public void open(Hero hero) {
		formerHeroAnimation = hero.sprite.getCurrentAnimation();
		hero.sprite.changeAnimationTo(WALK_DOWN);
	}
	
	public void close(Hero hero) {
		hero.sprite.changeAnimationTo(formerHeroAnimation);
	}
}
