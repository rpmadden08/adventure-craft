package com.madbros.tileminer.Items;


import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Rect;
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
		
//		cRectU = new Rect (15,14,41,16);
//		cRectD = new Rect (15,54,41,16);
//		cRectL = new Rect (-4,20,16,41);
//		cRectR = new Rect (52,20,16,41);
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
