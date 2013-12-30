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
	
	Color fontColor = Color.WHITE;
	
	StaticSprite sprite = Sprites.pixel;
	Color buttonColor = Color.DARK_GRAY;
	Color pressedColor = new Color(0.0f, 0.0f, 0.0f, 0.3f);
	
	public boolean mouseIsHovering = false;
	public boolean buttonIsPressedDown = false;
	
	public SelectUIButton(int x, int y, int w, int h, String s, SpriteBatch batch) {
		rect = new Rect(x, y, w, h);
		text = new Text(Sprites.font, s, batch);
	}
	
	public void render() {
		Sprites.pixel.setColor(buttonColor);

		sprite.draw(rect, Z_BUTTONS);
		if(buttonIsPressedDown) {
			Sprites.pixel.setColor(pressedColor);
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
