package com.madbros.adventurecraft.Sprites;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

public class SpriteBatch {
	Texture texture;
	
	public SpriteBatch(Texture t) {
		texture = t;
	}
	
	public void begin() {
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		texture.bind();
		GL11.glBegin(GL11.GL_QUADS);
	}

	public void end() {
		GL11.glEnd();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
	}
}
