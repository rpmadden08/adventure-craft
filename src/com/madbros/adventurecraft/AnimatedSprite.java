package com.madbros.adventurecraft;

import java.util.HashMap;

public class AnimatedSprite extends Sprite {
	private HashMap<String, Animation> animations = new HashMap<String, Animation>();
	private Animation currentAnimation;
	private String currentAnimationName;
	
	public AnimatedSprite(String[] animationNames, Animation[] animations) {
		for(int i = 0; i < animationNames.length; i++) {
			addAnimation(animationNames[i], animations[i]);
		}
		currentAnimation = animations[0];
		currentAnimationName = animationNames[0];
	}
	
	public void addAnimation(String animationName, Animation animation) {
		animations.put(animationName, animation);
	}
	
	public void changeAnimationTo(String animationName) {
		currentAnimationName = animationName;
		currentAnimation.resetFrame();
		currentAnimation = animations.get(animationName);
	}

	public void draw(float x, float y, float z, float delta) {
		currentAnimation.draw(x, y, z, delta);
	}
	
	public void draw(float x, float y, float z, float w, float h, float delta) {
		currentAnimation.draw(x, y, z, w, h, delta);
	}
	
	public String getCurrentAnimationName() {
		return currentAnimationName;
	}
	
	public void changeFrameTimesBy(int n) {
		for (String key : animations.keySet()) {
		    animations.get(key).changeFrameTimesBy(n);
		}
	}
}