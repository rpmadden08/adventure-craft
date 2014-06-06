package com.madbros.tileminer.GameStates;

import static com.madbros.tileminer.Constants.*;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.madbros.tileminer.*;

public abstract class GameState {
	public State type;
	public static BasicInput input;
	protected float dt;
//	protected int delta;
	
	protected void updateStates() {}
	
	protected void renderTextures() {}
	protected void renderText() {}
	
	public void update() {
		Game.debugger.start();
		Time.setDelta();
		
//		delta = Time.getDelta();
		updateStates();
		
		Game.debugger.stopUpdateTime();
	}
	
	public void resize(int width, int height) {
		Game.currentScreenSizeX = width;
		Game.currentScreenSizeY = height;
//		DesktopLauncher.cfg.width = currentScreenSizeX;
//		DesktopLauncher.cfg.height = currentScreenSizeY;
		Game.camera= new OrthographicCamera(width,height);
		Game.camera.setToOrtho(true, width, height);
		

		//camera.update();
		Game.fbo = new FrameBuffer(Format.RGBA8888, width, height, false);
		 
		Game.lightShader.begin();
		Game.lightShader.setUniformf("resolution", width, height);
		Game.lightShader.end();

		Game.finalShader.begin();
		Game.finalShader.setUniformf("resolution", width, height);
		Game.finalShader.end();	
		
	};
	
	public void render() {
		Game.debugger.start();
		
		renderTextures();
		
		Game.debugger.stopRenderTime();
	}
}