package com.madbros.tileminer.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.madbros.tileminer.Utils.*;

public abstract class Sprite extends com.badlogic.gdx.graphics.g2d.Sprite{
	public Sprite() {
	}
	
	public Sprite(Texture texture, int x, int y, int width, int height) {
		super(texture, x, y, width, height);
	}
	public abstract void draw(float x, float y, float z);
	
	public abstract void draw(Rect r, float z);
	
	public abstract void draw(float x, float y, float z, float width, float height);
	
	public abstract void draw(float x, float y, float w, float h, float newX, float newY, float newW, float newH);
	
	public abstract void draw(float x, float y, float z, float scale);
}