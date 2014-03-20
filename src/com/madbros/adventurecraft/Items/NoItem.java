package com.madbros.adventurecraft.Items;

import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Utils.Rect;
public class NoItem extends Item {
	public int[] craftCost = {};
	public int[] craftCostAmount = new int[]{0};
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
