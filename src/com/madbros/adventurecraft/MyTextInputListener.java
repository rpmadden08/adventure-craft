package com.madbros.adventurecraft;


import static com.madbros.adventurecraft.Constants.SAVE_LOC;

import java.io.File;

import com.badlogic.gdx.Input.TextInputListener;
	public class MyTextInputListener implements TextInputListener {
		@Override
		public void input (String text) {
			Game.gameFileName = text;
			//MainMenuState.newGame(Game.batch);
			File f = new File(SAVE_LOC + Game.gameFileName);
			if(f.exists()) {
				int gameNumber = 1;
				String baseName = "New Game ";
				while(true) {
					f = new File(SAVE_LOC + baseName + String.valueOf(gameNumber));
					if(!f.exists()) {
						Game.gameFileName = baseName + String.valueOf(gameNumber);
						break;
					}
					gameNumber++;
				}
			}
			Game.createNewGameAtLoc(SAVE_LOC + Game.gameFileName + "/");
		}
		
		@Override
		public void canceled () {
		}
	}
