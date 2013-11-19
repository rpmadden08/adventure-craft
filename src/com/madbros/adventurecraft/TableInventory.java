package com.madbros.adventurecraft;

import static com.madbros.adventurecraft.Constants.*;
import com.badlogic.gdx.Input.Keys;
import com.madbros.adventurecraft.Slots.*;
import com.madbros.adventurecraft.Sprites.*;
import com.madbros.adventurecraft.GameObjects.Hero;
import com.madbros.adventurecraft.Items.*;

public class TableInventory extends Inventory{
	public StaticSprite selectSprite = Sprites.sprites.get(Sprites.INVENTORY_MENU_SELECTOR);
	public StaticSprite[] menuSprites = Sprites.spriteCollections.get(Sprites.INVENTORY_MENU); // {Sprites.menuSprites1, Sprites.menuSprites2, Sprites.menuSprites3};
	public int formerHeroAnimation;
	
	public CraftingSlot[] invCrafting = new CraftingSlot[2 * 2];
	public CraftingSlot[] invTable = new CraftingSlot[3 * 3];
	public CraftedSlot[] invCrafted = new CraftedSlot[1];
	public ClothingSlot[] invClothing = new ClothingSlot[4];

	
	public Item heldItem = new NoItem();
	public int itemSelected = 0;
	public boolean isUsingRightItem = false;
	public boolean isUsingLeftItem = false;
	
	public TableInventory() {
		
		int k = 0;
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
		

	}
}
