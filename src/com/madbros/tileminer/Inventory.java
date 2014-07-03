package com.madbros.tileminer;

import static com.madbros.tileminer.Constants.*;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.madbros.tileminer.GameObjects.Hero;
import com.madbros.tileminer.Items.*;
import com.madbros.tileminer.Slots.*;
import com.madbros.tileminer.Sprites.*;
import com.madbros.tileminer.Utils.Rect;

public class Inventory {
	public StaticSprite selectSprite = Sprites.sprites.get(Sprites.SELECTOR);
	public StaticSprite[] menuSprites = Sprites.spriteCollections.get(Sprites.INVENTORY_MENU); // {Sprites.menuSprites1, Sprites.menuSprites2, Sprites.menuSprites3};
	public int formerHeroAnimation;
	public String menu1Title = "Inventory";
	public String menu2Title = "Crafting";
	public CraftingMenu craftingMenu = new CraftingMenu();
	public Slot[] invBar= new Slot[INV_LENGTH];
	public Slot[] invBag= new Slot[INV_LENGTH * INV_HEIGHT];
	public Slot[] invChest= new Slot[INV_LENGTH * INV_HEIGHT];
	public int currentWorkSpace = 0;
	public boolean craftingTableOn = false;
	public boolean chestOn = false;
	public boolean furnaceOn = false;
	public boolean cauldronOn = false;
	public CraftingSlot[] invCrafting = new CraftingSlot[2 * 2];
	public CraftingSlot[] invTable = new CraftingSlot[3 * 3];
	//public CraftingSlot[] invFurnace = new CraftingSlot[2];
	//public CraftingSlot[] invFurnaceFuel = new CraftingSlot[1];
	public CraftedSlot[] invCrafted = new CraftedSlot[1];
	public ClothingSlot[] invClothing = new ClothingSlot[4];
	public int currentInvBlockX = 0;
	public int currentInvBlockY = 0;
	public int currentInvActiveBlockX = 0;
	public int currentInvActiveBlockY = 0;
	//public Furnace furnace = new Furnace();
	
//	public int furnaceFuel = 0;
//	public int furnaceBuildTime = 0;
//	public Item possiblyCraftableItem = new NoItem();
//	public boolean furnaceIsBurning = false;
//	public int timeCheck = 0;

	
	public Item heldItem = new NoItem();
	public int itemSelected = 0;
	public boolean isUsingRightItem = false;
	public boolean isUsingLeftItem = false;
	
