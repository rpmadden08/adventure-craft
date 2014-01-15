package com.madbros.adventurecraft.Systems;

import static com.madbros.adventurecraft.Constants.*;

import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.Arrays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.madbros.adventurecraft.Block;
import com.madbros.adventurecraft.CollectibleController;
import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Inventory;
import com.madbros.adventurecraft.Level;
import com.madbros.adventurecraft.MobController;
import com.madbros.adventurecraft.NotificationController;
import com.madbros.adventurecraft.ParticleEffect;
import com.madbros.adventurecraft.ParticleEffectController;
import com.madbros.adventurecraft.GameObjects.Actor;
import com.madbros.adventurecraft.GameObjects.Collectible;
import com.madbros.adventurecraft.GameObjects.GameObject;
import com.madbros.adventurecraft.GameObjects.Hero;
import com.madbros.adventurecraft.GameObjects.Mob;
import com.madbros.adventurecraft.GameObjects.Notification;
import com.madbros.adventurecraft.Items.Item;
import com.madbros.adventurecraft.Slots.Slot;
import com.madbros.adventurecraft.Sprites.*;
import com.madbros.adventurecraft.TileTypes.Cauldron;
import com.madbros.adventurecraft.TileTypes.Furnace;
import com.madbros.adventurecraft.TileTypes.FurnaceTop;
import com.madbros.adventurecraft.TileTypes.LightTile;
import com.madbros.adventurecraft.TileTypes.Tile;
import com.madbros.adventurecraft.TileTypes.TreeLeafTile;
import com.madbros.adventurecraft.Utils.Helpers;
import com.madbros.adventurecraft.Utils.Rect;

public class RenderSystem {
//	private ArrayList<GameObject> alreadyRenderedObjects;
	public int startX;
	public int startY;
	private ArrayList<Block> lightTiles;
	private int frameCutter = 0;
	private float lightSizeRandom = 0;

	public void setStartPosition(Level lv) {
		lightTiles = new ArrayList<Block>();
		startX = lv.activeBlocks[lv.renderRect.x][lv.renderRect.y].absRect.x + lv.offsetX;
		startY = lv.activeBlocks[lv.renderRect.x][lv.renderRect.y].absRect.y + lv.offsetY;
	}
	/******************************************* Main State Rendering *******************************************/
	public void renderWorld(Level lv) {
//		alreadyRenderedObjects = new ArrayList<GameObject>();
		int i = 0; int j = 0;
		for(int x = lv.renderRect.x; x < lv.renderRect.x2(); x++) {
			for(int y = lv.renderRect.y; y < lv.renderRect.y2(); y++) {
//				if(x < lv.activeBlocks.length && y < lv.activeBlocks[0].length && x >= 0 && y >= 0) {					
					renderBlock(x, y, lv.activeBlocks[x][y], lv, i, j, true);
//				}
				j++;
			}
			i++; j = 0;
		}
	}
	public void renderWorldAbove(Level lv) {
//		alreadyRenderedObjects = new ArrayList<GameObject>();
		int i = 0; int j = 0;
		for(int x = lv.renderRect.x; x < lv.renderRect.x2(); x++) {
			for(int y = lv.renderRect.y; y < lv.renderRect.y2(); y++) {
//				if(x < lv.activeBlocks.length && y < lv.activeBlocks[0].length && x >= 0 && y >= 0) {					
					renderBlock(x, y, lv.activeBlocks[x][y], lv, i, j, false);
//				}
				j++;
			}
			i++; j = 0;
		}
	}
	
