package com.madbros.tileminer.UI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.madbros.tileminer.Sprites.*;
import com.madbros.tileminer.Utils.ButtonFunction;
import com.madbros.tileminer.Utils.Rect;

public class InGameMenuUIButton extends UIButton {
	StaticSprite sprite = Sprites.pixel;
	public Color buttonColor = Color.DARK_GRAY;
	Color highlight = new Color(1.0f, 1.0f, 1.0f, 0.3f);
	Color pressedColor = new Color(0.0f, 0.0f, 0.0f, 0.3f);
	
	public InGameMenuUIButton(int x, int y, int w, int h, String s, ButtonFunction func, SpriteBatch batch) {
		super(x, y, w, h, s, func, batch);

		rect = new Rect(text.getX(rect), text.getY(rect), text.getW()+4, text.getH());
	}
	
	public InGameMenuUIButton(Rect r, String s, Color fontColor, Color buttonColor, Color highlight, Color pressedColor, ButtonFunction func, SpriteBatch batch) {
		super(r.x, r.y, r.w, r.h, s, func, batch);
		this.fontColor = fontColor;
		this.buttonColor = buttonColor;
		this.highlight = highlight;
		this.pressedColor = pressedColor;
	}

	@Override
	public void render() {
		if(mouseIsHovering && buttonIsPressedDown) {
			Sprites.pixel.setColor(Color.WHITE);
			Sprites.pixel.draw(rect.x-2, rect.y-2, 0, rect.w+4, rect.h+4);
			Sprites.pixel.setColor(pressedColor);
			Sprites.pixel.draw(rect, 0);
			text.drawCenter(rect, fontColor);
			text.drawCenter(rect, pressedColor);
		} else if(mouseIsHovering) {
			Sprites.pixel.setColor(Color.BLACK);
			Sprites.pixel.draw(rect.x-2, rect.y-2, 0, rect.w+4, rect.h+4);
			Sprites.pixel.setColor(buttonColor);
			Sprites.pixel.draw(rect, 0);
			text.drawCenter(rect, highlight);
		} else {
			Sprites.pixel.setColor(Color.BLACK);
			Sprites.pixel.draw(rect.x-2, rect.y-2, 0, rect.w+4, rect.h+4);
			Sprites.pixel.setColor(buttonColor);
			Sprites.pixel.draw(rect, 0);
			text.drawCenter(rect, fontColor);
		}
		Sprites.pixel.setColor(Color.WHITE);
	}
}
