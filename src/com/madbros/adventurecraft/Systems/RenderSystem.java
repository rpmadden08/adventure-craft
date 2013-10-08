package com.madbros.adventurecraft.Systems;

import static com.madbros.adventurecraft.Constants.*;

import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.Arrays;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.madbros.adventurecraft.Block;
import com.madbros.adventurecraft.CollectibleController;
import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Inventory;
import com.madbros.adventurecraft.Level;
import com.madbros.adventurecraft.MobController;
import com.madbros.adventurecraft.GameObjects.Actor;
import com.madbros.adventurecraft.GameObjects.Collectible;
import com.madbros.adventurecraft.GameObjects.GameObject;
import com.madbros.adventurecraft.GameObjects.Hero;
import com.madbros.adventurecraft.GameObjects.Mob;
import com.madbros.adventurecraft.Slots.Slot;
import com.madbros.adventurecraft.Sprites.*;
import com.madbros.adventurecraft.TileTypes.Tile;
import com.madbros.adventurecraft.TileTypes.TreeLeafTile;
import com.madbros.adventurecraft.Utils.Helpers;
import com.madbros.adventurecraft.Utils.Rect;

public class RenderSystem {
//	private ArrayList<GameObject> alreadyRenderedObjects;
	private int startX;
	private int startY;
	private ArrayList<Block> lightTiles;

	public void setStartPosition(Level lv) {
		lightTiles = new ArrayList<Block>();
		startX = lv.activeBlocks[lv.renderRect.x][lv.renderRect.y].absRect.x + lv.offsetX;
		startY = lv.activeBlocks[lv.renderRect.x][lv.renderRect.y].absRect.y + lv.offsetY;
	}
	/******************************************* Main State Rendering *******************************************/
	public void renderWorld(Level lv) {
//		alreadyRenderedObjects = new ArrayList<GameObject>();
		for(int x = lv.renderRect.x; x < lv.renderRect.x2(); x++) {
			for(int y = lv.renderRect.y; y < lv.renderRect.y2(); y++) {
				if(x < lv.activeBlocks.length && y < lv.activeBlocks[0].length && x >= 0 && y >= 0) {					
					renderBlock(x, y, lv.activeBlocks[x][y]);
				}
			}
		}
	}
	
	public void renderBlock(int arrayX, int arrayY, Block block) {
		int x = block.absRect.x - startX;
		int y = block.absRect.y - startY;

		Tile[] renderTiles = block.getRenderTiles();
		
		for(int i = 0; i < renderTiles.length;i++) {
			if(renderTiles[i].isTreeLeafTile == true) ((TreeLeafTile)renderTiles[i]).render(x, y, i);
			//if(renderTiles[i].id == TREE_LEAF_RAIN) ((TreeLeafTile)renderTiles[i]).render(x, y, i);
			else renderTiles[i].render(x, y);
			if(renderTiles[i].isLightSource) lightTiles.add(block);
		}
		
		for(GameObject gameObject : block.objects) {
			gameObject.sprite.draw(gameObject.absRect.x - startX, gameObject.absRect.y - startY, gameObject.z);
		}
		
	
		
		if(block.isHighlighted) {
			Tile topTile = block.getTopTile();
//			System.out.println(topTile.id);
			if(topTile.currentHp < topTile.maxHp) topTile.renderHp(x, y);
			Color highlightColor = new Color(0.7f, 0.7f, 0.7f, 1.0f);
			topTile.sprites[topTile.autoTile].setColor(highlightColor);
			topTile.render(x, y);
			topTile.sprites[topTile.autoTile].setColor(Color.WHITE);
		}
		
		renderCollisionTiles(x, y, block);
		renderBlockCollisionRects(x, y, block);
		renderChunkBoundaries(arrayX, arrayY, x, y);
		if(block.isHighlightedDebug == true) {
			Color highlightColor = new Color(1, 1, 1, 0.2f);
			Sprites.pixel.setColor(highlightColor);
			Sprites.collisionDebugger.draw(x, y, Z_COLLISION_TILES, TILE_SIZE * Game.pixelModifier, TILE_SIZE * Game.pixelModifier);
			Sprites.pixel.setColor(Color.WHITE);
		}
	}
	
