package com.madbros.adventurecraft.Utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class TextFieldA {
	private TextField f;
	private String s;
	private float w;
	private float h;
	private SpriteBatch batch;
	
	public TextFieldA(TextField f, String s, SpriteBatch batch) {
		this.f = f;
		this.s = s;
		this.batch = batch;
//		w
		//TextBounds b = f..getBounds(s);
		w = f.getPrefWidth();
		h = f.getPrefHeight();
	}
	
	public String getString() {
		return s;
	}
	
	public void setString(String newString) {
		s = newString;
		//TextBounds b = f.getBounds(s);
		w = f.getPrefWidth();
		h = f.getPrefHeight();
	}
	
	public void drawString(Color fontColor) {
		f.setColor(fontColor);
		f.draw(batch, 100);
	}
	
	public void drawCenter(Color fontColor) {
		f.setColor(fontColor);
		f.draw(batch, 100);
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
