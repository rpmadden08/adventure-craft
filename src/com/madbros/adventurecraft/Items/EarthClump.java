package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;
import com.madbros.adventurecraft.Textures;

public class EarthClump extends BlockItem {
	public EarthClump() {
		id = EARTH_CLUMP;
		texture = Textures.earthClumpTexture;
	}
	
	@Override
	public EarthClump createNew() {
		return new EarthClump();
	}
}
