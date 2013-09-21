package com.madbros.adventurecraft.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.madbros.adventurecraft.Utils.*;

public abstract class Sprite extends com.badlogic.gdx.graphics.g2d.Sprite{
	public Sprite() {
	}
	
	public Sprite(Texture texture, int x, int y, int width, int height) {
		super(texture, x, y, width, height);
	}
	public abstract void draw(float x, float y, float z);
	
	public abstract void draw(Rect r, float z);
	
	public abstract void draw(float x, float y, float z, float w, float h);
	
	public abstract void draw(float x, float y, float z, float scale);
}