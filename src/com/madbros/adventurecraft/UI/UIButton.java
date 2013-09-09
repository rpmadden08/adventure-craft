package com.madbros.adventurecraft.UI;

import org.lwjgl.input.*;
import org.newdawn.slick.*;

import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Utils.ButtonFunction;
import com.madbros.adventurecraft.Utils.Helpers;
import com.madbros.adventurecraft.Utils.Rect;
import com.madbros.adventurecraft.Utils.Text;

import static com.madbros.adventurecraft.Constants.*;

public abstract class UIButton {
	Rect rect;
	Text text;
	
	ButtonFunction buttonFunction;
	
	Color fontColor = Color.white;
	
	public boolean mouseIsHovering = false;
	public boolean buttonIsPressedDown = false;
	
	public UIButton(int x, int y, int w, int h, String s, ButtonFunction func) {
		rect = new Rect(x, y, w, h);
		text = new Text(Sprites.font, s);
		buttonFunction = func;
	}
	
	public void render() {}

	public void renderText() {
		text.drawCenter(rect, fontColor);
	}
	
	public void handleMouseInput(boolean leftMouseButtonWasPressed, boolean leftMouseButtonWasReleased) {
		if(rect.detectCollision(Helpers.getMouseRect())) mouseIsHovering = true;
		else mouseIsHovering = false;
		
		if(leftMouseButtonWasPressed && mouseIsHovering) didPressDown();
		
		if(mouseIsHovering && leftMouseButtonWasReleased && buttonIsPressedDown) didPressUp();
		
		if(!Mouse.isButtonDown(LEFT_MOUSE_BUTTON)) buttonIsPressedDown = false;
	}

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
}
