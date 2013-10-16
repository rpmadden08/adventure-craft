package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.openal.Wav.Sound;
import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Sprites.Sprites;

public class Axe extends ToolItem {
	public Axe() {
		id = AXE;
		sprite = Sprites.sprites.get(Sprites.AXE_ITEM);
		attackPower = 1;
		is32 = true;
		isInUse = false;
		sound = (Sound) Gdx.audio.newSound(Gdx.files.internal("sounds/axeChop.wav"));
		
		
	}
	
	@Override
	public Axe createNew() {
		return new Axe();
	}
	
	public void useLeft() {
		
//		Tile topTile = Game.level.highlightedBlock.getTopTile();
		if(isInUse == false) {
			if(Game.level.tileBeingAttacked.isChoppable) {
				//take hp from top tile in highlightedblock
				Game.level.tileBeingAttacked.currentHp -= attackPower;
				if(Game.level.tileBeingAttacked.currentHp < 1) {
					Game.level.highlightedBlock.deleteObjectTile();
					Game.level.tileBeingAttacked.deleteMe(Game.level.highlightedBlockX, Game.level.highlightedBlockY, Game.level.activeBlocks);
					Game.level.autoTileHighlightedBlock();
				}
				//sound.play(1.0f);
				System.out.println("ONLY RAN ONCE");
				System.out.println(Game.level.tileBeingAttacked.currentHp);
				isInUse = true;
				int x = Game.level.highlightedBlock.absRect.x - Game.renderSystem.startX +(TILE_SIZE/2);
				int y = Game.level.highlightedBlock.absRect.y - Game.renderSystem.startY+ (TILE_SIZE/2);
				System.out.println(x+","+y);
				
				Game.p.setPosition((float)x,(float)y);
				//Game.p.update(Gdx.graphics.getRawDeltaTime());
				Game.p.start();
			}
			
		}
	}
}
