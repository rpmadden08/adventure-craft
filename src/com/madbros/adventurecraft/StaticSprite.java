package com.madbros.adventurecraft;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import com.madbros.adventurecraft.Utils.*;

public class StaticSprite extends Sprite {
	private float normalizedWidth, normalizedHeight;
	private float width, height;
	private float textureOffsetX, textureOffsetY;
	private int leftRenderOffset, topRenderOffset;

	/*********************************** Constructors ***********************************/
	public StaticSprite(Texture texture, float x, float y, float width, float height) {
		this(texture, x, y, width, height, 0, 0);
	}
	
	public StaticSprite(Texture texture, float x, float y, float width, float height, int leftRenderOffset, int topRenderOffset) {
		this.leftRenderOffset = leftRenderOffset;
		this.topRenderOffset = topRenderOffset;
		this.textureOffsetX = x / texture.getImageWidth() * texture.getWidth();
		this.textureOffsetY = y / texture.getImageHeight() * texture.getHeight();
		this.width = width;
		this.height = height;
		this.normalizedWidth = width / texture.getImageWidth() * texture.getWidth();
		this.normalizedHeight = height / texture.getImageHeight() * texture.getHeight();
	}
	
	/*********************************** Drawing ***********************************/
	@Override
	public void draw(float x, float y, float z) {
		this.draw(x, y, z, this.width, this.height);
	}
	
	@Override
	public void draw(Rect r, float z) {
		this.draw(r.x, r.y, z, r.w, r.h);
	}
	
	@Override
	public void draw(float x, float y, float z, float w, float h) {
		x += leftRenderOffset; y += topRenderOffset;
		GL11.glTexCoord3f(textureOffsetX, textureOffsetY, z); //TOP LEFT
		GL11.glVertex3f(x, y, z); 
		GL11.glTexCoord3f(textureOffsetX, textureOffsetY+normalizedHeight, z); //BOTTOM LEFT
		GL11.glVertex3f(x, y+h, z);         
		GL11.glTexCoord3f(textureOffsetX+normalizedWidth, textureOffsetY+normalizedHeight, z); //BOTTOM RIGHT 
		GL11.glVertex3f(x+w, y+h, z); 
		GL11.glTexCoord3f(textureOffsetX+normalizedWidth, textureOffsetY, z); //TOP RIGHT
		GL11.glVertex3f(x+w, y, z);
	}
	
	@Override
	public void draw(float x, float y, float z, float scale) {
		x += leftRenderOffset*scale; y += topRenderOffset*scale;
		GL11.glTexCoord3f(textureOffsetX, textureOffsetY, z); //TOP LEFT
		GL11.glVertex3f(x, y, z); 
		GL11.glTexCoord3f(textureOffsetX, textureOffsetY+normalizedHeight, z); //BOTTOM LEFT
		GL11.glVertex3f(x, y+this.height*scale, z);         
		GL11.glTexCoord3f(textureOffsetX+normalizedWidth, textureOffsetY+normalizedHeight, z); //BOTTOM RIGHT 
		GL11.glVertex3f(x+this.width*scale, y+this.height*scale, z); 
		GL11.glTexCoord3f(textureOffsetX+normalizedWidth, textureOffsetY, z); //TOP RIGHT
		GL11.glVertex3f(x+this.width*scale, y, z);
	}
	
	/*********************************** Other ***********************************/
	public float getWidth() {
		return width;
	}
	
	public float getHeight() {
		return height;
	}
	
	public void updateOffsets(int leftRenderOffset, int topRenderOffset) {
		this.leftRenderOffset = leftRenderOffset;
		this.topRenderOffset = topRenderOffset;
	}
}
