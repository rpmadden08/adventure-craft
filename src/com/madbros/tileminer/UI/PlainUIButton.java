package com.madbros.tileminer.UI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.madbros.tileminer.Sprites.*;
import com.madbros.tileminer.Utils.ButtonFunction;
import com.madbros.tileminer.Utils.Rect;

public class PlainUIButton extends UIButton {
	StaticSprite sprite = Sprites.pixel;
	public Color buttonColor = Color.DARK_GRAY;
	Color highlight = new Color(1.0f, 1.0f, 1.0f, 0.3f);
	Color pressedColor = new Color(0.0f, 0.0f, 0.0f, 0.3f);
	private StaticSprite buttonLeft = Sprites.sprites.get(Sprites.MM_BUTTON_LEFT);
	private StaticSprite buttonMiddle = Sprites.sprites.get(Sprites.MM_BUTTON_MIDDLE);
	private StaticSprite buttonRight = Sprites.sprites.get(Sprites.MM_BUTTON_RIGHT);
	
	private StaticSprite buttonLeftHover = Sprites.sprites.get(Sprites.MM_BUTTON_LEFT_HOVER);
	private StaticSprite buttonMiddleHover = Sprites.sprites.get(Sprites.MM_BUTTON_MIDDLE_HOVER);
	private StaticSprite buttonRightHover = Sprites.sprites.get(Sprites.MM_BUTTON_RIGHT_HOVER);
	
	private StaticSprite buttonLeftPushed = Sprites.sprites.get(Sprites.MM_BUTTON_LEFT_PUSHED);
	private StaticSprite buttonMiddlePushed = Sprites.sprites.get(Sprites.MM_BUTTON_MIDDLE_PUSHED);
	private StaticSprite buttonRightPushed = Sprites.sprites.get(Sprites.MM_BUTTON_RIGHT_PUSHED);
	
	public PlainUIButton(int x, int y, int w, int h, String s, ButtonFunction func, SpriteBatch batch) {
		super(x, y, w, h, s, func, batch);

		rect = new Rect(text.getX(rect), text.getY(rect), text.getW()+10, text.getH()+10);
	}
	
	public PlainUIButton(Rect r, String s, Color fontColor, Color buttonColor, Color highlight, Color pressedColor, ButtonFunction func, SpriteBatch batch) {
		super(r.x, r.y, r.w, r.h, s, func, batch);
		this.fontColor = fontColor;
		this.buttonColor = buttonColor;
		this.highlight = highlight;
		this.pressedColor = pressedColor;
	}

	@Override
	public void render() {
		Rect textRect = new Rect(rect.x, rect.y-2, rect.w, rect.h);
		if(mouseIsHovering && buttonIsPressedDown){
			buttonLeftPushed.draw(rect.x-6, rect.y-1,0);
			buttonMiddlePushed.draw(rect.x-6+buttonLeft.getWidth(), rect.y, 0, rect.w-37, buttonMiddle.getHeight());
			buttonRightPushed.draw(rect.x-6+buttonLeft.getWidth()+rect.w-37, rect.y-1, 0);
			text.drawCenter(textRect, fontColor);
		}
		else if(mouseIsHovering) {
			buttonLeftHover.draw(rect.x-8, rect.y-3,0);
			buttonMiddleHover.draw(rect.x-8+buttonLeftHover.getWidth(), rect.y-2, 0, rect.w-37, buttonMiddleHover.getHeight());
			buttonRightHover.draw(rect.x-6+buttonLeft.getWidth()+rect.w-37, rect.y-3, 0);
			text.drawCenter(textRect,  fontColor);
		} else {
			buttonLeft.draw(rect.x-6, rect.y-1,0);
			buttonMiddle.draw(rect.x-6+buttonLeft.getWidth(), rect.y, 0, rect.w-37, buttonMiddle.getHeight());
			buttonRight.draw(rect.x-6+buttonLeft.getWidth()+rect.w-37, rect.y-1, 0);
			text.drawCenter(textRect, fontColor);
			
			
		}
		Sprites.pixel.setColor(Color.WHITE);
	}
}
