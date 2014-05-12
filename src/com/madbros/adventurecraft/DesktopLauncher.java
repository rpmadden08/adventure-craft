package com.madbros.adventurecraft;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import static com.madbros.adventurecraft.Constants.*;

public class DesktopLauncher {
	public static LwjglApplicationConfiguration cfg;
	public static void main(String[] args) {
		cfg = new LwjglApplicationConfiguration();
		cfg.title = "Adventure Craft";
		cfg.useGL20 = true;
		cfg.width = INITIAL_WINDOW_WIDTH;
		cfg.height = INITIAL_WINDOW_HEIGHT;
		
		cfg.fullscreen = true;
		cfg.vSyncEnabled = true;
		cfg.resizable = false;
		
		
		new LwjglApplication(new Game(), cfg);
	}
}
