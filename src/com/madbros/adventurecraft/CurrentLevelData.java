package com.madbros.adventurecraft;

public class CurrentLevelData {
	public int[] collectibleItemIds = new int[Game.collectibleController.collectibles.size()];
	public int[] collectibleItemStackSizes = new int[Game.collectibleController.collectibles.size()];
	public int[] collectibleItemUses = new int[Game.collectibleController.collectibles.size()];
	public int[] collectibleItemX = new int[Game.collectibleController.collectibles.size()];
	public int[] collectibleItemY = new int[Game.collectibleController.collectibles.size()];
}
