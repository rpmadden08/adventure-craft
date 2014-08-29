package com.madbros.tileminer.Systems;

import static com.madbros.tileminer.Constants.*;

import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.Arrays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.madbros.tileminer.Block;
import com.madbros.tileminer.CollectibleController;
import com.madbros.tileminer.Game;
import com.madbros.tileminer.Inventory;
import com.madbros.tileminer.Level;
import com.madbros.tileminer.MobController;
import com.madbros.tileminer.NotificationController;
import com.madbros.tileminer.ParticleEffect;
import com.madbros.tileminer.ParticleEffectController;
import com.madbros.tileminer.Time;
import com.madbros.tileminer.Constants.State;
import com.madbros.tileminer.GameObjects.Actor;
import com.madbros.tileminer.GameObjects.Collectible;
import com.madbros.tileminer.GameObjects.GameObject;
import com.madbros.tileminer.GameObjects.Hero;
import com.madbros.tileminer.GameObjects.Mob;
import com.madbros.tileminer.Items.Item;
import com.madbros.tileminer.Notifications.Notification;
import com.madbros.tileminer.Slots.Slot;
import com.madbros.tileminer.Sprites.*;
import com.madbros.tileminer.TileTypes.CauldronTile;
import com.madbros.tileminer.TileTypes.FurnaceTile;
import com.madbros.tileminer.TileTypes.LightTile;
import com.madbros.tileminer.TileTypes.Tile;
import com.madbros.tileminer.TileTypes.TreeLeafTile;
import com.madbros.tileminer.Utils.Helpers;
import com.madbros.tileminer.Utils.Point;
import com.madbros.tileminer.Utils.Rect;
import com.madbros.tileminer.Utils.RectInt;

public class RenderSystem {
//	private ArrayList<GameObject> alreadyRenderedObjects;
	public int startX;
	public int startY;
	public ArrayList<Block> lightTiles = new ArrayList<Block>();
	private int frameCutter = 0;
	private float lightSizeRandom = 0;
	private Rect renderRect;
	//private RectInt renderRect2;
	private Point offsetPoint;

	public void setStartPosition(Level lv, Hero hero) {
//		lightTiles = new ArrayList<Block>();
		Block firstBlock = lv.activeBlocks[0][0];
		offsetPoint = Helpers.getOffsetPoint(hero, firstBlock);
		renderRect = Helpers.getRenderRect(hero, firstBlock);
		
		RectInt renderRect2 = renderRect.getRectInt();
		RectInt lvAbsRect = lv.activeBlocks[renderRect2.x][renderRect2.y].absRect.getRectInt();
		
		startX = lvAbsRect.x + offsetPoint.x;
		startY = lvAbsRect.y + offsetPoint.y;
	}
	/******************************************* Main State Rendering *******************************************/
	public void renderWorld(Level lv) {
//		alreadyRenderedObjects = new ArrayList<GameObject>();
		RectInt renderRect2 = renderRect.getRectInt();
		int i = 0; int j = 0;
		
		for(int y = renderRect2.y; y < renderRect2.y2(); y++) {
			for(int x = renderRect2.x; x < renderRect2.x2(); x++) {
//				if(x < lv.activeBlocks.length && y < lv.activeBlocks[0].length && x >= 0 && y >= 0) {	

					renderBlock(x, y, lv.activeBlocks[x][y], lv, i, j, true);
//				}
				i++;
			}
			j++; i = 0;
		}
	}
	public void renderWorldAbove(Level lv) {
//		alreadyRenderedObjects = new ArrayList<GameObject>();
		int i = 0; int j = 0;
		RectInt renderRect2 = renderRect.getRectInt();
		for(int y = renderRect2.y; y < renderRect2.y2(); y++) {
			for(int x = renderRect2.x; x < renderRect2.x2(); x++) {
//				if(x < lv.activeBlocks.length && y < lv.activeBlocks[0].length && x >= 0 && y >= 0) {
				
					renderBlock(x, y, lv.activeBlocks[x][y], lv, i, j, false);
					
//				}
				i++;
			}
			j++; i = 0;
		}
		i = 0; j = 0;
		for(int y = renderRect2.y; y < renderRect2.y2(); y++) {
			for(int x = renderRect2.x; x < renderRect2.x2(); x++) {
//				if(x < lv.activeBlocks.length && y < lv.activeBlocks[0].length && x >= 0 && y >= 0) {					
					renderBlockHighlight(x, y, lv.activeBlocks[x][y], lv, i, j, false);
					
//				}
				i++;
			}
			j++; i = 0;
		}
		//Game.level.autoTileHighlightedBlock();
	}
	
