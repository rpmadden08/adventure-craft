package com.madbros.tileminer.GameStates;

import static com.madbros.tileminer.Constants.*;

import com.badlogic.gdx.Gdx;
import com.madbros.tileminer.*;

public class FurnaceState extends MainState {
	public FurnaceState() {
		type = State.FURNACE;
		input = new FurnaceStateInput();
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
		Game.animationSystem.updateInventory(Game.hero, Game.inventory, Game.mobController);
		Game.inventory.craftingMenu.refreshCraftSlots(Game.inventory.craftingMenu.currentCraftableList);
		
	}
	
	@Override
	protected void renderHud() {
		Game.batch.setProjectionMatrix(Game.camera.combined);
		Game.batch.setShader(Game.defaultShader);
		Game.batch.begin();
		
			Game.renderSystem.renderHud(Game.inventory);
			Game.renderSystem.renderText(Game.inventory, Game.batch);
			Game.renderSystem.renderInventory(Game.hero, Game.inventory);
			//Game.renderSystem.renderInventoryPage2(Game.hero, Game.inventory);
			
			Game.renderSystem.renderArmorSlots(Game.hero, Game.inventory);
			Game.renderSystem.renderFurnace(Game.inventory);
			Game.inventory.craftingMenu.render();
			
			Game.renderSystem.renderInventoryText(Game.inventory, Game.batch);
			
			Game.renderSystem.renderCrafting(Game.inventory);
			Game.renderSystem.renderCraftingText(Game.inventory, Game.batch);
			Game.renderSystem.renderHeldItem(Game.inventory);
		Game.batch.end();
	}
}
