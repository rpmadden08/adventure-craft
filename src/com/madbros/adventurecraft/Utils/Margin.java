package com.madbros.adventurecraft.Utils;

public class Margin {
	public int left, right, top, bottom;
	
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