	public void renderBlockHighlight(int arrayX, int arrayY, Block block, Level lv, int i2, int j2, Boolean isAbove) {
		int x = i2 * TILE_SIZE - offsetPoint.x; //block.absRect.x - startX;
		int y = j2 * TILE_SIZE - offsetPoint.y; //block.absRect.y - startY;
		if(block.isHighlighted) {
			Item item = ITEM_HASH.get(Game.inventory.invBar[Game.inventory.itemSelected].item.id).createNew();
			if(item.is32) {
				item.highlightItem(block, x, y);
			} else if(block.getX(Game.level.activeBlocks) % 2 == 0 && block.getY(Game.level.activeBlocks) % 2 == 0) {
				item.highlightItem(block, x, y);
				
			}
			
			//Tile topTile = block.getTopTile();
			Tile topTile = item.topTile(block);
			if(topTile.currentHp < topTile.maxHp) {
				
				renderTileHealth(topTile, x, y, item.is32);
			}
		}
	}
	
	
	public void renderBlock(int arrayX, int arrayY, Block block, Level lv, int i2, int j2, Boolean isAbove) {
		int x = i2 * TILE_SIZE - offsetPoint.x; //block.absRect.x - startX;
		int y = j2 * TILE_SIZE - offsetPoint.y; //block.absRect.y - startY;
		Tile[] renderTiles;
		if(isAbove == true) {
			renderTiles = block.getRenderTilesBottom();
		} else {
			renderTiles = block.getRenderTilesTop();
			
		}
		for(int i = 0; i < renderTiles.length;i++) {
			if(renderTiles[i].isVisible) {
				//if this is 
				if(renderTiles[i].isTreeLeafTile == true) {
					((TreeLeafTile)renderTiles[i]).render(x, y, i);
				} else {
					renderTiles[i].render(x, y);
				}
				
				//if(renderTiles[i].isLightSource) lightTiles.add(block);
			}
			renderTiles[i].isTransparent = false;
			renderTiles[i].isVisible = true;
			
		}
		
		
		
		for(GameObject gameObject : block.objects) {
			RectInt objectAbsRect = gameObject.absRect.getRectInt();
			gameObject.sprite.draw(objectAbsRect.x - startX, objectAbsRect.y - startY, gameObject.z);
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
		RectInt heroAbsRect = hero.absRect.getRectInt();
		int width = heroAbsRect.w / 2;
		int height = heroAbsRect.h / 2+6;
		
		if(hero.isDead == false) {
			if(hero.isKnockingBack) {
				Color highlightColor = new Color(0.7f, 0.7f, 0.7f, 1.0f);
				hero.sprite.setColor(highlightColor);
				hero.sprite.draw(x, y, Z_CHARACTER);
				
				
				Sprites.pixel.setColor(Color.RED);
				Sprites.pixel.draw(x+width,y+height,Z_CHARACTER);
				
				hero.sprite.setColor(Color.WHITE);
			} else if(hero.isSwimming) {
				//TODO make head bob up and down here...  
				Sprites.waterSwimmingAnimation[0].draw(x, y+13, Z_CHARACTER);
				hero.swimmingSprite.draw(x, y+12, Z_CHARACTER);
			}else {
				
				
				hero.sprite.draw(x, y, Z_CHARACTER);
				
				renderCollisionRects(hero, x, y);
				
			}
			
			if(hero.isAttacking && hero.startWeaponAnimation == true) {
				//Item item = Game.inventory.heldItem;
				//item.sprite.setColor(Color.WHITE);
				hero.attackItem.swingSprite.setOrigin(hero.attackItem.originX, hero.attackItem.originY);
				//hero.attackItem.sprite.
				Sprites.pixel.setColor(Color.RED);
				Sprites.pixel.draw(hero.attackItem.originX, hero.attackItem.originY,Z_CHARACTER);
				
				hero.attackItem.swingSprite.rotate(hero.weaponR);
				hero.attackItem.swingSprite.draw(x+hero.weaponX+ hero.attackItem.weaponOffsetX, y+hero.weaponY+hero.attackItem.weaponOffsetY, Z_CHARACTER);
				hero.attackItem.swingSprite.rotate(-hero.weaponR);
				
				Sprites.pixel.setColor(Color.RED);
				Sprites.pixel.draw(x + hero.attackItem.originX+hero.weaponX+ hero.attackItem.weaponOffsetX, y + hero.attackItem.originY+hero.weaponY+hero.attackItem.weaponOffsetY,Z_CHARACTER);
				if(hero.dir == 0) {
					hero.sprite.draw(x, y, Z_CHARACTER);
					
					renderCollisionRects(hero, x, y);
				}
				//Collision rectangles for sword
			
//				Rect r = new Rect(x+hero.attackItem.cRectFinal.x,y+hero.attackItem.cRectFinal.y, hero.attackItem.cRectFinal.w,hero.attackItem.cRectFinal.h);
//				
//				Color highlightColor = new Color(0, 0, 1f, 0.6f);
//				Sprites.pixel.setColor(highlightColor);
//				
//				Sprites.pixel.draw(r, Z_COLLISION_RECTS);
//				Sprites.pixel.setColor(Color.WHITE);
				
			}	
		}
		
	}
	
	public void renderTileHealth(Tile tile, int x, int y, Boolean is32) {
		int tileOffset = 0;
		if(is32 == false) {
			tileOffset = 16;
		}
		double difference = (tile.maxHp - tile.currentHp);
		double percentage = (tile.maxHp-difference) / tile.maxHp;
		double hPCalc = percentage * 30; 
		int hP = (int) hPCalc;
		
		//The Red/Blue/Green Part
		float green;
		float red;
		if(percentage > 0.5) {
			green = 1f;
			red = (1f - (float)percentage) * 2;
		} else {
			green = (float)percentage*2;
			red = 1f;
		}
		Sprites.pixel.setColor(red, green, 0f, 1f);
		Sprites.pixel.draw(x+1+tileOffset,y+1,Z_CHARACTER,hP,4);
		
		//Red Highlight top
		Sprites.pixel.setColor(1f, 1f, 1f,0.4f);
		Sprites.pixel.draw(x+2+tileOffset,y+1,Z_CHARACTER,hP,1);
		
		//Red Highlight bottom
		Sprites.pixel.setColor(0f, 0f, 0f,0.3f);
		Sprites.pixel.draw(x+2+tileOffset,y+4,Z_CHARACTER,hP,1);
	
		//Black Edge
		Sprites.pixel.setColor(Color.BLACK);
		Sprites.pixel.draw(x+1+hP+tileOffset,y+1,Z_CHARACTER,30-hP,4);
		
		//Border left
		Sprites.healthBarMon.draw(x+tileOffset,y,Z_CHARACTER,2,6);

		
		//Border Top
		Sprites.pixel.setColor(0.886f, 0.914f, 0.98f,1f);
		Sprites.pixel.draw(x+2+tileOffset, y, Z_CHARACTER, 28, 1);
		
		//Border Bottom
		Sprites.pixel.draw(x+2+tileOffset, y+5, Z_CHARACTER, 28, 1);
		
		//Border Right
		Sprites.healthBarMon.rotate(180);
		Sprites.healthBarMon.draw(x+2+28+tileOffset,y,Z_CHARACTER,2,6);
		Sprites.healthBarMon.rotate(-180);
		
		//Reset
		Sprites.pixel.setColor(Color.WHITE);
}
	
	public void renderHealth(Hero hero) {

		StaticSprite healthBarMiddle = Sprites.sprites.get(Sprites.HEALTH_BAR_MIDDLE);
		StaticSprite healthBarLeft = Sprites.sprites.get(Sprites.HEALTH_BAR_LEFT);
		StaticSprite healthBarRight = Sprites.sprites.get(Sprites.HEALTH_BAR_RIGHT);
		StaticSprite energyBarMiddle = Sprites.sprites.get(Sprites.ENERGY_BAR_MIDDLE);
		StaticSprite magicBarMiddle = Sprites.sprites.get(Sprites.MAGIC_BAR_MIDDLE);
		StaticSprite emptyBarMiddle = Sprites.sprites.get(Sprites.EMPTY_BAR_MIDDLE);
		
		healthBarLeft.draw(1,1,0);
		emptyBarMiddle.draw(healthBarLeft.getX()+healthBarLeft.getWidth(),healthBarLeft.getY() +2,Z_CHARACTER,hero.maxHP,healthBarMiddle.getHeight());
		healthBarMiddle.draw(healthBarLeft.getX()+healthBarLeft.getWidth(),healthBarLeft.getY() +2,Z_CHARACTER,hero.hP,healthBarMiddle.getHeight());
		healthBarRight.draw(healthBarLeft.getX()+healthBarLeft.getWidth()+ hero.maxHP, healthBarLeft.getY(), Z_CHARACTER);
		
		
		healthBarLeft.draw(1,29,0);
		emptyBarMiddle.draw(healthBarLeft.getX()+healthBarLeft.getWidth(),healthBarLeft.getY() +2,Z_CHARACTER,hero.maxMP,healthBarMiddle.getHeight());
		magicBarMiddle.draw(healthBarLeft.getX()+healthBarLeft.getWidth(),healthBarLeft.getY() +2,Z_CHARACTER,hero.mP,healthBarMiddle.getHeight());
		healthBarRight.draw(healthBarLeft.getX()+healthBarLeft.getWidth()+ hero.maxMP, healthBarLeft.getY(), Z_CHARACTER);
		
		healthBarLeft.draw(1,57,0);
		emptyBarMiddle.draw(healthBarLeft.getX()+healthBarLeft.getWidth(),healthBarLeft.getY() +2,Z_CHARACTER,(int)hero.maxEP,healthBarMiddle.getHeight());
		energyBarMiddle.draw(healthBarLeft.getX()+healthBarLeft.getWidth(),healthBarLeft.getY() +2,Z_CHARACTER,(int)hero.eP,healthBarMiddle.getHeight());
		healthBarRight.draw(healthBarLeft.getX()+healthBarLeft.getWidth()+ (int)hero.maxEP, healthBarLeft.getY(), Z_CHARACTER);
		
		
		
//		//The Red/Blue/Green Part
//		Sprites.pixel.setColor(Color.toFloatBits(239, 29, 29, 255));
//		Sprites.pixel.draw(4,4,Z_CHARACTER,hero.hP,10);
//		
//		Sprites.pixel.setColor(Color.toFloatBits(28, 57, 234, 255));
//		Sprites.pixel.draw(4,20,Z_CHARACTER,hero.mP,10);
//		
//		Sprites.pixel.setColor(Color.toFloatBits(39, 194, 32, 255));
//		Sprites.pixel.draw(4,36,Z_CHARACTER,(int)hero.eP,10);
//		
//		//Red Highlight top
//		Sprites.pixel.setColor(Color.toFloatBits(239, 99, 99, 255));
//		Sprites.pixel.draw(4,4,Z_CHARACTER,hero.hP,2);
//		Sprites.pixel.setColor(Color.toFloatBits(68, 97, 234, 255));
//		Sprites.pixel.draw(4,20,Z_CHARACTER,hero.mP,2);
//		Sprites.pixel.setColor(1f, 1f, 1f,0.25f);
//		Sprites.pixel.draw(4,36,Z_CHARACTER,(int)hero.eP,2);
//		
//		//Red Highlight bottom
//		Sprites.pixel.setColor(0f, 0f, 0f,0.3f);
//		Sprites.pixel.draw(4,12,Z_CHARACTER,hero.hP,2);
//		Sprites.pixel.draw(4,28,Z_CHARACTER,hero.mP,2);
//		Sprites.pixel.draw(4,44,Z_CHARACTER,(int)hero.eP,2);
//	
//		//Black Edge
//		Sprites.pixel.setColor(Color.BLACK);
//		Sprites.pixel.draw(4+hero.hP,4,Z_CHARACTER,hero.maxHP-hero.hP,10);
//		Sprites.pixel.draw(4+hero.mP,20,Z_CHARACTER,hero.maxMP-hero.mP,10);
//		Sprites.pixel.draw(4+(int)hero.eP,36,Z_CHARACTER,(int)hero.maxEP-(int)hero.eP,10);
//		
//		//Border left
//		Sprites.healthBar.draw(2,2,Z_CHARACTER,4,14);
//		Sprites.healthBar.draw(2,18,Z_CHARACTER,4,14);
//		Sprites.healthBar.draw(2,34,Z_CHARACTER,4,14);
//		
//		//Border Top
//		Sprites.pixel.setColor(0.886f, 0.914f, 0.98f,1f);
//		Sprites.pixel.draw(6, 2, Z_CHARACTER, hero.maxHP-4, 2);
//		Sprites.pixel.draw(6, 18, Z_CHARACTER, hero.maxMP-4, 2);
//		Sprites.pixel.draw(6, 34, Z_CHARACTER, (int)hero.maxEP-4, 2);
//		
//		//Border Bottom
//		Sprites.pixel.draw(6, 14, Z_CHARACTER, hero.maxHP-4, 2);
//		Sprites.pixel.draw(6, 30, Z_CHARACTER, hero.maxMP-4, 2);
//		Sprites.pixel.draw(6, 46, Z_CHARACTER, (int)hero.maxEP-4, 2);
//		
//		//Border Right
//		Sprites.healthBar.rotate(180);
//		Sprites.healthBar.draw(2+hero.maxHP,2,Z_CHARACTER,4,14);
//		Sprites.healthBar.draw(2+hero.maxMP,18,Z_CHARACTER,4,14);
//		Sprites.healthBar.draw(2+(int)hero.maxEP,34,Z_CHARACTER,4,14);
//		Sprites.healthBar.rotate(-180);
//		
//		//Reset
//		Sprites.pixel.setColor(Color.WHITE);
//		
////		Sprites.pixel.draw(4, 3, Z_CHARACTER, hero.maxHP+1, 1);
////		Sprites.pixel.draw(3, 4, Z_CHARACTER, hero.maxHP+2, 1);
////		Sprites.pixel.draw(2, 5, Z_CHARACTER, hero.maxHP+3, 8);
////		Sprites.pixel.draw(3, 13, Z_CHARACTER, hero.maxHP+2, 1);
////		Sprites.pixel.draw(4, 14, Z_CHARACTER, hero.maxHP+1, 1);
////		Sprites.pixel.draw(5, 15, Z_CHARACTER, hero.maxHP, 1);
//		
//		Sprites.pixel.setColor(Color.WHITE);
	}
	
	public void renderMobHealth(Mob mob) {
		RectInt mobAbsRect = mob.absRect.getRectInt();
				int x = mobAbsRect.x+(mobAbsRect.w /2 -15) - startX;
				int y = mobAbsRect.y+(mob.margin.top-10) - startY - 6;
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
				Sprites.healthBarMon.rotate(-180);
				
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
		float green;
		float red;
		if(percentage > 0.5) {
			green = 1f;
			red = (1f - (float)percentage) * 2;
		} else {
			green = (float)percentage*2;
			red = 1f;
		}
		Sprites.pixel.setColor(red, green, 0f, 1f);
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
		Sprites.healthBarMon.rotate(-180);
		
		//Reset
		Sprites.pixel.setColor(Color.WHITE);
}
	public void renderMobs(MobController mobController) {
		for(Mob mob : mobController.mobs) {
			RectInt mobAbsRect = mob.absRect.getRectInt();
			int x = mobAbsRect.x - startX;
			int y = mobAbsRect.y - startY;
			//int width = mob.absRect.w / 2;
			//int height = mob.absRect.h / 2;
			if(Time.getTime() -mob.invincibleTime > 666.666 ) {
				mob.sprite.draw(x, y, Z_CHARACTER);
			} else {
				mob.sprite.setAnimColor(new Color(1f,1f,1f,0.2f));
				mob.sprite.draw(x, y, Z_CHARACTER);
				mob.sprite.setAnimColor(Color.WHITE);
			}
//			Rect test = new Rect(mob.absRect, mob.margin);
//			//Sprites.pixel.draw(x, y);
			
			renderMobCollisionRects(mob, x, y);
			if(mob.hP != mob.maxHP) renderMobHealth(mob);
			
		}
	}
	
	public void renderCollectibles(CollectibleController collectibleController) {
		for(Collectible collectible : collectibleController.collectibles) {
			RectInt colAbsRect = collectible.absRect.getRectInt();
			int x = colAbsRect.x - startX;
			int y = colAbsRect.y - startY;
			collectible.sprite.draw(x, y, Z_CHARACTER);
			//renderCollisionRects(collectible, x, y);
		}
	}
	
	public void renderNotifications(NotificationController notificationController, SpriteBatch batch) {
		for(Notification notification : notificationController.notifications) {
			
//			int x = notification.absRect.x - startX;
//			int y = notification.absRect.y - startY;
			RectInt notAbsRect = notification.absRect.getRectInt();
			notification.renderFont(notAbsRect.x, notAbsRect.y, batch);
			notification.render();
			//notification.sprite.draw(notification.absRect.x-(int)notification.size-38, notification.absRect.y, Z_CHARACTER);
			
			//renderCollisionRects(collectible, x, y);
		}
	}
	
	public void renderHud(Inventory inv) {
		Sprites.invBar.draw(0,0,0, Sprites.invBar.getWidth(), Sprites.invBar.getHeight());
		for(int i=0;i < inv.invBar.length; i++) {			
			inv.invBar[i].render();
			RectInt slotRect = inv.invBar[i].slotRect.getRectInt();
			if(i == inv.itemSelected) 
				
				inv.selectSprite.draw(slotRect.x - 1, slotRect.y - 2,
				Z_INV_SELECT, slotRect.w + 3, slotRect.h + 3);
			
		}
		renderHealth(Game.hero);
		renderStatusEffects();
	}

	public void renderStatusEffects() {
		int x = Game.currentScreenSizeX - 32;
		for(int a = 1; a < Game.hero.appliedStatusEffects.length; a++) {
			if(Game.hero.appliedStatusEffects[a].id == a) {
				StaticSprite sprite = Game.hero.appliedStatusEffects[a].sprite;
				sprite.draw(x, 0, 0f);
				if(Game.hero.appliedStatusEffects[a].usesLeft >9) {
					Sprites.arial10.draw(Game.batch, String.valueOf(Game.hero.appliedStatusEffects[a].usesLeft), x+18, 32);
				} else {
					Sprites.arial10.draw(Game.batch, String.valueOf(Game.hero.appliedStatusEffects[a].usesLeft), x+25, 32);
					//Sprites.font.draw(Game.batch, String.valueOf(Game.hero.appliedStatusEffects[a].usesLeft), x, 32);
				}
				
				x= x-32;
			}
		}
		
		for(int a = 1; a < Game.hero.timedStatusEffects.length; a++) {
			if(Game.hero.timedStatusEffects[a].id == a) {
				StaticSprite sprite = Game.hero.timedStatusEffects[a].sprite;
				sprite.draw(x, 0, 0f);
				x= x-32;
				//long time = Time.getTime() -Game.hero.timedStatusEffects[a].timeTriggered +Game.hero.timedStatusEffects[a].potionEffectTime;
				long time = Game.hero.timedStatusEffects[a].potionEffectTime - (Time.getTime() -Game.hero.timedStatusEffects[a].timeTriggered);
				
				long totalSeconds = time / 1000;
				long minutes = totalSeconds / 60;
				long secondsTens = totalSeconds % 60 /10;
				long seconds = totalSeconds % 60 % 10;
				String totalString = String.valueOf(minutes)+"."+String.valueOf(secondsTens)+String.valueOf(seconds);
				
				Sprites.arial10.setColor(Color.RED);
    			Sprites.arial10.draw(Game.batch, totalString, x+32+6, 32);
    			Sprites.arial10.setColor(Color.WHITE);
//				if(minutes > 0) {
//					int mWidth = (int) Sprites.arial10.getBounds(mString).width;
//					Sprites.arial10.draw(Game.batch, mString, x+32-(mWidth/2), 32);
//				} else {
//					int sWidth = (int) Sprites.arial10.getBounds(sString).width;
//					Sprites.arial10.draw(Game.batch, sString, x+32-(sWidth/2), 32);
//				}
				//int m = Time.getTime() -Game.hero.appliedStatusEffects[a].timeTriggered +Game.hero.appliedStatusEffects[a].potionEffectTime)
				//Sprites.arial10.draw(Game.batch, String.valueOf(minutes)+"."+String.valueOf(seconds)+ " min", x, 32);
			}
		}
	}
	
	
	public void renderText(Inventory inv, SpriteBatch batch) {
		Slot[][] slots = {inv.invBar};
		
		for(int i = 0; i < slots.length; i++) {
			for(int j = 0; j < slots[i].length; j++) {
				RectInt slotRect = slots[i][j].slotRect.getRectInt();
				slots[i][j].item.renderFont(slotRect.x2()-INV_SLOT_SIZE/2, slotRect.y2()-INV_SLOT_SIZE/2, batch);
			}
		}
	}

	public void renderLight(Hero hero) {
		float lightSize = 100;
		if(frameCutter >= 5) {
			lightSizeRandom = MathUtils.random(0, 20); //150; //lightOscillate? (4.75f + 0.25f * (float)Math.sin(zAngle) + .2f*MathUtils.random()) + 100:5.0f + 100;
			frameCutter = 0;
		}
		//lightSize = lightSize + lightSizeRandom;
		RectInt heroAbsRect = hero.absRect.getRectInt();
		if(Game.inventory.invBar[Game.inventory.itemSelected].item.id == TORCH) {
			lightSize = 500 + lightSizeRandom;
			float x = heroAbsRect.midX() - startX - lightSize/2;
			float y = heroAbsRect.midY() - startY - lightSize/2;
			StaticSprite lightSprite = Sprites.sprites.get(Sprites.CAMPFIRE_LIGHT);
			lightSprite.draw(x, y, 0.4f, lightSize, lightSize);
			lightSprite.setColor(1,1,1,Game.lightTransparency2);
		} else {
			float x = heroAbsRect.midX() - startX - lightSize/2;
			float y = heroAbsRect.midY() - startY - lightSize/2;
			StaticSprite lightSprite = Sprites.sprites.get(Sprites.LIGHT);
			lightSprite.draw(x, y, 0.4f, lightSize, lightSize);
			lightSprite.setColor(1,1,1,Game.lightTransparency2);
		}
		
		StaticSprite campFireSprite = Sprites.sprites.get(Sprites.CAMPFIRE_LIGHT);
		for(Block light : lightTiles) {
			RectInt lightAbsRect = light.absRect.getRectInt();
			if(light.layers[OBJECT_LAYER].isLightSource) {
				LightTile tile = (LightTile)light.layers[OBJECT_LAYER];
				lightSize = tile.lightSize + lightSizeRandom;
				float x = lightAbsRect.midX() - startX - lightSize/2;
				float y = lightAbsRect.midY() - startY - lightSize/2;
				campFireSprite.draw(x, y, 0.4f, lightSize, lightSize);
			}
		}
		lightTiles = new ArrayList<Block>();
		frameCutter ++;
	}
	
	/******************************************* Inventory State Rendering *******************************************/
	public void renderInventory(Hero hero, Inventory inv) {
		Sprites.gameMenu.draw(0,0,0, Sprites.gameMenu.getWidth(), Sprites.gameMenu.getHeight());
//		inv.menuSprites[0].draw(INV_BACKDROP_RECT.x, INV_BACKDROP_RECT.y, Z_INV_BACKDROP);	//top left
//		inv.menuSprites[6].draw(INV_BACKDROP_RECT.x2()-INV_MENU_TILE_SIZE, INV_BACKDROP_RECT.y, Z_INV_BACKDROP); //top right
//		inv.menuSprites[2].draw(INV_BACKDROP_RECT.x, INV_BACKDROP_RECT.y2()-INV_MENU_TILE_SIZE, Z_INV_BACKDROP);	//bottom left
//		inv.menuSprites[8].draw(INV_BACKDROP_RECT.x2()-INV_MENU_TILE_SIZE, INV_BACKDROP_RECT.y2()-INV_MENU_TILE_SIZE, Z_INV_BACKDROP);	//bottom right
//		
//		inv.menuSprites[3].draw(INV_BACKDROP_RECT.x+INV_MENU_TILE_SIZE, INV_BACKDROP_RECT.y, Z_INV_BACKDROP, INV_BACKDROP_RECT.w-INV_MENU_TILE_SIZE*2, INV_MENU_TILE_SIZE);	//top
//		inv.menuSprites[5].draw(INV_BACKDROP_RECT.x+INV_MENU_TILE_SIZE, INV_BACKDROP_RECT.y2()-INV_MENU_TILE_SIZE, Z_INV_BACKDROP, INV_BACKDROP_RECT.w-INV_MENU_TILE_SIZE*2, INV_MENU_TILE_SIZE);	//bottom
//		inv.menuSprites[1].draw(INV_BACKDROP_RECT.x, INV_BACKDROP_RECT.y+INV_MENU_TILE_SIZE, Z_INV_BACKDROP, INV_MENU_TILE_SIZE, INV_BACKDROP_RECT.h-INV_MENU_TILE_SIZE*2);	//left
//		inv.menuSprites[7].draw(INV_BACKDROP_RECT.x2()-INV_MENU_TILE_SIZE, INV_BACKDROP_RECT.y+INV_MENU_TILE_SIZE, Z_INV_BACKDROP, INV_MENU_TILE_SIZE, INV_BACKDROP_RECT.h-INV_MENU_TILE_SIZE*2);	//right
//		
//		inv.menuSprites[4].draw(INV_BACKDROP_RECT.x+INV_MENU_TILE_SIZE, INV_BACKDROP_RECT.y+INV_MENU_TILE_SIZE, Z_INV_BACKDROP, INV_BACKDROP_RECT.w-INV_MENU_TILE_SIZE*2, INV_BACKDROP_RECT.h-INV_MENU_TILE_SIZE*2); //middle
//		
//		
		for(int i = 0; i < inv.invBag.length; i++) {
			inv.invBag[i].render();
		}
		if(inv.currentWorkSpace == TABLE_WORKSPACE) {
			Sprite sprite = Sprites.sprites.get(Sprites.TABLE_ITEM);
			sprite.setColor(1.0f,1.0f,1.0f,0.3f);
			sprite.draw(542,446,0f,(int)(sprite.getWidth()*4.47), (int)(sprite.getHeight()* 4.47));
			sprite.setColor(1.0f,1.0f,1.0f,1f);
		}
		
	}
	public void renderCrafting(Inventory inv) {
		for(int i = 0; i < inv.craftingMenu.craftSlots.length; i++) {
			inv.craftingMenu.craftSlots[i].render();
		}
		
	}
	
	public void renderArmorSlots(Hero hero, Inventory inv) {
		Slot[][] slots = {inv.invClothing};
		for(int i = 0; i < slots.length; i++) {
			for(int j = 0; j < slots[i].length; j++) {
				slots[i][j].render();
			}
		}
		RectInt invCharRect = INV_CHAR_RECT.getRectInt();
		hero.sprite.draw(invCharRect.x, invCharRect.y, Z_INV_CHARACTER, 3);
	}
	
	public void renderHeldItem(Inventory inv) {
		inv.heldItem.render(Helpers.getX(), Helpers.getY());
		if(inv.heldItem.id != EMPTY) {
			inv.heldItem.renderFont(Helpers.getX(), Helpers.getY(), Game.batch);
		}
		
	}
	
	public void renderChest(Inventory inv) {
		for(int i = 0; i < inv.invChest.length; i++) {
			inv.invChest[i].render();
		}
			Sprite sprite = Sprites.sprites.get(Sprites.CHEST_ITEM);
			sprite.setColor(1.0f,1.0f,1.0f,0.3f);
			sprite.draw(542,446,0f,(int)(sprite.getWidth()*4.47), (int)(sprite.getHeight()* 4.47));
			sprite.setColor(Color.WHITE);
			
		
	}
	
	public void renderFurnace(Inventory inv) {
		FurnaceTile furnace = (FurnaceTile) Game.level.activeBlocks[inv.currentInvActiveBlockX][inv.currentInvActiveBlockY].layers[OBJECT_LAYER];
		//FurnaceTopTile furnaceTop = (FurnaceTopTile) Game.level.activeBlocks[inv.currentInvActiveBlockX][inv.currentInvActiveBlockY-1].layers[ABOVE_LAYER_1];
		if(furnace.furnaceIsBurning) {
			//AnimatedSprite[] sprite2 = Sprites.furnaceAnimation;
			Animation sprite2 = Sprites.furnaceAnimation[0].currentAnimation;
			sprite2.updateFrame();
			//int cA = sprite2[0].getCurrentAnimation();
			sprite2.sprites[sprite2.currentFrame].setColor(1.0f,1.0f,1.0f,0.3f);
			sprite2.sprites[sprite2.currentFrame].draw(567,505,0f,(int)(sprite2.sprites[sprite2.currentFrame].getWidth()*3.125), (int)(sprite2.sprites[sprite2.currentFrame].getHeight()* 3.125));
			sprite2.sprites[sprite2.currentFrame].setColor(1.0f,1.0f,1.0f,1f);
		} else {
			Sprite sprite2 = Sprites.sprites.get(Sprites.FURNACE_SINGLE);
			sprite2.setColor(1.0f,1.0f,1.0f,0.3f);
			sprite2.draw(567,505,0f,(int)(sprite2.getWidth()*3.125), (int)(sprite2.getHeight()* 3.125));
			sprite2.setColor(1.0f,1.0f,1.0f,1f);
		}
		
		
		Sprite[] sprite = Sprites.spriteCollections.get(Sprites.FURNACE_TOP);
		sprite[0].setColor(1.0f,1.0f,1.0f,0.3f);
		sprite[0].draw(567,433,0f,(int)(sprite[0].getWidth()*3.125), (int)(sprite[0].getHeight()* 3.125));
		sprite[0].setColor(Color.WHITE);
		
		FurnaceTile furnaceTile = (FurnaceTile) Game.level.activeBlocks[inv.currentInvActiveBlockX][inv.currentInvActiveBlockY].layers[OBJECT_LAYER];
		Slot[][] slots = {furnaceTile.craftedSlot, furnaceTile.furnaceSlots, furnaceTile.fuelSlot};
		for(int i = 0; i < slots.length; i++) {
			for(int j = 0; j < slots[i].length; j++) {
				slots[i][j].render();
			}
		}
	
		renderFurnaceFuel(furnace,540, 546);
		renderFurnaceBuildTime(furnace, 650, 549);
		
		
	}
	
	public void renderCauldron(Inventory inv) {
		CauldronTile cauldron = (CauldronTile) Game.level.activeBlocks[inv.currentInvActiveBlockX][inv.currentInvActiveBlockY].layers[OBJECT_LAYER];
		
		if(cauldron.cauldronIsBurning) {
			Animation sprite2 = Sprites.cauldronAnimation[0].currentAnimation;
			sprite2.updateFrame();
			//int cA = sprite2[0].getCurrentAnimation();
			sprite2.sprites[sprite2.currentFrame].setColor(1.0f,1.0f,1.0f,0.3f);
			sprite2.sprites[sprite2.currentFrame].draw(542,446,0f,(int)(sprite2.sprites[sprite2.currentFrame].getWidth()*4.47), (int)(sprite2.sprites[sprite2.currentFrame].getHeight()* 4.47));
			sprite2.sprites[sprite2.currentFrame].setColor(1.0f,1.0f,1.0f,1f);
		} else {
			Sprite sprite2 = Sprites.sprites.get(Sprites.CAULDRON_SINGLE);
			sprite2.setColor(1.0f,1.0f,1.0f,0.3f);
			sprite2.draw(542,446,0f,(int)(sprite2.getWidth()*4.47), (int)(sprite2.getHeight()* 4.47));
			sprite2.setColor(1.0f,1.0f,1.0f,1f);
		}
		
		//CauldronTile cauldron = (CauldronTile) Game.level.activeBlocks[inv.currentInvActiveBlockX][inv.currentInvActiveBlockY].layers[OBJECT_LAYER];
		
		CauldronTile cauldronTile = (CauldronTile) Game.level.activeBlocks[inv.currentInvActiveBlockX][inv.currentInvActiveBlockY].layers[OBJECT_LAYER];
		Slot[][] slots = {cauldronTile.craftedSlot, cauldronTile.cauldronSlots, cauldronTile.fuelSlot};
		for(int i = 0; i < slots.length; i++) {
			for(int j = 0; j < slots[i].length; j++) {
				slots[i][j].render();
			}
		}
		renderCauldronFuel(cauldron, 540, 546);
		renderCauldronBuildTime(cauldron, 650, 549);
	}
	
	public void renderFurnaceBuildTime(FurnaceTile furnace, int x, int y) {
		x = x +4;
		y = y +26;
		//double difference = (10 - furnace.furnaceBuildTime);
		double difference = furnace.furnaceBuildTime;
		double percentage = (10 - difference) / 10;
		double hPCalc = percentage * 29;
		int hP = (int) hPCalc;
		
		//The Red/Blue/Green Part
		Sprites.pixel.setColor(Color.WHITE);
		Sprites.pixel.draw(x+2,y+2,Z_CHARACTER,hP*2,4*2);
		
		//Red Highlight top
		Sprites.pixel.setColor(1f, 1f, 1f,0.4f);
		Sprites.pixel.draw(x+4,y+2,Z_CHARACTER,hP*2,1*2);
		
		//Red Highlight bottom
		Sprites.pixel.setColor(0f, 0f, 0f,0.3f);
		Sprites.pixel.draw(x+4,y+8,Z_CHARACTER,hP*2,1*2);
	
		//Black Edge
		Sprites.pixel.setColor(Color.BLACK);
		Sprites.pixel.draw(x+2+(hP*2),y+2,Z_CHARACTER,(29*2)-hP*2,4*2);
		
		//Border left
		Sprites.healthBarMon.draw(x,y,Z_CHARACTER,2*2,6*2);

		
		//Border Top
		Sprites.pixel.setColor(0.886f, 0.914f, 0.98f,1f);
		Sprites.pixel.draw(x+4, y, Z_CHARACTER, 28*2, 1*2);
		
		//Border Bottom
		Sprites.pixel.draw(x+4, y+10, Z_CHARACTER, 28*2, 1*2);
		
		//Border Right
		Sprites.healthBarMon.rotate(180);
		Sprites.healthBarMon.draw(x+2+58,y+6,Z_CHARACTER,2*2,6*2);
		Sprites.healthBarMon.rotate(-180);
		
		//Reset
		Sprites.pixel.setColor(Color.WHITE);
	}
	
	public void renderCauldronBuildTime(CauldronTile cauldron, int x, int y) {
		x = x +4;
		y = y +26;
		//double difference = (10 - furnace.furnaceBuildTime);
		double difference = cauldron.cauldronBuildTime;
		double percentage = (10 - difference) / 10;
		double hPCalc = percentage * 29;
		int hP = (int) hPCalc;
		
		//The Red/Blue/Green Part
		Sprites.pixel.setColor(Color.WHITE);
		Sprites.pixel.draw(x+2,y+2,Z_CHARACTER,hP*2,4*2);
		
		//Red Highlight top
		Sprites.pixel.setColor(1f, 1f, 1f,0.4f);
		Sprites.pixel.draw(x+4,y+2,Z_CHARACTER,hP*2,1*2);
		
		//Red Highlight bottom
		Sprites.pixel.setColor(0f, 0f, 0f,0.3f);
		Sprites.pixel.draw(x+4,y+8,Z_CHARACTER,hP*2,1*2);
	
		//Black Edge
		Sprites.pixel.setColor(Color.BLACK);
		Sprites.pixel.draw(x+2+(hP*2),y+2,Z_CHARACTER,(29*2)-hP*2,4*2);
		
		//Border left
		Sprites.healthBarMon.draw(x,y,Z_CHARACTER,2*2,6*2);

		
		//Border Top
		Sprites.pixel.setColor(0.886f, 0.914f, 0.98f,1f);
		Sprites.pixel.draw(x+4, y, Z_CHARACTER, 28*2, 1*2);
		
		//Border Bottom
		Sprites.pixel.draw(x+4, y+10, Z_CHARACTER, 28*2, 1*2);
		
		//Border Right
		Sprites.healthBarMon.rotate(180);
		Sprites.healthBarMon.draw(x+2+58,y+6,Z_CHARACTER,2*2,6*2);
		Sprites.healthBarMon.rotate(-180);
		
		//Reset
		Sprites.pixel.setColor(Color.WHITE);
	}
	
	public void renderFurnaceFuel(FurnaceTile furnace, int x, int y) {
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
		Sprites.healthBarMon.rotate(-180);
		
		//Reset
		Sprites.pixel.setColor(Color.WHITE);
	}
	
	public void renderCauldronFuel(CauldronTile cauldron, int x, int y) {
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
		Sprites.healthBarMon.rotate(-180);
		
		//Reset
		Sprites.pixel.setColor(Color.WHITE);
	}
	
	public void renderInventoryText(Inventory inv, SpriteBatch batch) {
		Slot[][] slots = new Slot[][]{inv.invBag, inv.invCrafting, inv.invCrafted};
		//
		
		for(int i = 0; i < slots.length; i++) {
			for(int j = 0; j < slots[i].length; j++) {
				RectInt slotRect = slots[i][j].slotRect.getRectInt();
				slots[i][j].item.renderFont(slotRect.x2()-INV_SLOT_SIZE/2, slotRect.y2()-INV_SLOT_SIZE/2, batch);
				
					}
		}
		if(Game.currentState.type == State.CHEST) {
			Slot[][]slots2 = {inv.invChest};
			for(int i = 0; i < slots2.length; i++) {
				for(int j = 0; j < slots2[i].length; j++) {
					RectInt slotRect = slots2[i][j].slotRect.getRectInt();
					slots2[i][j].item.renderFont(slotRect.x2()-INV_SLOT_SIZE/2, slotRect.y2()-INV_SLOT_SIZE/2, batch);
					
				}
			}
			Sprites.arial24.draw(batch, "Chest", 304, 108);
		}else if(Game.currentState.type == State.FURNACE) {
			FurnaceTile furnaceTile = (FurnaceTile) Game.level.activeBlocks[inv.currentInvActiveBlockX][inv.currentInvActiveBlockY].layers[OBJECT_LAYER];
			RectInt slotRect = furnaceTile.craftedSlot[0].slotRect.getRectInt();
			furnaceTile.craftedSlot[0].item.renderLargeFont(slotRect.x2()-INV_SLOT_SIZE/2, slotRect.y2()-INV_SLOT_SIZE/2, batch);
			Slot[][]slots2 = {inv.invClothing, furnaceTile.furnaceSlots, furnaceTile.fuelSlot};
			for(int i = 0; i < slots2.length; i++) {
				for(int j = 0; j < slots2[i].length; j++) {
					RectInt slotRect2 = slots2[i][j].slotRect.getRectInt();
					slots2[i][j].item.renderFont(slotRect2.x2()-INV_SLOT_SIZE/2, slotRect2.y2()-INV_SLOT_SIZE/2, batch);
					
				}
			}
			
			Sprites.arial24.draw(batch, "Furnace", 304, 108);
			Sprites.arial10.draw(batch, "Fuel", 508, 562);
			Sprites.arial10.draw(batch, "Queue", 504, 461);
			Sprites.arial10.draw(batch, "Output", 662, 474);
		}else if(Game.currentState.type == State.CAULDRON) {
			CauldronTile cauldronTile = (CauldronTile) Game.level.activeBlocks[inv.currentInvActiveBlockX][inv.currentInvActiveBlockY].layers[OBJECT_LAYER];
			RectInt slotRect = cauldronTile.craftedSlot[0].slotRect.getRectInt();
			cauldronTile.craftedSlot[0].item.renderLargeFont(slotRect.x2()-INV_SLOT_SIZE/2, slotRect.y2()-INV_SLOT_SIZE/2, batch);
			
			Slot[][]slots2 = {inv.invClothing, cauldronTile.cauldronSlots, cauldronTile.fuelSlot};
			for(int i = 0; i < slots2.length; i++) {
				for(int j = 0; j < slots2[i].length; j++) {
					RectInt slotRect2 = slots2[i][j].slotRect.getRectInt();
					slots2[i][j].item.renderFont(slotRect2.x2()-INV_SLOT_SIZE/2, slotRect2.y2()-INV_SLOT_SIZE/2, batch);
					
				}
			}
			Sprites.arial24.draw(batch, "Cauldron", 304, 108);
			Sprites.arial10.draw(batch, "Fuel", 508, 562);
			Sprites.arial10.draw(batch, "Queue", 504, 461); //-2, -38
			Sprites.arial10.draw(batch, "Output", 662, 474);
		}else {
			Sprites.arial24.draw(batch, inv.menu2Title, 304, 108);
		}
		Sprites.arial24.draw(batch, inv.menu1Title, 104, 108); //53, 32
		Sprites.arial24.draw(batch, "Character", 526, 108);
		Sprites.arial24.draw(batch, "Workspace", 521, 388);
		Sprites.arial10.draw(batch, "Armor", 603, 168);
		Sprites.arial10.draw(batch, Game.hero.armor+" Defense", 522, 342);
		//Sprites.arial24.draw(batch, inv.menu2Title, 288, 32);
		
		//inv.menu1Title = "Crafting";
		
	}
	
	public void renderCraftingText(Inventory inv, SpriteBatch batch) {
		Slot[][] slots = new Slot[][]{inv.craftingMenu.craftSlots};
		//
		for(int i = 0; i < slots.length; i++) {
			for(int j = 0; j < slots[i].length; j++) {

				RectInt slotRect = slots[i][j].slotRect.getRectInt();
				slots[i][j].item.renderFont(slotRect.x2()-INV_SLOT_SIZE/2, slotRect.y2()-INV_SLOT_SIZE/2, batch);
			}
		}
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
			Sprites.pixel.draw(new Rect(new Rect(x, y, CHARACTER_SIZE, CHARACTER_SIZE), h.margin), Z_CHARACTER);
			//Sprites.pixel.draw(new Rect(x + h.margin.left, y+ h.margin.top, CHARACTER_SIZE-h.margin.right, CHARACTER_SIZE- h.margin.bottom), Z_CHARACTER);
			
			Sprites.pixel.setColor(Color.YELLOW);
			if(h.collisionDetectionBlocks[0] != null) {
				for(int i = 0; i < h.collisionDetectionBlocks.length; i++) {
					if(h.collisionDetectionBlocks[i].sRect != null && h.collisionDetectionBlocks[i].isCollidable()) {
						Sprites.pixel.draw(h.collisionDetectionBlocks[i].sRect, Z_COLLISION_RECTS + 0.01f);
						//RectInt colRect = h.collisionDetectionBlocks[i].sRect.getRectInt();
						//Sprites.pixel.draw(colRect.x,colRect.y, Z_COLLISION_RECTS + 0.01f,colRect.w,colRect.h);
					}
				}
			}
			Sprites.pixel.setColor(Color.WHITE);
		}
	}
	
