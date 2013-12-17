package com.madbros.adventurecraft.Sprites;

import java.util.ArrayList;
import java.util.Collections;

import com.madbros.adventurecraft.AtlasComparator;
import com.madbros.adventurecraft.Utils.*;

public class CompoundAnimatedSprite extends CompoundSprite {
	protected ArrayList<AnimatedSprite> sprites = new ArrayList<AnimatedSprite>();
	
	/*********************************** Constructors ***********************************/
	public CompoundAnimatedSprite(AnimatedSprite sprite) {
		this.sprites.add(sprite.getCopy());
	}
	
	public CompoundAnimatedSprite(AnimatedSprite[] sprites) {
		for(AnimatedSprite s : sprites) {
			this.sprites.add(s.getCopy());
		}
	}
	
	public CompoundAnimatedSprite(ArrayList<AnimatedSprite> sprites) {
		for(AnimatedSprite s : sprites) {
			this.sprites.add(s.getCopy());
		}
	}
	
	/*********************************** Drawing ***********************************/
	@Override
	public void draw(float x, float y, float z) {
		for(AnimatedSprite sprite : sprites) {
			sprite.draw(x, y, z);
		}
	}
	
	@Override
	public void draw(Rect r, float z) {
		for(AnimatedSprite sprite : sprites) {
			sprite.draw(r, z);
		}
	}

	@Override
	public void draw(float x, float y, float z, float w, float h) {
		for(AnimatedSprite sprite : sprites) {
			sprite.draw(x, y, z, w, h);
		}
	}
	
	@Override
	public void draw(float x, float y, float z, float scale) {
		for(AnimatedSprite sprite : sprites) {
			sprite.draw(x, y, z, scale);
		}
	}
	
	/*********************************** Other ***********************************/
	public void addSprite(AnimatedSprite sprite) {
		sprites.add(sprite);
	}
	
	public void removeSprite(AnimatedSprite sprite) {
		sprites.remove(sprite);
	}

	public void changeAnimationTo(int animationId) {
		for(AnimatedSprite sprite : sprites) {
			sprite.changeAnimationTo(animationId);
		}
	}

	public void updateCurrentAnimation() {
		for(AnimatedSprite sprite : sprites) {
			sprite.updateCurrentAnimation();
		}
	}
	
	public void changeFrameTimesBy(int n) {
		for(AnimatedSprite sprite : sprites) {
			sprite.changeFrameTimesBy(n);
		}
	}
	
	public void changeFrameTimes(int n) {
		for(AnimatedSprite sprite : sprites) {
			sprite.changeFrameTimes(n);
		}
	}
	
	public int getCurrentAnimation() {
		return sprites.get(0).getCurrentAnimation();
	}
	public int getCurrentAnimationFrame() {
		return sprites.get(0).getCurrentAnimationFrame();
	}
	
	public void sort() {
		Collections.sort(sprites, new AtlasComparator());
	}
	
	public CompoundAnimatedSprite getCopy() {
		ArrayList<AnimatedSprite> temp = new ArrayList<AnimatedSprite>();
		
		for(AnimatedSprite sprite : sprites) {
			temp.add(sprite.getCopy());
		}

		return new CompoundAnimatedSprite(temp);
	}
}
