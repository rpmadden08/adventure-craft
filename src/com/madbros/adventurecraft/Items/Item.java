package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.madbros.adventurecraft.Block;
import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Slots.*;
import com.madbros.adventurecraft.Sprites.*;
import com.madbros.adventurecraft.TileTypes.Tile;
import com.madbros.adventurecraft.Utils.*;

public abstract class Item {
	public int id = EMPTY;
	public String name = "This item needs a name";
	public int stackSize = 1;
	public int maxStackSize = 1;
	public int numberProducedByCrafting = 1;
	public int[] itemsPossiblyCraftable = {};
	public int[] itemsPossiblyBurnable = {};
	public int[] itemsPossiblyBrewable = {};
	public int[] craftCost = {};
	public int[] craftCostAmount =new int[]{1};
	public boolean is32 = true;
	public float originX = 2;
	public float originY = 30;
	public float weaponOffsetX = 0;
	public float weaponOffsetY = 0;
	public Rect cRectFinal = new Rect (0,0,0,0);
	public Rect cRectU = new Rect (0,0,0,0);
	public Rect cRectD = new Rect (0,0,0,0);
	public Rect cRectR = new Rect (0,0,0,0);
	public Rect cRectL = new Rect (0,0,0,0);
	public Rect range = new Rect(0,0, 256,256);
	public String sound;
	public int attackPower = 1;
	public int itemPower = 1;
	public int maxUses = 0;
	public int uses = 0;
	public boolean isUseable = false;
	public boolean isFuelSource = false;
	public int fuelAmount = 0;
	public boolean isInactive = false;
	public boolean isInUse = false;
	
	public void calculateUsage() {
		isInUse = false;
		Game.hero.eP = Game.hero.eP - 0.1;
		uses = uses -1;
		if(uses <= 0) {
			stackSize = stackSize - 1;
			Game.inventory.deleteItemIfNecessary();
		}
	}
	
	
	
	public StaticSprite sprite;
	public StaticSprite swingSprite = sprite;
	public boolean isInRange;
	
	public void render(Rect slotRect) {
		sprite.draw(slotRect.x + ITEM_OFFSET, slotRect.y + ITEM_OFFSET, Z_INV_ITEMS);
		if(uses != maxUses) {
			Game.renderSystem.renderItemHealth(this, slotRect.x, slotRect.y);
		}
	}
	
	public void render(int x, int y) {
		sprite.draw(x-ITEM_SIZE/2, y-ITEM_SIZE/2, Z_INV_ITEMS);
	}
	
	public void getTopTile() {
		if(Game.level.tileBeingAttacked != Game.level.highlightedBlock.getTopTile()) {
			Game.level.tileBeingAttacked.currentHp = Game.level.tileBeingAttacked.maxHp;
			Game.level.tileBeingAttacked = Game.level.highlightedBlock.getTopTile();
		}
	}
	
	public Tile topTile(Block block) {
		return block.getTopTile();
	}
	
	public boolean areIngredientsInInventory() {
		//boolean[] ingChecklist = new boolean[craftCost.length];
		for(int x = 0; x < craftCost.length; x++) {
			int currentCraftCostAmount = craftCostAmount[x];
			for(int invBar = 0; invBar < Game.inventory.invBar.length; invBar++) {
				if(Game.inventory.invBar[invBar].item.id == craftCost[x]) {
					if(currentCraftCostAmount <= Game.inventory.invBar[invBar].item.stackSize) {
						//ingChecklist[x] = true;
						currentCraftCostAmount = currentCraftCostAmount - Game.inventory.invBar[invBar].item.stackSize;
						break;
					} else {
						currentCraftCostAmount = currentCraftCostAmount - Game.inventory.invBar[invBar].item.stackSize;
					}
				}
			}
			for(int invBag = 0; invBag < Game.inventory.invBag.length; invBag++) {
				if(Game.inventory.invBag[invBag].item.id == craftCost[x]) {
					if(currentCraftCostAmount <= Game.inventory.invBag[invBag].item.stackSize) {
						//ingChecklist[x] = true;
						currentCraftCostAmount = currentCraftCostAmount - Game.inventory.invBag[invBag].item.stackSize;
						break;
					} else {
						currentCraftCostAmount = currentCraftCostAmount - Game.inventory.invBag[invBag].item.stackSize;
					}
				}
			}
			if(currentCraftCostAmount >0) {
				return false;
			}
			
		}
		
		return true;
	}
	
	public void renderFont(int x, int y, SpriteBatch batch) {
		Color c = new Color(1.0f, 1.0f, 1.0f, 0.8f);
		Sprites.pixel.setColor(c);
		int adjX = x+1; int adjY = y + 4;
		if(stackSize < 10) adjX += 4;
		
		Sprites.font.setColor(Color.WHITE);
		if(stackSize > 1) Sprites.font.draw(batch, String.valueOf(stackSize), adjX, adjY);
		Sprites.pixel.setColor(Color.WHITE);
	}
	
	public void useRight() {
		//place 
		if(Game.level.tileBeingAttacked.isUseable) {
			Game.level.tileBeingAttacked.rightClicked();
		}
	}
	
	public void checkIsInRange() {
		useRight();
	}
	
	public void useLeft() {
		//attack
		if(Game.level.tileBeingAttacked.isBreakable && isInRange == true) {
			Game.level.tileBeingAttacked.currentHp -= attackPower;
			if(Game.level.tileBeingAttacked.currentHp < 1) {
				Game.level.highlightedBlock.deleteObjectTile();
				Game.level.tileBeingAttacked.deleteMe(Game.level.highlightedBlockX, Game.level.highlightedBlockY, Game.level.activeBlocks);
				
				//Game.level.autoTileHighlightedBlock();
			}
		}
	}
	
	public boolean isValidRecipe(Slot[] craftingSlots) {
		return false;
	}
	
	public boolean isValidFurnaceRecipe(Slot[] craftingSlots) {
		return false;
	}
	
	public boolean isValidTableRecipe(Slot[] craftingSlots) {
		return false;
	}
	
	public boolean isValidCauldronRecipe(Slot[] craftingSlots) {
		return false;
	}
	
	public void highlightItem(Block block, int x, int y) {
		
	}
	
	
	abstract public Item createNew(); 
}