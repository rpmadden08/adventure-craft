package com.madbros.tileminer.UI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.ButtonFunction;
import com.madbros.tileminer.Utils.Helpers;
import com.madbros.tileminer.Utils.Rect;
import com.madbros.tileminer.Utils.Text;

public abstract class UIButton {
	Rect rect;
	Text text;
	
	ButtonFunction buttonFunction;
	
	Color fontColor = Color.WHITE;
	
	public boolean mouseIsHovering = false;
	public boolean buttonIsPressedDown = false;
	
	public UIButton(int x, int y, int w, int h, String s, ButtonFunction func, SpriteBatch batch) {
		rect = new Rect(x, y, w, h);
		text = new Text(Sprites.arial24, s, batch);
		
		buttonFunction = func;
	}
	
	public void render() {}

	public void renderText() {
		Rect textRect = new Rect(rect.x, rect.y-2, rect.w, rect.h);
		text.drawCenter(textRect, fontColor);
	}
	
	public void handleMouseInput(boolean leftMousePressed, boolean leftMouseUp) {
		if(leftMousePressed && mouseIsHovering) didPressDown();
		
		if(mouseIsHovering && leftMouseUp && buttonIsPressedDown) {
			didPressUp();
			buttonIsPressedDown = false;
		}
		
		if(!leftMousePressed) buttonIsPressedDown = false;
	}
	
	public void handleMouseMove(int x, int y) {
		if(rect.detectCollision(Helpers.getMouseRect())) {
			mouseIsHovering = true;
		}
		else mouseIsHovering = false;
	}
	
//	public void handleMouseUp(int x, int y, int button) {
//		if(mouseIsHovering && button == Input.Buttons.LEFT && buttonIsPressedDown) {
//			didPressUp();
//			buttonIsPressedDown = false;
//		}
//	}

	public void didPressDown() {
		buttonIsPressedDown = true;
		//for press down function, just extend this class, add a pressDownFunction ButtonFunction object, and invoke it in the didPressDown function
	}
	
	public void didPressUp() {
		buttonFunction.invoke();
	}
	
	public void setString(String s) {
		text.setString(s);
	}
	public void disableButton() {
		
	}
}
