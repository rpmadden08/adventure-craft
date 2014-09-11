package com.madbros.tileminer.Items;



import static com.madbros.tileminer.Constants.*;
import com.madbros.tileminer.Sprites.Sprites;

public class WoodenPick extends Pick {
	public WoodenPick() {
		id = WOODEN_PICK;
		name = "Wooden Pickaxe";
		sprite = Sprites.sprites.get(Sprites.WOODEN_PICK);
		swingSprite = sprite;
		attackPower = 2;
		pickPower = 4;
		isRepeatable = true;
		craftCost = new int[]{PLANK, STICK};
		craftCostAmount = new int[]{3, 2};
		maxUses = 15;// 15
		uses = 15;
		
	}
	
	@Override
	public WoodenPick createNew() {
		return new WoodenPick();
	}
}