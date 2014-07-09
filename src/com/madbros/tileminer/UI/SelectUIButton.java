package com.madbros.tileminer.UI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.madbros.tileminer.Sprites.*;
import com.madbros.tileminer.Utils.*;

public class SelectUIButton {
	//TODO: refactor with UIButton
	Rect rect;
	public Text text;
	public int iD;
	private StaticSprite buttonLeft = Sprites.sprites.get(Sprites.MM_BUTTON_LEFT);
	private StaticSprite buttonMiddle = Sprites.sprites.get(Sprites.MM_BUTTON_MIDDLE);
	private StaticSprite buttonRight = Sprites.sprites.get(Sprites.MM_BUTTON_RIGHT);
	
//	private StaticSprite buttonLeftHover = Sprites.sprites.get(Sprites.MM_BUTTON_LEFT_HOVER);
//	private StaticSprite buttonMiddleHover = Sprites.sprites.get(Sprites.MM_BUTTON_MIDDLE_HOVER);
//	private StaticSprite buttonRightHover = Sprites.sprites.get(Sprites.MM_BUTTON_RIGHT_HOVER);
	
	private StaticSprite buttonLeftPushed = Sprites.sprites.get(Sprites.MM_BUTTON_LEFT_PUSHED);
	private StaticSprite buttonMiddlePushed = Sprites.sprites.get(Sprites.MM_BUTTON_MIDDLE_PUSHED);
	private StaticSprite buttonRightPushed = Sprites.sprites.get(Sprites.MM_BUTTON_RIGHT_PUSHED);
	
	Color fontColor = Color.WHITE;
	
	StaticSprite sprite = Sprites.pixel;
	Color buttonColor = new Color(0.431f, 0.247f, 0.176f, 1f);
	Color pressedColor = new Color(0.0f, 0.0f, 0.0f, 0.3f);
	
	public boolean mouseIsHovering = false;
	public boolean buttonIsPressedDown = false;
	
	public SelectUIButton(int x, int y, int w, int h, String s, SpriteBatch batch) {
		rect = new Rect(x, y+4, w+10, h);
		text = new Text(Sprites.font, s, batch);
	}
	
	public void render() {
		
		
		if(buttonIsPressedDown) {
			buttonLeftPushed.draw(rect.x-6, rect.y-1,0);
			buttonMiddlePushed.draw(rect.x-6+buttonLeft.getWidth(), rect.y, 0, rect.w-37, buttonMiddle.getHeight());
			buttonRightPushed.draw(rect.x-6+buttonLeft.getWidth()+rect.w-37, rect.y-1, 0);
			//text.drawCenter(textRect, highlight);
		} else {
			
			buttonLeft.draw(rect.x-6, rect.y-1,0);
			buttonMiddle.draw(rect.x-6+buttonLeft.getWidth(), rect.y, 0, rect.w-37, buttonMiddle.getHeight());
			buttonRight.draw(rect.x-6+buttonLeft.getWidth()+rect.w-37, rect.y-1, 0);
			//text.drawCenter(textRect, fontColor);
			

		}
		Sprites.pixel.setColor(Color.WHITE);
	}

	public void renderText() {
		text.drawCenter(rect, fontColor);
	}
	
	public boolean handleMouseInput(boolean leftMousePressed, boolean leftMouseReleased) {
		if(rect.detectCollision(Helpers.getMouseRect())) mouseIsHovering = true;
		else mouseIsHovering = false;
		
		if(leftMousePressed && mouseIsHovering) {
			didPressDown();
			return true;
		} else {
			return false;
		}
	}
	
	public void didPressDown() {
		buttonIsPressedDown = true;
	}
}
