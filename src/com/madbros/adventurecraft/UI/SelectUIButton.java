package com.madbros.adventurecraft.UI;

import org.newdawn.slick.Color;

import com.madbros.adventurecraft.*;
import static com.madbros.adventurecraft.Constants.*;
import com.madbros.adventurecraft.Utils.*;

public class SelectUIButton {
	//TODO: refactor with UIButton
	Rect rect;
	public Text text;
	
	Color fontColor = Color.white;
	
	StaticSprite texture = Textures.pixel;
	Color buttonColor = Color.darkGray;
	Color pressedColor = new Color(0.0f, 0.0f, 0.0f, 0.3f);
	
	public boolean mouseIsHovering = false;
	public boolean buttonIsPressedDown = false;
	
	public SelectUIButton(int x, int y, int w, int h, String s) {
		rect = new Rect(x, y, w, h);
		text = new Text(Textures.font, s);
	}
	
	public void render() {
		buttonColor.bind();
		texture.draw(rect, Z_BUTTONS);
		if(buttonIsPressedDown) {
			pressedColor.bind();
			texture.draw(rect, Z_BUTTONS);
		}
		Color.white.bind();
	}

	public void renderText() {
		text.drawCenter(rect, fontColor);
	}
	
	public boolean handleMouseInput(boolean leftMouseButtonWasPressed, boolean leftMouseButtonWasReleased) {	
		if(rect.detectCollision(Helpers.getMouseRect())) mouseIsHovering = true;
		else mouseIsHovering = false;
		
		if(leftMouseButtonWasPressed && mouseIsHovering) {
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
