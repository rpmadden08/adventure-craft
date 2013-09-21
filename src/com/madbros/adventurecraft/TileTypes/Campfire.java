package com.madbros.adventurecraft.TileTypes;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprites.Sprites;

public class Campfire extends LightTile {

	public Campfire() {
		super();
		id = CAMPFIRE;
		sprites = Sprites.campfireAnimation;
		autoTile = 0;
		isAutoTileable = false;
	}
	@Override
	public LightTile createNew() {
		return new Campfire();
	}
	
	@Override
	public void render(int x, int y) {
		sprites[0].draw(x, y, z);
	}
}
