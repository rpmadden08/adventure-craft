package com.madbros.tileminer.Slots;

import static com.madbros.tileminer.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.madbros.tileminer.Game;
import com.madbros.tileminer.Inventory;
import com.madbros.tileminer.Items.Item;
import com.madbros.tileminer.Items.NoItem;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.UI.InGameMenuUIButton;
import com.madbros.tileminer.Utils.ButtonFunction;
import com.madbros.tileminer.Utils.Rect;

public class CauldronSlot extends Slot{
	public boolean hasIngedients = false;
	private InGameMenuUIButton deleteQueue;
	private InGameMenuUIButton deleteOneQueue;
	
	public CauldronSlot(int x, int y) {
		super(x, y);
		type = CRAFTING_SLOT;
		ButtonFunction deleteQueueFunction = new ButtonFunction() { public void invoke() { deleteQueue2(); } };
		deleteQueue =  new InGameMenuUIButton(x-5,y,14,14, Sprites.X_ICON, deleteQueueFunction, Game.batch, x-1,y+4, 1);
		ButtonFunction deleteOneQueueFunction = new ButtonFunction() { public void invoke() { deleteOneQueue2(); } };
		deleteOneQueue =  new InGameMenuUIButton(x-5,y+30,14,14, Sprites.LEFT_ARROW, deleteOneQueueFunction, Game.batch, x-2,y+33, 1);
	}
	
	public void deleteQueue2() {
		for(int a = 0; a < item.stackSize; a++) {
			for(int x = 0; x < item.craftCost.length; x++) {
				Item tempCraftCostItem = ITEM_HASH.get(item.craftCost[x]).createNew();
				if(!Game.inventory.add(tempCraftCostItem, item.craftCostAmount[x], item.maxUses)) {
					Rect collectibleRect = new Rect(Game.hero.absRect.x, Game.hero.absRect.y, 16, 16);
					Game.collectibleController.add(item.craftCost[x], tempCraftCostItem.sprite, collectibleRect, item.craftCostAmount[x], item.maxUses);
					
				}
			}
		}
		item = new NoItem();
		
	}
	
	public void deleteOneQueue2() {
		for(int x = 0; x < item.craftCost.length; x++) {
			Item tempCraftCostItem = ITEM_HASH.get(item.craftCost[x]).createNew();
			//Game.inventory.add(tempCraftCostItem, item.craftCostAmount[x], item.maxUses);
			if(!Game.inventory.add(tempCraftCostItem, item.craftCostAmount[x], item.maxUses)) {
				Rect collectibleRect = new Rect(Game.hero.absRect.x, Game.hero.absRect.y, 16, 16);
				Game.collectibleController.add(item.craftCost[x], tempCraftCostItem.sprite, collectibleRect, item.craftCostAmount[x], item.maxUses);
			}
		}
		item.stackSize = item.stackSize -1;
		if(item.stackSize < 1) {
			item = new NoItem();
		}
	}
	
	public void handleMouseInput(boolean leftMousePressed, boolean leftMouseUp, Rect mouseRect) {
		if(mouseRect.detectCollision(deleteQueue.rect)) {
			deleteQueue.handleMouseInput(leftMousePressed, leftMouseUp);
		}
		if(mouseRect.detectCollision(deleteOneQueue.rect)) {
			deleteOneQueue.handleMouseInput(leftMousePressed, leftMouseUp);
		}
	}
	
	public void handleMouseMove(int x,int y) {
		deleteQueue.handleMouseMove(x, y);
		deleteOneQueue.handleMouseMove(x, y);
	}
	
	public void render() {		
		item.render(slotRect);
		if(item.id != 0) {
			deleteQueue.render();
			deleteOneQueue.render();
		}
		
	}
	
	public void handleLeftClick(Inventory inv) {
		
	}
	
	public void handleRightClick(Inventory inv) {
		
	}
}