	public Inventory() {
		for(int i = 0; i < INV_LENGTH; i++) {
			invBar[i] = new BarSlot(INV_BAR_RECT.x, INV_BAR_RECT.y + (INV_SLOT_SIZE + INV_SLOT_MARGIN.right) * i);
		}
		
		int k = 0;
		for(int x = 0; x < INV_LENGTH; x++) {
			for(int y = 0; y < INV_HEIGHT; y++) {
				invBag[k] = new BagSlot(INV_BAG_RECT.x + (INV_SLOT_SIZE + INV_SLOT_MARGIN.right) * y,
									 INV_BAG_RECT.y + (INV_SLOT_SIZE + INV_SLOT_MARGIN.right) * x);
				k++;
			}
		}
		
		k = 0;
		for(int x = 0; x < INV_LENGTH; x++) {
			for(int y = 0; y < INV_HEIGHT; y++) {
				invChest[k] = new ChestSlot(INV_CRAFTING_RECT.x + (INV_SLOT_SIZE + INV_SLOT_MARGIN.right) * y,
						INV_CRAFTING_RECT.y + (INV_SLOT_SIZE + INV_SLOT_MARGIN.right) * x);
				k++;
			}
		}
		
		k = 0;
		for(int x = 0; x < 2; x++) {	
			for(int y = 0; y < 2; y++) {
				invCrafting[k] = new CraftingSlot(INV_CRAFTING_RECT.x + (INV_SLOT_SIZE+ INV_SLOT_MARGIN.right) * y,
										  INV_CRAFTING_RECT.y + (INV_SLOT_SIZE+ INV_SLOT_MARGIN.right) * x);
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
		
//		for(int i = 0; i < 2; i++) {
//			invFurnace[i] = new CraftingSlot(INV_CRAFTING_RECT.x + (INV_SLOT_SIZE),
//					  INV_CRAFTING_RECT.y + (INV_SLOT_SIZE)*i);
//		}
		
		invCrafted[0] = new CraftedSlot(INV_CRAFTING_RECT.x2() + 75, INV_CRAFTING_RECT.y);

		invClothing[0] = new ClothingSlot(INV_CLOTHING_RECT.x ,INV_CLOTHING_RECT.y, HELMET_SLOT);
		invClothing[1] = new ClothingSlot(INV_CLOTHING_RECT.x ,INV_CLOTHING_RECT.y +40,ARMOR_SLOT);
		invClothing[2] = new ClothingSlot(INV_CLOTHING_RECT.x ,INV_CLOTHING_RECT.y +80,LEGGINGS_SLOT);
		invClothing[3] = new ClothingSlot(INV_CLOTHING_RECT.x ,INV_CLOTHING_RECT.y +120,BOOTS_SLOT);
//		
		invBar[0].item = new Cauldron();
		invBar[0].item.stackSize = 3;
		invBar[1].item = new RedFlowers();
		invBar[1].item.stackSize = 99;
		invBar[2].item = new Honey();
		invBar[2].item.stackSize = 99;
		invBar[3].item = new GlassBottle();
		invBar[3].item.stackSize = 99;
		invBar[4].item = new Coal();
		invBar[4].item.stackSize = 99;
		invBar[5].item = new Furnace();
		invBar[5].item.stackSize = 1;
		invBar[6].item = new Steak();
		invBar[6].item.stackSize = 99;
		invBar[7].item = new CopperAxe();
		invBar[7].item.stackSize = 1;
		invBar[8].item = new LeatherHat();
		invBar[8].item.stackSize = 1;
		invBar[9].item = new Chest();
		invBar[9].item.stackSize = 1;
		
//		invBag[0].item = new FireStarter();
//		invBag[0].item.stackSize = 1;
//		invBag[1].item = new FirePit();
//		invBag[1].item.stackSize = 1;
//		invBag[2].item = new TallGrassA();
//		invBag[2].item.stackSize = 99;
//		invBag[3].item = new ArtichokeSprout();
//		invBag[3].item.stackSize = 99;
//		invBag[4].item = new IronBoots();
//		invBag[4].item.stackSize = 1;
//		invBag[5].item = new IronLeggings();
//		invBag[5].item.stackSize = 1;
//		invBag[6].item = new IronArmor();
//		invBag[6].item.stackSize = 1;
//		invBag[7].item = new IronHelmet();
//		invBag[7].item.stackSize = 1;
//		
//		invBag[8].item = new TinBoots();
//		invBag[8].item.stackSize = 1;
//		invBag[9].item = new TinLeggings();
//		invBag[9].item.stackSize = 1;
//		invBag[10].item = new TinArmor();
//		invBag[10].item.stackSize = 1;
//		invBag[11].item = new TinHelmet();
//		invBag[11].item.stackSize = 1;
//		
//		invBag[12].item = new CopperBoots();
//		invBag[12].item.stackSize = 1;
//		invBag[13].item = new CopperLeggings();
//		invBag[13].item.stackSize = 1;
//		invBag[14].item = new CopperArmor();
//		invBag[14].item.stackSize = 1;
//		invBag[15].item = new CopperHelmet();
//		invBag[15].item.stackSize = 1;
		

//		invClothing[0].item = new IronHelmet();
//		Game.hero.addClothingItem((ClothingItem)invClothing[0].item);
		

	}
	
	public void dropAll() {
		for(int i = 0; i < invBar.length; i++) {
			if(invBar[i].item.id != 0) {
				Rect collectibleRect = new Rect(Game.hero.absRect.x, Game.hero.absRect.y, 16, 16);
				Game.collectibleController.add(invBar[i].item.id, invBar[i].item.sprite, collectibleRect, invBar[i].item.stackSize, invBar[i].item.uses);
				invBar[i].item.stackSize = 0;
				invBar[i].item = new NoItem();
			}
			
		}
		
		for(int i = 0; i < invBag.length; i++) {
			if(invBag[i].item.id != 0) {
				Rect collectibleRect = new Rect(Game.hero.absRect.x, Game.hero.absRect.y, 16, 16);
				Game.collectibleController.add(invBag[i].item.id, invBag[i].item.sprite, collectibleRect, invBag[i].item.stackSize, invBag[i].item.uses);
				invBag[i].item.stackSize = 0;
				invBag[i].item = new NoItem();
			}
		}
		
		for(int i = 0; i < invClothing.length; i++) {
			if(invClothing[i].item.id != 0) {
				Rect collectibleRect = new Rect(Game.hero.absRect.x, Game.hero.absRect.y, 16, 16);
				Game.collectibleController.add(invClothing[i].item.id, invClothing[i].item.sprite, collectibleRect, invClothing[i].item.stackSize, invClothing[i].item.uses);
				invClothing[i].item.stackSize = 0;
				invClothing[i].item = new NoItem();
			}
		}
	}
	
	public void remove(Item removedItem, int stackSize) {
		for(int i = 0; i < invBar.length; i++) {
			if(invBar[i].item.id == removedItem.id ) {
				if(stackSize > invBar[i].item.stackSize) {
					stackSize = stackSize - invBar[i].item.stackSize;
					invBar[i].item.stackSize = 0;
					invBar[i].item = ITEM_HASH.get(EMPTY).createNew();
					//System.out.println(stackSize);
				} else {
					invBar[i].item.stackSize = invBar[i].item.stackSize - stackSize;
					stackSize = 0;
					if(invBar[i].item.stackSize == 0) {
						invBar[i].item =ITEM_HASH.get(EMPTY).createNew();
					}
					return;
				}
			}
		}
		for(int i = 0; i < invBag.length; i++) {
			if(invBag[i].item.id == removedItem.id ) {
				if(stackSize > invBag[i].item.stackSize) {
					stackSize = stackSize - invBag[i].item.stackSize;
					invBag[i].item.stackSize = 0;
					invBag[i].item = ITEM_HASH.get(EMPTY).createNew();
					//System.out.println(stackSize);
				} else {
					invBag[i].item.stackSize = invBag[i].item.stackSize - stackSize;
					stackSize = 0;
					if(invBag[i].item.stackSize == 0) {
						invBag[i].item =ITEM_HASH.get(EMPTY).createNew();
					}
					return;
				}
			}
		}
	}
	
	public void add(Item addedItem, int stackSize, int uses) {
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
				invBar[i].item.uses = uses;
				return;
			}
		}
		for(int i = 0; i < invBag.length; i++) {
			if(invBag[i].item.id == 0) {
				invBag[i].item = addedItem;
				invBag[i].item.stackSize = stackSize;
				invBag[i].item.uses = uses;
				return;
			}
		}
	}
	public void addItemToSlotArray(Item item, Slot[] slots) {
		int stackSize = item.stackSize;
		for(int i = 0; i < slots.length; i++) {
			if(slots[i].item.id == item.id ) {
				if(slots[i].item.stackSize != item.maxStackSize ) {
					if(slots[i].item.stackSize + stackSize > item.maxStackSize) {
						int diff = item.maxStackSize - slots[i].item.stackSize;
						stackSize = stackSize - diff;
						slots[i].item.stackSize = slots[i].item.stackSize + diff;
					} else {
						slots[i].item.stackSize = slots[i].item.stackSize + stackSize;
						return;
					}
					
					
				}
				
			}
		}
		for(int i = 0; i < slots.length; i++) {
			if(slots[i].item.id == 0 ) {
				slots[i].item = item;
				slots[i].item.stackSize = stackSize;
				return;
			}
		}
		
	}
	
	public void removeSlot(Slot slot) {
		slot.item = new NoItem();
	}
	
	public boolean isSlotAvailable(Item item, Slot[] slot) {
		int totalStack = item.stackSize;
		for(int x = 0; x < slot.length; x++) {
			
			if(slot[x].item.id == 0) {
				return true;
			} else if (slot[x].item.id == item.id) {
				if(slot[x].item.maxStackSize > totalStack + slot[x].item.stackSize) {
					return true;
				} else {
					totalStack = totalStack - (slot[x].item.maxStackSize-slot[x].item.stackSize);
				}
			}
		}
		return false;
	}
	
	public void update() {
		if(isUsingLeftItem) invBar[itemSelected].item.useLeft();
		if(isUsingRightItem) invBar[itemSelected].item.checkIsInRange();
		
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
		hero.sprite.changeAnimationTo(STAND_DOWN);
	}
	
	public void close(Hero hero) {
		hero.sprite.changeAnimationTo(formerHeroAnimation);
	}
}
