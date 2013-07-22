package com.madbros.adventurecraft.UIButtons;

import com.madbros.adventurecraft.Debugger;

public class CollisionRectanglesButton extends DebuggerMenuButton{
	public CollisionRectanglesButton(int x, int y, boolean isOn) {
		super(x, y, "Collision Rectangles Are ");
		if(isOn) status = "On"; else status = "Off";
	}
	
	public void didPressUp(Debugger d) {
		if(d.collisionRectsAreOn) {
			d.collisionRectsAreOn = false;
			status = "Off";
		} else {
			d.collisionRectsAreOn = true;
			status = "On";
		}
	}
}
