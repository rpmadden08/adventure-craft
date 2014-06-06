package com.madbros.tileminer.TileTypes;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Game;
import com.madbros.tileminer.Sprites.*;
import com.madbros.tileminer.Utils.Margin;

public class TreeLeafTile extends CollisionTile {
	public TreeLeafTile() {
		super();
		currentSpriteId = 0;
		sprites = Sprites.spriteCollections.get(Sprites.TREE_TWO);

		margin = new Margin(9, 9, 12, 11);
		id = TREE_LEAF;
		layer = ABOVE_LAYER_4;
		z = Z_ABOVE_LAYER;
		isDiggable = false;
		isAutoTileable = false;
		autoTile = 0;
		isTreeLeafTile = true;
	}
	
//	@Override
	public void render(int x, int y, int layer) {
		sprites[currentSpriteId].draw(x, y, z);
	}
	
	@Override
	public void update(int x, int y) {
		
		if(Game.level.activeBlocks[x][y].layers[OBJECT_LAYER].id == 6 && currentSpriteId == 4) {
			isVisible = false;
			
		} else if(Game.level.activeBlocks[x][y].layers[OBJECT_LAYER].id == 6 && currentSpriteId == 2) {
			//Checks to see if the tree trunk on the left is a tree trunk and if it should overlap...
			isVisible = false;
			
		} else if(Game.level.activeBlocks[x][y+1].layers[ABOVE_LAYER_2].id == Game.level.activeBlocks[x][y].layers[ABOVE_LAYER_2].id && Game.level.activeBlocks[x][y].layers[ABOVE_LAYER_2].id != AIR) {
		
			if(currentSpriteId == 0 || currentSpriteId == 6) {
			//This make sure the tree leaf tiles don't overlap autotiled walls...
			isVisible = false;
			}
		} else {
			isVisible = true;
		}
		
	}
	
	public TreeLeafTile createNew() {
		return new TreeLeafTile();
	}
}
