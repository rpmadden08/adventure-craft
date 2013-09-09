package com.madbros.adventurecraft;

import com.madbros.adventurecraft.Utils.*;

public class CompoundAnimatedSprite extends CompoundSprite {
	protected AnimatedSprite[] sprites;
	
	/*********************************** Constructors ***********************************/
	public CompoundAnimatedSprite(AnimatedSprite[] sprites) {
		this.sprites = sprites;
	}
	
	/*********************************** Drawing ***********************************/
	@Override
	public void draw(float x, float y, float z) {
		for(int i = 0; i < sprites.length; i++) {
			sprites[i].draw(x, y, z);
		}
	}
	
	@Override
	public void draw(Rect r, float z) {
		for(int i = 0; i < sprites.length; i++) {
			sprites[i].draw(r, z);
		}
	}

	@Override
	public void draw(float x, float y, float z, float w, float h) {
		for(int i = 0; i < sprites.length; i++) {
			sprites[i].draw(x, y, z, w, h);
		}
	}
	
	@Override
	public void draw(float x, float y, float z, float scale) {
		for(int i = 0; i < sprites.length; i++) {
			sprites[i].draw(x, y, z, scale);
		}
	}
	
	/*********************************** Other ***********************************/
	public void addSprite(AnimatedSprite sprite) {
		AnimatedSprite[] temp = new AnimatedSprite[sprites.length + 1];
		for(int i = 0; i < sprites.length; i++) {
			temp[i] = sprites[i];
		}
		temp[sprites.length] = sprite;
		sprites = temp;
	}

	public void changeAnimationTo(int animationId) {
		for(int i = 0; i < sprites.length; i++) {
			sprites[i].changeAnimationTo(animationId);
		}
	}

	public void updateCurrentAnimation() {
		for(int i = 0; i < sprites.length; i++) {
			sprites[i].updateCurrentAnimation();
		}
	}
	
	public void changeFrameTimesBy(int n) {
		for(int i = 0; i < sprites.length; i++) {
			sprites[i].changeFrameTimesBy(n);
		}
	}
	
	public CompoundAnimatedSprite getCopy() {
		AnimatedSprite[] temp = new AnimatedSprite[sprites.length];
		for(int i = 0; i < sprites.length; i++) {
			temp[i] = sprites[i].getCopy();
		}
		return new CompoundAnimatedSprite(temp);
	}
}
