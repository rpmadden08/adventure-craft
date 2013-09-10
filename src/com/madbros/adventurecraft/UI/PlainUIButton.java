package com.madbros.adventurecraft.UI;

import org.newdawn.slick.Color;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprites.*;
import com.madbros.adventurecraft.Utils.ButtonFunction;
import com.madbros.adventurecraft.Utils.Rect;

public class PlainUIButton extends UIButton{
	StaticSprite sprite = Sprites.pixel;
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

		sprite.draw(rect, Z_BUTTONS);
		if(mouseIsHovering && buttonIsPressedDown) {
			pressedColor.bind();
			sprite.draw(rect, Z_BUTTONS);
		} else if(mouseIsHovering) {
			highlight.bind();
			sprite.draw(rect, Z_BUTTONS_HIGHLIGHT);
		}
		Color.white.bind();
	}
}
