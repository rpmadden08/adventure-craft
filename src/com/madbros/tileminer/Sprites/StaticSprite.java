package com.madbros.tileminer.Sprites;

//import org.lwjgl.opengl.GL11;
//import org.newdawn.slick.opengl.Texture;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.madbros.tileminer.Utils.*;

public class StaticSprite extends Sprite {
	private float width, height;
	private int leftRenderOffset, topRenderOffset;
	private SpriteBatch spriteBatch;

	/*********************************** Constructors ***********************************/
	public StaticSprite(Texture texture, int x, int y, int width, int height, SpriteBatch spriteBatch) {
		this(texture, x, y, width, height, 0, 0, spriteBatch);
	}
	
	public StaticSprite(Texture texture, int x, int y, int width, int height, int leftRenderOffset, int topRenderOffset, SpriteBatch spriteBatch) {
		super(texture, x, y, width, height);
		this.spriteBatch = spriteBatch;
		this.leftRenderOffset = leftRenderOffset;
		this.topRenderOffset = topRenderOffset;
		this.width = width;
		this.height = height;
		flip(false, true);
	}
	
	/*********************************** Drawing ***********************************/
	@Override
	public void draw(float x, float y, float z) {
		this.draw(x, y, z, this.getWidth(), this.getHeight());
	}
	
	@Override
	public void draw(Rect r, float z) {

		RectInt r2 = r.getRectInt();
		this.draw(r2.x, r2.y, z, r2.w, r2.h);
	}
	
	@Override
	public void draw(float x, float y, float z, float w, float h) {
		x += leftRenderOffset; y += topRenderOffset;
	    //TextureRegion regions = new TextureRegion(getTexture(), 0, 0, 32, 32);      // #3
	       
		//setBounds(x, y, w, h);
		setBounds(x, y, w, h);
		draw(spriteBatch);
		//spriteBatch.draw(regions, 0,0,32,32);
		
//		GL11.glTexCoord3f(textureOffsetX, textureOffsetY, z); //TOP LEFT
//		GL11.glVertex3f(x, y, z); 
//		GL11.glTexCoord3f(textureOffsetX, textureOffsetY+normalizedHeight, z); //BOTTOM LEFT
//		GL11.glVertex3f(x, y+h, z);         
//		GL11.glTexCoord3f(textureOffsetX+normalizedWidth, textureOffsetY+normalizedHeight, z); //BOTTOM RIGHT 
//		GL11.glVertex3f(x+w, y+h, z); 
//		GL11.glTexCoord3f(textureOffsetX+normalizedWidth, textureOffsetY, z); //TOP RIGHT
//		GL11.glVertex3f(x+w, y, z);
	}
	
	@Override
	public void draw(float x, float y, float w, float h, float newX, float newY, float newW, float newH) {
		x += leftRenderOffset; y += topRenderOffset;
		setBounds(x, y, w, h);
		
		draw(spriteBatch);
//		GL11.glTexCoord3f(textureOffsetX, textureOffsetY, z); //TOP LEFT
//		GL11.glVertex3f(x, y, z); 
//		GL11.glTexCoord3f(textureOffsetX, textureOffsetY+normalizedHeight, z); //BOTTOM LEFT
//		GL11.glVertex3f(x, y+h, z);         
//		GL11.glTexCoord3f(textureOffsetX+normalizedWidth, textureOffsetY+normalizedHeight, z); //BOTTOM RIGHT 
//		GL11.glVertex3f(x+w, y+h, z); 
//		GL11.glTexCoord3f(textureOffsetX+normalizedWidth, textureOffsetY, z); //TOP RIGHT
//		GL11.glVertex3f(x+w, y, z);
	}
	
	@Override
	public void draw(float x, float y, float z, float scale) {
		x += leftRenderOffset*scale; y += topRenderOffset*scale;
		
		setOrigin(0, 0);
		setScale(scale);
		setPosition(x, y);
		
		draw(spriteBatch);
		setScale(1);
//		GL11.glTexCoord3f(textureOffsetX, textureOffsetY, z); //TOP LEFT
//		GL11.glVertex3f(x, y, z); 
//		GL11.glTexCoord3f(textureOffsetX, textureOffsetY+normalizedHeight, z); //BOTTOM LEFT
//		GL11.glVertex3f(x, y+this.height*scale, z);         
//		GL11.glTexCoord3f(textureOffsetX+normalizedWidth, textureOffsetY+normalizedHeight, z); //BOTTOM RIGHT 
//		GL11.glVertex3f(x+this.width*scale, y+this.height*scale, z); 
//		GL11.glTexCoord3f(textureOffsetX+normalizedWidth, textureOffsetY, z); //TOP RIGHT
//		GL11.glVertex3f(x+this.width*scale, y, z);
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
