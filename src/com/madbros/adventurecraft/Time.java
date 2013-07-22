package com.madbros.adventurecraft;

public class Time {
	private static long lastFrame = 0;
	
	public static long getTime() {
		return System.nanoTime() / 1000000; //<- time in milliseconds
	}
	
	public static long getTimeNano() {
		return System.nanoTime();
	}
	
	public static int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastFrame);
		
		lastFrame = time;
	
		return delta;

	}
}
