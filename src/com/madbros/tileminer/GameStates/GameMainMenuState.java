package com.madbros.tileminer.GameStates;

import static com.madbros.tileminer.Constants.*;

import com.badlogic.gdx.Gdx;
import com.madbros.tileminer.*;

public class GameMainMenuState extends MainState {
	public GameMainMenuState() {
		type = State.INVENTORY;
		input = new GameMainMenuStateInput();
		Gdx.input.setInputProcessor(input);
	}
	
	@Override
	protected void updateStates() {
		dt = Gdx.graphics.getRawDeltaTime();
		Game.zAngle += dt * Game.zSpeed;
		while(Game.zAngle > Game.PI2)
			Game.zAngle -= Game.PI2;
		Game.animationSystem.updateMain(Game.hero, Game.mobController);	//a list of mobs will also be passed to this system
		input.mouseMoved(Gdx.input.getX(), Gdx.input.getY());
		
		Game.mobController.update();
		Game.soundController.update();
		Game.musicController.update();
		Game.collectibleController.update();
		Game.notificationController.update();
		Game.particleEffectController.update();
		Game.level.update();
		Game.debugger.update();
		Game.hero.update();
		
		if(Game.hero.isDead == true && Game.hero.deathWait >59) {
			return;
		}
	}
	
	@Override
	protected void renderHud() {
		Game.batch.setProjectionMatrix(Game.camera.combined);
		Game.batch.setShader(Game.defaultShader);
		Game.batch.begin();
			Game.renderSystem.renderHud(Game.inventory);
			Game.renderSystem.renderText(Game.inventory, Game.batch);
			Game.gameMainMenu.render();
			Game.gameMainMenu.renderText();
			if(Game.debugger.isDebugging) Game.debugger.renderText(Game.hero.absRect, Game.batch);
		Game.batch.end();
	}
}
