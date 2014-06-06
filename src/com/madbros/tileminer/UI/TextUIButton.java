package com.madbros.tileminer.UI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.ButtonFunction;
import com.madbros.tileminer.Utils.Rect;

public class TextUIButton extends UIButton {
	private Color highlight = new Color(1.0f, 1.0f, 1.0f, 1f);
	private Color pressedColor = new Color(0.6f, 0.6f, 0.6f, 1f);
	
	public TextUIButton(int x, int y, int w, int h, String s, ButtonFunction func, SpriteBatch batch) {
		super(x, y, w, h, s, func, batch);
		fontColor = new Color(1f, 1f, 1f, 1f);
		rect = new Rect(text.getX(rect), text.getY(rect), text.getW()+16, text.getH()+10);
	}

	public TextUIButton(Rect r, String s, Color fontColor, Color highlight, Color pressedColor, ButtonFunction func, SpriteBatch batch) {
		super(r.x, r.y, r.w, r.h, s, func, batch);
		this.fontColor = fontColor;
		this.highlight = highlight;
		this.pressedColor = pressedColor;
	}
	
	@Override
	public void renderText() {
		if(mouseIsHovering && buttonIsPressedDown){
			Sprites.pixel.setColor(Color.WHITE);
			Sprites.pixel.draw(rect.x-2, rect.y-2, 0, rect.w+4, rect.h+4);
			Sprites.pixel.setColor(0.431f, 0.247f, 0.176f, 1f);
			Sprites.pixel.draw(rect, 0);
			text.drawCenter(rect, fontColor);
			text.drawCenter(rect, pressedColor);
		}
		else if(mouseIsHovering) {
			Sprites.pixel.setColor(Color.BLACK);
			Sprites.pixel.draw(rect.x-2, rect.y-2, 0, rect.w+4, rect.h+4);
			Sprites.pixel.setColor(0.643f, 0.396f, 0.302f, 1f);
			Sprites.pixel.draw(rect, 0);
			text.drawCenter(rect, highlight);
		} else {
			Sprites.pixel.setColor(Color.BLACK);
			Sprites.pixel.draw(rect.x-2, rect.y-2, 0, rect.w+4, rect.h+4);
			Sprites.pixel.setColor(0.431f, 0.247f, 0.176f, 1f);
			Sprites.pixel.draw(rect, 0);
			text.drawCenter(rect, fontColor);
		}
		Sprites.pixel.setColor(Color.WHITE);
	}
	
	public void disableButton() {
		//Currently only disables the colors...
		this.fontColor = Color.GRAY;
		this.highlight = Color.GRAY;
		this.pressedColor =  Color.GRAY;
				
	}
	
}
