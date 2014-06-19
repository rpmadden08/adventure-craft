package com.madbros.tileminer;

import com.badlogic.gdx.Gdx;

import static com.madbros.tileminer.Constants.*;

public class Time {
	public static long lastFrame = 0;
	public static float delta;
	
	public Time() {
		lastFrame = getTime();
		setDelta();
		
	}
	
	public static long getTime() {
		return System.nanoTime() / 1000000; //<- time in milliseconds
	}
	
	public static long getGameTime() {
		return System.nanoTime() / 1000000 -Game.gameStartTime + Game.timeSpentInPreviousSaves;
	}
	
	public static long getTimeNano() {
		return System.nanoTime();
	}
	
	public static float getDelta() {
		return Gdx.graphics.getRawDeltaTime() *1000;
	}
	public static long getMilliseconds() {
		long totalMilli = System.nanoTime() / 1000000; //<- time in milliseconds
		
		totalMilli = (totalMilli - Game.gameStartTime+ Game.timeSpentInPreviousSaves)/10;
		return totalMilli % 100;
	}
	
	public static long getTotalSeconds() {
		long currentTime =  System.nanoTime() / 1000000; //<- time in milliseconds
		
		return (currentTime - Game.gameStartTime+ Game.timeSpentInPreviousSaves)/1000; //Should be 1000 but made it /10 for testing purposes...
	}
	
	public static long getSeconds() {
		long currentTime = getTotalSeconds();
		
		long timeNow = currentTime % 60;
		return timeNow;
	}
	
	public static String getSecondsString() {
		long timeNow = getSeconds();
		if (timeNow / 10 == 0) {
	        return "0" + timeNow;
	    }
		return "" + timeNow;
	}
	
	public static long getMinutes() {
		long currentTime = getTotalSeconds();
		
		long timeNow = currentTime / 60+8;  //The + 8 makes the time start at 8:00
		timeNow = timeNow % 24;
		if (timeNow == 0)  {
			timeNow = 24;
		}
		
		return timeNow;
	}
	
	public static String getMinutesString() {
		long timeNow = getMinutes();
		if (timeNow > 12) {
			timeNow = timeNow - 12;
		}
		return "" + timeNow;
	}
	
	public static void checkTime() {
		long seconds = getMilliseconds();
		long minutes = getSeconds();
		long hours = getMinutes();
		if(Game.currentLevel == OVERWORLD_FOLDER) {
			if(hours >= 6 && hours < 19) {
				Game.currentShader = Game.defaultShader; // Should be default
				Game.level.isDay = true;
	//			Game.currentShader = Game.finalShader; // Should be default
	//			Game.level.isDay = true;
			} else if (hours >= 5 && hours < 6) {
				if(minutes >= 30) {
					Game.currentShader = Game.finalShader;
					double currentTime = minutes;
					currentTime = currentTime + (seconds * 0.01);
					double amountCompleted = currentTime - 30;
					double percentage = amountCompleted / 30;
					float shaderInfo[] = {Game.ambientIntensity, Game.ambientColor.x, Game.ambientColor.y,Game.ambientColor.z};
					for(int a = 0;a < 4; a++) {
						float x = shaderInfo[a];
						float shaderTotal = 1f - x;
						float amountToAdd = (float) (shaderTotal * percentage);// /2 was added to hopefully decrease the extreme brightness...
						
						switch (a) {
							case 0: Game.ambientIntensity2 = x + amountToAdd;
							break;
							case 1: Game.ambientColor2.x = x + amountToAdd;
							break;
							case 2: Game.ambientColor2.y = x + amountToAdd;
							break;
							case 3: Game.ambientColor2.z = x + amountToAdd;
							break;
						} 
					}
					float amountToAddLighting = 1f * (float)percentage;
					Game.lightTransparency2 = Game.lightTransparency -amountToAddLighting;
					Game.reShade(Game.ambientColor2, Game.ambientIntensity2);
					
				}
			} else if (hours >= 19 &&  hours < 20) {
				if(minutes >= 30) {
					Game.currentShader = Game.finalShader;
					double currentTime = minutes;
					currentTime = currentTime + (seconds * 0.01); //IE 16.45
					double amountCompleted = currentTime - 30; //IE 15
					double percentage = amountCompleted / 30; 
					float shaderInfo[] = {Game.ambientIntensity, Game.ambientColor.x, Game.ambientColor.y,Game.ambientColor.z};
					for(int a = 0;a < 4; a++) {
						float x = shaderInfo[a];
						float shaderTotal = 1f - x; // to get 100% value
						float amountToAdd = (float) (shaderTotal * percentage);
						//float amountToSubtract = shaderTotal-amountToAdd;
						switch (a) {
							case 0: Game.ambientIntensity2 = 1f- amountToAdd;
							break;
							case 1: Game.ambientColor2.x = 1f - amountToAdd;
							break;
							case 2: Game.ambientColor2.y = 1f - amountToAdd;
							break;
							case 3: Game.ambientColor2.z = 1f - amountToAdd;
							break;
						} 
					}
					float amountToAddLighting = 1f * (float)percentage;
					
					Game.reShade(Game.ambientColor2, Game.ambientIntensity2);
					Game.currentShader = Game.finalShader;
					Game.lightTransparency2 = amountToAddLighting;
				} else {
					Game.currentShader = Game.defaultShader;
					Game.lightTransparency2 = 0f;
				}
			} else {
				Game.level.isDay = false;
				Game.currentShader = Game.finalShader;
				Game.lightTransparency2 = 1f;
			}
		} else {
			Game.currentShader = Game.defaultShader;
//			Game.ambientColor2.x = 0f;
//			Game.ambientColor2.y = 0f;
//			Game.ambientColor2.z = 0f;
//			Game.ambientIntensity2 = 0f;
//			Game.reShade(Game.ambientColor2, Game.ambientIntensity2);
//			Game.currentShader = Game.finalShader;
//			Game.lightTransparency2 = 1f;
		}
		
	}
	
	public static void setDeltaToZero() {
		long time = getTime();
		delta = 0;
		lastFrame = time;
	}
	
	public static void setDelta() {
		long time = getTime();
		delta = (float) (time - lastFrame);
		lastFrame = time;
	}
}
