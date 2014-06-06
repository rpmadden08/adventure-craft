package com.madbros.tileminer;

import static com.madbros.tileminer.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.madbros.tileminer.Sprites.Sprites;

public class MiniMap {
	public void render(Block[][] aBlocks) {
		int sX = Game.currentScreenSizeX - 100;
		int sY = 10;
		Color brown = new Color(0.4f, 0.4f, 0.4f, 1);
		Color darkGreen = new Color(0f, 0.5f, 0f, 1f);
		for(int x = 0; x < aBlocks.length; x++) {
			for(int y = 0; y < aBlocks.length; y++) {
				for(int layer = 3; layer > -1; layer--) {
					if(aBlocks[x][y].layers[layer].id != AIR) {
						if(aBlocks[x][y].layers[layer].id == GRASS) Sprites.pixel.setColor(Color.GREEN);
						if(aBlocks[x][y].layers[layer].id == TREE) Sprites.pixel.setColor(darkGreen);
						if(aBlocks[x][y].layers[layer].id == WATER) Sprites.pixel.setColor(Color.BLUE);
						if(aBlocks[x][y].layers[layer].id == DIRT) Sprites.pixel.setColor(brown);

						Sprites.pixel.draw(sX + x, sY + y, Z_MINIMAP, 1, 1);

						Sprites.pixel.setColor(Color.WHITE);
						break;
					}
				}
			}
		}
	}
}
