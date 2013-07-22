package com.madbros.adventurecraft.UIButtons;

import com.madbros.adventurecraft.Debugger;

public class CollisionTilesButton extends DebuggerMenuButton {
	public CollisionTilesButton(int x, int y, boolean isOn) {
		super(x, y, "Collision Tiles Are ");
		if(isOn) status = "On"; else status = "Off";
	}
	
	public void didPressUp(Debugger d) {
		if(d.collisionTilesAreOn) {
			d.collisionTilesAreOn = false;
			status = "Off";
		} else {
			d.collisionTilesAreOn = true;
			status = "On";
		}
	}
}
