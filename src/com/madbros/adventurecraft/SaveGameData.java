package com.madbros.adventurecraft;

import static com.madbros.adventurecraft.Constants.*;

public class SaveGameData {
	public int heroX;
	public int heroY;
	public int offsetX;
	public int offsetY;
	
	public int hP;
	public int maxHP;
	public int mP;
	public int maxMP;
	public double eP;
	public double maxEP;
	public int[] invBarID= new int[INV_LENGTH];
	public int[] invBagID= new int[INV_LENGTH * INV_HEIGHT];
	public int[] invBarStackSize= new int[INV_LENGTH];
	public int[] invBagStackSize= new int[INV_LENGTH * INV_HEIGHT];
	
	public long gameTime;
	
	
}
