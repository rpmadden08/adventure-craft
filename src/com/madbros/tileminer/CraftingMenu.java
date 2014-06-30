package com.madbros.tileminer;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Slots.CraftingSlot;
import com.madbros.tileminer.UI.InGameMenuUIButton;
import com.madbros.tileminer.Utils.ButtonFunction;

public class CraftingMenu {
	public CraftingSlot[] craftSlots= new CraftingSlot[INV_LENGTH * INV_HEIGHT];
	public int[] craftableList = {
		BEEHIVE, CAULDRON, CHEST, COPPER_ARMOR, COPPER_AXE, COPPER_BOOTS, COPPER_HELMET, 
		COPPER_HOE, COPPER_LEGGINGS, COPPER_PICK, COPPER_SHOVEL, COPPER_SWORD, DIRT_MOUNTAIN_ITEM,
		FIRE_PIT, FIRE_STARTER, FURNACE, IRON_ARMOR, IRON_AXE, IRON_BOOTS, IRON_HELMET, 
		IRON_HOE, IRON_LEGGINGS, IRON_PICK, IRON_SHOVEL, IRON_SWORD, LEATHER_ARMOR,
		LEATHER_BOOTS, LEATHER_HAT, LEATHER_LEGGINGS, PLANK, STAIRS_DOWN, STAIRS_UP,
		STICK, STONE_AXE, STONE_HOE, STONE_PICK, STONE_SHOVEL, STONE_SWORD, TABLE, 
		TIN_ARMOR, TIN_AXE, TIN_BOOTS, TIN_HELMET, TIN_HOE, TIN_LEGGINGS, TIN_PICK,
		TIN_SHOVEL, TIN_SWORD, TORCH, WOODEN_AXE, WOODEN_HOE, WOODEN_PICK, 
		WOODEN_SHOVEL, WOODEN_SWORD
	};
	public int[] currentCraftableList = craftableList;
	public int currentPage = 0;
	public InGameMenuUIButton nextPage;
	public InGameMenuUIButton lastPage;
	
	
	
	public CraftingMenu() {
		ButtonFunction nextPageFunction = new ButtonFunction() { public void invoke() { nextPage2(); } };
		nextPage =  new InGameMenuUIButton(366,35,20,20, ">",nextPageFunction, Game.batch );
		//nextPage.buttonColor = Color.GREEN;
		ButtonFunction lastPageFunction = new ButtonFunction() { public void invoke() { lastPage2(); } };
		lastPage =  new InGameMenuUIButton(344,35,20,20, "<",lastPageFunction, Game.batch );
		//lastPage.buttonColor = Color.GREEN;
		//public TextUIButton(int x, int y, int w, int h, String s, ButtonFunction func, SpriteBatch batch)
		int k = 0;
		for(int x = 0; x < INV_LENGTH; x++) {
			for(int y = 0; y < INV_HEIGHT; y++) {
				craftSlots[k] = new CraftingSlot(INV_CRAFTING_RECT.x + (INV_SLOT_SIZE + INV_SLOT_MARGIN.right) * y,
						INV_CRAFTING_RECT.y + (INV_SLOT_SIZE + INV_SLOT_MARGIN.right) * x);
				k++;
			}
		}
		
	}
	
	public void nextPage2() {
		if(currentCraftableList.length / 40 > currentPage) {
			currentPage = currentPage+1;
		} else {
			
		}
	}
	
	public void lastPage2() {
		if(currentPage!= 0) {
			currentPage = currentPage-1;
		}
		
	}
	
	public void handleMouseInput(boolean leftMousePressed, boolean leftMouseUp) {
		nextPage.handleMouseInput(leftMousePressed, leftMouseUp);
		lastPage.handleMouseInput(leftMousePressed, leftMouseUp);
	}
	
	public void handleMouseMove(int x,int y) {
		nextPage.handleMouseMove(x, y);
		lastPage.handleMouseMove(x, y);
	}
	
	public void render() {
		nextPage.render();
		nextPage.renderText();
		lastPage.render();
		lastPage.renderText();
	}
	
	public void update() {
		
	}
	
	public void refreshCraftSlots(int[] itemsPossiblyCraftable) {
		int itemStartPoint = currentPage * 40;
		for(int x = 0; x <craftSlots.length; x++) {
			if(x+ itemStartPoint < itemsPossiblyCraftable.length) {
				craftSlots[x].item = ITEM_HASH.get(itemsPossiblyCraftable[x+ itemStartPoint]).createNew();
				craftSlots[x].item.stackSize = craftSlots[x].item.numberProducedByCrafting;
				//If the item is craftable then do nothing.  If it is then mark the slot as uncraftable.  
				if(craftSlots[x].item.areIngredientsInInventory()) {
					craftSlots[x].isInactive = false;
				} else {
					craftSlots[x].isInactive = true;
				}
			} else {
				craftSlots[x].item = ITEM_HASH.get(EMPTY).createNew();
				craftSlots[x].item.stackSize = craftSlots[x].item.numberProducedByCrafting;
			}
		}
	}
	
	
	public void resetCraftSlots(int[] itemsPossiblyCraftable) {
		for(int x = 0; x <craftSlots.length; x++) {
			if(x < itemsPossiblyCraftable.length) {
				craftSlots[x].item = ITEM_HASH.get(craftableList[x]).createNew();
				craftSlots[x].item.stackSize = craftSlots[x].item.numberProducedByCrafting;
			} else {
				craftSlots[x].item = ITEM_HASH.get(EMPTY).createNew();
				craftSlots[x].item.stackSize = craftSlots[x].item.numberProducedByCrafting;
			}
		}
	}
}
