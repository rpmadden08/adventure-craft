package com.madbros.tileminer.UI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Sprites.StaticSprite;
import com.madbros.tileminer.Utils.ButtonFunction;
import com.madbros.tileminer.Utils.Rect;
import com.madbros.tileminer.Utils.RectInt;

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

	
	public TextUIButton(int x, int y, int w, int h, String s, ButtonFunction func, SpriteBatch batch) {
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
		RectInt rect2 = rect.getRectInt();
		Rect textRect = new Rect(rect2.x, rect2.y-2, rect2.w, rect2.h);
		if(mouseIsHovering && buttonIsPressedDown){
			buttonLeftPushed.draw(rect2.x-6, rect2.y-1,0);
			buttonMiddlePushed.draw(rect2.x-6+buttonLeft.getWidth(), rect2.y, 0, rect2.w-37, buttonMiddle.getHeight());
			buttonRightPushed.draw(rect2.x-6+buttonLeft.getWidth()+rect2.w-37, rect2.y-1, 0);
			text.drawCenter(textRect, fontColor);
		}
		else if(mouseIsHovering) {
			buttonLeftHover.draw(rect2.x-8, rect2.y-3,0);
			buttonMiddleHover.draw(rect2.x-8+buttonLeftHover.getWidth(), rect2.y-2, 0, rect2.w-37, buttonMiddleHover.getHeight());
			buttonRightHover.draw(rect2.x-6+buttonLeft.getWidth()+rect2.w-37, rect2.y-3, 0);
			text.drawCenter(textRect,  fontColor);
		} else {
			buttonLeft.draw(rect2.x-6, rect2.y-1,0);
			buttonMiddle.draw(rect2.x-6+buttonLeft.getWidth(), rect2.y, 0, rect2.w-37, buttonMiddle.getHeight());
			buttonRight.draw(rect2.x-6+buttonLeft.getWidth()+rect2.w-37, rect2.y-1, 0);
			text.drawCenter(textRect, fontColor);
			
			
		}
		Sprites.pixel.setColor(Color.WHITE);
	}
	
	public void disableButton() {
		//Currently only disables the colors...
		this.fontColor = Color.GRAY;
				
	}
	
}
