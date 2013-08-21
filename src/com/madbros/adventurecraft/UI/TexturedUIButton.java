package com.madbros.adventurecraft.UI;

import com.madbros.adventurecraft.*;
import static com.madbros.adventurecraft.Constants.*;
import com.madbros.adventurecraft.Utils.ButtonFunction;
import com.madbros.adventurecraft.Utils.Rect;

public class TexturedUIButton extends UIButton {
	StaticSprite mainTexture = Textures.buttonTexture;
	StaticSprite pressedTexture = Textures.pressedButtonTexture;
	
	public TexturedUIButton(int x, int y, int w, int h, String s, ButtonFunction func) {
		super(x, y, w, h, s, func);
	}
	
	public TexturedUIButton(Rect r, String s, StaticSprite mainTexture, StaticSprite pressedTexture, ButtonFunction func) {
		super(r.x, r.y, r.w, r.h, s, func);
		this.mainTexture = mainTexture;
		this.pressedTexture = pressedTexture;
	}

	public void render() {
		if(mouseIsHovering && buttonIsPressedDown) pressedTexture.draw(rect, Z_BUTTONS);
		else mainTexture.draw(rect, Z_BUTTONS);
	}
}
