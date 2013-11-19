package com.madbros.adventurecraft.UI;

import static com.madbros.adventurecraft.Constants.*;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.madbros.adventurecraft.Sprites.*;
import com.madbros.adventurecraft.Utils.ButtonFunction;
import com.madbros.adventurecraft.Utils.Rect;

public class TexturedUIButton extends UIButton {
	StaticSprite mainSprite = Sprites.buttonSprite;
	StaticSprite pressedSprite = Sprites.pressedButtonSprite;
	
	public TexturedUIButton(int x, int y, int w, int h, String s, ButtonFunction func) {
		super(x, y, w, h, s, func);
	}
	
	public TexturedUIButton(Rect r, String s, StaticSprite mainSprite, StaticSprite pressedSprite, ButtonFunction func, SpriteBatch batch) {
		super(r.x, r.y, r.w, r.h, s, func);
		this.mainSprite = mainSprite;
		this.pressedSprite = pressedSprite;
	}

	public void render() {
		if(mouseIsHovering && buttonIsPressedDown) pressedSprite.draw(rect, Z_BUTTONS);
		else mainSprite.draw(rect, Z_BUTTONS);
	}
}
