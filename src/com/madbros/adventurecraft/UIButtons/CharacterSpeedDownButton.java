package com.madbros.adventurecraft.UIButtons;

import com.madbros.adventurecraft.*;
import static com.madbros.adventurecraft.Constants.*;

public class CharacterSpeedDownButton extends DebuggerMenuButton {
	public CharacterSpeedDownButton(int x, int y) {
		super(x, y, DEBUG_MENU_SIZEX/2, DEBUG_MENU_SIZEY, "Speed-");
	}
	
	public void didPressUp(Debugger d) {
		if(Game.character.currentSpeed > 0.09f) {
			Game.character.currentSpeed -= 0.1f;
		}
	}
	
	public void renderFont() {
		font.drawString(marginX + rect.x, marginY + rect.y, text, fontColor);
	}
}
