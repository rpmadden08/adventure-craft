package com.madbros.adventurecraft;

import static com.madbros.adventurecraft.Constants.*;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import com.madbros.adventurecraft.Slots.*;
import com.madbros.adventurecraft.Sprites.*;
import com.madbros.adventurecraft.GameObjects.Hero;
import com.madbros.adventurecraft.Items.*;

public class Inventory {
	public StaticSprite selectSprite = Sprites.selectSprite;
	public StaticSprite[][] menuSprites = {Sprites.menuSprites1, Sprites.menuSprites2, Sprites.menuSprites3};
	public int formerHeroAnimation;
	
	public Slot[] invBar= new Slot[INV_LENGTH];
	public Slot[] invBag= new Slot[INV_LENGTH * INV_HEIGHT];
	public CraftingSlot[] invCrafting= new CraftingSlot[2 * 2];
	public CraftedSlot[] invCrafted = new CraftedSlot[1];
	public ClothingSlot[] invClothing = new ClothingSlot[4];

	
	public Item heldItem = new NoItem();
	public int itemSelected = 0;
	public boolean isUsingItemRight = false;
	public boolean isUsingItemLeft = false;
	
	public Inventory() {
		for(int i = 0; i < INV_LENGTH; i++) {
			invBar[i] = new Slot(INV_BAR_RECT.x + (INV_SLOT_SIZE + INV_SLOT_MARGIN.right) * i, INV_BAR_RECT.y);
		}
		
		int k = 0;
		for(int x = 0; x < INV_LENGTH; x++) {
			for(int y = 0; y < INV_HEIGHT; y++) {
				invBag[k] = new Slot(INV_BAG_RECT.x + (INV_SLOT_SIZE + INV_SLOT_MARGIN.right) * x,
									 INV_BAG_RECT.y + (INV_SLOT_SIZE + INV_SLOT_MARGIN.bottom) * y);
				k++;
			}
		}
		
		k = 0;
		for(int x = 0; x < 2; x++) {	
			for(int y = 0; y < 2; y++) {
				invCrafting[k] = new CraftingSlot(INV_CRAFTING_RECT.x + (INV_SLOT_SIZE + 2) * x,
										  INV_CRAFTING_RECT.y + (INV_SLOT_SIZE + 2) * y);
				k++;
			}
		}
		
		invCrafted[0] = new CraftedSlot(INV_CRAFTING_RECT.x2() + 5, INV_CRAFTING_RECT.y);

		invClothing[0] = new ClothingSlot(INV_CHAR_RECT.x - 50,INV_CHAR_RECT.y +60, HELMET_SLOT);
		invClothing[1] = new ClothingSlot(INV_CHAR_RECT.x - 50,INV_CHAR_RECT.y +110,ARMOR_SLOT);
		invClothing[2] = new ClothingSlot(INV_CHAR_RECT.x - 50,INV_CHAR_RECT.y +160,LEGGINGS_SLOT);
		invClothing[3] = new ClothingSlot(INV_CHAR_RECT.x - 50,INV_CHAR_RECT.y +210,BOOTS_SLOT);
		
		invBar[0].item = new IronBoots();
		invBar[1].item = new IronArmor();
		invBar[2].item = new Log();
		invBar[2].item.stackSize = 99;
		invBar[3].item = new SandClump();
		invBar[3].item.stackSize = 99;
		invBar[4].item = new GrassSeed();
		invBar[4].item.stackSize = 99;
		invBar[5].item = new EarthClump();
		invBar[5].item.stackSize = 99;
		invBar[6].item = new IronLeggings();
		invBar[7].item = new Sapling();
		invBar[7].item.stackSize = 99;

		invClothing[0].item = new IronHelmet();
		Game.hero.addClothingItem((ClothingItem)invClothing[0].item);
		invBar[8].item = new IronHelmet();

	}
	
	public void update() {
		if(Mouse.isButtonDown(LEFT_MOUSE_BUTTON)) invBar[itemSelected].item.useLeft();
		if(Mouse.isButtonDown(RIGHT_MOUSE_BUTTON)) invBar[itemSelected].item.useRight();
	}

	public void mouseWheelDidIncrement() {
		if(itemSelected > 0) itemSelected -= 1;
		else itemSelected = invBar.length - 1;
	}
	
	public void mouseWheelDidDecrement() {
		if(itemSelected < invBar.length - 1) itemSelected += 1;
		else itemSelected = 0;
	}
	
	public void changeSelectedItemTo(int key) {
		if(key == Keyboard.KEY_1) itemSelected = 0;
		else if(key == Keyboard.KEY_2) itemSelected = 1;
		else if(key == Keyboard.KEY_3) itemSelected = 2;
		else if(key == Keyboard.KEY_4) itemSelected = 3;
		else if(key == Keyboard.KEY_5) itemSelected = 4;
		else if(key == Keyboard.KEY_6) itemSelected = 5;
		else if(key == Keyboard.KEY_7) itemSelected = 6;
		else if(key == Keyboard.KEY_8) itemSelected = 7;
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
