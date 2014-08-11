package com.madbros.tileminer.Items;


//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.*;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Rect;

public class Hoe extends ToolItem {
	public Hoe() {
		id = HOE;
		name = "Hoe";
		is32 = true;
		sprite = Sprites.sprites.get(Sprites.HOE);
		swingSprite = sprite;
		itemPower = 5;
		isRepeatable = true;
		isInUse = false;
		cRectU = new Rect (0,-6,82,32);
		cRectD = new Rect (0,54,82,32);
		cRectL = new Rect (-22,0,32,82);
		cRectR = new Rect (46,0,32,82);
	}
	
	@Override
	public Hoe createNew() {
		return new Hoe();
	}
	
	public boolean checkID(int id) {
		if(id == AIR  || id == TALL_GRASS_A_TILE || id == TALL_GRASS_B_TILE || id == TALL_GRASS_C_TILE || 
				id == RED_MUSHROOM_TILE || id == BROWN_MUSHROOM_TILE || id == RED_FLOWERS_TILE || id == YELLOW_FLOWERS_TILE ) {
			return true;	
		} else {
			return false;
		}
		
	}
	public void useLeft() {
		
//		Tile topTile = Game.level.highlightedBlock.getTopTile();
		if(Game.hero.isSwimming == false) {
			if(Game.level.tileBeingAttacked.isTillable && isInRange == true
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
	}
	public void impact() {
		Game.level.tileBeingAttacked.currentHp -= itemPower;
		if(Game.level.tileBeingAttacked.currentHp < 1) {
			Game.level.highlightedBlock.deleteTopTileTilled();
			Game.level.tileBeingAttacked.deleteMe(Game.level.highlightedBlockX, Game.level.highlightedBlockY, Game.level.activeBlocks);
			
			Game.level.autoTileHighlightedBlock();
			calculateUsage();
		}
	}
	
	public void highlightItem(Block block, int x, int y) {
		
		if(Game.level.tileBeingAttacked.isTillable && Game.level.activeBlocks[block.getX(Game.level.activeBlocks)][block.getY(Game.level.activeBlocks)].layers[OBJECT_LAYER].id == AIR) {
			int objectTileID = Game.level.activeBlocks[block.getX(Game.level.activeBlocks)][block.getY(Game.level.activeBlocks)].layers[OBJECT_LAYER].id;
			
			if (checkID(objectTileID)) {
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
			}
		}
	}
}
