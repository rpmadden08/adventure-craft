package com.madbros.adventurecraft.GameStates;

import static com.madbros.adventurecraft.Constants.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Time;
import com.madbros.adventurecraft.Menus.*;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Utils.Rect;

public class LoadingState extends GameState{
	
	public LoadingState(SpriteBatch batch) {
		type = State.LOADING;
		
		input = new LoadingStateInput();
		Gdx.input.setInputProcessor(input);
	}

	@Override
	protected void renderTextures() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Game.batch.setProjectionMatrix(Game.camera.combined);
		//Game.batch.setShader(Game.currentShader);
		Game.batch.begin();
			Rect test = new Rect(Game.currentScreenSizeX/2-40,Game.currentScreenSizeX/2-10, 80, 20);
			Sprites.pixel.setColor(Color.GREEN);
			Sprites.pixel.draw(0,0,0,100,100);
			Sprites.pixel.setColor(Color.WHITE);

		Game.batch.end();
	}
	
	@Override
	protected void updateStates() {
		dt = Gdx.graphics.getRawDeltaTime();
		Game.zAngle += dt * Game.zSpeed;
		while(Game.zAngle > Game.PI2)
			Game.zAngle -= Game.PI2;
		
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