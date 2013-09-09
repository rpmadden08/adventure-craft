package com.madbros.adventurecraft;

import java.util.HashMap;

import com.madbros.adventurecraft.Utils.*;

public class AnimatedSprite extends Sprite {
	private HashMap<Integer, Animation> animations = new HashMap<Integer, Animation>();
	private Animation currentAnimation;
	
	/*********************************** Constructors ***********************************/
	public AnimatedSprite(Animation animation) {
		this(new Animation[]{animation});
	}
	
	public AnimatedSprite(Animation[] animations) {
		for(int i = 0; i < animations.length; i++) {
			addAnimation(animations[i]);
		}
		currentAnimation = animations[0];
	}
	
	/*********************************** Drawing ***********************************/
	@Override
	public void draw(float x, float y, float z) {
		currentAnimation.draw(x, y, z);
	}
	
	@Override
	public void draw(Rect r, float z) {
		currentAnimation.draw(r, z);
	}
	
	@Override
	public void draw(float x, float y, float z, float w, float h) {
		currentAnimation.draw(x, y, z, w, h);
	}
	
	@Override
	public void draw(float x, float y, float z, float scale) {
		currentAnimation.draw(x, y, z, scale);
	}
	
	/*********************************** Other ***********************************/
	private void addAnimation(Animation animation) {
		animations.put(animation.id, animation);
	}
	
	public void changeAnimationTo(int animationId) {
		currentAnimation.resetFrame();
		currentAnimation = animations.get(animationId);
	}
	
	public void updateCurrentAnimation() {
		currentAnimation.updateFrame();
	}
	
	public void changeFrameTimesBy(int n) {
		for (int key : animations.keySet()) {
		    animations.get(key).changeFrameTimesBy(n);
		}
	}
	
	public AnimatedSprite getCopy() {
		Animation[] anims = new Animation[animations.size()];
		int i = 0;
		for (int key : animations.keySet()) {
		    anims[i] = animations.get(key).getCopy();
		    i++;
		}
		return new AnimatedSprite(anims);
	}
}