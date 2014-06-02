package com.madbros.adventurecraft.TileTypes;

import static com.madbros.adventurecraft.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Items.Item;
import com.madbros.adventurecraft.Sprites.Sprite;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Utils.Helpers;
import com.madbros.adventurecraft.Utils.Margin;
import com.madbros.adventurecraft.Utils.Rect;

public abstract class Tile {
	public Sprite[] sprites;
	public int maxHp = 10;
	public int currentHp = 10;
	
	public boolean is32 = true;
	public int currentSpriteId = 0;
	public int layer = DARK_DIRT_LAYER;
	public float z = Z_DARK_DIRT;
	public boolean isVisible = true;
	public boolean isCollidable = false;
	public boolean isPickable = false;
	public boolean isDiggable = true;
	public boolean isChoppable = false;
	public boolean isTillable = false;
	public boolean isBreakable = false; //This is for items breakable by hand
	public long timeCreated = Time.getGameTime();
	public boolean isLightSource = false;
	public boolean isAutoTileable = true;
	public boolean isUseable = false;
	public int absX = 0;
	public int absY = 0;
	public int activeBlocksX = 0;
	public int activeBlocksY = 0;
	public int id = AIR;
	public int autoTileID = id;
	public int autoTile = MIDDLE_TILE;
	public boolean isMiddleTile = true;
	public boolean isTreeLeafTile = false; //When it renders treeleaftiles act differently. 
	public Margin margin = new Margin(0,0,0,0);
	public Rect absRect = new Rect(absX, absY, 32, 32);
	public Rect cRect = new Rect(absRect, margin);
	public int topLeftAutoTile = 0;
	public int topRightAutoTile = 0;
	public int bottomRightAutoTile = 0;
	public int bottomLeftAutoTile = 0;
	public String particleEffect = "chunks.p";
	
//	public Tile() {
//		
//	}
	
	public void render(int x, int y) {
		sprites[autoTile].draw(x, y, z);
	}
	
	public void renderHp(int x, int y) {
		int w = 20; int h = 4;
		int sX = x+TEXTURE_SIZE - 10; int sY = y - h*2;
		Sprites.pixel.setColor(Color.GREEN);
		
		Sprites.pixel.draw(sX+1, sY+1, Z_HEALTHBAR, Math.round((currentHp * 1.0f / maxHp) * w-2), h-2);
		Sprites.pixel.setColor(Color.WHITE);
		Helpers.drawRect(new Rect(sX, sY, w, h), Z_HEALTHBAR);
		Item item = ITEM_HASH.get(Game.inventory.invBar[Game.inventory.itemSelected].item.id).createNew();
		Game.renderSystem.renderTileHealth(this, x, y, item.is32);
	}
	
	public void bloom(int x,int y, Block[][] activeBlocks) {
		
	}
	
	public void rightClicked() {
		
	}
	
	public abstract Tile createNew();
	
	public void update(int x, int y) {
		
	}
	
	public void deleteMe(int x, int y, Block[][] activeBlocks) {}
	
	public void deleteMeStairs(int x, int y, Block[][] activeBlocks) {}
	
	public void deleteThisTile(int x, int y, Block[][] activeBlocks) {
		if(is32 == true) {
			deleteMe(x,y, activeBlocks);
		} else {
			int finalX = x;
			int finalY = y;
			if(x % 2 == 0 && y % 2 == 0) {

			} else if (x % 2 == 1 && y % 2 == 0) {
				finalX= x-1;
			} else if (x % 2 == 0 && y % 2 == 1) {
				finalY = y-1;
			} else {
				finalX = x-1;
				finalY = y-1;
			}
			activeBlocks[finalX][finalY].layers[OBJECT_LAYER].deleteMe(finalX, finalY, activeBlocks);
			activeBlocks[finalX+1][finalY].layers[OBJECT_LAYER].deleteMe(finalX+1, finalY, activeBlocks);
			activeBlocks[finalX][finalY+1].layers[OBJECT_LAYER].deleteMe(finalX, finalY+1, activeBlocks);
			activeBlocks[finalX+1][finalY+1].layers[OBJECT_LAYER].deleteMe(finalX+1, finalY+1, activeBlocks);
			
			activeBlocks[finalX][finalY].layers[OBJECT_LAYER] = new NoTile();
			activeBlocks[finalX+1][finalY].layers[OBJECT_LAYER] = new NoTile();
			activeBlocks[finalX][finalY+1].layers[OBJECT_LAYER] = new NoTile();
			activeBlocks[finalX+1][finalY+1].layers[OBJECT_LAYER] = new NoTile();
			
			activeBlocks[finalX][finalY].collisionTile = null;
			activeBlocks[finalX+1][finalY].collisionTile = null;
			activeBlocks[finalX][finalY+1].collisionTile = null;
			activeBlocks[finalX+1][finalY+1].collisionTile = null;
			
			activeBlocks[finalX][finalY].layers[ABOVE_LAYER_1].deleteMe(finalX, finalY, activeBlocks);
			activeBlocks[finalX+1][finalY].layers[ABOVE_LAYER_1].deleteMe(finalX+1, finalY, activeBlocks);
			activeBlocks[finalX][finalY+1].layers[ABOVE_LAYER_1].deleteMe(finalX, finalY+1, activeBlocks);
			activeBlocks[finalX+1][finalY+1].layers[ABOVE_LAYER_1].deleteMe(finalX+1, finalY+1, activeBlocks);
			
			Game.level.autoTileBlock(finalX, finalY);
			
		}
	}
	
	public void highlightEntireObject(int x, int y, int drawX, int drawY) {
		
	}
	
	public void setCollisionRect(Rect absRect) {
		cRect = new Rect(absRect, margin);
	}

	public boolean updateStairs(int x, int y) {
		return true;
	}
}
