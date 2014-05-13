package com.madbros.adventurecraft.UI;

import static com.madbros.adventurecraft.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.madbros.adventurecraft.Sprites.*;
import com.madbros.adventurecraft.Utils.*;

public class SelectUIButton {
	//TODO: refactor with UIButton
	Rect rect;
	public Text text;
	public int iD;
	
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
			Sprites.pixel.setColor(Color.WHITE);
			sprite.draw(rect.x-2,rect.y-2, Z_BUTTONS,rect.w+4, rect.h+4);
			Sprites.pixel.setColor(buttonColor);
			sprite.draw(rect, Z_BUTTONS);
			Sprites.pixel.setColor(pressedColor);
			sprite.draw(rect, Z_BUTTONS);
		} else {
			Sprites.pixel.setColor(Color.BLACK);
			sprite.draw(rect.x-2,rect.y-2, Z_BUTTONS,rect.w+4, rect.h+4);
			Sprites.pixel.setColor(buttonColor);
			sprite.draw(rect, Z_BUTTONS);
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
