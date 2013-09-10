package com.madbros.adventurecraft.Sprites;

import com.madbros.adventurecraft.Sprites.StaticSprite;
import com.madbros.adventurecraft.Utils.Rect;

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

}
