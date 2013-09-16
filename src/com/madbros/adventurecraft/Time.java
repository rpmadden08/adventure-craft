package com.madbros.adventurecraft;

public class Time {
	private static long lastFrame = 0;
	private static int delta;
	
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
	
	public static int getDelta() {
		return delta;
	}
	
	public static void setDelta() {
		long time = getTime();
		delta = (int) (time - lastFrame);
		lastFrame = time;
	}
}