	public void renderBlock(int arrayX, int arrayY, Block block, Level lv, int i2, int j2, Boolean isAbove) {
		int x = i2 * TILE_SIZE - lv.offsetX; //block.absRect.x - startX;
		int y = j2 * TILE_SIZE - lv.offsetY; //block.absRect.y - startY;
		Tile[] renderTiles;
		if(isAbove == true) {
			renderTiles = block.getRenderTilesBottom();
		} else {
			renderTiles = block.getRenderTilesTop();
			
		}
		for(int i = 0; i < renderTiles.length;i++) {
			if(renderTiles[i].isVisible) {
				if(renderTiles[i].isTreeLeafTile == true) ((TreeLeafTile)renderTiles[i]).render(x, y, i);
				else renderTiles[i].render(x, y);
				if(renderTiles[i].isLightSource) lightTiles.add(block);
			}
		}
		
		for(GameObject gameObject : block.objects) {
			gameObject.sprite.draw(gameObject.absRect.x - startX, gameObject.absRect.y - startY, gameObject.z);
		}
		
	
		
		if(block.isHighlighted) {
			Item item = ITEM_HASH.get(Game.inventory.invBar[Game.inventory.itemSelected].item.id).createNew();
			item.highlightItem(x, y);
			
			Tile topTile = block.getTopTile();
//			Color highlightColor = new Color(0.7f, 0.7f, 0.7f, 1.0f);
//			
//			topTile.sprites[topTile.autoTile].setColor(highlightColor);
//			topTile.render(x, y);
//			topTile.sprites[topTile.autoTile].setColor(Color.WHITE);
//			
			if(topTile.currentHp < topTile.maxHp) {
				renderTileHealth(topTile, x, y);
			}
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
	
	public void renderParticle(ParticleEffectController pController) {
		for(ParticleEffect p : pController.particleEffects) {
			p.flipY();
			p.setPosition(p.x - startX, p.y - startY);
			p.update(Gdx.graphics.getRawDeltaTime());
			p.draw(Game.batch);
			p.flipY();
			
		}
	}
	
	public void renderHero(Hero hero, int x, int y) {
		int width = hero.absRect.w / 2;
		int height = hero.absRect.h / 2+6;
		
		
		if(hero.isDead == false) {
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
		
	}
	
	public void renderTileHealth(Tile tile, int x, int y) {
		double difference = (tile.maxHp - tile.currentHp);
		double percentage = (tile.maxHp-difference) / tile.maxHp;
		double hPCalc = percentage * 30; 
		int hP = (int) hPCalc;
		
		//The Red/Blue/Green Part
		Sprites.pixel.setColor(Color.RED);
		Sprites.pixel.draw(x+1,y+1,Z_CHARACTER,hP,4);
		
		//Red Highlight top
		Sprites.pixel.setColor(1f, 1f, 1f,0.4f);
		Sprites.pixel.draw(x+2,y+1,Z_CHARACTER,hP,1);
		
		//Red Highlight bottom
		Sprites.pixel.setColor(0f, 0f, 0f,0.3f);
		Sprites.pixel.draw(x+2,y+4,Z_CHARACTER,hP,1);
	
		//Black Edge
		Sprites.pixel.setColor(Color.BLACK);
		Sprites.pixel.draw(x+1+hP,y+1,Z_CHARACTER,30-hP,4);
		
		//Border left
		Sprites.healthBarMon.draw(x,y,Z_CHARACTER,2,6);

		
		//Border Top
		Sprites.pixel.setColor(0.886f, 0.914f, 0.98f,1f);
		Sprites.pixel.draw(x+2, y, Z_CHARACTER, 28, 1);
		
		//Border Bottom
		Sprites.pixel.draw(x+2, y+5, Z_CHARACTER, 28, 1);
		
		//Border Right
		Sprites.healthBarMon.rotate(180);
		Sprites.healthBarMon.draw(x+2+28,y,Z_CHARACTER,2,6);
		Sprites.healthBarMon.rotate(180);
		
		//Reset
		Sprites.pixel.setColor(Color.WHITE);
}
	
	public void renderHealth(Hero hero) {
		
		//The Red/Blue/Green Part
		Sprites.pixel.setColor(Color.toFloatBits(239, 29, 29, 255));
		Sprites.pixel.draw(4,4,Z_CHARACTER,hero.hP,10);
		
		Sprites.pixel.setColor(Color.toFloatBits(28, 57, 234, 255));
		Sprites.pixel.draw(4,20,Z_CHARACTER,hero.mP,10);
		
		Sprites.pixel.setColor(Color.toFloatBits(39, 194, 32, 255));
		Sprites.pixel.draw(4,36,Z_CHARACTER,(int)hero.eP,10);
		
		//Red Highlight top
		Sprites.pixel.setColor(Color.toFloatBits(239, 99, 99, 255));
		Sprites.pixel.draw(4,4,Z_CHARACTER,hero.hP,2);
		Sprites.pixel.setColor(Color.toFloatBits(68, 97, 234, 255));
		Sprites.pixel.draw(4,20,Z_CHARACTER,hero.mP,2);
		Sprites.pixel.setColor(1f, 1f, 1f,0.25f);
		Sprites.pixel.draw(4,36,Z_CHARACTER,(int)hero.eP,2);
		
		//Red Highlight bottom
		Sprites.pixel.setColor(0f, 0f, 0f,0.3f);
		Sprites.pixel.draw(4,12,Z_CHARACTER,hero.hP,2);
		Sprites.pixel.draw(4,28,Z_CHARACTER,hero.mP,2);
		Sprites.pixel.draw(4,44,Z_CHARACTER,(int)hero.eP,2);
	
		//Black Edge
		Sprites.pixel.setColor(Color.BLACK);
		Sprites.pixel.draw(4+hero.hP,4,Z_CHARACTER,hero.maxHP-hero.hP,10);
		Sprites.pixel.draw(4+hero.mP,20,Z_CHARACTER,hero.maxMP-hero.mP,10);
		Sprites.pixel.draw(4+(int)hero.eP,36,Z_CHARACTER,(int)hero.maxEP-(int)hero.eP,10);
		
		//Border left
		Sprites.healthBar.draw(2,2,Z_CHARACTER,4,14);
		Sprites.healthBar.draw(2,18,Z_CHARACTER,4,14);
		Sprites.healthBar.draw(2,34,Z_CHARACTER,4,14);
		
		//Border Top
		Sprites.pixel.setColor(0.886f, 0.914f, 0.98f,1f);
		Sprites.pixel.draw(6, 2, Z_CHARACTER, hero.maxHP-4, 2);
		Sprites.pixel.draw(6, 18, Z_CHARACTER, hero.maxMP-4, 2);
		Sprites.pixel.draw(6, 34, Z_CHARACTER, (int)hero.maxEP-4, 2);
		
		//Border Bottom
		Sprites.pixel.draw(6, 14, Z_CHARACTER, hero.maxHP-4, 2);
		Sprites.pixel.draw(6, 30, Z_CHARACTER, hero.maxMP-4, 2);
		Sprites.pixel.draw(6, 46, Z_CHARACTER, (int)hero.maxEP-4, 2);
		
		//Border Right
		Sprites.healthBar.rotate(180);
		Sprites.healthBar.draw(2+hero.maxHP,2,Z_CHARACTER,4,14);
		Sprites.healthBar.draw(2+hero.maxMP,18,Z_CHARACTER,4,14);
		Sprites.healthBar.draw(2+(int)hero.maxEP,34,Z_CHARACTER,4,14);
		Sprites.healthBar.rotate(180);
		
		//Reset
		Sprites.pixel.setColor(Color.WHITE);
		
//		Sprites.pixel.draw(4, 3, Z_CHARACTER, hero.maxHP+1, 1);
//		Sprites.pixel.draw(3, 4, Z_CHARACTER, hero.maxHP+2, 1);
//		Sprites.pixel.draw(2, 5, Z_CHARACTER, hero.maxHP+3, 8);
//		Sprites.pixel.draw(3, 13, Z_CHARACTER, hero.maxHP+2, 1);
//		Sprites.pixel.draw(4, 14, Z_CHARACTER, hero.maxHP+1, 1);
//		Sprites.pixel.draw(5, 15, Z_CHARACTER, hero.maxHP, 1);
		
		Sprites.pixel.setColor(Color.WHITE);
	}
	
	public void renderMobHealth(Mob mob) {
				int x = mob.absRect.x - startX;
				int y = mob.absRect.y - startY - 6;
				double difference = (mob.maxHP - mob.hP);
				double percentage = (mob.maxHP-difference) / mob.maxHP;
				double hPCalc = percentage * 29;
				int hP = (int) hPCalc;
				
				//The Red/Blue/Green Part
				Sprites.pixel.setColor(Color.RED);
				Sprites.pixel.draw(x+1,y+1,Z_CHARACTER,hP,4);
				
				//Red Highlight top
				Sprites.pixel.setColor(1f, 1f, 1f,0.4f);
				Sprites.pixel.draw(x+2,y+1,Z_CHARACTER,hP,1);
				
				//Red Highlight bottom
				Sprites.pixel.setColor(0f, 0f, 0f,0.3f);
				Sprites.pixel.draw(x+2,y+4,Z_CHARACTER,hP,1);
			
				//Black Edge
				Sprites.pixel.setColor(Color.BLACK);
				Sprites.pixel.draw(x+1+hP,y+1,Z_CHARACTER,29-hP,4);
				
				//Border left
				Sprites.healthBarMon.draw(x,y,Z_CHARACTER,2,6);

				
				//Border Top
				Sprites.pixel.setColor(0.886f, 0.914f, 0.98f,1f);
				Sprites.pixel.draw(x+2, y, Z_CHARACTER, 27, 1);
				
				//Border Bottom
				Sprites.pixel.draw(x+2, y+5, Z_CHARACTER, 27, 1);
				
				//Border Right
				Sprites.healthBarMon.rotate(180);
				Sprites.healthBarMon.draw(x+2+27,y,Z_CHARACTER,2,6);
				Sprites.healthBarMon.rotate(180);
				
				//Reset
				Sprites.pixel.setColor(Color.WHITE);
	}
	
	public void renderItemHealth(Item item, int x, int y) {
//		int x = mob.absRect.x - startX;
//		int y = mob.absRect.y - startY - 6;
		x = x +4;
		y = y +26;
		double difference = (item.maxUses - item.uses);
		double percentage = (item.maxUses-difference) / item.maxUses;
		double hPCalc = percentage * 29;
		int hP = (int) hPCalc;
		
		//The Red/Blue/Green Part
		Sprites.pixel.setColor(Color.RED);
		Sprites.pixel.draw(x+1,y+1,Z_CHARACTER,hP,4);
		
		//Red Highlight top
		Sprites.pixel.setColor(1f, 1f, 1f,0.4f);
		Sprites.pixel.draw(x+2,y+1,Z_CHARACTER,hP,1);
		
		//Red Highlight bottom
		Sprites.pixel.setColor(0f, 0f, 0f,0.3f);
		Sprites.pixel.draw(x+2,y+4,Z_CHARACTER,hP,1);
	
		//Black Edge
		Sprites.pixel.setColor(Color.BLACK);
		Sprites.pixel.draw(x+1+hP,y+1,Z_CHARACTER,29-hP,4);
		
		//Border left
		Sprites.healthBarMon.draw(x,y,Z_CHARACTER,2,6);

		
		//Border Top
		Sprites.pixel.setColor(0.886f, 0.914f, 0.98f,1f);
		Sprites.pixel.draw(x+2, y, Z_CHARACTER, 28, 1);
		
		//Border Bottom
		Sprites.pixel.draw(x+2, y+5, Z_CHARACTER, 28, 1);
		
		//Border Right
		Sprites.healthBarMon.rotate(180);
		Sprites.healthBarMon.draw(x+2+28,y,Z_CHARACTER,2,6);
		Sprites.healthBarMon.rotate(180);
		
		//Reset
		Sprites.pixel.setColor(Color.WHITE);
}
	public void renderMobs(MobController mobController) {
		for(Mob mob : mobController.mobs) {
			int x = mob.absRect.x - startX;
			int y = mob.absRect.y - startY;
			//int width = mob.absRect.w / 2;
			//int height = mob.absRect.h / 2;
			mob.sprite.draw(x, y, Z_CHARACTER);
			renderCollisionRects(mob, x, y);
			renderMobHealth(mob);
		}
	}
	
	public void renderCollectibles(CollectibleController collectibleController) {
		for(Collectible collectible : collectibleController.collectibles) {
			int x = collectible.absRect.x - startX;
			int y = collectible.absRect.y - startY;
			collectible.sprite.draw(x, y, Z_CHARACTER);
			//System.out.println(collectible.absRect.x-startX);
			//renderCollisionRects(collectible, x, y);
		}
	}
	
	public void renderNotifications(NotificationController notificationController, SpriteBatch batch) {
		for(Notification notification : notificationController.notifications) {
//			int x = notification.absRect.x - startX;
//			int y = notification.absRect.y - startY;
			notification.renderFont(notification.absRect.x, notification.absRect.y, batch);
			notification.sprite.draw(notification.absRect.x-(int)notification.size-38, notification.absRect.y, Z_CHARACTER);
			
			//renderCollisionRects(collectible, x, y);
		}
	}
	
	public void renderHud(Inventory inv) {
		for(int i=0;i < inv.invBar.length; i++) {			
			inv.invBar[i].render();
			if(i == inv.itemSelected) 
				inv.selectSprite.draw(inv.invBar[i].slotRect.x - 2, inv.invBar[i].slotRect.y - 2,
				Z_INV_SELECT, inv.invBar[i].slotRect.w + 3, inv.invBar[i].slotRect.h + 3);
			
		}
		renderHealth(Game.hero);
	}
	
	public void renderText(Inventory inv, SpriteBatch batch) {
		Slot[][] slots = {inv.invBar};
		
		for(int i = 0; i < slots.length; i++) {
			for(int j = 0; j < slots[i].length; j++) {
				slots[i][j].item.renderFont(slots[i][j].slotRect.x2()-INV_SLOT_SIZE/2, slots[i][j].slotRect.y2()-INV_SLOT_SIZE/2, batch);
			}
		}
	}
	
	//FIXME (placeholder)
	public void renderLight(Hero hero) {
		float lightSize = 100;
		if(frameCutter >= 5) {
			lightSizeRandom = MathUtils.random(0, 20); //150; //lightOscillate? (4.75f + 0.25f * (float)Math.sin(zAngle) + .2f*MathUtils.random()) + 100:5.0f + 100;
			frameCutter = 0;
		}
		//lightSize = lightSize + lightSizeRandom;
		float x = hero.absRect.midX() - startX - lightSize/2;
		float y = hero.absRect.midY() - startY - lightSize/2;
		StaticSprite lightSprite = Sprites.sprites.get(Sprites.LIGHT);
		lightSprite.draw(x, y, 0.4f, lightSize, lightSize);
		lightSprite.setColor(1,1,1,Game.lightTransparency2);
		
		StaticSprite campFireSprite = Sprites.sprites.get(Sprites.CAMPFIRE_LIGHT);
		for(Block light : lightTiles) {
			LightTile tile = (LightTile)light.layers[OBJECT_LAYER];
			lightSize = tile.lightSize + lightSizeRandom;
			x = light.absRect.midX() - startX - lightSize/2;
			y = light.absRect.midY() - startY - lightSize/2;
			campFireSprite.draw(x, y, 0.4f, lightSize, lightSize);
		}
		frameCutter ++;
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
		
		//Slot[][] slots = {inv.invBag, inv.invCrafted, inv.invClothing};
		for(int i = 0; i < inv.invBag.length; i++) {
			inv.invBag[i].render();
		}
		
		if(inv.craftingTableOn == true) {
			Slot[][] slots = {inv.invCrafted, inv.invClothing, inv.invTable};
			for(int i = 0; i < slots.length; i++) {
				for(int j = 0; j < slots[i].length; j++) {
					slots[i][j].render();
				}
			}
			hero.sprite.draw(INV_CHAR_RECT.x, INV_CHAR_RECT.y, Z_INV_CHARACTER, 3);
			
		} else if(inv.chestOn) {
			for(int i = 0; i < inv.invChest.length; i++) {
				inv.invChest[i].render();
			}
			//System.out.println(inv.invChest[0].item.id);
			
		} else if(inv.furnaceOn) {
			Furnace furnace = (Furnace) Game.level.activeBlocks[inv.currentInvActiveBlockX][inv.currentInvActiveBlockY].layers[OBJECT_LAYER];
			FurnaceTop furnaceTop = (FurnaceTop) Game.level.activeBlocks[inv.currentInvActiveBlockX][inv.currentInvActiveBlockY-1].layers[ABOVE_LAYER_1];
			furnace.sprites[0].draw(276, 396, 300);
			furnaceTop.sprites[0].draw(276, 364, 300);
			Furnace furnaceTile = (Furnace) Game.level.activeBlocks[inv.currentInvActiveBlockX][inv.currentInvActiveBlockY].layers[OBJECT_LAYER];
			Slot[][] slots = {furnaceTile.craftedSlot, inv.invClothing, furnaceTile.furnaceSlots};
			for(int i = 0; i < slots.length; i++) {
				for(int j = 0; j < slots[i].length; j++) {
					slots[i][j].render();
				}
			}
			hero.sprite.draw(INV_CHAR_RECT.x, INV_CHAR_RECT.y, Z_INV_CHARACTER, 3);
		
			renderFurnaceFuel(furnace, 272, 396);
			renderFurnaceBuildTime(furnace, 330, 390);
			
			
		} else if(inv.cauldronOn) {
			Cauldron cauldron = (Cauldron) Game.level.activeBlocks[inv.currentInvActiveBlockX][inv.currentInvActiveBlockY].layers[OBJECT_LAYER];
			cauldron.sprites[0].draw(276, 396, 300);
			Cauldron cauldronTile = (Cauldron) Game.level.activeBlocks[inv.currentInvActiveBlockX][inv.currentInvActiveBlockY].layers[OBJECT_LAYER];
			Slot[][] slots = {cauldronTile.craftedSlot, inv.invClothing, cauldronTile.cauldronSlots};
			for(int i = 0; i < slots.length; i++) {
				for(int j = 0; j < slots[i].length; j++) {
					slots[i][j].render();
				}
			}
			hero.sprite.draw(INV_CHAR_RECT.x, INV_CHAR_RECT.y, Z_INV_CHARACTER, 3);
		
			renderCauldronFuel(cauldron, 272, 396);
			renderCauldronBuildTime(cauldron, 330, 390);
			
		} else {
			Slot[][] slots = {inv.invCrafted, inv.invClothing, inv.invCrafting};
			for(int i = 0; i < slots.length; i++) {
				for(int j = 0; j < slots[i].length; j++) {
					slots[i][j].render();
				}
			}
			hero.sprite.draw(INV_CHAR_RECT.x, INV_CHAR_RECT.y, Z_INV_CHARACTER, 3);
		}

		
		
		inv.heldItem.render(Helpers.getX(), Helpers.getY());
	}
	
	public void renderFurnaceBuildTime(Furnace furnace, int x, int y) {
		x = x +4;
		y = y +26;
		//double difference = (10 - furnace.furnaceBuildTime);
		double difference = furnace.furnaceBuildTime;
		double percentage = (10 - difference) / 10;
		double hPCalc = percentage * 29;
		int hP = (int) hPCalc;
		
		//The Red/Blue/Green Part
		Sprites.pixel.setColor(Color.WHITE);
		Sprites.pixel.draw(x+1,y+1,Z_CHARACTER,hP,4);
		
		//Red Highlight top
		Sprites.pixel.setColor(1f, 1f, 1f,0.4f);
		Sprites.pixel.draw(x+2,y+1,Z_CHARACTER,hP,1);
		
		//Red Highlight bottom
		Sprites.pixel.setColor(0f, 0f, 0f,0.3f);
		Sprites.pixel.draw(x+2,y+4,Z_CHARACTER,hP,1);
	
		//Black Edge
		Sprites.pixel.setColor(Color.BLACK);
		Sprites.pixel.draw(x+1+hP,y+1,Z_CHARACTER,29-hP,4);
		
		//Border left
		Sprites.healthBarMon.draw(x,y,Z_CHARACTER,2,6);

		
		//Border Top
		Sprites.pixel.setColor(0.886f, 0.914f, 0.98f,1f);
		Sprites.pixel.draw(x+2, y, Z_CHARACTER, 28, 1);
		
		//Border Bottom
		Sprites.pixel.draw(x+2, y+5, Z_CHARACTER, 28, 1);
		
		//Border Right
		Sprites.healthBarMon.rotate(180);
		Sprites.healthBarMon.draw(x+2+28,y,Z_CHARACTER,2,6);
		Sprites.healthBarMon.rotate(180);
		
		//Reset
		Sprites.pixel.setColor(Color.WHITE);
	}
	
	public void renderCauldronBuildTime(Cauldron cauldron, int x, int y) {
		x = x +4;
		y = y +26;
		//double difference = (10 - furnace.furnaceBuildTime);
		double difference = cauldron.cauldronBuildTime;
		double percentage = (10 - difference) / 10;
		double hPCalc = percentage * 29;
		int hP = (int) hPCalc;
		
		//The Red/Blue/Green Part
		Sprites.pixel.setColor(Color.WHITE);
		Sprites.pixel.draw(x+1,y+1,Z_CHARACTER,hP,4);
		
		//Red Highlight top
		Sprites.pixel.setColor(1f, 1f, 1f,0.4f);
		Sprites.pixel.draw(x+2,y+1,Z_CHARACTER,hP,1);
		
		//Red Highlight bottom
		Sprites.pixel.setColor(0f, 0f, 0f,0.3f);
		Sprites.pixel.draw(x+2,y+4,Z_CHARACTER,hP,1);
	
		//Black Edge
		Sprites.pixel.setColor(Color.BLACK);
		Sprites.pixel.draw(x+1+hP,y+1,Z_CHARACTER,29-hP,4);
		
		//Border left
		Sprites.healthBarMon.draw(x,y,Z_CHARACTER,2,6);

		
		//Border Top
		Sprites.pixel.setColor(0.886f, 0.914f, 0.98f,1f);
		Sprites.pixel.draw(x+2, y, Z_CHARACTER, 28, 1);
		
		//Border Bottom
		Sprites.pixel.draw(x+2, y+5, Z_CHARACTER, 28, 1);
		
		//Border Right
		Sprites.healthBarMon.rotate(180);
		Sprites.healthBarMon.draw(x+2+28,y,Z_CHARACTER,2,6);
		Sprites.healthBarMon.rotate(180);
		
		//Reset
		Sprites.pixel.setColor(Color.WHITE);
	}
	
	public void renderFurnaceFuel(Furnace furnace, int x, int y) {
		x = x +4;
		y = y +26;
		double difference = (furnace.furnaceMaxFuel - furnace.furnaceFuel);
		double percentage = (furnace.furnaceMaxFuel - difference) / furnace.furnaceMaxFuel;
		double hPCalc = percentage * 29;
		int hP = (int) hPCalc;
		
		//The Red/Blue/Green Part
		Sprites.pixel.setColor(Color.RED);
		Sprites.pixel.draw(x+1,y+1,Z_CHARACTER,hP,4);
		
		//Red Highlight top
		Sprites.pixel.setColor(1f, 1f, 1f,0.4f);
		Sprites.pixel.draw(x+2,y+1,Z_CHARACTER,hP,1);
		
		//Red Highlight bottom
		Sprites.pixel.setColor(0f, 0f, 0f,0.3f);
		Sprites.pixel.draw(x+2,y+4,Z_CHARACTER,hP,1);
	
		//Black Edge
		Sprites.pixel.setColor(Color.BLACK);
		Sprites.pixel.draw(x+1+hP,y+1,Z_CHARACTER,29-hP,4);
		
		//Border left
		Sprites.healthBarMon.draw(x,y,Z_CHARACTER,2,6);

		
		//Border Top
		Sprites.pixel.setColor(0.886f, 0.914f, 0.98f,1f);
		Sprites.pixel.draw(x+2, y, Z_CHARACTER, 28, 1);
		
		//Border Bottom
		Sprites.pixel.draw(x+2, y+5, Z_CHARACTER, 28, 1);
		
		//Border Right
		Sprites.healthBarMon.rotate(180);
		Sprites.healthBarMon.draw(x+2+28,y,Z_CHARACTER,2,6);
		Sprites.healthBarMon.rotate(180);
		
		//Reset
		Sprites.pixel.setColor(Color.WHITE);
	}
	
	public void renderCauldronFuel(Cauldron cauldron, int x, int y) {
		x = x +4;
		y = y +26;
		double difference = (cauldron.cauldronMaxFuel - cauldron.cauldronFuel);
		double percentage = (cauldron.cauldronMaxFuel - difference) / cauldron.cauldronMaxFuel;
		double hPCalc = percentage * 29;
		int hP = (int) hPCalc;
		
		//The Red/Blue/Green Part
		Sprites.pixel.setColor(Color.RED);
		Sprites.pixel.draw(x+1,y+1,Z_CHARACTER,hP,4);
		
		//Red Highlight top
		Sprites.pixel.setColor(1f, 1f, 1f,0.4f);
		Sprites.pixel.draw(x+2,y+1,Z_CHARACTER,hP,1);
		
		//Red Highlight bottom
		Sprites.pixel.setColor(0f, 0f, 0f,0.3f);
		Sprites.pixel.draw(x+2,y+4,Z_CHARACTER,hP,1);
	
		//Black Edge
		Sprites.pixel.setColor(Color.BLACK);
		Sprites.pixel.draw(x+1+hP,y+1,Z_CHARACTER,29-hP,4);
		
		//Border left
		Sprites.healthBarMon.draw(x,y,Z_CHARACTER,2,6);

		
		//Border Top
		Sprites.pixel.setColor(0.886f, 0.914f, 0.98f,1f);
		Sprites.pixel.draw(x+2, y, Z_CHARACTER, 28, 1);
		
		//Border Bottom
		Sprites.pixel.draw(x+2, y+5, Z_CHARACTER, 28, 1);
		
		//Border Right
		Sprites.healthBarMon.rotate(180);
		Sprites.healthBarMon.draw(x+2+28,y,Z_CHARACTER,2,6);
		Sprites.healthBarMon.rotate(180);
		
		//Reset
		Sprites.pixel.setColor(Color.WHITE);
	}
	
	public void renderInventoryText(Inventory inv, SpriteBatch batch) {
		Slot[][] slots = new Slot[][]{inv.invBag, inv.invCrafting, inv.invCrafted, inv.invTable, inv.invChest};
		//
		for(int i = 0; i < slots.length; i++) {
			for(int j = 0; j < slots[i].length; j++) {
				slots[i][j].item.renderFont(slots[i][j].slotRect.x2()-INV_SLOT_SIZE/2, slots[i][j].slotRect.y2()-INV_SLOT_SIZE/2, batch);
				
					}
		}
		if(inv.furnaceOn) {
			Furnace furnaceTile = (Furnace) Game.level.activeBlocks[inv.currentInvActiveBlockX][inv.currentInvActiveBlockY].layers[OBJECT_LAYER];
			Slot[][]slots2 = {furnaceTile.craftedSlot, inv.invClothing, furnaceTile.furnaceSlots};
			for(int i = 0; i < slots2.length; i++) {
				for(int j = 0; j < slots2[i].length; j++) {
					slots2[i][j].item.renderFont(slots2[i][j].slotRect.x2()-INV_SLOT_SIZE/2, slots2[i][j].slotRect.y2()-INV_SLOT_SIZE/2, batch);
					
				}
			}
		}else if(inv.cauldronOn) {
			Cauldron cauldronTile = (Cauldron) Game.level.activeBlocks[inv.currentInvActiveBlockX][inv.currentInvActiveBlockY].layers[OBJECT_LAYER];
			Slot[][]slots2 = {cauldronTile.craftedSlot, inv.invClothing, cauldronTile.cauldronSlots};
			for(int i = 0; i < slots2.length; i++) {
				for(int j = 0; j < slots2[i].length; j++) {
					slots2[i][j].item.renderFont(slots2[i][j].slotRect.x2()-INV_SLOT_SIZE/2, slots2[i][j].slotRect.y2()-INV_SLOT_SIZE/2, batch);
					
				}
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
