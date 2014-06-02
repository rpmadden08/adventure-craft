package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.madbros.adventurecraft.Block;
import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.TileTypes.CampfireTile;
import com.madbros.adventurecraft.TileTypes.Tile;
import com.madbros.adventurecraft.Utils.Helpers;

public class FireStarter extends BlockItem32 {
	public FireStarter() {
		id = FIRE_STARTER;
		name = "Fire Starter";
		sprite = Sprites.sprites.get(Sprites.FIRE_STARTER);
		numberProducedByCrafting = 1;
		itemsPossiblyCraftable = new int[]{};
		craftCost = new int[]{STICK};
		craftCostAmount = new int[]{2};
		placeableTileIds = new int[]{FIRE_PIT_TILE};
		workSpaceNeeded = new int[]{BARE_HANDS_WORKSPACE,TABLE_WORKSPACE};
		//set recipes
	}
	
	public void useRight() {
		//if(isInRange) {
		checkUsability();
			Tile tile = TILE_HASH.get(tileId).createNew();
			Block hB = Game.level.highlightedBlock;
			
			if(Helpers.arrayDoesContainInt(placeableTileIds, hB.getTopTile().id)) {
				placeTile(hB, tile);
				stackSize -= 1;
				Game.inventory.deleteItemIfNecessary();
			}
		//}
	}
	
	@Override
	public void placeTile(Block hB, Tile tile) {
		Tile tile2 = new CampfireTile();
		hB.layers[OBJECT_LAYER] = tile2;
	}
	
	@Override
	public void highlightItem(Block block, int x, int y) {
		
		if(Helpers.arrayDoesContainInt(placeableTileIds, block.getTopTerrainTile().id) && AIR == block.layers[OBJECT_LAYER].id) {

			sprite.setColor(HIGHLIGHT_COLOR);
			sprite.draw(x, y, Z_CHARACTER, block.absRect.w, block.absRect.h);
			sprite.setColor(Color.WHITE);
		}
}
	
	@Override
	public FireStarter createNew() {
		return new FireStarter();
	}
}
