package com.madbros.tileminer.Utils;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.GameObjects.Actor;

public class Rect {
	public double x;
	public double y;
	public double w;
	public double h;
	
	public Rect(double x, double y, double w, double h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public Rect(int x, int y) {
		this(x, y, TILE_SIZE, TILE_SIZE);
	}
	
	//for getting collision rects from absolute rects
	public Rect(Rect r, Margin m) {
		this(r.x + m.left, r.y + m.top, r.w - m.left - m.right, r.h - m.top - m.bottom);
	}
	
	//for getting screen rects from absolute/collision rects
	public Rect(Rect aRect, Actor c) {
		this(c.sRect.x - (c.absRect.x - aRect.x), c.sRect.y - (c.absRect.y - aRect.y), aRect.w, aRect.h);
	}
	
	public double x1() { return x; }
	
	public double y1() { return y; }
	
	public double x2() { return x + w; }
	
	public double y2() { return y + h; }
	
	public double w() { return w; }
	
	public double h() { return h; }
	
	public double midX() { return x + w/2; }
	
	public double midY() { return y + h/2; }
	
	public boolean detectCollision(Rect r) {
		if(!(x1() >= r.x2() || x2() <= r.x1() || y2() <= r.y1() || y1() >= r.y2())) {
			return true;
		} else {
			return false;
		}
	}
	
	public double getBottomCollisionDiff(Rect r) {
		return y2() - r.y1();
	}
	
	public double getTopCollisionDiff(Rect r) {
		return y1() - r.y2();
	}
	
	public double getLeftCollisionDiff(Rect r) {
		return x1() - r.x2() ;
	}
	
	public double getRightCollisionDiff(Rect r) {
		return x2() - r.x1();
	}
}
