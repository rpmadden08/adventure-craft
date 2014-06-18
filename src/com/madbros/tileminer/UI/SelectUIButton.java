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
	private StaticSprite button = Sprites.sprites.get(Sprites.BUTTON);
	private StaticSprite buttonCorner = Sprites.sprites.get(Sprites.BUTTON_EDGE);
	private StaticSprite buttonSelect = Sprites.sprites.get(Sprites.BUTTON_SELECT);
	private StaticSprite buttonSelectCorner = Sprites.sprites.get(Sprites.BUTTON_SELECT_EDGE);
	
	Color fontColor = Color.WHITE;
	
	StaticSprite sprite = Sprites.pixel;
	Color buttonColor = new Color(0.431f, 0.247f, 0.176f, 1f);
	Color pressedColor = new Color(0.0f, 0.0f, 0.0f, 0.3f);
	
	public boolean mouseIsHovering = false;
	public boolean buttonIsPressedDown = false;
	
	public SelectUIButton(int x, int y, int w, int h, String s, SpriteBatch batch) {
		rect = new Rect(x, y, w+10, h-4);
		text = new Text(Sprites.font, s, batch);
	}
	
	public void render() {
		
		
		if(buttonIsPressedDown) {
			Sprites.pixel.setColor(Color.BLACK);
//			Sprites.pixel.draw(rect.x-2, rect.y-2, 0, rect.w+4, rect.h+4);
//			Sprites.pixel.setColor(0.643f, 0.396f, 0.302f, 1f);
			Sprites.pixel.setColor(0.345f, 0.345f, 0.345f, 1f);
			Sprites.pixel.draw(rect, 0);
//			text.drawCenter(textRect, highlight);
			buttonSelectCorner.draw(rect.x-2, rect.y-2, 0);
			buttonSelect.draw(rect.x+5, rect.y-2, 0, rect.w-10, buttonSelect.getHeight());
			buttonSelectCorner.rotate90(false);
			buttonSelectCorner.draw(rect.x+rect.w-5, rect.y-2, 0);
			buttonSelect.rotate90(true);
			buttonSelect.draw(rect.x+rect.w-4, rect.y+5, 0, buttonSelect.getHeight(), rect.h-10);
			buttonSelectCorner.rotate90(false);
			buttonSelectCorner.draw(rect.x+rect.w-5, rect.y+rect.h-5, 0);
			buttonSelect.rotate90(false);
			buttonSelect.draw(rect.x+5, rect.y+rect.h-4, 0, rect.w-10, buttonSelect.getHeight());
			buttonSelectCorner.rotate90(false);
			buttonSelectCorner.draw(rect.x-2, rect.y+rect.h-5, 0);
			buttonSelect.rotate90(false);
			buttonSelect.draw(rect.x-2, rect.y+5, 0, buttonSelect.getHeight(), rect.h-10);
			buttonSelectCorner.rotate90(false);
			buttonSelect.rotate90(false);
		} else {
			Sprites.pixel.setColor(0.216f, 0.216f, 0.216f, 1f);
			Sprites.pixel.draw(rect, 0);
			
			button.setColor(0.886f, 0.914f, 0.984f, 1f);
			buttonCorner.draw(rect.x-2, rect.y-2, 0);
			button.draw(rect.x+3, rect.y-2, 0, rect.w-6, button.getHeight());
			buttonCorner.rotate90(true);
			buttonCorner.draw(rect.x+rect.w-3, rect.y-2, 0);
			button.draw(rect.x+rect.w, rect.y+3, 0, button.getHeight(), rect.h-6);
			buttonCorner.rotate90(true);
			buttonCorner.draw(rect.x+rect.w-3, rect.y+rect.h-3, 0);
			button.draw(rect.x+3, rect.y+rect.h, 0, rect.w-6, button.getHeight());
			buttonCorner.rotate90(true);
			buttonCorner.draw(rect.x-2, rect.y+rect.h-3, 0);
			button.draw(rect.x-2, rect.y+3, 0, button.getHeight(), rect.h-6);
			buttonCorner.rotate90(true);
			buttonSelect.setColor(0.886f, 0.914f, 0.984f, 1f);
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
