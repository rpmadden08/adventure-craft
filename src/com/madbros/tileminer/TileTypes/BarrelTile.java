package com.madbros.tileminer.TileTypes;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.*;
import com.madbros.tileminer.Items.Item;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Helpers;
import com.madbros.tileminer.Utils.Margin;
import com.madbros.tileminer.Utils.Rect;

public class BarrelTile extends CollisionTile {
	
	public BarrelTile() {
		super();
		currentSpriteId = 0;
		sprites = Sprites.spriteCollections.get(Sprites.BARREL);
		margin = new Margin(2, 2, 0, 1);
		id = BARREL;
		layer = OBJECT_LAYER;
		z = Z_OBJECT;
		isDiggable = false;
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
		return new BarrelTile();
	}
	
	@Override
	public void update(int x, int y) {
		if(Game.hero.isAttacking) {
			Rect wRect = new Rect(Game.hero.absRect.x+ Game.hero.attackItem.cRectFinal.x, Game.hero.absRect.y + Game.hero.attackItem.cRectFinal.y, Game.hero.attackItem.cRectFinal.w,Game.hero.attackItem.cRectFinal.h);
			
			if(cRect.detectCollision(wRect)) {
				deleteMe(x,y, Game.level.activeBlocks);
			}
		}
	}
	
	public void deleteMe(int x, int y, Block[][] activeBlocks) {
		Block b = activeBlocks[x][y];
		b.layers[OBJECT_LAYER] = new NoTile();
		b.layers[OBJECT_LAYER].setCollisionRect(b.absRect);
		b.collisionTile = null;
		b = activeBlocks[x][y-1];
		b.layers[ABOVE_LAYER_1] = new NoTile();
		Game.soundController.create("sounds/barrelBreak.wav", 1f);
		
		//FIXME this needs to randomly drop basic goodies:)
		Item items[] = Helpers.getRandomLoot(
					  new int[]{TORCH, HEALTH_POTION, COAL_ITEM, STICK, STONE, WHEAT_SPROUT}, 
					  new int[]{2,3,1,1,2,2},
					  new int[]{1,1,1,1,1,1},
					  new int[]{5,3,5,4,3,3}, 
					  1, 
					  3);
		for(int a=0; a<items.length;a++) {
			Rect collectibleRect = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
			Item item = ITEM_HASH.get(items[a].id).createNew();
			Game.collectibleController.add(items[a].id, items[a].sprite, collectibleRect, items[a].stackSize, item.maxUses);
		}
		//Helpers.getRandomLoot(int, probabilities, stackSizeMin, stackSizeMax, min, max)
//		Rect collectibleRect = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
//		Game.collectibleController.add(POTATOES, Sprites.sprites.get(Sprites.POTATOES), collectibleRect, 1);
//		
	}
}
