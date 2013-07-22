package com.madbros.adventurecraft;

import static com.madbros.adventurecraft.Constants.*;

import org.newdawn.slick.*;

import com.madbros.adventurecraft.Items.*;

public class Cell {
	private Sprite cellTexture = Textures.cellTexture;
	private Sprite highlighter = Textures.pixel;
	
	private Color highlightColor = new Color(1.0f, 1.0f, 1.0f, 0.2f);
	
	public Rect cellRect;
	
	public Item item = new NoItem();
	public int type;
	
	public boolean isHighlighted = false;
	
	public Cell(int x, int y, int type) {
		Rect r = new Rect(x, y, INV_CELL_SIZE, INV_CELL_SIZE);
		cellRect = r;
		this.type = type;
	}
	
	public void render() {
		cellTexture.draw(cellRect);
		
		item.render(cellRect);
		
		if(isHighlighted) {
			highlightColor.bind();
			highlighter.draw(cellRect);
			Color.white.bind();
		}
	}
	
	/* Handle Events */
	public void handleLeftClick(Inventory inv) {//Item heldItem, Cell[] invCrafting, Cell[]invCrafted, Inventory inv) {
		if(inv.heldItem.id == this.item.id && this.item.id != EMPTY) {
			int total = inv.heldItem.stackSize + this.item.stackSize;
			if(total > inv.heldItem.maxStackSize) {
				this.item.stackSize = this.item.maxStackSize;
				inv.heldItem.stackSize = total - inv.heldItem.maxStackSize;
			} else {
				this.item.stackSize = total;
				inv.heldItem = new NoItem();
			}
		} else {
			swapItems(inv);
		}
		
		handleAdditional(inv.invCrafting, inv.invCrafted);
	}
	
	public void handleRightClick(Inventory inv) {
		if(inv.heldItem.id != EMPTY && this.item.id == EMPTY) {
			inv.heldItem.stackSize -= 1;
			this.item = Helpers.itemHash.get(inv.heldItem.id).createNew();
			if(inv.heldItem.stackSize < 1) inv.heldItem = new NoItem();
		} else if(inv.heldItem.id != EMPTY && inv.heldItem.id == this.item.id) {
			if(this.item.stackSize < this.item.maxStackSize) {
				inv.heldItem.stackSize -= 1;
				this.item.stackSize += 1;
				if(inv.heldItem.stackSize < 1) inv.heldItem = new NoItem();
			}
		} else if(inv.heldItem.id == EMPTY && this.item.stackSize > 1) {
			inv.heldItem = Helpers.itemHash.get(this.item.id).createNew();
			inv.heldItem.stackSize = (int)Math.ceil(this.item.stackSize / 2f);
			this.item.stackSize = this.item.stackSize / 2;
		} else {
			swapItems(inv);
		}
		
		handleAdditional(inv.invCrafting, inv.invCrafted);
	}
	
	public void handleAdditional(Cell[] invCrafting, Cell[] invCrafted) { }
	
	/* Helpers */
	public void swapItems(Inventory inv) {
		Item temp = inv.heldItem;
		inv.heldItem = this.item;
		this.item = temp;
	}
	
	public void removeRecipeItemsFromCraftingCells(int[] recipeCost, Cell[] invCrafting) {
		int[] itemPositionsRemovedAlready = new int[recipeCost.length];	//so you don't remove two planks from the same stack, for example
		for(int i = 0; i < recipeCost.length; i++) {
			for(int j = 0; j < invCrafting.length; j++) {
				if(invCrafting[j].item.id == recipeCost[i] && !Helpers.arrayDoesContainInt(itemPositionsRemovedAlready, j)) {
					invCrafting[j].item.stackSize -= 1;
					if(invCrafting[j].item.stackSize < 1) invCrafting[j].item = new NoItem();
					itemPositionsRemovedAlready[i] = j;
					break;
				}
			}
		}
	}
	
	public void craftAnotherItemIfPossible(Cell[] invCrafting, Cell[] invCrafted) {
		for(int i = 0; i < invCrafting.length; i++) {
			if(invCrafting[i].item.id != EMPTY) {
				craftAnItemFromThisListIfPossible(invCrafting, invCrafted, invCrafting[i].item.itemsPossiblyCraftable);
				return;
			}
		}
		invCrafted[0].item = new NoItem();
	}
	
	public void craftAnItemFromThisListIfPossible(Cell[] invCrafting, Cell[] invCrafted, int[] itemsPossiblyCraftable) {
		for(int i = 0; i < itemsPossiblyCraftable.length; i++) {
			Item possiblyCraftableItem = Helpers.itemHash.get(itemsPossiblyCraftable[i]);
			if(possiblyCraftableItem.isValidRecipe(invCrafting)) {
				invCrafted[0].item = possiblyCraftableItem.createNew();
				invCrafted[0].item.stackSize = invCrafted[0].item.numberProducedByCrafting;
				return;
			}
		}
		invCrafted[0].item = new NoItem();
	}
}