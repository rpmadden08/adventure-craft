package com.madbros.tileminer.UI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.madbros.tileminer.Sprites.*;
import com.madbros.tileminer.Utils.ButtonFunction;

public class InGameMenuUIButton extends UIButton {
	StaticSprite sprite = Sprites.pixel;
	public Color buttonColor = Color.DARK_GRAY;
	Color highlight = new Color(1.0f, 1.0f, 1.0f, 0.3f);
	Color pressedColor = new Color(0.0f, 0.0f, 0.0f, 0.3f);
	public String spriteString;
	public int iconX;
	public int iconY;
	private StaticSprite button;
	private StaticSprite buttonHover;
	private StaticSprite buttonPushed;
	private StaticSprite buttonIcon;
	
	public InGameMenuUIButton(int x, int y, int w, int h, String s, ButtonFunction func, SpriteBatch batch, int iconX, int iconY, int color) {
		super(x, y, w, h, s, func, batch);
		spriteString = s;
		this.iconX = iconX;
		this.iconY = iconY;
		if(color == 0) {
			button = Sprites.sprites.get(Sprites.GREEN_BUTTON);
			buttonHover = Sprites.sprites.get(Sprites.GREEN_BUTTON_HOVER);
			buttonPushed = Sprites.sprites.get(Sprites.GREEN_BUTTON_PUSHED);
			buttonIcon = Sprites.sprites.get(spriteString);
		} else {
			button = Sprites.sprites.get(Sprites.RED_BUTTON);
			buttonHover = Sprites.sprites.get(Sprites.RED_BUTTON_HOVER);
			buttonPushed = Sprites.sprites.get(Sprites.RED_BUTTON_PUSHED);
			buttonIcon = Sprites.sprites.get(spriteString);
		}
		

		//rect = new Rect(text.getX(rect), text.getY(rect), text.getW()+4, text.getH());
	}
	
//	public InGameMenuUIButton(Rect r, String s, Color fontColor, Color buttonColor, Color highlight, Color pressedColor, ButtonFunction func, SpriteBatch batch, String buttonSprite) {
//		super(r.x, r.y, r.w, r.h, s, func, batch);
//		this.fontColor = fontColor;
//		this.buttonColor = buttonColor;
//		this.highlight = highlight;
//		this.pressedColor = pressedColor;
//
//		
//	}

	@Override
	public void render() {
		
	if(mouseIsHovering && buttonIsPressedDown) {
		buttonPushed.draw(rect.x,rect.y, 0);
		buttonIcon.draw(iconX,iconY, 0);
	} else if(mouseIsHovering) {
		buttonHover.draw(rect.x,rect.y, 0);
		buttonIcon.draw(iconX,iconY, 0);
	} else {
		button.draw(rect.x,rect.y, 0);
		buttonIcon.draw(iconX,iconY, 0);
	}
		
//		if(mouseIsHovering && buttonIsPressedDown) {
//			Sprites.pixel.setColor(Color.WHITE);
//			Sprites.pixel.draw(rect.x-2, rect.y-2, 0, rect.w+4, rect.h+4);
//			Sprites.pixel.setColor(pressedColor);
//			Sprites.pixel.draw(rect, 0);
//			text.drawCenter(rect, fontColor);
//			text.drawCenter(rect, pressedColor);
//		} else if(mouseIsHovering) {
//			Sprites.pixel.setColor(Color.BLACK);
//			Sprites.pixel.draw(rect.x-2, rect.y-2, 0, rect.w+4, rect.h+4);
//			Sprites.pixel.setColor(buttonColor);
//			Sprites.pixel.draw(rect, 0);
//			text.drawCenter(rect, highlight);
//		} else {
//			Sprites.pixel.setColor(Color.BLACK);
//			Sprites.pixel.draw(rect.x-2, rect.y-2, 0, rect.w+4, rect.h+4);
//			Sprites.pixel.setColor(buttonColor);
//			Sprites.pixel.draw(rect, 0);
//			text.drawCenter(rect, fontColor);
//		}
		Sprites.pixel.setColor(Color.WHITE);
	}
}
