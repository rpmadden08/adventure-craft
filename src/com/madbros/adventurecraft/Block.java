package com.madbros.adventurecraft;

import static com.madbros.adventurecraft.Constants.*;

import java.util.ArrayList;

import com.madbros.adventurecraft.GameObjects.GameObject;
import com.madbros.adventurecraft.TileTypes.*;
import com.madbros.adventurecraft.Utils.*;

public class Block {
	public ArrayList<GameObject> objects = new ArrayList<GameObject>();
	public Tile[] layers;
	public CollisionTile collisionTile;
	public Rect sRect;	//screen rect positions - only used for collision detection debugging (see character collision)
	public Rect absRect;	//absolute rect positions

	public boolean canPlace = true;		//FIXME: if we scrap dark dirt, do we still need this?
	
	public Rect cRect;	//the collision detection rect for colidable blocks (this rect acounts for any offsets)
	public float noise;
	public boolean isUnfinished = false;

	public Long timePlaced= Time.getTime();
	
	public boolean isHighlighted = false;
	public boolean isHighlightedDebug = false;
	
	public Block(Tile tile, int absX, int absY, boolean isUnfinished) {
		this(new Tile[]{tile}, absX, absY, isUnfinished);
	}
	
	public int getX(Block[][] activeBlocks) {
		int x = (absRect.x - activeBlocks[0][0].absRect.x) / TILE_SIZE;
		//int y = (absRect.y - Game.level.activeBlocks[0][0].absRect.y) / TILE_SIZE;
		return x;
	}
	public int getY(Block[][] activeBlocks) {
		int y = (absRect.y - activeBlocks[0][0].absRect.y) / TILE_SIZE;
		return y;
	}
	
	
	public Block(Tile[] t, int absX, int absY, boolean isUnfinished) {
		absRect = new Rect(absX, absY);
		this.isUnfinished = isUnfinished;
		
		layers = new Tile[23];
		layers[DARK_DIRT_LAYER] = t[DARK_DIRT_LAYER];
		layers[LIGHT_DIRT_LAYER] = t[LIGHT_DIRT_LAYER];
		layers[GRASS_LAYER] = t[GRASS_LAYER];
		layers[WATER_LAYER] = t[WATER_LAYER];
		layers[OBJECT_LAYER] = t[OBJECT_LAYER];
		layers[TREE_LEFT_0] = t[TREE_LEFT_0];
		layers[TREE_CENTER_0] = t[TREE_CENTER_0];
		layers[TREE_RIGHT_0] = t[TREE_RIGHT_0];
		layers[ABOVE_LAYER_1] = t[ABOVE_LAYER_1];
		layers[TREE_LEFT_1] = t[TREE_LEFT_1];
		layers[TREE_CENTER_1] = t[TREE_CENTER_1];
		layers[TREE_RIGHT_1] = t[TREE_RIGHT_1];
		layers[ABOVE_LAYER_2] = t[ABOVE_LAYER_2];
		layers[TREE_LEFT_2] = t[TREE_LEFT_2];
		layers[TREE_CENTER_2] = t[TREE_CENTER_2];
		layers[TREE_RIGHT_2] = t[TREE_RIGHT_2];
		layers[ABOVE_LAYER_3] = t[ABOVE_LAYER_3];
		layers[TREE_LEFT_3] = t[TREE_LEFT_3];
		layers[TREE_CENTER_3] = t[TREE_CENTER_3];
		layers[TREE_RIGHT_3] = t[TREE_RIGHT_3];
		layers[ABOVE_LAYER_4] = t[ABOVE_LAYER_4];
		layers[ABOVE_LAYER_5] = t[ABOVE_LAYER_5];
		layers[ABOVE_LAYER_6] = t[ABOVE_LAYER_6];
		
		
		if(t[WATER_LAYER].isCollidable) setCollisionTile((CollisionTile)t[WATER_LAYER]);
		else if(t[OBJECT_LAYER].isCollidable) setCollisionTile((CollisionTile)t[OBJECT_LAYER]);

		if(t[WATER_LAYER].isCollidable) {
			collisionTile = (CollisionTile)t[WATER_LAYER];
			cRect = new Rect(absRect, collisionTile.margin);
		} else if(t[OBJECT_LAYER].isCollidable) {
			collisionTile = (CollisionTile)t[OBJECT_LAYER];
			cRect = new Rect(absRect, collisionTile.margin);
		}
	}
	
//	public void render(int x, int y) {
//		Tile[] renderTiles = getRenderTiles();
//		
//		for(int i = 0; i < renderTiles.length;i++) {
//			if(renderTiles[i].id == TREE_LEAF) ((TreeLeafTile)renderTiles[i]).render(x, y, i);
//			else renderTiles[i].render(x, y);
//		}
//		
//		Tile topTile = getTopTile();
//		
//		if(isHighlighted) {
//			if(topTile.currentHp < topTile.maxHp) topTile.renderHp(x, y);
//			Color highlightColor = new Color(0.8f, 0.8f, 0.8f, 1.0f);
//			topTile.sprites[topTile.currentSpriteId].setColor(highlightColor);
//			topTile.render(x, y);
//			topTile.sprites[topTile.currentSpriteId].setColor(Color.WHITE);
//		}
//		
//		if(Game.debugMenu.collisionTilesAreOn && Arrays.asList(Game.hero.collisionDetectionBlocks).contains(this)) {
//			Color highlightColor = new Color(1, 1, 1, 0.2f);
//			Sprites.pixel.setColor(highlightColor);
//			Sprites.collisionDebugger.draw(x, y, Z_COLLISION_TILES, TILE_SIZE * Game.pixelModifier, TILE_SIZE * Game.pixelModifier);
//			Sprites.pixel.setColor(Color.WHITE);
//		}
//		
//		if(Game.debugMenu.collisionRectsAreOn && isCollidable()) {
//			Rect r = new Rect((int)x, (int)y);
//			r = new Rect(r, collisionTile.margin);
//			
//			Color highlightColor = new Color(0, 0, 1f, 0.6f);
//			Sprites.pixel.setColor(highlightColor);
//			
//			Sprites.pixel.draw(r, Z_COLLISION_RECTS);
//			Sprites.pixel.setColor(Color.WHITE);
//		}
//	}
	
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
				setCollisionTile((CollisionTile)layers[WATER_LAYER]);
			}
		}
	}
	
	//returns all visable layers
	public Tile[] getRenderTiles() {
		Tile[] tiles = new Tile[23];
		tiles[ABOVE_LAYER_6] = layers[ABOVE_LAYER_6];
		tiles[ABOVE_LAYER_5] = layers[ABOVE_LAYER_5];
		tiles[ABOVE_LAYER_4] = layers[ABOVE_LAYER_4];
		tiles[ABOVE_LAYER_3] = layers[ABOVE_LAYER_3];
		tiles[ABOVE_LAYER_2] = layers[ABOVE_LAYER_2];
		tiles[TREE_LEFT_0] = layers[TREE_LEFT_0];
		tiles[TREE_CENTER_0] = layers[TREE_CENTER_0];
		tiles[TREE_RIGHT_0] = layers[TREE_RIGHT_0];
		tiles[TREE_LEFT_1] = layers[TREE_LEFT_1];
		tiles[TREE_CENTER_1] = layers[TREE_CENTER_1];
		tiles[TREE_RIGHT_1] = layers[TREE_RIGHT_1];
		tiles[TREE_LEFT_2] = layers[TREE_LEFT_2];
		tiles[TREE_CENTER_2] = layers[TREE_CENTER_2];
		tiles[TREE_RIGHT_2] = layers[TREE_RIGHT_2];
		tiles[TREE_LEFT_3] = layers[TREE_LEFT_3];
		tiles[TREE_CENTER_3] = layers[TREE_CENTER_3];
		tiles[TREE_RIGHT_3] = layers[TREE_RIGHT_3];
		tiles[ABOVE_LAYER_1] = layers[ABOVE_LAYER_1];
		tiles[OBJECT_LAYER] = layers[OBJECT_LAYER];
		
		boolean middleTileReached = false;
		for(int i = WATER_LAYER; i > -1; i--) {
			if(layers[i].id == AIR || middleTileReached) tiles[i] = new NoTile();
			else {
				tiles[i] = layers[i];
				if(layers[i].isMiddleTile) middleTileReached = true;
			}
		}
		return tiles;
	}
	
	public void setCollisionTile(CollisionTile t) {
		collisionTile = t;
		collisionTile.setCollisionRect(absRect);
	}

	public void detectCollisions() {
		//if this block is collidabe, add it to the collidableEntities array
		//loop through all collidableEntities to detect collisions
	}
}
