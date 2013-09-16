package com.madbros.adventurecraft.Systems;

import static com.madbros.adventurecraft.Constants.*;

//import java.util.ArrayList;
import java.util.Arrays;

import org.newdawn.slick.Color;

import com.madbros.adventurecraft.Block;
import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Inventory;
import com.madbros.adventurecraft.Level;
import com.madbros.adventurecraft.GameObjects.Actor;
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
	
	/******************************************* Main State Rendering *******************************************/
	public void renderWorld(Level lv) {
//		alreadyRenderedObjects = new ArrayList<GameObject>();
		startX = lv.activeBlocks[lv.renderRect.x][lv.renderRect.y].absRect.x + lv.offsetX;
		startY = lv.activeBlocks[lv.renderRect.x][lv.renderRect.y].absRect.y + lv.offsetY;
		
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
			if(renderTiles[i].id == TREE_LEAF) ((TreeLeafTile)renderTiles[i]).render(x, y, i);
			else renderTiles[i].render(x, y);
		}
		
		for(GameObject gameObject : block.objects) {
			gameObject.sprite.draw(gameObject.absRect.x - startX, gameObject.absRect.y - startY, gameObject.z);
		}
		
		if(block.isHighlighted) {
			Tile topTile = block.getTopTile();
			if(topTile.currentHp < topTile.maxHp) topTile.renderHp(x, y);
			Color highlightColor = new Color(0.8f, 0.8f, 0.8f, 1.0f);
			highlightColor.bind();
			Helpers.drawRect(new Rect(x, y, TILE_SIZE, TILE_SIZE), Z_TILE_HIGHLIGHT);
			Color.white.bind();
		}
		
		renderCollisionTiles(x, y, block);
		renderBlockCollisionRects(x, y, block);
		renderChunkBoundaries(arrayX, arrayY, x, y);
	}
	
	public void renderHero(Hero hero, int x, int y) {
		hero.sprite.draw(x, y, Z_CHARACTER);
		renderCollisionRects(hero, x, y);
	}
	
	public void renderMob(Mob mob) {
		int x = mob.absRect.x - startX;
		int y = mob.absRect.y - startY;
		mob.sprite.draw(x, y, Z_CHARACTER);
		renderCollisionRects(mob, x, y);
	}
	
	public void renderHud(Inventory inv) {
		for(int i=0;i < inv.invBar.length; i++) {			
			inv.invBar[i].render();
			if(i == inv.itemSelected) inv.selectSprite.draw(inv.invBar[i].slotRect.x - 2, inv.invBar[i].slotRect.y - 2,
									  Z_INV_SELECT, inv.invBar[i].slotRect.w + 3, inv.invBar[i].slotRect.h + 3);
		}
	}
	
	public void renderText(Inventory inv) {
		Slot[][] slots = {inv.invBar};
		
		for(int i = 0; i < slots.length; i++) {
			for(int j = 0; j < slots[i].length; j++) {
				slots[i][j].item.renderFont(slots[i][j].slotRect.x2()-INV_SLOT_SIZE/2, slots[i][j].slotRect.y2()-INV_SLOT_SIZE/2);
			}
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
	
	public void renderInventoryText(Inventory inv) {
		Slot[][] slots = new Slot[][]{inv.invBag, inv.invCrafting, inv.invCrafted};

		for(int i = 0; i < slots.length; i++) {
			for(int j = 0; j < slots[i].length; j++) {
				slots[i][j].item.renderFont(slots[i][j].slotRect.x2()-INV_SLOT_SIZE/2, slots[i][j].slotRect.y2()-INV_SLOT_SIZE/2);
			}
		}
		
		if(inv.heldItem.id != EMPTY) inv.heldItem.renderFont(Helpers.getX(), Helpers.getY());
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
			Color.red.bind();
			Sprites.pixel.draw(new Rect(new Rect(x, y, CHARACTER_SIZE, CHARACTER_SIZE), h.margin), Z_CHARACTER + 0.01f);
			
			Color.yellow.bind();
			if(h.collisionDetectionBlocks[0] != null) {
				for(int i = 0; i < h.collisionDetectionBlocks.length; i++) {
					if(h.collisionDetectionBlocks[i].sRect != null && h.collisionDetectionBlocks[i].isCollidable())
						Sprites.pixel.draw(h.collisionDetectionBlocks[i].sRect, Z_COLLISION_RECTS + 0.01f);
				}
			}
			Color.white.bind();
		}
	}
	
	public void renderBlockCollisionRects(int x, int y, Block block) {
		if(Game.debugMenu.collisionRectsAreOn && block.isCollidable()) {
			Rect r = new Rect(x, y);
			r = new Rect(r, block.collisionTile.margin);
			
			Color highlightColor = new Color(0, 0, 1f, 0.6f);
			highlightColor.bind();
			
			Sprites.pixel.draw(r, Z_COLLISION_RECTS);

			Color.white.bind();
		}
	}
	
	public void renderCollisionTiles(int x, int y, Block block) {
		if(Game.debugMenu.collisionTilesAreOn && Arrays.asList(Game.hero.collisionDetectionBlocks).contains(block)) {
			Color highlightColor = new Color(1, 1, 1, 0.2f);
			highlightColor.bind();
			Sprites.collisionDebugger.draw(x, y, Z_COLLISION_TILES, TILE_SIZE * Game.pixelModifier, TILE_SIZE * Game.pixelModifier);

			Color.white.bind();
		}
	}
}
