package com.madbros.adventurecraft.TileTypes;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprites.*;
import com.madbros.adventurecraft.Utils.Margin;

public class TreeLeafRainTile extends TreeLeafTile {
	public TreeLeafRainTile() {
		super();
		currentSpriteId = 1;
		sprites = Sprites.spriteCollections.get(Sprites.TREE_THREE);

		margin = new Margin(0, 0, 0, 0);
		id = TREE_LEAF_RAIN;
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
	
	public TreeLeafRainTile createNew() {
		return new TreeLeafRainTile();
	}
}
