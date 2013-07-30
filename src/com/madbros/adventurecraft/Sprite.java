package com.madbros.adventurecraft;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import com.madbros.adventurecraft.Utils.Rect;

public class Sprite {
	private float originalX, originalY;
	private float textureW1, textureW2;
	private float textureH1, textureH2;
	private float normalizedWidth, normalizedHeight;
	private float width, height;
	private float textureOffsetX, textureOffsetY;

	public Sprite(Texture texture, float x, float y, float width, float height) {
		this.originalX = x;
		this.originalY = y;
		this.textureW1 = texture.getImageWidth();
		this.textureW2 = texture.getWidth();
		this.textureH1 = texture.getImageHeight();
		this.textureH2 = texture.getHeight();
		this.textureOffsetX = x / texture.getImageWidth() * texture.getWidth();
		this.textureOffsetY = y / texture.getImageHeight() * texture.getHeight();
		this.width = width;
		this.height = height;
		this.normalizedWidth = width / texture.getImageWidth() * texture.getWidth();
		this.normalizedHeight = height / texture.getImageHeight() * texture.getHeight();
	}

	public void draw(float x, float y) {
		this.draw(x, y, this.width, this.height);
	}
	
	public void draw(float x, float y, float w, float h) {
		GL11.glTexCoord2f(textureOffsetX, textureOffsetY); //TOP LEFT
		GL11.glVertex2f(x, y); 
		GL11.glTexCoord2f(textureOffsetX, textureOffsetY+normalizedHeight); //BOTTOM LEFT
		GL11.glVertex2f(x, y+h);         
		GL11.glTexCoord2f(textureOffsetX+normalizedWidth, textureOffsetY+normalizedHeight); //BOTTOM RIGHT 
		GL11.glVertex2f(x+w, y+h); 
		GL11.glTexCoord2f(textureOffsetX+normalizedWidth, textureOffsetY); //TOP RIGHT
		GL11.glVertex2f(x+w, y);
	}
	
	public void draw(Rect r) {
		this.draw(r.x, r.y, r.w, r.h);
	}
	
	public void draw(float x, float y, float w, float h, float newX, float newY, float newW, float newH) {
		float tOX = (originalX + newX) / textureW1 * textureW2;
		float tOY = (originalY + newY) / textureH1 * textureH2;
		float nW = newW / textureW1 * textureW2;
		float nH = newH / textureH1 * textureH2;
		GL11.glTexCoord2f(tOX, tOY); //TOP LEFT
		GL11.glVertex2f(x, y); 
		GL11.glTexCoord2f(tOX, tOY+nH); //BOTTOM LEFT
		GL11.glVertex2f(x, y+h);         
		GL11.glTexCoord2f(tOX+nW, tOY+nH); //BOTTOM RIGHT 
		GL11.glVertex2f(x+w, y+h); 
		GL11.glTexCoord2f(tOX+nW, tOY); //TOP RIGHT
		GL11.glVertex2f(x+w, y);
	}
}