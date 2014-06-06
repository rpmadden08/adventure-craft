package com.madbros.tileminer.Sprites;

import com.madbros.tileminer.Utils.*;

public abstract class CompoundSprite extends Sprite {	
	@Override
	public abstract void draw(float x, float y, float z);
	
	@Override
	public abstract void draw(Rect r, float z);

	@Override
	public abstract void draw(float x, float y, float z, float w, float h);
}
