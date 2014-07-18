package com.madbros.tileminer.Sprites;

import java.util.ArrayList;
import java.util.Collections;

import com.badlogic.gdx.graphics.Color;
import com.madbros.tileminer.AtlasComparator;
import com.madbros.tileminer.Utils.*;

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
	
	public void draw(float x, float y, float w, float h, float newX, float newY, float newW, float newH) {
		for(AnimatedSprite sprite : sprites) {
			sprite.draw(x, y, w, h);
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
	
	public void setCurrentFrame(int frame) {
		for(AnimatedSprite sprite : sprites) {
			sprite.setCurrentFrame(frame);
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
	
	public void resetFrames() {
		for(AnimatedSprite sprite : sprites) {
			sprite.resetFrame();
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
	
	public void setAnimColor(Color c) {
		for(AnimatedSprite s : sprites) {
			//this.sprites.add(s.getCopy());
			s.setColor(c);
		}
	}
}
