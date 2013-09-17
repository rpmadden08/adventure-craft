package com.madbros.adventurecraft.Sprites;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

public class SpriteBatch {
	Texture texture;
	
	public SpriteBatch(Texture t) {
		texture = t;
	}
	
	public void begin() {
//		if(Game.renderSystem.useShader)
//            ARBShaderObjects.glUseProgramObjectARB(Game.renderSystem.program);
//			glUniform1iARB(glGetUniformLocationARB(Game.renderSystem.program,"firstGrassTexture"),0);
//	
//			glUniform1iARB(glGetUniformLocationARB(Game.renderSystem.program,"secondGrassTexture"),1);
//	
//			glUniform1iARB(glGetUniformLocationARB(Game.renderSystem.program,"maskTexture"),2);
 
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		texture.bind();
		GL11.glBegin(GL11.GL_QUADS);
	}

	public void end() {
		GL11.glEnd();
		//release the shader
//        if(Game.renderSystem.useShader)
//        	ARBShaderObjects.glUseProgramObjectARB(0);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
	}
}
