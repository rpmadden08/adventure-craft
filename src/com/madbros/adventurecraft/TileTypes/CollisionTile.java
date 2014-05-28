package com.madbros.adventurecraft.TileTypes;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.GameObjects.Actor;
import com.madbros.adventurecraft.Utils.Margin;
import com.madbros.adventurecraft.Utils.Rect;

public abstract class CollisionTile extends Tile {
	
	public int arrayX;
	public int arrayY;
	
	public CollisionTile() {
		isCollidable = true;
	}
	
	public void heroDidCollide(Actor actor, int dir, int move, Rect charCRect, Rect tileRect) {
		int extra;
		
		switch(dir) {
		case DOWN:
			extra = move - charCRect.getBottomCollisionDiff(tileRect);
			actor.yMove(-move + extra);
			break;
		case UP:
			extra = move - charCRect.getTopCollisionDiff(tileRect);
			actor.yMove(-move + extra);
			break;
		case LEFT:
			extra = move - charCRect.getLeftCollisionDiff(tileRect);
			actor.xMove(-move + extra);
			break;
		case RIGHT:
			extra = move - charCRect.getRightCollisionDiff(tileRect);
			actor.xMove(-move + extra);
			break;
		}
		
	}

	public void setArrayPos(int x, int y) {
		arrayX = x;
		arrayY = y;
	}
	
	public void setCollisionRect() {
		cRect = new Rect(absRect, margin);
	}
	
//	public Rect getCollisionRect() {
//		return new Rect(arrayX * TILE_SIZE + margin.left, arrayY * TILE_SIZE + margin.top, 
//						TILE_SIZE - margin.left - margin.right, TILE_SIZE - margin.top - margin.bottom);
//	}
	
	public abstract Tile createNew();
}
