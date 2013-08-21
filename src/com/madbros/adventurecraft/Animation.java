package com.madbros.adventurecraft;

public class Animation {
	private StaticSprite[] sprites;
	private int[] frameTimesInMilliseconds;
	private int currentFrame = 0;
	private int time = 0;
	
	/*********************************** Constructors ***********************************/
	public Animation(StaticSprite[] sprites, int frameTimeInMilliseconds) {
		this.sprites = sprites;
		
		frameTimesInMilliseconds = new int[sprites.length];
		for(int i = 0; i < sprites.length; i++) {
			frameTimesInMilliseconds[i] = frameTimeInMilliseconds;
		}
	}
	
	public Animation(StaticSprite sprite, int frameTimeInMilliseconds) {
		this(new StaticSprite[]{sprite}, frameTimeInMilliseconds) ;
	}
	
	public Animation(StaticSprite[] sprites, int[] frameTimesInMilliseconds) {
		this.sprites = sprites;
		this.frameTimesInMilliseconds = frameTimesInMilliseconds;
	}
	
	public Animation(StaticSprite sprite, int[] frameTimesInMilliseconds) {
		this(new StaticSprite[]{sprite}, frameTimesInMilliseconds) ;
	}
	
	/*********************************** Drawing ***********************************/
	public void draw(float x, float y, float z, float delta) {
		this.draw(x, y, z, sprites[currentFrame].getWidth(), sprites[currentFrame].getHeight(), delta);
	}
	
	public void draw(float x, float y, float z, float w, float h, float delta) {
		time = Math.max(time + (int)delta, 0);
		while(time >= frameTimesInMilliseconds[currentFrame]) nextFrame(time - frameTimesInMilliseconds[currentFrame]);
		sprites[currentFrame].draw(x, y, z, w, h);
	}
	
	private void nextFrame(int t) {
		time = t;
		if(currentFrame >= sprites.length - 1) currentFrame = 0;
		else currentFrame++;
	}
	
	public void resetFrame() {
		currentFrame = 0;
	}
	
	public void changeFrameTimesBy(int n) {
		for(int i = 0; i < sprites.length; i++) {
			frameTimesInMilliseconds[i] += n;
		}
	}
}
