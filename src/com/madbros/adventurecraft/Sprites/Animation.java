package com.madbros.adventurecraft.Sprites;

import com.madbros.adventurecraft.Time;
import com.madbros.adventurecraft.Sprites.StaticSprite;
import com.madbros.adventurecraft.Utils.*;

public class Animation {
	public StaticSprite[] sprites;
	private int[] frameTimesInMilliseconds;
	public int currentFrame = 0;
	private float time;
	public int id;
	
	/*********************************** Constructors ***********************************/
	public Animation(StaticSprite[] sprites, int frameTimeInMilliseconds, int id) {
		this.sprites = sprites;
		this.id = id;
		
		frameTimesInMilliseconds = new int[sprites.length];
		for(int i = 0; i < sprites.length; i++) {
			frameTimesInMilliseconds[i] = frameTimeInMilliseconds;
		}
	}
	
	public Animation(StaticSprite sprite, int frameTimeInMilliseconds, int id) {
		this(new StaticSprite[]{sprite}, frameTimeInMilliseconds, id);
	}
	
	public Animation(StaticSprite[] sprites, int[] frameTimesInMilliseconds, int id) {
		this.sprites = sprites;
		this.id = id;
		this.frameTimesInMilliseconds = frameTimesInMilliseconds;
	}
	
	public Animation(StaticSprite sprite, int[] frameTimesInMilliseconds, int id) {
		this(new StaticSprite[]{sprite}, frameTimesInMilliseconds, id);
	}
	
	/*********************************** Drawing ***********************************/	
	public void draw(float x, float y, float z) {
		this.draw(x, y, z, sprites[currentFrame].getWidth(), sprites[currentFrame].getHeight());
	}
	
	public void draw(Rect r, float z) {
		this.draw(r.x, r.y, r.w, r.h, z);
	}
	
	public void draw(float x, float y, float z, float w, float h) {
		sprites[currentFrame].draw(x, y, z, w, h);
	}
	
	public void draw(float x, float y, float z, float scale) {
		sprites[currentFrame].draw(x, y, z, scale);
	}

	/*********************************** Other ***********************************/
	public void updateFrame() {
		//FIXME: shouldn't have to do this min max stuff...
		time = Math.max(0, time + Time.getDelta());
		time = Math.min(time, 1000);
		if(time >= frameTimesInMilliseconds[currentFrame]) nextFrame(time - frameTimesInMilliseconds[currentFrame]);
	}
	
	private void nextFrame(float f) {
		time = f;
		if(currentFrame >= sprites.length - 1) currentFrame = 0;
		else currentFrame++;
	}
	
	public void resetFrame() {
		currentFrame = 0;
		time = 0;
	}
	
	public void changeFrameTimesBy(int n) {
		for(int i = 0; i < sprites.length; i++) {
			frameTimesInMilliseconds[i] += n;
		}
	}
	
	public void changeFrameTimes(int n) {
		for(int i = 0; i < sprites.length; i++) {
			frameTimesInMilliseconds[i] = n;
		}
	}
	
	public Animation getCopy() {
		int[] frameTimesInMilliseconds = new int[this.frameTimesInMilliseconds.length];
		for(int i = 0; i < frameTimesInMilliseconds.length; i++) {
			frameTimesInMilliseconds[i] = this.frameTimesInMilliseconds[i];
		}
		return new Animation(sprites, frameTimesInMilliseconds, id);
	}
}
