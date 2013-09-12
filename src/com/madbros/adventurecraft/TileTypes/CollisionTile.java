package com.madbros.adventurecraft.TileTypes;

import static com.madbros.adventurecraft.Constants.*;
import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Utils.Margin;
import com.madbros.adventurecraft.Utils.Rect;

public abstract class CollisionTile extends Tile {
	public Margin margin;
	public Rect cRect;
	
	public CollisionTile() {
		isCollidable = true;
	}
	
	public void heroDidCollide(int dir, int move, Rect charCRect, Rect tileRect) {
		int extra;
		switch(dir) {
		case DOWN:
			extra = move - charCRect.getBottomCollisionDiff(tileRect);
			Game.hero.yMove(-move + extra);
			break;
		case UP:
			extra = move - charCRect.getTopCollisionDiff(tileRect);
			Game.hero.yMove(-move + extra);
			break;
		case LEFT:
			extra = move - charCRect.getLeftCollisionDiff(tileRect);
			Game.hero.xMove(-move + extra);
			break;
		case RIGHT:
			extra = move - charCRect.getRightCollisionDiff(tileRect);
			Game.hero.xMove(-move + extra);
			break;
		}
	}

	public void setCollisionRect(Rect absRect) {
		cRect = new Rect(absRect, margin);
	}
	
	public abstract Tile createNew();
}
