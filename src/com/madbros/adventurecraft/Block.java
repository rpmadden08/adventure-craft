package com.madbros.adventurecraft;

import static com.madbros.adventurecraft.Constants.TILE_SIZE;

import java.util.Arrays;

import org.newdawn.slick.Color;

import com.madbros.adventurecraft.TileTypes.CollisionTile;

public class Block {
	Tile[] tiles;
	public Tile baseTile;
	public CollisionTile collisionTile;	//only one tile per block can be a collision tile
	public Rect sRect;	//screen rect positions - only used for collision detection debugging (see character collision)
	public Rect aRect;	//absolute rect positions
	public Rect cRect;	//the collision detection rect for colidable blocks (this rect acounts for any offsets)
	public float mapHeight;
	
	public boolean isCollidable = false;
	
	public Block(Tile tile, int absX, int absY) {
		this(new Tile[]{tile}, absX, absY);
	}
	
	public Block(Tile[] t, int absX, int absY) {
		tiles = t;
		aRect = new Rect(absX, absY);
		
		for(int i = 0; i < t.length; i++) {
			if(t[i].isCollidable) {
				isCollidable = true;
				collisionTile = (CollisionTile)t[i];
				cRect = new Rect(aRect, collisionTile.margin);
			}
			if(t[i].isBaseTile) {
				baseTile = t[i];
			}
		}
	}
	
	public void render(int x, int y) {
		for(int i = 0; i < tiles.length; i++) {
			tiles[i].render(x, y);
		}
		
		if(Game.debugger.collisionTilesAreOn && Arrays.asList(Game.character.collisionDetectionBlocks).contains(this)) {
			Color highlightColor = new Color(1, 1, 1, 0.2f);
			highlightColor.bind();
			Textures.collisionDebugger.draw(x, y, TILE_SIZE * Game.pixelModifier, TILE_SIZE * Game.pixelModifier);
			Color.white.bind();
		}
		
		if(Game.debugger.collisionRectsAreOn && isCollidable) {
			Rect r = new Rect((int)x, (int)y);
			r = new Rect(r, collisionTile.margin);
			
			Color highlightColor = new Color(0, 0, 1f, 0.6f);
			highlightColor.bind();
			Textures.pixel.draw(r);
			
			Color.white.bind();
		}
	}
}
