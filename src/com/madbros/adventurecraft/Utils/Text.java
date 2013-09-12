package com.madbros.adventurecraft.Utils;

import org.newdawn.slick.*;


public class Text {
	private Font f;
	private String s;
	private int w;
	private int h;
	
	public Text(Font f, String s) {
		this.f = f;
		this.s = s;
		w = f.getWidth(s);
		h = f.getHeight(s);
	}
	
	public String getString() {
		return s;
	}
	
	public void setString(String newString) {
		s = newString;
		w = f.getWidth(s);
		h = f.getHeight(s);
	}
	
	public void drawString(Rect r, Color fontColor) {
		f.drawString(r.x, r.y, s, fontColor);
	}
	
	public void drawCenter(Rect r, Color fontColor) {
		f.drawString(r.x + r.w / 2 - w / 2, r.y + r.h / 2 - h / 2 - 1, s, fontColor);
	}
	
	public int getX(Rect r) {
		return r.x + r.w / 2 - w / 2;
	}
	
	public int getY(Rect r) {
		return r.y + r.h / 2 - h / 2;
	}
	
	public int getW() {
		return w;
	}
	
	public int getH() {
		return h;
	}
}
