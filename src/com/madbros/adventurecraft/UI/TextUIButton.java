package com.madbros.adventurecraft.UI;

import org.newdawn.slick.Color;

import com.madbros.adventurecraft.Utils.ButtonFunction;
import com.madbros.adventurecraft.Utils.Rect;

public class TextUIButton extends UIButton {
	private Color highlight = new Color(1.0f, 1.0f, 1.0f);
	private Color pressedColor = new Color(0.6f, 0.6f, 0.6f);
	
	public TextUIButton(int x, int y, int w, int h, String s, ButtonFunction func) {
		super(x, y, w, h, s, func);
		fontColor = new Color(0.8f, 0.8f, 0.8f);
		rect = new Rect(text.getX(rect), text.getY(rect), text.getW(), text.getH());
	}

	public TextUIButton(Rect r, String s, Color fontColor, Color highlight, Color pressedColor, ButtonFunction func) {
		super(r.x, r.y, r.w, r.h, s, func);
		this.fontColor = fontColor;
		this.highlight = highlight;
		this.pressedColor = pressedColor;
	}
	
	@Override
	public void renderText() {
		if(mouseIsHovering && buttonIsPressedDown) text.drawCenter(rect, pressedColor);
		else if(mouseIsHovering) text.drawCenter(rect, highlight);
		else text.drawCenter(rect, fontColor);
		Color.white.bind();
	}
	
}
