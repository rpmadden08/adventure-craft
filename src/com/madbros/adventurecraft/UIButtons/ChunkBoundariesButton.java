package com.madbros.adventurecraft.UIButtons;

import com.madbros.adventurecraft.Debugger;

public class ChunkBoundariesButton extends DebuggerMenuButton{
	public ChunkBoundariesButton(int x, int y, boolean isOn) {
		super(x, y, "Chunk Boundaries Are ");
		if(isOn) status = "On"; else status = "Off";
	}
	
	public void didPressUp(Debugger d) {
		if(d.chunkBoundariesAreOn) {
			d.chunkBoundariesAreOn = false;
			status = "Off";
		} else {
			d.chunkBoundariesAreOn = true;
			status = "On";
		}
	}
}
