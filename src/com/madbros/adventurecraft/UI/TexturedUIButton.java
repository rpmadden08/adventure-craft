package com.madbros.adventurecraft.UI;

import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Utils.ButtonFunction;
import com.madbros.adventurecraft.Utils.Rect;

public class TexturedUIButton extends UIButton {
	Sprite mainTexture = Textures.buttonTexture;
	Sprite pressedTexture = Textures.pressedButtonTexture;
	
	public TexturedUIButton(int x, int y, int w, int h, String s, ButtonFunction func) {
		super(x, y, w, h, s, func);
	}
	
	public TexturedUIButton(Rect r, String s, Sprite mainTexture, Sprite pressedTexture, ButtonFunction func) {
		super(r.x, r.y, r.w, r.h, s, func);
		this.mainTexture = mainTexture;
		this.pressedTexture = pressedTexture;
	}

	public void render() {
		if(mouseIsHovering && buttonIsPressedDown) pressedTexture.draw(rect);
		else mainTexture.draw(rect);
	}
}
