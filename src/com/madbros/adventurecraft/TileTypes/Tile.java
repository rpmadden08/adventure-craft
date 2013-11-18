package com.madbros.adventurecraft.TileTypes;

import static com.madbros.adventurecraft.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Sprites.Sprite;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Utils.Helpers;
import com.madbros.adventurecraft.Utils.Rect;

public abstract class Tile {
	public Sprite[] sprites;
	public int maxHp = 10;
	public int currentHp = 10;

	public int currentSpriteId = 0;
	public int layer = DARK_DIRT_LAYER;
	public float z = Z_DARK_DIRT;
	public boolean isCollidable = false;
	public boolean isDiggable = true;
	public boolean isChoppable = false;
	public boolean isTillable = false;
	public boolean isBreakable = false; //This is for items breakable by hand
	public long timeCreated = Time.getTime();
	public boolean isLightSource = false;
	public boolean isAutoTileable = true;
	public boolean isUseable = false;
	
	public int id = AIR;
	public int autoTile = MIDDLE_TILE;
	public boolean isMiddleTile = true;
	public boolean isTreeLeafTile = false; //When it renders treeleaftiles act differently.  
//	public int topLeftAutoTile = MIDDLE_TILE;
//	public int topRightAutoTile = MIDDLE_TILE;
//	public int bottomRightAutoTile = MIDDLE_TILE;
//	public int bottomLeftAutoTile = MIDDLE_TILE;
	
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
		Game.renderSystem.renderTileHealth(this, x, y);
	}
	
	public void bloom(int x,int y, Block[][] activeBlocks) {
		
	}
	
	public void rightClicked() {
		
	}
	
	public abstract Tile createNew();
	
	public void update(int x, int y) {
		
	}
	
	public void deleteMe(int x, int y, Block[][] activeBlocks) {}
	
}
