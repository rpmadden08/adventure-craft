package com.madbros.adventurecraft.GameStates;

import static com.madbros.adventurecraft.Constants.*;

//import com.alcovegames.fireshader.Game.ShaderSelection;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.madbros.adventurecraft.*;

public class MainState extends GameState {
	public MainState() {
		this(false);
	}
	
	public MainState(boolean isQuickLoad) {
		type = State.MAIN;
		if(isQuickLoad) Game.createDefaultSaveGameIfNecessary();
		
//		Game.camera= new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//		Game.camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//		Game.camera.update();
		
		Game.fbo = new FrameBuffer(Format.RGBA8888, Game.currentScreenSizeX, Game.currentScreenSizeY, false);
		 
		Game.lightShader.begin();
		Game.lightShader.setUniformf("resolution", Game.currentScreenSizeX, Game.currentScreenSizeY);
		Game.lightShader.end();

		Game.finalShader.begin();
		Game.finalShader.setUniformf("resolution", Game.currentScreenSizeX, Game.currentScreenSizeY);
		Game.finalShader.end();	
		
		input = new MainStateInput();
		Gdx.input.setInputProcessor(input);
	}
	
	@Override
	public void resize(int width, int height) {
//		Game.camera= new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//		Game.camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//		Game.camera.update();
		
		Game.fbo = new FrameBuffer(Format.RGBA8888, width, height, false);
		 
		Game.lightShader.begin();
		Game.lightShader.setUniformf("resolution", width, height);
		Game.lightShader.end();

		Game.finalShader.begin();
		Game.finalShader.setUniformf("resolution", width, height);
		Game.finalShader.end();	
	}
	
	@Override
	protected void updateStates() {
		dt = Gdx.graphics.getRawDeltaTime();
		Game.zAngle += dt * Game.zSpeed;
		while(Game.zAngle > Game.PI2)
			Game.zAngle -= Game.PI2;
		Game.animationSystem.updateMain(Game.hero, Game.mobController);	//a list of mobs will also be passed to this system
		input.mouseMoved(Gdx.input.getX(), Gdx.input.getY());
		Game.hero.update();
		Game.mobController.update();
		Game.soundController.update();
		Game.musicController.update();
		Game.collectibleController.update();
		Game.notificationController.update();
		Game.particleEffectController.update();
		Game.level.update();
		Game.inventory.update();
		Game.debugger.update();
	}
		
	@Override
	protected void renderTextures() {
		Game.renderSystem.setStartPosition(Game.level, Game.hero);
		
		
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Game.batch.setProjectionMatrix(Game.camera.combined);
		//TURN LIGHTING ON OFF HERE
		Game.batch.setShader(Game.currentShader);
		Game.batch.begin();
			Game.fbo.getColorBufferTexture().bind(1);
			Game.light.bind(0);

			Game.renderSystem.renderWorld(Game.level);
			Game.renderSystem.renderHero(Game.hero, Game.getCenterScreenX() - CHARACTER_SIZE/2, Game.getCenterScreenY() - CHARACTER_SIZE/2);
			Game.renderSystem.renderMobs(Game.mobController);
			Game.renderSystem.renderParticle(Game.particleEffectController);
			Game.renderSystem.renderWorldAbove(Game.level);
			Game.renderSystem.renderCollectibles(Game.collectibleController);
			
			
			//Game.map.render(Game.level.activeBlocks);
			
			
			
		Game.batch.end();
		
		
		Game.fbo.begin();
		Game.batch.setProjectionMatrix(Game.camera.combined);
		Game.batch.setShader(Game.defaultShader);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Game.batch.begin();
		Game.renderSystem.renderLight(Game.hero);
		Game.batch.end();
		
		Game.fbo.end();
		renderHud();
		
		
		//Game.map.render(Game.level.activeBlocks);
	}
	
	protected void renderHud() {
		Game.batch.setProjectionMatrix(Game.camera.combined);
		
		Game.batch.setShader(Game.defaultShader);
		Game.batch.begin();
			Game.renderSystem.renderHud(Game.inventory);
			Game.renderSystem.renderNotifications(Game.notificationController, Game.batch);
			Game.renderSystem.renderText(Game.inventory, Game.batch);
			if(Game.debugger.isDebugging) Game.debugger.renderText(Game.hero.absRect, Game.batch);
			if(Game.debugMenu.menuIsActive) {
				Game.debugMenu.render();
				Game.debugMenu.renderText();
			}
			if(Game.gameMainMenu.menuIsActive) {
				Game.gameMainMenu.render();
				Game.gameMainMenu.renderText();
			}
		Game.batch.end();
	}
}