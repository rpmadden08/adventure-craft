package com.madbros.tileminer;

import java.text.*;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Rect;

import static com.madbros.tileminer.Constants.*;

public class Debugger {
	public boolean isDebugging = false;
	//public long gameStartTime;
	private long timeSpentInGame;    // in seconds
	//public long timeSpentInPreviousSaves;
	
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
//	private int hours = 0;
//	private int minutes = 0;
	private String hours;
	private String minutes;
	
	private BitmapFont font;
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
	
	public Debugger() {
		font = Sprites.font;
		fontColor = Color.WHITE;
		
		lastFPS = Time.getTime();
		fps = 0;
			
		numberOfFramesAddedToTheRenderStoreArray = 0;
		numberOfFramesAddedToTheUpdateStoreArray = 0;
		renderTimeStore = new long[NUMBER_OF_FRAMES_USED_FOR_SAMPLE_MEAN];
		updateTimeStore = new long[NUMBER_OF_FRAMES_USED_FOR_SAMPLE_MEAN];
		
		runtime = Runtime.getRuntime();
		
		Game.gameStartTime = Time.getTime();
		timeSpentInGame = 0;
		Game.timeSpentInPreviousSaves = 0;
	}
	
	private void updateFPS() {
		long timeNow = Time.getTime();
		timeSpentInGame = (long) ((timeNow - Game.gameStartTime + Game.timeSpentInPreviousSaves)/1000);  // ms --> secs
//		hours = (int) Time.getMinutes();
//		minutes = (int) Time.getSeconds();
		hours = Time.getMinutesString();
		minutes = Time.getSecondsString();
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
	
	public void renderText(Rect charRect, SpriteBatch batch) {
//		font.draw(batch, str, x, y, start, end);
		font.setColor(fontColor);
		font.draw(batch, "Time Spent In Game: " + displayedGameTime + " seconds", DISPLAY_STARTX, DISPLAY_STARTY + DISPLAY_MARGIN*0);
		font.draw(batch, "Current Time: "+hours+":"+minutes, DISPLAY_STARTX, DISPLAY_STARTY + DISPLAY_MARGIN*1);
		
		
		font.draw(batch, "Current fps: " + displayedFPS + " fps", DISPLAY_STARTX, DISPLAY_STARTY + DISPLAY_MARGIN*2);
		font.draw(batch, "Average ms for update: " + displayedMillisecondsPerUpdate, DISPLAY_STARTX, DISPLAY_STARTY + DISPLAY_MARGIN*3);
		font.draw(batch, "Average ms for render: " + displayedMillisecondsPerRender, DISPLAY_STARTX, DISPLAY_STARTY + DISPLAY_MARGIN*4);
		
		font.draw(batch, "Free Memory: " + displayedFreeMemory + " mb", DISPLAY_STARTX, DISPLAY_STARTY + DISPLAY_MARGIN*6);
		font.draw(batch, "Used Memory: " + displayedUsedMemory + " mb", DISPLAY_STARTX, DISPLAY_STARTY + DISPLAY_MARGIN*7);
		font.draw(batch, "Memory Left: " + displayedPercentOfMemoryLeft + "%", DISPLAY_STARTX, DISPLAY_STARTY + DISPLAY_MARGIN*8);
		font.draw(batch, "Character Position: (" + charRect.x/TILE_SIZE + ", " + charRect.y/TILE_SIZE + ")", DISPLAY_STARTX, DISPLAY_STARTY + DISPLAY_MARGIN*10);
		font.draw(batch, "Extra Debugger: " + displayedExtra, DISPLAY_STARTX, DISPLAY_STARTY + DISPLAY_MARGIN*12);
	
	}
	
	public void toggle() {
		if (isDebugging) isDebugging = false;
		else isDebugging = true;
	}
}
