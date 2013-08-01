package com.madbros.adventurecraft;

import static com.madbros.adventurecraft.Constants.*;

import java.util.Arrays;

import org.newdawn.slick.Color;

import com.madbros.adventurecraft.TileTypes.*;
import com.madbros.adventurecraft.Utils.*;

public class Block {
//	public Tile[] tiles;
	public Tile[] layers;
//	public Tile baseTile;
//	public CollisionTile collisionTile;	//only one tile per block can be a collision tile
	public Rect sRect;	//screen rect positions - only used for collision detection debugging (see character collision)
	public Rect aRect;	//absolute rect positions
	public Rect cRect;	//the collision detection rect for colidable blocks (this rect acounts for any offsets)
//	public float mapHeight;
	
//	public boolean isCollidable = false;
	public boolean isHighlighted = false;
	
	public Block(Tile tile, int absX, int absY) {
		this(new Tile[]{tile}, absX, absY);
	}
	
	public Block(Tile[] t, int absX, int absY) {
//		tiles = t;
		aRect = new Rect(absX, absY);
		
		layers = new Tile[4];
			layers[DARK_DIRT_LAYER] = t[DARK_DIRT_LAYER];
			layers[LIGHT_DIRT_LAYER] = t[LIGHT_DIRT_LAYER];
			layers[GRASS_LAYER] = t[GRASS_LAYER];
			layers[WATER_LAYER] = t[WATER_LAYER];
			
			if(t[WATER_LAYER].id != AIR) {
				cRect = new Rect(aRect, ((CollisionTile)t[WATER_LAYER]).margin);
			}
	}
	
	public void render(int x, int y) {
		for(int i = 0; i < layers.length; i++) {
			layers[i].render(x, y);
		}
		
		if(isHighlighted) {
			Color highlightColor = new Color(0.8f, 0.8f, 0.8f, 1.0f);
			highlightColor.bind();
			Helpers.drawRect(new Rect(x, y, TILE_SIZE, TILE_SIZE));
			Color.white.bind();
		}
		
		if(Game.debugMenu.collisionTilesAreOn && Arrays.asList(Game.hero.collisionDetectionBlocks).contains(this)) {
			Color highlightColor = new Color(1, 1, 1, 0.2f);
			highlightColor.bind();
			Textures.collisionDebugger.draw(x, y, TILE_SIZE * Game.pixelModifier, TILE_SIZE * Game.pixelModifier);
			Color.white.bind();
		}
		
		if(Game.debugMenu.collisionRectsAreOn && isCollidable()) {
			Rect r = new Rect((int)x, (int)y);
			r = new Rect(r, ((CollisionTile) layers[3]).margin);
			
			Color highlightColor = new Color(0, 0, 1f, 0.6f);
			highlightColor.bind();
			Textures.pixel.draw(r);
			
			Color.white.bind();
		}
	}
	
	public boolean isCollidable() {
		for(int i = 0; i < layers.length; i++) {
			if(layers[i].isCollidable) return true;
		}
		return false;
	}
}
