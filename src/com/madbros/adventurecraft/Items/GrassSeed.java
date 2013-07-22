package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Textures;

public class GrassSeed extends SeedItem {
	public GrassSeed() {
		id = GRASS_SEED;
		texture = Textures.grassSeedTexture;
	}
	
	@Override
	public GrassSeed createNew() {
		return new GrassSeed();
	}
}
