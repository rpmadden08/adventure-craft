package com.madbros.tileminer.GameObjects;

import com.madbros.tileminer.Sprites.Sprite;
import com.madbros.tileminer.Utils.*;

public class GameObject {
	public Rect absRect;
	public Margin margin;
	public boolean isCollidable;
	public Sprite sprite;
	public int z;
	
	public Rect getCRect() {
		return new Rect(absRect, margin);
	}
	
//absRect
	
//blocks...
//tiles...
//heros...
//npcs...

}
