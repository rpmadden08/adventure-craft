package com.madbros.adventurecraft.UIButtons;

import org.lwjgl.input.*;
import org.newdawn.slick.*;

import com.madbros.adventurecraft.*;
import static com.madbros.adventurecraft.Constants.*;

public abstract class UIButton {
	Rect rect;
	String text;
	
	Sprite texture = Textures.pixel;
	Font font = Textures.font;
	Color fontColor = Color.white;
	Color tint = Color.darkGray;
	Color highlight = new Color(1.0f, 1.0f, 1.0f, 0.3f);
	Color pressedColor = new Color(0.0f, 0.0f, 0.0f, 0.3f);
	int marginX = 0;
	int marginY = 0;
	
	public boolean mouseIsHovering = false;
	public boolean mouseIsPressedDown = false;
	public boolean mousePressedDown = false;
	
	public UIButton(int x, int y, int w, int h, String s) {
		rect = new Rect(x, y, w, h);
		text = s;
	}
	
	public void render() {
		tint.bind();
		texture.draw(rect);
		if(mouseIsHovering && mousePressedDown) {
			pressedColor.bind();
			texture.draw(rect);
		} else if(mouseIsHovering && !Mouse.isButtonDown(LEFT_MOUSE_BUTTON)) {
			highlight.bind();
			texture.draw(rect);
		}
		Color.white.bind();
	}

	public void renderFont() {
		font.drawString(marginX + rect.x, marginY + rect.y, text, fontColor);
	}
	
	public void setMouseIsHovering() {
		if(rect.detectCollision(Helpers.getMouseRect())) mouseIsHovering = true;
		else mouseIsHovering = false;
	}
	
	
	public void setMouseIsPressedDown() {
		if(Mouse.isButtonDown(LEFT_MOUSE_BUTTON) && mouseIsHovering) mouseIsPressedDown = true;
		else mouseIsPressedDown = false;
	}
	
	public void didPressUp() {}
}
