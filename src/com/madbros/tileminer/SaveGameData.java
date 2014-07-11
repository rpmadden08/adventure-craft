package com.madbros.tileminer;

import static com.madbros.tileminer.Constants.*;

public class SaveGameData {
	public int heroX;
	public int heroY;
	public int spawnX;
	public int spawnY;
	public String currentLevel;
	
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
	public int[] invBarUsage= new int[INV_LENGTH];
	public int[] invBagUsage= new int[INV_LENGTH * INV_HEIGHT];
	
	public int[] invCraftingID = new int[2 * 2];
	public int[] invCraftingStackSize = new int[2 * 2];
	public int invCraftedID;
	public int invCraftedStackSize;
	public int[] invClothingID = new int[4];
	public int[] invClothingUsage = new int[4];
	
	public long seed;
	public long gameTime;
	
	
}
