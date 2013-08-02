package com.madbros.adventurecraft;

import static com.madbros.adventurecraft.Constants.*;

import java.util.Arrays;

import org.newdawn.slick.Color;

import com.madbros.adventurecraft.TileTypes.*;
import com.madbros.adventurecraft.Utils.*;

public class Block {
	public Tile[] layers;
	public CollisionTile collisionTile;
	public Rect sRect;	//screen rect positions - only used for collision detection debugging (see character collision)
	public Rect aRect;	//absolute rect positions
	public Rect cRect;	//the collision detection rect for colidable blocks (this rect acounts for any offsets)
	
	public boolean isHighlighted = false;
	
	public Block(Tile tile, int absX, int absY) {
		this(new Tile[]{tile}, absX, absY);
	}
	
	public Block(Tile[] t, int absX, int absY) {
		aRect = new Rect(absX, absY);
		
		layers = new Tile[5];
			layers[DARK_DIRT_LAYER] = t[DARK_DIRT_LAYER];
			layers[LIGHT_DIRT_LAYER] = t[LIGHT_DIRT_LAYER];
			layers[GRASS_LAYER] = t[GRASS_LAYER];
			layers[WATER_LAYER] = t[WATER_LAYER];
			layers[OBJECT_LAYER] = t[OBJECT_LAYER];
			
			if(t[WATER_LAYER].isCollidable) {
				collisionTile = (CollisionTile)t[WATER_LAYER];
				cRect = new Rect(aRect, collisionTile.margin);
			} else if(t[OBJECT_LAYER].isCollidable) {
				collisionTile = (CollisionTile)t[OBJECT_LAYER];
				cRect = new Rect(aRect, collisionTile.margin);
			}
	}
	
	public void render(int x, int y) {
		Tile[] renderTiles = getRenderTiles();
		
		for(int i = 0; i < renderTiles.length;i++) {
			renderTiles[i].render(x, y);
		}
		
		Tile topTile = getTopTile();
		if(isHighlighted && topTile.currentHp < topTile.maxHp) topTile.renderHp(x, y);
		
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
			r = new Rect(r, collisionTile.margin);
			
			Color highlightColor = new Color(0, 0, 1f, 0.6f);
			highlightColor.bind();
			Textures.pixel.draw(r);
			
			Color.white.bind();
		}
	}
	
	public boolean isCollidable() {
		return collisionTile != null;
	}
	
	public Tile getTopTerrainTile() {
		for(int i = WATER_LAYER; i > -1; i--) {
			if(layers[i].id != AIR) return layers[i];
		}
		return new NoTile();
	}
	
	public Tile getTopTile() {
		for(int i = layers.length-1; i > -1; i--) {
			if(layers[i].id != AIR) return layers[i];
		}
		return new NoTile();
	}
	
	public void deleteTopTile() {
		for(int i = layers.length-1; i > -1; i--) {
			if(layers[i].id != AIR && layers[i].id != DARK_DIRT) { 
				layers[i] = new NoTile();
				return;
			} else if(layers[i].id == DARK_DIRT) {
				layers[WATER_LAYER] = new HoleTile();
				collisionTile = (CollisionTile)layers[WATER_LAYER];
				cRect = new Rect(aRect, collisionTile.margin);
			}
		}
	}
	
	//returns all visable layers
	public Tile[] getRenderTiles() {
		Tile[] tiles = new Tile[5];
		
		tiles[OBJECT_LAYER] = layers[OBJECT_LAYER];
		
		boolean middleTileReached = false;
		for(int i = WATER_LAYER; i > -1; i--) {
			if(layers[i].id == AIR || middleTileReached) {
				tiles[i] = new NoTile();
			} else {
				tiles[i] = layers[i];
				if(layers[i].isMiddleTile) middleTileReached = true;
			}
		}
		return tiles;
	}
}