	public void renderHero(Hero hero, int x, int y) {
		int width = hero.absRect.w / 2;
		int height = hero.absRect.h / 2+6;
		if(hero.knockBackTime > 0) {
			Color highlightColor = new Color(0.7f, 0.7f, 0.7f, 1.0f);
			hero.sprite.setColor(highlightColor);
			hero.sprite.draw(x, y, Z_CHARACTER);
			
			
			Sprites.pixel.setColor(Color.RED);
			Sprites.pixel.draw(x+width,y+height,Z_CHARACTER);
			
			hero.sprite.setColor(Color.WHITE);
		} else {
			
		
			hero.sprite.draw(x, y, Z_CHARACTER);
			
			renderCollisionRects(hero, x, y);
			
		}
		
		if(hero.isAttacking && hero.startWeaponAnimation == true) {
			//Item item = Game.inventory.heldItem;
			//item.sprite.setColor(Color.WHITE);
			//System.out.println(hero.attackItem.sprite.getOriginX()+"/"+hero.attackItem.sprite.getOriginY());
			hero.attackItem.sprite.setOrigin(hero.attackItem.originX, hero.attackItem.originY);
			//hero.attackItem.sprite.
			Sprites.pixel.setColor(Color.RED);
			Sprites.pixel.draw(hero.attackItem.originX, hero.attackItem.originY,Z_CHARACTER);
			
			hero.attackItem.sprite.rotate(hero.weaponR);
			hero.attackItem.sprite.draw(x+hero.weaponX+ hero.attackItem.weaponOffsetX, y+hero.weaponY+hero.attackItem.weaponOffsetY, Z_CHARACTER);
			hero.attackItem.sprite.rotate(-hero.weaponR);
			
			Sprites.pixel.setColor(Color.RED);
			Sprites.pixel.draw(x + hero.attackItem.originX+hero.weaponX+ hero.attackItem.weaponOffsetX, y + hero.attackItem.originY+hero.weaponY+hero.attackItem.weaponOffsetY,Z_CHARACTER);
			
			//Collision rectangles
		
//			Rect r = new Rect(x+hero.attackItem.cRectFinal.x,y+hero.attackItem.cRectFinal.y, hero.attackItem.cRectFinal.w,hero.attackItem.cRectFinal.h);
//			
//			Color highlightColor = new Color(0, 0, 1f, 0.6f);
//			Sprites.pixel.setColor(highlightColor);
//			
//			Sprites.pixel.draw(r, Z_COLLISION_RECTS);
//			Sprites.pixel.setColor(Color.WHITE);
			
		}
	}

	
	public void renderMobs(MobController mobController) {
		for(Mob mob : mobController.mobs) {
			int x = mob.absRect.x - startX;
			int y = mob.absRect.y - startY;
			int width = mob.absRect.w / 2;
			int height = mob.absRect.h / 2;

			mob.sprite.draw(x, y, Z_CHARACTER);
			renderCollisionRects(mob, x, y);
			Sprites.pixel.setColor(Color.RED);
			Sprites.pixel.draw(x+width,y+height,Z_CHARACTER);
		}
	}
	
	public void renderCollectibles(CollectibleController collectibleController) {
		for(Collectible collectible : collectibleController.collectibles) {
			int x = collectible.absRect.x - startX;
			int y = collectible.absRect.y - startY;
			collectible.sprite.draw(x, y, Z_CHARACTER);
			//renderCollisionRects(collectible, x, y);
		}
	}
	
	public void renderHud(Inventory inv) {
		for(int i=0;i < inv.invBar.length; i++) {			
			inv.invBar[i].render();
			if(i == inv.itemSelected) inv.selectSprite.draw(inv.invBar[i].slotRect.x - 2, inv.invBar[i].slotRect.y - 2,
									  Z_INV_SELECT, inv.invBar[i].slotRect.w + 3, inv.invBar[i].slotRect.h + 3);
		}
	}
	
