package com.madbros.adventurecraft.UIButtons;

import com.madbros.adventurecraft.*;
import static com.madbros.adventurecraft.Constants.*;

public class DebuggerMenuButton extends UIButton {
	String status;
	
	public DebuggerMenuButton(int x, int y, String s) {
		super(x, y, DEBUG_MENU_SIZEX, DEBUG_MENU_SIZEY, s);
		marginX = 10;
		marginY = 5;
	}
	
	public DebuggerMenuButton(int x, int y, int w, int h, String s) {
		super(x, y, w, h, s);
		marginX = 10;
		marginY = 5;
	}
	
	public void didPressUp(Debugger d) {
		
	}
	
	public void renderFont() {
		font.drawString(marginX + rect.x, marginY + rect.y, text+status, fontColor);
	}
}
