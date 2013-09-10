package com.madbros.adventurecraft.Sprites;

import com.madbros.adventurecraft.Utils.*;

public abstract class Sprite {
	public abstract void draw(float x, float y, float z);
	
	public abstract void draw(Rect r, float z);
	
	public abstract void draw(float x, float y, float z, float w, float h);
	
	public abstract void draw(float x, float y, float z, float scale);
}