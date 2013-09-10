package com.madbros.adventurecraft.Sprites;

import com.madbros.adventurecraft.Utils.*;

public abstract class CompoundSprite extends Sprite {	
	@Override
	public abstract void draw(float x, float y, float z);
	
	@Override
	public abstract void draw(Rect r, float z);

	@Override
	public abstract void draw(float x, float y, float z, float w, float h);
}
