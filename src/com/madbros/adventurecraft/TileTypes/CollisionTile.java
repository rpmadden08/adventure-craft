package com.madbros.adventurecraft.TileTypes;

import static com.madbros.adventurecraft.Constants.*;
import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Utils.Margin;
import com.madbros.adventurecraft.Utils.Rect;

public abstract class CollisionTile extends Tile {
	public Margin margin;
	public CollisionTile() {
		isCollidable = true;
	}
	
	public void characterDidCollide(int dir, int move, Rect charCRect, Rect tileRect) {
		int extra;
		switch(dir) {
		case DOWN:
			extra = move - charCRect.getBottomCollisionDiff(tileRect);
			Game.character.yMove(-move + extra);
			break;
		case UP:
			extra = move - charCRect.getTopCollisionDiff(tileRect);
			Game.character.yMove(-move + extra);
			break;
		case LEFT:
			extra = move - charCRect.getLeftCollisionDiff(tileRect);
			Game.character.xMove(-move + extra);
			break;
		case RIGHT:
			extra = move - charCRect.getRightCollisionDiff(tileRect);
			Game.character.xMove(-move + extra);
			break;
		}
	}

	public abstract Tile createNew();
}
