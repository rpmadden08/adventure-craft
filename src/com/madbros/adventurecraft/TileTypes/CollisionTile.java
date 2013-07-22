package com.madbros.adventurecraft.TileTypes;

import static com.madbros.adventurecraft.Constants.DOWN;
import static com.madbros.adventurecraft.Constants.LEFT;
import static com.madbros.adventurecraft.Constants.RIGHT;
import static com.madbros.adventurecraft.Constants.UP;

import com.madbros.adventurecraft.Character;
import com.madbros.adventurecraft.Margin;
import com.madbros.adventurecraft.Rect;
import com.madbros.adventurecraft.Tile;

public abstract class CollisionTile extends Tile {
	public Margin margin;
	public CollisionTile() {
		isCollidable = true;
	}
	
	public void characterDidCollide(int dir, int move, Rect charCRect, Rect tileRect, Character c) {
		int extra;
		switch(dir) {
		case DOWN: 
			extra = move - charCRect.getBottomCollisionDiff(tileRect);
			c.yMove(-move + extra);
			break;
		case UP:
			extra = move - charCRect.getTopCollisionDiff(tileRect);
			c.yMove(-move + extra);
			break;
		case LEFT:
			extra = move - charCRect.getLeftCollisionDiff(tileRect);
			c.xMove(-move + extra);
			break;
		case RIGHT:
			extra = move - charCRect.getRightCollisionDiff(tileRect);
			c.xMove(-move + extra);
			break;
		}
	}

	public abstract Tile createNew();
}
