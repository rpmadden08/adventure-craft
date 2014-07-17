package com.madbros.tileminer.UI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Sprites.StaticSprite;
import com.madbros.tileminer.Utils.ButtonFunction;
import com.madbros.tileminer.Utils.Rect;

public class TextUIButton extends UIButton {
//	private StaticSprite button = Sprites.sprites.get(Sprites.BUTTON);
//	private StaticSprite buttonCorner = Sprites.sprites.get(Sprites.BUTTON_EDGE);
//
//	private StaticSprite buttonSelect = Sprites.sprites.get(Sprites.BUTTON_SELECT);
//	private StaticSprite buttonSelectCorner = Sprites.sprites.get(Sprites.BUTTON_SELECT_EDGE);
	
	private StaticSprite buttonLeft = Sprites.sprites.get(Sprites.MM_BUTTON_LEFT);
	private StaticSprite buttonMiddle = Sprites.sprites.get(Sprites.MM_BUTTON_MIDDLE);
	private StaticSprite buttonRight = Sprites.sprites.get(Sprites.MM_BUTTON_RIGHT);
	
	private StaticSprite buttonLeftHover = Sprites.sprites.get(Sprites.MM_BUTTON_LEFT_HOVER);
	private StaticSprite buttonMiddleHover = Sprites.sprites.get(Sprites.MM_BUTTON_MIDDLE_HOVER);
	private StaticSprite buttonRightHover = Sprites.sprites.get(Sprites.MM_BUTTON_RIGHT_HOVER);
	
	private StaticSprite buttonLeftPushed = Sprites.sprites.get(Sprites.MM_BUTTON_LEFT_PUSHED);
	private StaticSprite buttonMiddlePushed = Sprites.sprites.get(Sprites.MM_BUTTON_MIDDLE_PUSHED);
	private StaticSprite buttonRightPushed = Sprites.sprites.get(Sprites.MM_BUTTON_RIGHT_PUSHED);

	
	public TextUIButton(double x, double y, double w, double h, String s, ButtonFunction func, SpriteBatch batch) {
		super(x, y, w, h, s, func, batch);
		fontColor = new Color(1f, 1f, 1f, 1f);
		rect = new Rect(text.getX(rect), text.getY(rect), text.getW()+16, text.getH()+10);
	}

	public TextUIButton(Rect r, String s, Color fontColor, Color highlight, Color pressedColor, ButtonFunction func, SpriteBatch batch) {
		super(r.x, r.y, r.w, r.h, s, func, batch);
		this.fontColor = fontColor;
	}
	
	@Override
	public void renderText() {
		Rect textRect = new Rect(rect.x, rect.y-2, rect.w, rect.h);
		if(mouseIsHovering && buttonIsPressedDown){
			buttonLeftPushed.draw((int)rect.x-6, (int)rect.y-1,0);
			buttonMiddlePushed.draw((int)rect.x-6+buttonLeft.getWidth(), (int)rect.y, 0, (int)rect.w-37, buttonMiddle.getHeight());
			buttonRightPushed.draw((int)rect.x-6+buttonLeft.getWidth()+(int)rect.w-37, (int)rect.y-1, 0);
			text.drawCenter(textRect, fontColor);
		}
		else if(mouseIsHovering) {
			buttonLeftHover.draw((int)rect.x-8, (int)rect.y-3,0);
			buttonMiddleHover.draw((int)rect.x-8+buttonLeftHover.getWidth(), (int)rect.y-2, 0, (int)rect.w-37, buttonMiddleHover.getHeight());
			buttonRightHover.draw((int)rect.x-6+buttonLeft.getWidth()+(int)rect.w-37, (int)rect.y-3, 0);
			text.drawCenter(textRect,  fontColor);
		} else {
			buttonLeft.draw((int)rect.x-6, (int)rect.y-1,0);
			buttonMiddle.draw((int)rect.x-6+buttonLeft.getWidth(), (int)rect.y, 0, (int)rect.w-37, buttonMiddle.getHeight());
			buttonRight.draw((int)rect.x-6+buttonLeft.getWidth()+(int)rect.w-37, (int)rect.y-1, 0);
			text.drawCenter(textRect, fontColor);
			
			
		}
		Sprites.pixel.setColor(Color.WHITE);
	}
	
	public void disableButton() {
		//Currently only disables the colors...
		this.fontColor = Color.GRAY;
				
	}
	
}
