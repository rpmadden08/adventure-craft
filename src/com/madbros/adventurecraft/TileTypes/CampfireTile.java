package com.madbros.adventurecraft.TileTypes;

import static com.madbros.adventurecraft.Constants.*;

import java.util.ArrayList;

import com.madbros.adventurecraft.Block;
import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Items.Item;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Utils.Margin;
import com.madbros.adventurecraft.Utils.Rect;

public class CampfireTile extends LightTile {

	public CampfireTile() {
		super();
		lightSize = 500;
		id = CAMPFIRE;
		sprites = Sprites.campfireAnimation;
		autoTile = 0;
		isAutoTileable = false;
		isBreakable = true;
		currentHp = 1;
		maxHp = 1;
		margin = new Margin(2, 0, 1, 1);
	}
	
	@Override
	public LightTile createNew() {
		return new CampfireTile();
	}
	
	public void update(int x,int y) {
		cRect = new Rect(absX *TILE_SIZE, absY*TILE_SIZE, 32,32);
		Rect detectRect = new Rect(cRect.x - ((int)lightSize /2), cRect.y - ((int)lightSize/2), cRect.w +((int)lightSize), cRect.h +((int)lightSize));
		
		for(int a = 0; a < Game.mobController.mobs.size(); a++) {
//			System.out.println(Game.mobController.mobs.get(a).absRect.x+","+Game.mobController.mobs.get(a).absRect.x);
//			System.out.println("CAMPFIRE:  "+cRect.x+","+cRect.y);
			if(Game.mobController.mobs.get(a).isInRangeOfCampfire == false) {
				if(Game.mobController.mobs.get(a).absRect.detectCollision(detectRect)) {
					Game.mobController.mobs.get(a).isInRangeOfCampfire = true;
					
					Game.mobController.mobs.get(a).campFireRect = cRect;
				}
			}
		}
	}
	
	@Override
	public void render(int x, int y) {
		sprites[0].draw(x, y, z);
	}
	
	public void deleteMe(int x, int y, Block[][] activeBlocks) {
		Block b = activeBlocks[x][y];
		b.layers[OBJECT_LAYER] = new NoTile();
		Rect collectibleRect = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
		Item item = ITEM_HASH.get(CAMPFIRE).createNew();
		Game.collectibleController.add(CAMPFIRE, Sprites.sprites.get(Sprites.CAMPFIRE_SINGLE), collectibleRect, 1, item.maxUses);
	}
}
