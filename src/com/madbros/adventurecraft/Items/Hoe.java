package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Sprites.Sprites;

public class Hoe extends ToolItem {
	public Hoe() {
		id = HOE;
		name = "Hoe";
		is32 = true;
		sprite = Sprites.sprites.get(Sprites.HOE);
		attackPower = 5;
		isRepeatable = true;
	}
	
	@Override
	public Hoe createNew() {
		return new Hoe();
	}
	
	public void useLeft() {
		
//		Tile topTile = Game.level.highlightedBlock.getTopTile();
		
		if(Game.level.tileBeingAttacked.isTillable && isInRange == true) {
			swing();

		}
		Game.hero.attack(this);
	}
	public void impact() {
		Game.level.tileBeingAttacked.currentHp -= attackPower;
		if(Game.level.tileBeingAttacked.currentHp < 1) {
			Game.level.highlightedBlock.deleteTopTileTilled();
			Game.level.tileBeingAttacked.deleteMe(Game.level.highlightedBlockX, Game.level.highlightedBlockY, Game.level.activeBlocks);
			
			Game.level.autoTileHighlightedBlock();
			calculateUsage();
		}
	}
	
	public void highlightItem(Block block, int x, int y) {
		
		if(Game.level.tileBeingAttacked.isTillable && 

				Game.level.activeBlocks[block.getX(Game.level.activeBlocks)][block.getY(Game.level.activeBlocks)].layers[OBJECT_LAYER].id == AIR) {

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
