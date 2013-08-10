package com.madbros.adventurecraft;

import static com.madbros.adventurecraft.Constants.*;

import org.newdawn.slick.Color;

public class MiniMap {
	public void render(Block[][] aBlocks) {
		int sX = Game.currentScreenSizeX - 50;
		int sY = 10;
		Color brown = new Color(100, 100, 100);
		Color darkGreen = new Color(0, 150, 0);
		for(int x = 0; x < aBlocks.length; x++) {
			for(int y = 0; y < aBlocks.length; y++) {
				for(int layer = 3; layer > -1; layer--) {
					if(aBlocks[x][y].layers[layer].id != AIR) {
						if(aBlocks[x][y].layers[layer].id == GRASS) Color.green.bind();
						if(aBlocks[x][y].layers[layer].id == TREE) darkGreen.bind();
						if(aBlocks[x][y].layers[layer].id == WATER) Color.blue.bind();
						if(aBlocks[x][y].layers[layer].id == DIRT) brown.bind();
						Textures.pixel.draw(sX + x, sY + y, Z_MINIMAP, 1, 1);
						Color.white.bind();
						break;
					}
				}
			}
		}
	}
}
