package com.madbros.adventurecraft.Items;


import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Utils.Rect;
public class NoItem extends BaseItem {
	public int[] craftCost = {};
	public int[] craftCostAmount = new int[]{0};
	
	public NoItem() {
		maxStackSize = 99;
		swingSprite = Sprites.sprites.get(Sprites.SWORD);
		swingSprite.setColor(0, 0, 0, 0);
		originX = 0;
		originY = 32;
		weaponOffsetX = 0;
		weaponOffsetY = 0;
		cRectFinal = new Rect (0,0,0,0);
		sound = "sounds/swordSwing1.wav";
		attackPower = 1;
		
		cRectU = new Rect (0,-6,82,32);
		cRectD = new Rect (0,54,82,32);
		cRectL = new Rect (-22,0,40,82);
		cRectR = new Rect (46,0,40,82);
	}
	

	

	@Override
	public void render(Rect rect) {
		
	}
	
	@Override
	public void render(int x, int y) {
		
	}
	
	@Override
	public NoItem createNew() {
		return new NoItem();
	}
}
