package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Textures;

public class SandClump extends BlockItem {
	public SandClump() {
		id = SAND_CLUMP;
		texture = Textures.sandClumpTexture;
	}
	
	@Override
	public SandClump createNew() {
		return new SandClump();
	}
}
