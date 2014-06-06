package com.madbros.tileminer.Sprites;

import com.madbros.tileminer.Sprites.StaticSprite;
import com.madbros.tileminer.Utils.Rect;

public class CompoundStaticSprite extends CompoundSprite{
	protected StaticSprite[] sprites;
	
	public CompoundStaticSprite(StaticSprite[] sprites) {
		this.sprites = sprites;
	}
	
	/*********************************** Drawing ***********************************/
	@Override
	public void draw(float x, float y, float z) {
		for(int i = 0; i < sprites.length; i++) {
			sprites[i].draw(x, y, z);
		}
	}
	
	@Override
	public void draw(Rect r, float z) {
		for(int i = 0; i < sprites.length; i++) {
			sprites[i].draw(r, z);
		}
	}

	@Override
	public void draw(float x, float y, float z, float w, float h) {
		for(int i = 0; i < sprites.length; i++) {
			sprites[i].draw(x, y, z, w, h);
		}
	}
	
	@Override
	public void draw(float x, float y, float z, float scale) {
		for(int i = 0; i < sprites.length; i++) {
			sprites[i].draw(x, y, z, scale);
		}
	}
	
	public void draw(float x, float y, float w, float h, float newX, float newY, float newW, float newH) {
		for(int i = 0; i < sprites.length; i++) {
			sprites[i].draw(x, y, w, h);
		}
	}

}
