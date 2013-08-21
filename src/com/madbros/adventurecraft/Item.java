package com.madbros.adventurecraft;

import static com.madbros.adventurecraft.Constants.*;

import org.newdawn.slick.Color;

import com.madbros.adventurecraft.Slots.Slot;
import com.madbros.adventurecraft.Utils.Rect;

public abstract class Item {
	public int id = EMPTY;
	public int stackSize = 1;
	public int maxStackSize = 1;
	public int numberProducedByCrafting = 0;
	public int[] itemsPossiblyCraftable = {};
	public int[] craftCost = {};
	
	public StaticSprite texture;
	
	public void render(Rect slotRect) {
		texture.draw(slotRect.x + ITEM_OFFSET, slotRect.y + ITEM_OFFSET, Z_INV_ITEMS, ITEM_SIZE, ITEM_SIZE);
	}
	
	public void render(int x, int y) {
		texture.draw(x-ITEM_SIZE/2, y-ITEM_SIZE/2, Z_INV_ITEMS, ITEM_SIZE, ITEM_SIZE);
	}
	
	public void renderFont(int x, int y) {
		Color c = new Color(1.0f, 1.0f, 1.0f, 0.8f);
		int adjX = x - 2; int adjY = y - 2;
		if(stackSize < 10) adjX += 4;
		
		if(stackSize > 1) Textures.font.drawString(adjX, adjY, String.valueOf(stackSize), c);
		
		Color.white.bind();
	}
	
	public void useRight() {
		//place 
	}
	
	public void useLeft() {
		//attack
	}
	
	public boolean isValidRecipe(Slot[] craftingSlots) {
		return false;
	}
	
	abstract public Item createNew(); 
}