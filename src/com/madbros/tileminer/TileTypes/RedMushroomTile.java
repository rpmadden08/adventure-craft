package com.madbros.tileminer.TileTypes;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.*;
import com.madbros.tileminer.Items.Item;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Margin;
import com.madbros.tileminer.Utils.Rect;

public class RedMushroomTile extends Tile {
	
	public RedMushroomTile() {
		super();
		currentSpriteId = 0;
		sprites = Sprites.spriteCollections.get(Sprites.RED_MUSHROOM_TILE);
		margin = new Margin(9, 9, 12, 11);
		id = RED_MUSHROOM_TILE;
		layer = OBJECT_LAYER;
		z = Z_OBJECT;
		isDiggable = true;
		isAutoTileable = false;
		autoTile = 0;
		isBreakable = true;
		currentHp = 1;
		maxHp = 1;
	}
	
	@Override
	public void render(int x, int y) {
		sprites[currentSpriteId].draw(x, y, z);
	}
	
	public Tile createNew() {
		return new RedMushroomTile();
	}
	
	@Override
	public void update(int x, int y) {
		if(Game.hero.isAttacking) {
			Rect wRect = new Rect(Game.hero.absRect.x+ Game.hero.attackItem.cRectFinal.x, Game.hero.absRect.y + Game.hero.attackItem.cRectFinal.y, Game.hero.attackItem.cRectFinal.w,Game.hero.attackItem.cRectFinal.h);

			if(cRect.detectCollision(wRect)) {
				deleteMe(x,y, Game.level.activeBlocks);
			}
		}
		if(Game.level.activeBlocks[x][y].layers[GRASS_LAYER].autoTile == 4 && Game.level.activeBlocks[x][y].layers[GRASS_LAYER].id == GRASS) {
			
		} else {
			deleteMe(x,y, Game.level.activeBlocks);
		}
	}
	
	public void deleteMe(int x, int y, Block[][] activeBlocks) {
		Block b = activeBlocks[x][y];
		b.layers[OBJECT_LAYER] = new NoTile();
		b.collisionTile = null;

		
		
		//FIXME this should drop something other than tall grass
		Rect collectibleRect = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
		Item item = ITEM_HASH.get(RED_MUSHROOM).createNew();
		Game.collectibleController.add(RED_MUSHROOM, Sprites.sprites.get(Sprites.RED_MUSHROOM), collectibleRect, 1, item.maxUses);
		
	}
}
