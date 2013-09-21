package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.madbros.adventurecraft.Slots.*;
import com.madbros.adventurecraft.Sprites.*;
import com.madbros.adventurecraft.Utils.*;

public abstract class Item {
	public int id = EMPTY;
	public int stackSize = 1;
	public int maxStackSize = 1;
	public int numberProducedByCrafting = 0;
	public int[] itemsPossiblyCraftable = {};
	public int[] craftCost = {};
	public boolean is32 = false;
	
	public StaticSprite sprite;
	
	public void render(Rect slotRect) {
		sprite.draw(slotRect.x + ITEM_OFFSET, slotRect.y + ITEM_OFFSET, Z_INV_ITEMS);
	}
	
	public void render(int x, int y) {
		sprite.draw(x-ITEM_SIZE/2, y-ITEM_SIZE/2, Z_INV_ITEMS);
	}
	
	public void renderFont(int x, int y, SpriteBatch batch) {
		Color c = new Color(1.0f, 1.0f, 1.0f, 0.8f);
		Sprites.pixel.setColor(c);
		int adjX = x - 2; int adjY = y - 2;
		if(stackSize < 10) adjX += 4;
		
		Sprites.font.setColor(Color.WHITE);
		if(stackSize > 1) Sprites.font.draw(batch, String.valueOf(stackSize), adjX, adjY);
		Sprites.pixel.setColor(Color.WHITE);
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