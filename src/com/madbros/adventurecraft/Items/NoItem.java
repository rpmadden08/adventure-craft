package com.madbros.adventurecraft.Items;

import com.madbros.adventurecraft.Utils.Rect;
public class NoItem extends Item {
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
