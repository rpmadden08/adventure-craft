package com.madbros.adventurecraft.UIButtons;

import com.madbros.adventurecraft.*;

public class CollisionDetectionButton extends DebuggerMenuButton {
	public CollisionDetectionButton(int x, int y, boolean isOn) {
		super(x, y, "Collision Detection Is ");
		if(isOn) status = "On"; else status = "Off";
	}
	
	public void didPressUp(Debugger d) {
		if(d.collisionDetectionIsOn) {
			d.collisionDetectionIsOn = false;
			status = "Off";
		} else {
			d.collisionDetectionIsOn = true;
			status = "On";
		}
	}
}
