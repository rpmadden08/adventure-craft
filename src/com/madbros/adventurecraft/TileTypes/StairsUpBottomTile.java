package com.madbros.adventurecraft.TileTypes;
//NOTES
//With stairs up and stairs down I needed to do a hack.  The object itself is NOT collidable but I'm using collision tile because it DOES need
//the cRect attribute to calculate collisions. It's kind of a hack, but it works as long as the update function sets the collision tile to null...
import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.GameStates.LoadingState;
import com.madbros.adventurecraft.Items.Item;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Utils.Margin;
import com.madbros.adventurecraft.Utils.Rect;

public class StairsUpBottomTile extends CollisionTile {
	public Boolean hasReset = false;
	public StairsUpBottomTile() {
		super();
		currentSpriteId = 0;
		sprites = Sprites.spriteCollections.get(Sprites.STAIRS_UP_BOTTOM_TILE);
		margin = new Margin(9, 9, 9, 9);
		id = STAIRS_UP_BOTTOM_TILE;
		layer = OBJECT_LAYER;
		z = Z_OBJECT;
		isDiggable = false;
		isAutoTileable = false;
		autoTile = 0;
		isBreakable = true;
		currentHp = 1;
		maxHp = 1;
		//isCollidable = false;
	}
	
	@Override
	public void render(int x, int y) {
		sprites[currentSpriteId].draw(x, y, z);
	}
	
	public Tile createNew() {
		return new StairsUpBottomTile();
	}
	
	@Override
	public boolean updateStairs(int x, int y) {
		Game.level.activeBlocks[x][y].collisionTile = null;
		Rect finalCRect = new Rect(cRect, margin);
		if(finalCRect.detectCollision(new Rect(Game.hero.absRect, Game.hero.margin))) {
			if(hasReset == true) {

				Game.currentState = new LoadingState(Game.batch);
				
				Game.replaceableX = Game.level.activeBlocks[x][y].getAbsX();
				Game.replaceableY = Game.level.activeBlocks[x][y].getAbsY();
				Game.musicController.music.stop();
				Game.hero.stop();
				Game.switchLevel();

				Game.level.teleportHero(Game.replaceableX, Game.replaceableY);
				return false;
			}
			return true;
		} else {
			hasReset = true;
			return true;
		}
	}
	
	public void deleteMe(int x, int y, Block[][] activeBlocks) {
		Block b = activeBlocks[x][y];
		b.layers[OBJECT_LAYER] = new NoTile();
		b.collisionTile = null;
		b = activeBlocks[x][y-1];
		b.layers[ABOVE_LAYER_1] = new NoTile();
		
		
		//FIXME this needs to randomly drop basic goodies:)
		Rect collectibleRect = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
		Item item = ITEM_HASH.get(STAIRS_UP).createNew();
		Game.collectibleController.add(STAIRS_UP, Sprites.sprites.get(Sprites.STAIRS_UP), collectibleRect, 1, item.maxUses);
		
	}
}
