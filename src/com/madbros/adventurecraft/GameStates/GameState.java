package com.madbros.adventurecraft.GameStates;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.*;

public abstract class GameState {
	public State type;
	public BasicInput input;
	public StandardInventory standardInventory;
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
	
	public void resize(int width, int height) {};
	
	public void render() {
		Game.debugger.start();
		
		renderTextures();
		
		Game.debugger.stopRenderTime();
	}
}