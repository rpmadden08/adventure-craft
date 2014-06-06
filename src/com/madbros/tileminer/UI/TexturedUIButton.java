package com.madbros.tileminer.UI;

import static com.madbros.tileminer.Constants.*;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.madbros.tileminer.Sprites.*;
import com.madbros.tileminer.Utils.ButtonFunction;
import com.madbros.tileminer.Utils.Rect;

public class TexturedUIButton extends UIButton {
	StaticSprite mainSprite = Sprites.buttonSprite;
	StaticSprite pressedSprite = Sprites.pressedButtonSprite;
	
	public TexturedUIButton(int x, int y, int w, int h, String s, ButtonFunction func, SpriteBatch batch) {
		super(x, y, w, h, s, func, batch);
	}
	
	public TexturedUIButton(Rect r, String s, StaticSprite mainSprite, StaticSprite pressedSprite, ButtonFunction func, SpriteBatch batch) {
		super(r.x, r.y, r.w, r.h, s, func, batch);
		this.mainSprite = mainSprite;
		this.pressedSprite = pressedSprite;
	}

	public void render() {
		if(mouseIsHovering && buttonIsPressedDown) pressedSprite.draw(rect, Z_BUTTONS);
		else mainSprite.draw(rect, Z_BUTTONS);
	}
}
