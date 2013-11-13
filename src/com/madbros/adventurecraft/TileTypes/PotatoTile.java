package com.madbros.adventurecraft.TileTypes;

import static com.madbros.adventurecraft.Constants.*;

import java.util.Random;

import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Utils.Margin;
import com.madbros.adventurecraft.Utils.Rect;

public class PotatoTile extends CollisionTile {
	
	public PotatoTile() {
		super();
		currentSpriteId = 0;
		sprites = Sprites.spriteCollections.get(Sprites.POTATO_PLANT);
		margin = new Margin(9, 9, 12, 11);
		id = POTATO_TILE;
		layer = OBJECT_LAYER;
		z = Z_OBJECT;
		isDiggable = false;
		isAutoTileable = false;
		autoTile = 0;
		isBreakable = true;
	}
	
	@Override
	public void render(int x, int y) {
		sprites[currentSpriteId].draw(x, y, z);
	}
	
	public Tile createNew() {
		return new PotatoTile();
	}
	
	@Override
	public void update(int x, int y) {
		if(currentSpriteId != 3) {
			if(Time.getTime() - timeCreated > 2000) {// 3000
				if(currentSpriteId == 0) {
					timeCreated = Time.getTime();
					currentSpriteId = 1;
				} else if (currentSpriteId == 1) {
					timeCreated = Time.getTime();
					currentSpriteId = 2;
				} else if (currentSpriteId == 2) {
					timeCreated = Time.getTime();
					currentSpriteId = 3;
				} 
			}
		}
	}
	
	public void deleteMe(int x, int y, Block[][] activeBlocks) {
		Block b = activeBlocks[x][y];
		b.layers[OBJECT_LAYER] = new NoTile();
		Random rnd = new Random();
		int dropAmount1 = rnd.nextInt(2)+1;
		int dropAmount2 = rnd.nextInt(1)+1;
		if(currentSpriteId == 3) {
			for(int i = dropAmount1; i >-1 ; i--) {
				Rect collectibleRect = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
				Game.collectibleController.add(SEED_POTATO, Sprites.sprites.get(Sprites.SEED_POTATO), collectibleRect, 1);
			}
			for(int i = dropAmount2; i >-1 ; i--) {
				Rect collectibleRect = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
				Game.collectibleController.add(POTATOES, Sprites.sprites.get(Sprites.POTATOES), collectibleRect, 1);
			}
		} else {
			Rect collectibleRect = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
			Game.collectibleController.add(SEED_POTATO, Sprites.sprites.get(Sprites.SEED_POTATO), collectibleRect, 1);
		}
	}
}
