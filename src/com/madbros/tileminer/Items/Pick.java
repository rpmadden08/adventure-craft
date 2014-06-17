package com.madbros.tileminer.Items;


//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.*;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Rect;

public class Pick extends ToolItem {
	public Pick() {
		id = PICK;
		name = "Iron Pickaxe";
		is32 = true;
		sprite = Sprites.sprites.get(Sprites.PICK);
		swingSprite = sprite;
		itemPower = 5;
		isRepeatable = true;
		isInUse = false;
		sound = "sounds/pickSound.wav";
		cRectU = new Rect (0,-6,82,32);
		cRectD = new Rect (0,54,82,32);
		cRectL = new Rect (-22,0,32,82);
		cRectR = new Rect (46,0,32,82);
	}
	
	@Override
	public Pick createNew() {
		return new Pick();
	}
	
	public void useLeft() {
		
		if(Game.level.tileBeingAttacked.isPickable &&isInRange == true 
				&& Game.level.tileBeingAttacked.isToolStrongEnough(this)) {
			swing();

		} else if(!Game.hero.isAttacking && Game.hero.attackButtonReleased) {
			Game.soundController.create("sounds/swordSwing1.wav", 0.5f);
		}
		if(Game.level.tileBeingAttacked.isBreakable && isInRange == true) {
			Game.level.tileBeingAttacked.currentHp -= attackPower;
			if(Game.level.tileBeingAttacked.currentHp < 1) {
				Game.level.highlightedBlock.deleteObjectTile();
				Game.level.tileBeingAttacked.deleteMe(Game.level.highlightedBlockX, Game.level.highlightedBlockY, Game.level.activeBlocks);
				
				//Game.level.autoTileHighlightedBlock();
			}
		}
		Game.hero.attack(this);
	}
	public void impact() {
		Game.level.tileBeingAttacked.currentHp -= itemPower;
		String tileParticle = Game.level.tileBeingAttacked.particleEffect;
		if(Game.level.tileBeingAttacked.currentHp < 1) {
			Game.soundController.create("sounds/stoneRubble.wav", 0.8f);
			Game.level.highlightedBlock.deleteObjectTile();
			Game.level.highlightedBlock.collisionTile = null;
			Game.level.activeBlocks[Game.level.highlightedBlockX][Game.level.highlightedBlockY-1].deleteTile(ABOVE_LAYER_1);
			Game.level.activeBlocks[Game.level.highlightedBlockX][Game.level.highlightedBlockY-2].deleteTile(ABOVE_LAYER_2);
			
			Game.level.tileBeingAttacked.deleteMe(Game.level.highlightedBlockX, Game.level.highlightedBlockY, Game.level.activeBlocks);
			
			
			Game.level.autoTileBlock(Game.level.highlightedBlockX, Game.level.highlightedBlockY-2);
			Game.level.autoTileHighlightedBlock();
			calculateUsage();
		} else {
			Game.soundController.create(sound, 0.5f);
		}
		Game.particleEffectController.add(tileParticle, 
				Game.level.highlightedBlock.absRect.x +(TILE_SIZE/2), Game.level.highlightedBlock.absRect.y + (TILE_SIZE/2));
	}
	
	public void highlightItem(Block block, int x, int y) {
		
		if(Game.level.tileBeingAttacked.isPickable && Game.level.tileBeingAttacked.isAutoTileable) {

			Game.level.tileBeingAttacked.sprites[Game.level.tileBeingAttacked.topLeftAutoTile].setColor(HIGHLIGHT_COLOR);
			Game.level.tileBeingAttacked.sprites[Game.level.tileBeingAttacked.topLeftAutoTile].draw(x, y, Z_CHARACTER);
			Game.level.tileBeingAttacked.sprites[Game.level.tileBeingAttacked.topLeftAutoTile].setColor(1f,1f,1f,1f);
			
			Game.level.tileBeingAttacked.sprites[Game.level.tileBeingAttacked.topRightAutoTile].setColor(HIGHLIGHT_COLOR);
			Game.level.tileBeingAttacked.sprites[Game.level.tileBeingAttacked.topRightAutoTile].draw(x+16, y, Z_CHARACTER);
			Game.level.tileBeingAttacked.sprites[Game.level.tileBeingAttacked.topRightAutoTile].setColor(1f,1f,1f,1f);
			
			Game.level.tileBeingAttacked.sprites[Game.level.tileBeingAttacked.bottomLeftAutoTile].setColor(HIGHLIGHT_COLOR);
			Game.level.tileBeingAttacked.sprites[Game.level.tileBeingAttacked.bottomLeftAutoTile].draw(x, y+16, Z_CHARACTER);
			Game.level.tileBeingAttacked.sprites[Game.level.tileBeingAttacked.bottomLeftAutoTile].setColor(1f,1f,1f,1f);
			
			Game.level.tileBeingAttacked.sprites[Game.level.tileBeingAttacked.bottomRightAutoTile].setColor(HIGHLIGHT_COLOR);
			Game.level.tileBeingAttacked.sprites[Game.level.tileBeingAttacked.bottomRightAutoTile].draw(x+16, y+16, Z_CHARACTER);
			Game.level.tileBeingAttacked.sprites[Game.level.tileBeingAttacked.bottomRightAutoTile].setColor(1f,1f,1f,1f);
			//Someday maybe add the above layer 1 and 2 highlighting...
		}
	}
}