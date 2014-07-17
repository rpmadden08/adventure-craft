package com.madbros.tileminer.GameStates;

import static com.madbros.tileminer.Constants.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.madbros.tileminer.Game;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Rect;

public class LoadingState extends GameState{
	
	public LoadingState(SpriteBatch batch) {
		type = State.LOADING;
		Game.currentLoadingPoints = 0;
		input = new LoadingStateInput();
		Gdx.input.setInputProcessor(input);
	}

	@Override
	protected void renderTextures() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Game.batch.setProjectionMatrix(Game.camera.combined);
		Rect totalLoadingBar = new Rect(0,0,300, 40);
		float percentage = Game.currentLoadingPoints/Game.totalLoadingPoints; //Giving me 0.3 if 3/10
		float loadingBarWidth = (int)totalLoadingBar.w*percentage;
		Rect loadingBar = new Rect(Game.currentScreenSizeX/2-(totalLoadingBar.w/2),Game.currentScreenSizeY/2-(totalLoadingBar.h/2),(int) loadingBarWidth, totalLoadingBar.h);
		Game.batch.begin();
			
			//Rect test = new Rect(Game.currentScreenSizeX/2-150,Game.currentScreenSizeY/2-20, 300, 40);
			
			Sprites.pixel.draw((int)loadingBar.x-1,(int)loadingBar.y-1,0,(int)totalLoadingBar.w+2,(int)totalLoadingBar.h+2);
		
			Sprites.pixel.setColor(Color.BLACK);
			Sprites.pixel.draw((int)loadingBar.x,(int)loadingBar.y,0,(int)totalLoadingBar.w,(int)totalLoadingBar.h);
			Sprites.pixel.setColor(Color.WHITE);
			
			Sprites.pixel.setColor(Color.GREEN);
			Sprites.pixel.draw((int)loadingBar.x,(int)loadingBar.y,0,(int)loadingBar.w,(int)loadingBar.h);
			Sprites.pixel.setColor(Color.WHITE);

		Game.batch.end();
	}
	
	@Override
	protected void updateStates() {
		dt = Gdx.graphics.getRawDeltaTime();
		Game.zAngle += dt * Game.zSpeed;
		while(Game.zAngle > Game.PI2)
			Game.zAngle -= Game.PI2;
		Game.musicController.update();
		
	}
	
//	public void update() {
////		Game.debugger.start();
////		Time.setDelta();
////		
////		delta = Time.getDelta();
////		updateStates();
////		
////		Game.debugger.stopUpdateTime();
//	}
}