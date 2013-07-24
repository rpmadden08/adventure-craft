package com.madbros.adventurecraft;

import java.text.*;
import org.lwjgl.input.*;
import org.newdawn.slick.*;
import com.madbros.adventurecraft.UIButtons.*;

import static com.madbros.adventurecraft.Constants.*;

public class Debugger {
	private boolean isDebugging = false;
	private long gameStartTime;
	private long timeSpentInGame;    // in seconds

	private DecimalFormat df = new DecimalFormat("0.###");
	
	private long lastFPS;
	private long fps;
	
	public String tempVar = "Temp";	//for random temporary debugging
	
	private long startOfFrameTime;
	private int numberOfFramesAddedToTheRenderStoreArray;
	private int numberOfFramesAddedToTheUpdateStoreArray;
	private long[] renderTimeStore;
	private long[] updateTimeStore;
	private long startTime = 0;
	private long endTime = 0;
	
	private Font font;
	private Color fontColor;
	private String displayedFPS = "-";
	private String displayedMillisecondsPerUpdate = "-";	//average number of milliseconds it takes to update once
	private String displayedMillisecondsPerRender = "-";	//average number of milliseconds it takes to render once
	private String displayedFreeMemory = "-";
	private String displayedUsedMemory = "-";
	private String displayedPercentOfMemoryLeft = "-";
	private String displayedGameTime = "-";
	public String displayedExtra = "-";
	
	private Runtime runtime;
	
	public boolean collisionTilesAreOn = false;			//displays tiles used for collision detection
	public boolean collisionRectsAreOn = false;			//displays rectangles around collidable objects
	public boolean chunkBoundariesAreOn = true;		//displays chunk boundaries
	public boolean collisionDetectionIsOn = false;		//turns collision detection on/off
	
	public boolean menuIsActive = false;
	public DebuggerMenuButton[] menuButtons;
	
	public Debugger() {
		font = Textures.font;
		fontColor = Color.white;
		
		lastFPS = Time.getTime();
		fps = 0;
		
		menuButtons = new DebuggerMenuButton[4];
		menuButtons[0] = new CollisionDetectionButton(Game.currentScreenSizeX - DEBUG_MENU_SIZEX, 0, collisionDetectionIsOn);
		menuButtons[1] = new CollisionRectanglesButton(Game.currentScreenSizeX - DEBUG_MENU_SIZEX, DEBUG_MENU_SIZEY, collisionRectsAreOn);
		menuButtons[2] = new CollisionTilesButton(Game.currentScreenSizeX - DEBUG_MENU_SIZEX, DEBUG_MENU_SIZEY*2, collisionTilesAreOn);
		menuButtons[3] = new ChunkBoundariesButton(Game.currentScreenSizeX - DEBUG_MENU_SIZEX, DEBUG_MENU_SIZEY*3, chunkBoundariesAreOn);
		
		numberOfFramesAddedToTheRenderStoreArray = 0;
		numberOfFramesAddedToTheUpdateStoreArray = 0;
		renderTimeStore = new long[NUMBER_OF_FRAMES_USED_FOR_SAMPLE_MEAN];
		updateTimeStore = new long[NUMBER_OF_FRAMES_USED_FOR_SAMPLE_MEAN];
		
		runtime = Runtime.getRuntime();
		
		gameStartTime = Time.getTime();
		timeSpentInGame = 0;
	}

