package com.madbros.adventurecraft.Utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;

public class Text {
	private BitmapFont f;
	private String s;
	private float w;
	private float h;
	
	public Text(BitmapFont f, String s) {
		this.f = f;
		this.s = s;
//		w
		TextBounds b = f.getBounds(s);
		w = b.width;
		h = b.height;
	}
	
	public String getString() {
		return s;
	}
	
	public void setString(String newString) {
		s = newString;
		TextBounds b = f.getBounds(s);
		w = b.width;
		h = b.height;
	}
	
	public void drawString(Rect r, Color fontColor) {
		f.setColor(fontColor);
		f.draw(batch, s, r.x, r.y);
	}
	
	public void drawCenter(Rect r, Color fontColor) {
		f.setColor(fontColor);
		f.draw(batch, s, r.x + r.w / 2 - w / 2, r.y + r.h / 2 - h / 2 - 1);
	}
	
	public int getX(Rect r) {
		return (int)(r.x + r.w / 2 - w / 2);
	}
	
	public int getY(Rect r) {
		return (int)(r.y + r.h / 2 - h / 2);
	}
	
	public int getW() {
		return (int)w;
	}
	
	public int getH() {
		return (int)h;
	}
}
