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
	
	public void render(int x, int y) {
		sprites[autoTile].draw(x, y, z);
//		sprites[topLeftAutoTile].draw(x, y, z);
//		sprites[topRightAutoTile].draw(x+TEXTURE_SIZE, y, z);
//		sprites[bottomLeftAutoTile].draw(x, y+TEXTURE_SIZE, z);
//		sprites[bottomRightAutoTile].draw(x+TEXTURE_SIZE, y+TEXTURE_SIZE, z);
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
	
	public void highlightEntireObject(int x, int y, int drawX, int drawY) {
		
	}
	
	public void setCollisionRect(Rect absRect) {
		cRect = new Rect(absRect, margin);
	}
}