	private void updateFPS() {
		long timeNow = Time.getTime();
		timeSpentInGame = (long) ((timeNow - gameStartTime)/1000);  // ms --> secs
		displayedGameTime = String.valueOf(timeSpentInGame);
		
		if(timeNow - lastFPS > 1000) {
			displayedFPS = String.valueOf(fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}
	
	private void updateMemoryUsage() {
		long freeMemory = runtime.freeMemory();
		long totalMemory = runtime.totalMemory();
		displayedFreeMemory = df.format(freeMemory * 1f / BYTES_IN_MEGABYTE);
		displayedUsedMemory = df.format(totalMemory * 1f / BYTES_IN_MEGABYTE);
		displayedPercentOfMemoryLeft = df.format(freeMemory * 1f / (totalMemory + freeMemory) * 100);
	}
	
	public void update() {
		if(isDebugging) {
			updateMemoryUsage();
			updateFPS();
		}
	}
	
	public void startTimer() {
		startTime = Time.getTimeNano();
	}
	
	public void endTimer() {
		endTime = Time.getTimeNano();
	}
	
	public long getTimeDiff() {
		return (endTime - startTime) / 1000000;
	}
	
	public void start() {
		startOfFrameTime = Time.getTimeNano();
	}
	
	public void stopRenderTime() {
		renderTimeStore[numberOfFramesAddedToTheRenderStoreArray] = Time.getTimeNano() - startOfFrameTime;
		numberOfFramesAddedToTheRenderStoreArray++;
		
		if(numberOfFramesAddedToTheRenderStoreArray == NUMBER_OF_FRAMES_USED_FOR_SAMPLE_MEAN) {
			long total = 0;
			for (int i=0; i < NUMBER_OF_FRAMES_USED_FOR_SAMPLE_MEAN; i++) {
				total += renderTimeStore[i];
			}
			displayedMillisecondsPerRender = df.format(total * 1.0f / NUMBER_OF_FRAMES_USED_FOR_SAMPLE_MEAN / 1000000);
			numberOfFramesAddedToTheRenderStoreArray = 0;
		}
	}
	
	public void stopUpdateTime() {
		updateTimeStore[numberOfFramesAddedToTheUpdateStoreArray] = Time.getTimeNano() - startOfFrameTime;
		numberOfFramesAddedToTheUpdateStoreArray++;
		
		if(numberOfFramesAddedToTheUpdateStoreArray == NUMBER_OF_FRAMES_USED_FOR_SAMPLE_MEAN) {
			long total = 0;
			for (int i=0; i < NUMBER_OF_FRAMES_USED_FOR_SAMPLE_MEAN; i++) {
				total += updateTimeStore[i];
			}
			displayedMillisecondsPerUpdate = df.format(total * 1.0f / NUMBER_OF_FRAMES_USED_FOR_SAMPLE_MEAN / 1000000);
			numberOfFramesAddedToTheUpdateStoreArray = 0;
		}
	}
	
	public void render() {
		if(menuIsActive) {
			for(int i = 0; i < menuButtons.length; i++) {
				menuButtons[i].render();
			}
		}
	}
	
	public void renderFont() {
		if (isDebugging) {
			font.drawString(DISPLAY_STARTX, DISPLAY_STARTY + DISPLAY_MARGIN*0, "Time Spent In Game: " + displayedGameTime + " seconds", fontColor);
			
			font.drawString(DISPLAY_STARTX, DISPLAY_STARTY + DISPLAY_MARGIN*2, "Current fps: " + displayedFPS + " fps", fontColor);
			font.drawString(DISPLAY_STARTX, DISPLAY_STARTY + DISPLAY_MARGIN*3, "Average ms for update: " + displayedMillisecondsPerUpdate, fontColor);
			font.drawString(DISPLAY_STARTX, DISPLAY_STARTY + DISPLAY_MARGIN*4, "Average ms for render: " + displayedMillisecondsPerRender, fontColor);
			
			font.drawString(DISPLAY_STARTX, DISPLAY_STARTY + DISPLAY_MARGIN*6, "Free Memory: " + displayedFreeMemory + " mb", fontColor);
			font.drawString(DISPLAY_STARTX, DISPLAY_STARTY + DISPLAY_MARGIN*7, "Used Memory: " + displayedUsedMemory + " mb", fontColor);
			font.drawString(DISPLAY_STARTX, DISPLAY_STARTY + DISPLAY_MARGIN*8, "Memory Left: " + displayedPercentOfMemoryLeft + "%", fontColor);
			font.drawString(DISPLAY_STARTX, DISPLAY_STARTY + DISPLAY_MARGIN*10, "Character Position: (" + Game.character.aRect.x + ", " +
																									 Game.character.aRect.y + ")", fontColor);
			font.drawString(DISPLAY_STARTX, DISPLAY_STARTY + DISPLAY_MARGIN*12, "Extra Debugger: " + displayedExtra, fontColor);
		}
		
		if(menuIsActive) {
			for(int i = 0; i < menuButtons.length; i++) {
				menuButtons[i].renderFont();
			}
		}
	}
	
	public void toggle() {
		if (isDebugging) isDebugging = false;
		else isDebugging = true;
	}
	
	public void toggleMenu() {
		if(menuIsActive) menuIsActive = false;
		else menuIsActive = true;
	}
	
	public void checkMouseInput(boolean leftMousePressedDown, boolean leftMousePressedUp) {
		for(int i = 0; i < menuButtons.length; i++) {
			menuButtons[i].setMouseIsHovering();
			menuButtons[i].setMouseIsPressedDown();
			
			if(leftMousePressedDown && menuButtons[i].mouseIsHovering) menuButtons[i].mousePressedDown = true;
			
			if(menuButtons[i].mouseIsHovering && leftMousePressedUp && menuButtons[i].mousePressedDown) menuButtons[i].didPressUp(this);
			if(!Mouse.isButtonDown(LEFT_MOUSE_BUTTON)) menuButtons[i].mousePressedDown = false;
		}
	}
}
