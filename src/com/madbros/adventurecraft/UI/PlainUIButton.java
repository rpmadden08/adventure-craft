package com.madbros.adventurecraft.UI;

import org.newdawn.slick.Color;

import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Utils.ButtonFunction;
import com.madbros.adventurecraft.Utils.Rect;

public class PlainUIButton extends UIButton{
	Sprite texture = Textures.pixel;
	Color buttonColor = Color.darkGray;
	Color highlight = new Color(1.0f, 1.0f, 1.0f, 0.3f);
	Color pressedColor = new Color(0.0f, 0.0f, 0.0f, 0.3f);
	
	public PlainUIButton(int x, int y, int w, int h, String s, ButtonFunction func) {
		super(x, y, w, h, s, func);
	}
	
	public PlainUIButton(Rect r, String s, Color fontColor, Color buttonColor, Color highlight, Color pressedColor, ButtonFunction func) {
		super(r.x, r.y, r.w, r.h, s, func);
		this.fontColor = fontColor;
		this.buttonColor = buttonColor;
		this.highlight = highlight;
		this.pressedColor = pressedColor;
	}

	@Override
	public void render() {
		buttonColor.bind();
		texture.draw(rect);
		if(mouseIsHovering && buttonIsPressedDown) {
			pressedColor.bind();
			texture.draw(rect);
		} else if(mouseIsHovering) {
			highlight.bind();
			texture.draw(rect);
		}
		Color.white.bind();
	}
}
