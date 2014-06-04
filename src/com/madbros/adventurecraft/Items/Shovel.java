package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.TileTypes.Tile;
import com.madbros.adventurecraft.Utils.Rect;

public class Shovel extends ToolItem {
	public Shovel() {
		id = SHOVEL;
		name = "Shovel";
		sprite = Sprites.sprites.get(Sprites.SHOVEL_ITEM);
		sound = "sounds/shovelDig.wav";
		swingSprite = sprite;
		itemPower = 5;
		is32 = true;
		isRepeatable = true;
		isInUse = false;
		cRectU = new Rect (0,-6,82,32);
		cRectD = new Rect (0,54,82,32);
		cRectL = new Rect (-22,0,40,82);
		cRectR = new Rect (46,0,40,82);
	}
	
	@Override
	public Shovel createNew() {
		return new Shovel();
	}
	
	public void useLeft() {
		if(Game.level.tileBeingAttacked.isDiggable &&isInRange == true 
				&& !isPlacementCollidingWithHero(Game.level.highlightedBlock, Game.level.tileBeingAttacked.layer)
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
		if(Game.level.tileBeingAttacked.layer == OBJECT_LAYER) {
			Game.level.tileBeingAttacked.deleteMe(Game.level.highlightedBlockX, Game.level.highlightedBlockY, Game.level.activeBlocks);
			calculateUsage();
		} else if(Game.level.tileBeingAttacked.currentHp < 1) {
			
			Game.level.highlightedBlock.deleteTopGrassTile();
			
			Game.level.tileBeingAttacked.deleteMe(Game.level.highlightedBlockX, Game.level.highlightedBlockY, Game.level.activeBlocks);
			
			Game.level.autoTileHighlightedBlock();
			calculateUsage();
		} else {
			Game.soundController.create(sound, 0.2f);
		}
		Game.particleEffectController.add(tileParticle, 
				Game.level.highlightedBlock.absRect.x +(TILE_SIZE/2), Game.level.highlightedBlock.absRect.y + (TILE_SIZE/2));
	}
	public boolean checkID(int id) {
		if(id == AIR  || id == TALL_GRASS_A_TILE || id == TALL_GRASS_B_TILE || id == TALL_GRASS_C_TILE || 
				id == RED_MUSHROOM_TILE || id == BROWN_MUSHROOM_TILE || id == RED_FLOWERS_TILE || id == YELLOW_FLOWERS_TILE ) {
			return true;	
		} else {
			return false;
		}
		
	}
	
	public void getTopTile() {
		if(Game.level.tileBeingAttacked != Game.level.highlightedBlock.getTopTerrainTile()) {
			Game.level.tileBeingAttacked.currentHp = Game.level.tileBeingAttacked.maxHp;
			Game.level.tileBeingAttacked = Game.level.highlightedBlock.getTopTerrainTile();
//			Game.level.tileBeingAttacked2 = Game.level.highlightedBlock2.getTopTerrainTile();
//			Game.level.tileBeingAttacked3 = Game.level.highlightedBlock3.getTopTerrainTile();
//			Game.level.tileBeingAttacked4 = Game.level.highlightedBlock4.getTopTerrainTile();
		}
	}
	
	public Tile topTile(Block block) {
		return block.getTopTerrainTile();
	}
	
	public void highlightItem(Block block, int x, int y) {
		
		if(Game.level.tileBeingAttacked.isDiggable ) {
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
