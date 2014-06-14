package com.madbros.tileminer;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import static com.madbros.tileminer.Constants.*;

public class DesktopLauncher {
	public static LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
	public static void main(String[] args) {
		cfg = new LwjglApplicationConfiguration();
		cfg.title = "Tile Miner";
		cfg.useGL20 = true;
		cfg.width = INITIAL_WINDOW_WIDTH;
		cfg.height = INITIAL_WINDOW_HEIGHT;
		
		cfg.fullscreen = false;
		cfg.vSyncEnabled = true;
		cfg.resizable = false;
		
		
		new LwjglApplication(new Game(), cfg);
	}
}
