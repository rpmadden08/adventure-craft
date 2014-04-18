package com.madbros.adventurecraft.TileTypes;

import static com.madbros.adventurecraft.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Items.Item;
import com.madbros.adventurecraft.Items.StairsUp;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Utils.Margin;
import com.madbros.adventurecraft.Utils.Rect;

public class StairsDownTile extends CollisionTile {
	public boolean hasReset = false;
	public StairsDownTile() {
		super();
		currentSpriteId = 0;
		sprites = Sprites.spriteCollections.get(Sprites.STAIRS_DOWN_TILE);
		margin = new Margin(9, 9, 9, 9);
		id = STAIRS_DOWN_TILE;
		layer = OBJECT_LAYER;
		z = Z_OBJECT;
		isDiggable = false;
		isAutoTileable = false;
		autoTile = 0;
		isBreakable = true;
		currentHp = 1;
		maxHp = 1;
		//isCollidable = false;
		
	}
	
	@Override
	public void render(int x, int y) {
		sprites[currentSpriteId].draw(x, y, z);
	}
	
	public Tile createNew() {
		return new StairsDownTile();
	}
	
	@Override
	public void update(int x, int y) {
		Game.level.activeBlocks[x][y].collisionTile = null;
		Rect finalCRect = new Rect(cRect, margin);
		if(finalCRect.detectCollision(new Rect(Game.hero.absRect, Game.hero.margin))) {
			if(hasReset == true) {
				int x2 = Game.level.activeBlocks[x][y].getAbsX();
				int y2 = Game.level.activeBlocks[x][y].getAbsY();
				Game.switchLevel();
				Game.level.teleportHero(x2, y2-1);
				x = Game.level.getXFromAbs(x2);
				y = Game.level.getYFromAbs(y2);
				if(Game.level.activeBlocks[x][y].layers[OBJECT_LAYER].id != STAIRS_UP_BOTTOM_TILE) {
					Game.level.activeBlocks[x][y].layers[OBJECT_LAYER].deleteThisTile(x, y, Game.level.activeBlocks);
					Game.level.activeBlocks[x][y].layers[OBJECT_LAYER] = new StairsUpBottomTile();
					Game.level.activeBlocks[x][y-1].layers[ABOVE_LAYER_1] = new StairsUpTopTile();
					Game.level.activeBlocks[x][y].layers[OBJECT_LAYER].cRect.x = Game.level.activeBlocks[x][y].getAbsX()* TILE_SIZE;
					Game.level.activeBlocks[x][y].layers[OBJECT_LAYER].cRect.y = Game.level.activeBlocks[x][y].getAbsY()* TILE_SIZE;
					
				}
				Game.level.autoTileBlock(x, y);
				Game.level.autoTileBlock(x, y+1);
				Game.level.autoTileBlock(x, y+2);
				Game.level.autoTileBlock(x-1, y);
				Game.level.autoTileBlock(x-1, y-1);
				Game.level.autoTileBlock(x-1, y-2);
				Game.level.autoTileBlock(x-2, y);
				Game.level.autoTileBlock(x-2, y-1);
				Game.level.autoTileBlock(x-2, y-2);
				//Game.level.autoTileHighlightedBlock();
			}
			
		} else {
			hasReset = true;
		}
	}
	
	public void deleteMe(int x, int y, Block[][] activeBlocks) {
		Block b = activeBlocks[x][y];
		b.layers[OBJECT_LAYER] = new NoTile();
		b.collisionTile = null;
		
		
		//FIXME this needs to randomly drop basic goodies:)
		Rect collectibleRect = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
		Item item = ITEM_HASH.get(STAIRS_DOWN).createNew();
		Game.collectibleController.add(STAIRS_DOWN, Sprites.sprites.get(Sprites.STAIRS_DOWN), collectibleRect, 1, item.maxUses);
		
	}
}
