package com.madbros.tileminer.TileTypes;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.*;
import com.madbros.tileminer.Utils.Margin;

public class TreeLeafPineTile extends TreeLeafTile {
	public TreeLeafPineTile() {
		super();
		currentSpriteId = 0;
		sprites = Sprites.spriteCollections.get(Sprites.TREE_FOUR);

		margin = new Margin(9, 9, 12, 11);
		id = TREE_LEAF_PINE;
		layer = ABOVE_LAYER_1;
		z = Z_ABOVE_LAYER;
		isDiggable = false;
		isAutoTileable = false;
		autoTile = 0;
		isTreeLeafTile = true;
	}
	
//	@Override
	public void render(int x, int y, int layer) {
		float z;
		z = this.z + layer / 100f; //FIXME!!
		
		sprites[currentSpriteId].draw(x, y, z);
		
//		if(currentTexture == 0 ) {
//		Helpers.println(String.valueOf(layer / 100f));
//		z = this.z + Math.max(layer / 100f, layer / 100f);

//			else z = this.z + Math.max(Game.level.test.x / 1000f, Game.level.test.y / 1000f);
//		} else {
//			z = Z_ABOVE_LAYER;
//		}
//		if(y > Game.hero.sRect.y) z = Z_ABOVE_LAYER;// + Math.max(Game.level.test.x / 1000f, Game.level.test.y / 1000f);	//FIXME: not an ideal conditional
//		else z = this.z;// + Math.max(Game.level.test.x / 1000f, Game.level.test.y / 1000f);
	}
	
	public TreeLeafPineTile createNew() {
		return new TreeLeafPineTile();
	}
}
