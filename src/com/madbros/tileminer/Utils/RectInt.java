package com.madbros.tileminer.Utils;

import static com.madbros.tileminer.Constants.*;


public class RectInt {
	public int x;
	public int y;
	public int w;
	public int h;
	
	public RectInt(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public RectInt(int x, int y) {
		this(x, y, TILE_SIZE, TILE_SIZE);
	}
	
	//for getting collision rects from absolute rects
	public RectInt(RectInt r, Margin m) {
		this(r.x + m.left, r.y + m.top, r.w - m.left - m.right, r.h - m.top - m.bottom);
	}
	
	//for getting screen rects from absolute/collision rects
//	public RectInt(RectInt aRect, Actor c) {
//		RectInt cSRect = 
//		this(c.sRect.x - (c.absRect.x - aRect.x), c.sRect.y - (c.absRect.y - aRect.y), aRect.w, aRect.h);
//	}
	
	public int x1() { return x; }
	
	public int y1() { return y; }
	
	public int x2() { return x + w; }
	
	public int y2() { return y + h; }
	
	public int w() { return w; }
	
	public int h() { return h; }
	
	public int midX() { return x + w/2; }
	
	public int midY() { return y + h/2; }
	
	public boolean detectCollision(RectInt r) {
		if(!(x1() >= r.x2() || x2() <= r.x1() || y2() <= r.y1() || y1() >= r.y2())) {
			return true;
		} else {
			return false;
		}
	}
	
	public int getBottomCollisionDiff(RectInt r) {
		return y2() - r.y1();
	}
	
	public int getTopCollisionDiff(RectInt r) {
		return y1() - r.y2();
	}
	
	public int getLeftCollisionDiff(RectInt r) {
		return x1() - r.x2() ;
	}
	
	public int getRightCollisionDiff(RectInt r) {
		return x2() - r.x1();
	}
}