	public void renderText(Inventory inv, SpriteBatch batch) {
		Slot[][] slots = {inv.invBar};
		
		for(int i = 0; i < slots.length; i++) {
			for(int j = 0; j < slots[i].length; j++) {
				slots[i][j].item.renderFont(slots[i][j].slotRect.x2()-INV_SLOT_SIZE/2, slots[i][j].slotRect.y2()-INV_SLOT_SIZE/2, batch);
			}
		}
	}
	
	public void renderLight(Hero hero) {
		float lightSize = MathUtils.random(100, 101)+150; //150; //lightOscillate? (4.75f + 0.25f * (float)Math.sin(zAngle) + .2f*MathUtils.random()) + 100:5.0f + 100;
		float x = hero.absRect.midX() - startX - lightSize/2;
		float y = hero.absRect.midY() - startY - lightSize/2;
		StaticSprite lightSprite = Sprites.sprites.get(Sprites.LIGHT);
		lightSprite.draw(x, y, 0.4f, lightSize, lightSize);
		
		for(Block light : lightTiles) {
			x = light.absRect.midX() - startX - lightSize/2;
			y = light.absRect.midY() - startY - lightSize/2;
			lightSprite.draw(x, y, 0.4f, lightSize, lightSize);
		}
	}
	
	/******************************************* Inventory State Rendering *******************************************/
	public void renderInventory(Hero hero, Inventory inv) {
		inv.menuSprites[0].draw(INV_BACKDROP_RECT.x, INV_BACKDROP_RECT.y, Z_INV_BACKDROP);	//top left
		inv.menuSprites[6].draw(INV_BACKDROP_RECT.x2()-INV_MENU_TILE_SIZE, INV_BACKDROP_RECT.y, Z_INV_BACKDROP); //top right
		inv.menuSprites[2].draw(INV_BACKDROP_RECT.x, INV_BACKDROP_RECT.y2()-INV_MENU_TILE_SIZE, Z_INV_BACKDROP);	//bottom left
		inv.menuSprites[8].draw(INV_BACKDROP_RECT.x2()-INV_MENU_TILE_SIZE, INV_BACKDROP_RECT.y2()-INV_MENU_TILE_SIZE, Z_INV_BACKDROP);	//bottom right
		
		inv.menuSprites[3].draw(INV_BACKDROP_RECT.x+INV_MENU_TILE_SIZE, INV_BACKDROP_RECT.y, Z_INV_BACKDROP, INV_BACKDROP_RECT.w-INV_MENU_TILE_SIZE*2, INV_MENU_TILE_SIZE);	//top
		inv.menuSprites[5].draw(INV_BACKDROP_RECT.x+INV_MENU_TILE_SIZE, INV_BACKDROP_RECT.y2()-INV_MENU_TILE_SIZE, Z_INV_BACKDROP, INV_BACKDROP_RECT.w-INV_MENU_TILE_SIZE*2, INV_MENU_TILE_SIZE);	//bottom
		inv.menuSprites[1].draw(INV_BACKDROP_RECT.x, INV_BACKDROP_RECT.y+INV_MENU_TILE_SIZE, Z_INV_BACKDROP, INV_MENU_TILE_SIZE, INV_BACKDROP_RECT.h-INV_MENU_TILE_SIZE*2);	//left
		inv.menuSprites[7].draw(INV_BACKDROP_RECT.x2()-INV_MENU_TILE_SIZE, INV_BACKDROP_RECT.y+INV_MENU_TILE_SIZE, Z_INV_BACKDROP, INV_MENU_TILE_SIZE, INV_BACKDROP_RECT.h-INV_MENU_TILE_SIZE*2);	//right
		
		inv.menuSprites[4].draw(INV_BACKDROP_RECT.x+INV_MENU_TILE_SIZE, INV_BACKDROP_RECT.y+INV_MENU_TILE_SIZE, Z_INV_BACKDROP, INV_BACKDROP_RECT.w-INV_MENU_TILE_SIZE*2, INV_BACKDROP_RECT.h-INV_MENU_TILE_SIZE*2); //middle
		
		Slot[][] slots = {inv.invBag, inv.invCrafting, inv.invCrafted, inv.invClothing};
		for(int i = 0; i < slots.length; i++) {
			for(int j = 0; j < slots[i].length; j++) {
				slots[i][j].render();
			}
		}

		hero.sprite.draw(INV_CHAR_RECT.x, INV_CHAR_RECT.y, Z_INV_CHARACTER, 3);
		
		inv.heldItem.render(Helpers.getX(), Helpers.getY());
	}
	
