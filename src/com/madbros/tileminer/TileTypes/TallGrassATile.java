package com.madbros.tileminer.TileTypes;

import static com.madbros.tileminer.Constants.*;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.openal.Wav.Sound;
import com.madbros.tileminer.*;
import com.madbros.tileminer.Items.Item;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Margin;
import com.madbros.tileminer.Utils.Rect;
import com.madbros.tileminer.Utils.RectInt;

public class TallGrassATile extends CollisionTile {
	
	public TallGrassATile() {
		super();
		currentSpriteId = 0;
		sprites = Sprites.spriteCollections.get(Sprites.TALL_GRASS_A);
		margin = new Margin(9, 9, 12, 11);
		id = TALL_GRASS_A_TILE;
		layer = OBJECT_LAYER;
		z = Z_OBJECT;
		isDiggable = false;
		isAutoTileable = false;
		autoTile = 0;
		isBreakable = true;
		currentHp = 1;
		maxHp = 1;
		isCollidable = false;
		particleEffect = "grassChunks.p";
	}
	
	@Override
	public void render(int x, int y) {
		sprites[currentSpriteId].draw(x, y, z);
	}
	
	public Tile createNew() {
		return new TallGrassATile();
	}
	
	@Override
	public void update(int x, int y) {
		if(Game.hero.isAttacking) {
			Rect wRect = new Rect(Game.hero.absRect.x+ Game.hero.attackItem.cRectFinal.x, Game.hero.absRect.y + Game.hero.attackItem.cRectFinal.y, Game.hero.attackItem.cRectFinal.w,Game.hero.attackItem.cRectFinal.h);

			if(cRect.detectCollision(wRect)) {
				deleteMe(x,y, Game.level.activeBlocks);
			}
			//RectInt hBAbsRect = Game.level.highlightedBlock.absRect.getRectInt();
			
		}
	}
	
	public void deleteMe(int x, int y, Block[][] activeBlocks) {
		Block b = activeBlocks[x][y];
		b.layers[OBJECT_LAYER] = new NoTile();
		b.collisionTile = null;
		b.layers[OBJECT_LAYER].setCollisionRect(b.absRect);
		RectInt hBAbsRect = b.absRect.getRectInt();
		Game.particleEffectController.add(particleEffect, 
				hBAbsRect.x +(TILE_SIZE/2), hBAbsRect.y + (TILE_SIZE/2));
		Sound test = (Sound) Gdx.audio.newSound(Gdx.files.internal("sounds/grassSlash.wav"));
		boolean isSoundAlreadyPlaying = false;
		for(int a = 0; a < Game.soundController.sounds.size(); a++) {
			Sound sound = Game.soundController.sounds.get(a);
			if(sound.duration() == test.duration()) {
				isSoundAlreadyPlaying = true;
			}
		}
		if(isSoundAlreadyPlaying == false) {
			Game.soundController.create("sounds/grassSlash.wav", 0.2f);
		}
		
		Random rand = new Random();
		int num = rand.nextInt(10);//150 
		
		if(num == 0) {
			Rect collectibleRect = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
			Item item = ITEM_HASH.get(TALL_GRASS_A).createNew();
			Game.collectibleController.add(TALL_GRASS_A, Sprites.sprites.get(Sprites.TALL_GRASS_A_ITEM), collectibleRect, 1, item.maxUses);
		}
		
	}
}
