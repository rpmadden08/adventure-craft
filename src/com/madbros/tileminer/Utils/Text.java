package com.madbros.tileminer.Utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;

public class Text {
	private BitmapFont f;
	private String s;
	private float w;
	private float h;
	private SpriteBatch batch;
	
	public Text(BitmapFont f, String s, SpriteBatch batch) {
		this.f = f;
		this.s = s;
		this.batch = batch;
//		w
		TextBounds b = f.getBounds(s);
		w = b.width;
		h = b.height * -1;
	}
	
	public String getString() {
		return s;
	}
	
	public void setString(String newString) {
		s = newString;
		TextBounds b = f.getBounds(s);
		w = b.width;
		h = b.height * -1;
	}
	
	public void drawString(Rect r, Color fontColor) {
		f.setColor(fontColor);
		RectInt r2 = r.getRectInt();
		f.draw(batch, s, r2.x, r2.y);
	}
	
	public void drawCenter(Rect r, Color fontColor) {
		f.setColor(fontColor);
		RectInt r2 = r.getRectInt();
		f.draw(batch, s, r2.x + r2.w / 2 - w / 2, r2.y + r2.h / 2 - h / 2 - 1);
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
