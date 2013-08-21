package com.madbros.adventurecraft.TileTypes;

import static com.madbros.adventurecraft.Constants.*;

import org.newdawn.slick.Color;

import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Utils.Helpers;
import com.madbros.adventurecraft.Utils.Rect;

public abstract class Tile {
	public StaticSprite[] textures;
	public int maxHp = 10;
	public int currentHp = 10;
	public int currentTexture = 0;
	public int layer = DARK_DIRT_LAYER;
	public float z = Z_DARK_DIRT;
	public boolean isCollidable = false;
	public boolean isDiggable = true;
	public boolean isChoppable = false;
	public long timeCreated = Time.getTime();
	
	public int id = AIR;
	public boolean isMiddleTile = true;
	public int topLeftAutoTile = MIDDLE_TILE;
	public int topRightAutoTile = MIDDLE_TILE;
	public int bottomRightAutoTile = MIDDLE_TILE;
	public int bottomLeftAutoTile = MIDDLE_TILE;
	
	public void render(int x, int y) {
		int size = TILE_SIZE/2;		
		textures[topLeftAutoTile].draw(x, y, z, size, size);
		textures[topRightAutoTile].draw(x+size, y, z, size, size);
		textures[bottomLeftAutoTile].draw(x, y+size, z, size, size);
		textures[bottomRightAutoTile].draw(x+size, y+size, z, size, size);
	}
	
	public void renderHp(int x, int y) {
		int w = 20; int h = 4;
		int sX = x+TEXTURE_SIZE - 10; int sY = y - h*2;
		new Color(0.0f, 1.0f, 0.0f).bind();
		Textures.pixel.draw(sX+1, sY+1, Z_HEALTHBAR, Math.round((currentHp * 1.0f / maxHp) * w-2), h-2);
		Color.white.bind();
		Helpers.drawRect(new Rect(sX, sY, w, h), Z_HEALTHBAR);
	}
	
	public abstract Tile createNew();
	
	public void update(int x, int y) {
		
	}
}
