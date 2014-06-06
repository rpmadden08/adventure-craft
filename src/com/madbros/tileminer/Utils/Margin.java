package com.madbros.tileminer.Utils;

public class Margin {
	public int left, right, top, bottom;
	
	public Margin() {
		left = 0; right = 0; top = 0; bottom = 0;
	}
	
	public Margin(int left, int right, int top, int bottom) {
		this.left = left;
		this.right = right;
		this.top = top;
		this.bottom = bottom;
	}
	
	public int getHorizontalLength() {
		return (left + right);
	}
	
	public int getVerticalLength() {
		return (top + bottom);
	}
}