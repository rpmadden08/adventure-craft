package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Textures;

public class Shovel extends ToolItem {
	public Shovel() {
		id = SHOVEL;
		texture = Textures.shovelTexture;
	}
	
	@Override
	public Shovel createNew() {
		return new Shovel();
	}
}
