package com.madbros.adventurecraft.TileTypes;

import static com.madbros.adventurecraft.Constants.*;
import com.madbros.adventurecraft.GameObjects.Actor;
import com.madbros.adventurecraft.Utils.Margin;
import com.madbros.adventurecraft.Utils.Rect;

public abstract class CollisionTile extends Tile {
	public Margin margin;
	public Rect cRect;
	
	public CollisionTile() {
		isCollidable = true;
	}
	
	public void heroDidCollide(Actor actor, int dir, int move, Rect charCRect, Rect tileRect) {
		int extra;
		switch(dir) {
		case DOWN:
			extra = move - charCRect.getBottomCollisionDiff(tileRect);
			actor.yMove(-move);
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
			actor.xMove(-move);
			break;
		}
	}

	public void setCollisionRect(Rect absRect) {
		cRect = new Rect(absRect, margin);
	}
	
	public abstract Tile createNew();
}