	public void renderInventoryText(Inventory inv, SpriteBatch batch) {
		Slot[][] slots = new Slot[][]{inv.invBag, inv.invCrafting, inv.invCrafted};

		for(int i = 0; i < slots.length; i++) {
			for(int j = 0; j < slots[i].length; j++) {
				slots[i][j].item.renderFont(slots[i][j].slotRect.x2()-INV_SLOT_SIZE/2, slots[i][j].slotRect.y2()-INV_SLOT_SIZE/2, batch);
			}
		}
		
		if(inv.heldItem.id != EMPTY) inv.heldItem.renderFont(Helpers.getX(), Helpers.getY(), batch);
	}
	
	/******************************************* Debug Rendering *******************************************/
	public void renderChunkBoundaries(int x, int y, int sX, int sY) {
		if(Game.debugMenu.chunkBoundariesAreOn) {
			if(x % CHUNK_SIZE == 0) Sprites.pixel.draw(sX, sY, Z_BOUNDARIES, 1, TILE_SIZE);
			if(y % CHUNK_SIZE == 0) Sprites.pixel.draw(sX, sY, Z_BOUNDARIES, TILE_SIZE, 1);
		}
	}
	
	public void renderCollisionRects(Actor h, int x, int y) {
		if(Game.debugMenu.collisionRectsAreOn)  {
			Sprites.pixel.setColor(Color.RED);
			Sprites.pixel.draw(new Rect(new Rect(x, y, CHARACTER_SIZE, CHARACTER_SIZE), h.margin), Z_CHARACTER + 0.01f);
			
			Sprites.pixel.setColor(Color.YELLOW);
			if(h.collisionDetectionBlocks[0] != null) {
				for(int i = 0; i < h.collisionDetectionBlocks.length; i++) {
					if(h.collisionDetectionBlocks[i].sRect != null && h.collisionDetectionBlocks[i].isCollidable())
						Sprites.pixel.draw(h.collisionDetectionBlocks[i].sRect, Z_COLLISION_RECTS + 0.01f);
				}
			}
			Sprites.pixel.setColor(Color.WHITE);
		}
	}
	
	public void renderBlockCollisionRects(int x, int y, Block block) {
		if(Game.debugMenu.collisionRectsAreOn && block.isCollidable()) {
			Rect r = new Rect(x, y);
			r = new Rect(r, block.collisionTile.margin);
			
			Color highlightColor = new Color(0, 0, 1f, 0.6f);
			Sprites.pixel.setColor(highlightColor);
			
			Sprites.pixel.draw(r, Z_COLLISION_RECTS);
			Sprites.pixel.setColor(Color.WHITE);
		}
	}
	
	public void renderCollisionTiles(int x, int y, Block block) {
		if(Game.debugMenu.collisionTilesAreOn && Arrays.asList(Game.hero.collisionDetectionBlocks).contains(block)) {
			Color highlightColor = new Color(1, 1, 1, 0.2f);
			Sprites.pixel.setColor(highlightColor);
			Sprites.collisionDebugger.draw(x, y, Z_COLLISION_TILES, TILE_SIZE * Game.pixelModifier, TILE_SIZE * Game.pixelModifier);
			Sprites.pixel.setColor(Color.WHITE);
		}
	}
}
