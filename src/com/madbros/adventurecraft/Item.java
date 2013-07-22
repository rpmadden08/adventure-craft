package com.madbros.adventurecraft;

import static com.madbros.adventurecraft.Constants.*;

import org.newdawn.slick.Color;

public abstract class Item {
	public int id = EMPTY;
	public int stackSize = 1;
	public int maxStackSize = 1;
	public int numberProducedByCrafting = 0;
	public int[] itemsPossiblyCraftable = {};
	public int[] craftCost = {};
	
	public Sprite texture;
	
	public void render(Rect cellRect) {
		texture.draw(cellRect.x + ITEM_OFFSET, cellRect.y + ITEM_OFFSET, ITEM_SIZE, ITEM_SIZE);
	}
	
	public void render(int x, int y) {
		texture.draw(x-ITEM_SIZE/2, y-ITEM_SIZE/2, ITEM_SIZE, ITEM_SIZE);
	}
	
	public void renderFont(int x, int y) {
		if(stackSize > 1) {
			Textures.font.drawString(x + 2, y + 2, String.valueOf(stackSize), Color.white);
		}
	}
	
	public void useRight() {
		
	}
	
	public void useLeft() {
		
	}
	
	public boolean isValidRecipe(Cell[] craftingSlots) {
		return false;
	}
	
	abstract public Item createNew(); 
}