	public void renderMobCollisionRects(Actor h, int x, int y) {
		if(Game.debugMenu.collisionRectsAreOn)  {
			
			Sprites.pixel.setColor(Color.YELLOW);
//			if(h.collisionDetectionBlocks[0] != null) {
//				for(int i = 0; i < h.collisionDetectionBlocks.length; i++) {
////					if(h.collisionDetectionBlocks[i].sRect != null && h.collisionDetectionBlocks[i].isCollidable()) {
//					//if(h.collisionDetectionBlocks[i].sRect != null) {
//					RectInt colRect = h.collisionDetectionBlocks[i].absRect.getRectInt();
//						Sprites.pixel.draw(colRect.x-startX,colRect.y-startY, Z_COLLISION_RECTS + 0.01f,colRect.w,colRect.h);
//					//}
//				}
//			}
			
			Sprites.pixel.setColor(1f, 0f,0f,0.7f);
			//Sprites.pixel.draw(new Rect(new Rect(x, y, CHARACTER_SIZE, CHARACTER_SIZE), h.margin), Z_CHARACTER);
			Sprites.pixel.draw(new Rect(x + h.margin.left, y+ h.margin.top, h.absRect.w-h.margin.right-h.margin.left, h.absRect.h- h.margin.bottom- h.margin.top), Z_CHARACTER);
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
			Sprites.pixel.draw(x, y, Z_COLLISION_TILES, TILE_SIZE * Game.pixelModifier, TILE_SIZE * Game.pixelModifier);
			Sprites.pixel.setColor(Color.WHITE);
		}
	}
}
