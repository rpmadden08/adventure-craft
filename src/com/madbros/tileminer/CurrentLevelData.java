package com.madbros.tileminer;

public class CurrentLevelData {
	public int[] collectibleItemIds = new int[Game.collectibleController.collectibles.size()];
	public int[] collectibleItemStackSize = new int[Game.collectibleController.collectibles.size()];
	public int[] collectibleItemUses = new int[Game.collectibleController.collectibles.size()];
	public int[] collectibleItemX = new int[Game.collectibleController.collectibles.size()];
	public int[] collectibleItemY = new int[Game.collectibleController.collectibles.size()];
}
