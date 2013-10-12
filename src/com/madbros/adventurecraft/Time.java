package com.madbros.adventurecraft;

import com.badlogic.gdx.Gdx;

public class Time {
	private static long lastFrame = 0;
	private static float delta;
	
	public Time() {
		lastFrame = getTime();
		setDelta();
	}
	
	public static long getTime() {
		return System.nanoTime() / 1000000; //<- time in milliseconds
	}
	
	public static long getTimeNano() {
		return System.nanoTime();
	}
	
	public static float getDelta() {
		//System.out.println(Gdx.graphics.getRawDeltaTime());
		return Gdx.graphics.getRawDeltaTime() *1000;
	}
	
	public static void setDelta() {
		long time = getTime();
		delta = (float) (time - lastFrame);
		lastFrame = time;
	}
}
