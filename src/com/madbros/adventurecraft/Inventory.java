package com.madbros.adventurecraft;

import static com.madbros.adventurecraft.Constants.*;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import com.madbros.adventurecraft.Cells.*;
import com.madbros.adventurecraft.Items.*;
import com.madbros.adventurecraft.Utils.Helpers;

public class Inventory {
	private Sprite selectTexture = Textures.selectTexture;
	private Sprite[][] menuTextures = Textures.menuTextures;
	
	public Cell[] invBar= new Cell[INV_LENGTH];
	public Cell[] invBag= new Cell[INV_LENGTH * INV_HEIGHT];
	public CraftingCell[] invCrafting= new CraftingCell[2 * 2];
	public CraftedCell[] invCrafted = new CraftedCell[1];
	
	public Item heldItem = new NoItem();
	private int itemSelected = 0;
	public boolean isUsingItemRight = false;
	public boolean isUsingItemLeft = false;
	
	public Inventory() {
		for(int i = 0; i < INV_LENGTH; i++) {
			invBar[i] = new Cell(INV_BAR_RECT.x + (INV_CELL_SIZE + INV_CELL_MARGIN.right) * i, INV_BAR_RECT.y, BAR);
		}
		
		int k = 0;
		for(int x = 0; x < INV_LENGTH; x++) {
			for(int y = 0; y < INV_HEIGHT; y++) {
				invBag[k] = new Cell(INV_BAG_RECT.x + (INV_CELL_SIZE + INV_CELL_MARGIN.right) * x,
									 INV_BAG_RECT.y + (INV_CELL_SIZE + INV_CELL_MARGIN.bottom) * y, BAG);
				k++;
			}
		}
		
		k = 0;
		for(int x = 0; x < 2; x++) {	
			for(int y = 0; y < 2; y++) {
				invCrafting[k] = new CraftingCell(INV_CRAFTING_RECT.x + (INV_CELL_SIZE + 2) * x,
										  INV_CRAFTING_RECT.y + (INV_CELL_SIZE + 2) * y, CRAFTING);
				k++;
			}
		}
		
		invCrafted[0] = new CraftedCell(INV_CRAFTING_RECT.x2() + 5, INV_CRAFTING_RECT.y, CRAFTED);
		
		invBar[0].item = new Sword();
		invBar[1].item = new Log();
		invBar[1].item.stackSize = 10;
		invBar[2].item = new Log();
		invBar[2].item.stackSize = 99;
		invBar[3].item = new SandClump();
		invBar[3].item.stackSize = 99;
		invBar[4].item = new GrassSeed();
		invBar[4].item.stackSize = 99;
		invBar[5].item = new EarthClump();
		invBar[5].item.stackSize = 99;
		invBar[6].item = new Shovel();
		invBar[7].item = new Sapling();
	}
	
	public void update() {
		if(Mouse.isButtonDown(LEFT_MOUSE_BUTTON)) invBar[itemSelected].item.useLeft();
		if(Mouse.isButtonDown(RIGHT_MOUSE_BUTTON)) invBar[itemSelected].item.useRight();
	}
	
	public void renderBackdrop() {
		menuTextures[0][0].draw(INV_BACKDROP_RECT.x, INV_BACKDROP_RECT.y);	//top left
		menuTextures[2][0].draw(INV_BACKDROP_RECT.x2()-INV_MENU_TILE_SIZE, INV_BACKDROP_RECT.y); //top right
		menuTextures[0][2].draw(INV_BACKDROP_RECT.x, INV_BACKDROP_RECT.y2()-INV_MENU_TILE_SIZE);	//bottom left
		menuTextures[2][2].draw(INV_BACKDROP_RECT.x2()-INV_MENU_TILE_SIZE, INV_BACKDROP_RECT.y2()-INV_MENU_TILE_SIZE);	//bottom right
		
		menuTextures[1][0].draw(INV_BACKDROP_RECT.x+INV_MENU_TILE_SIZE, INV_BACKDROP_RECT.y, INV_BACKDROP_RECT.w-INV_MENU_TILE_SIZE*2, INV_MENU_TILE_SIZE);	//top
		menuTextures[1][2].draw(INV_BACKDROP_RECT.x+INV_MENU_TILE_SIZE, INV_BACKDROP_RECT.y2()-INV_MENU_TILE_SIZE, INV_BACKDROP_RECT.w-INV_MENU_TILE_SIZE*2, INV_MENU_TILE_SIZE);	//bottom
		menuTextures[0][1].draw(INV_BACKDROP_RECT.x, INV_BACKDROP_RECT.y+INV_MENU_TILE_SIZE, INV_MENU_TILE_SIZE, INV_BACKDROP_RECT.h-INV_MENU_TILE_SIZE*2);	//left
		menuTextures[2][1].draw(INV_BACKDROP_RECT.x2()-INV_MENU_TILE_SIZE, INV_BACKDROP_RECT.y+INV_MENU_TILE_SIZE, INV_MENU_TILE_SIZE, INV_BACKDROP_RECT.h-INV_MENU_TILE_SIZE*2);	//right
		
		menuTextures[1][1].draw(INV_BACKDROP_RECT.x+INV_MENU_TILE_SIZE, INV_BACKDROP_RECT.y+INV_MENU_TILE_SIZE, INV_BACKDROP_RECT.w-INV_MENU_TILE_SIZE*2, INV_BACKDROP_RECT.h-INV_MENU_TILE_SIZE*2); //middle
	}
	
	public void render() {
		for(int i=0;i < invBar.length; i++) {			
			invBar[i].render();
			if(i == itemSelected) {
				selectTexture.draw(invBar[i].cellRect.x - 2, invBar[i].cellRect.y -2, invBar[i].cellRect.w + 3, invBar[i].cellRect.h + 3);
			}
		}
	}
	
	public void renderText() {
		Cell[][] cells = {invBar};
		
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells[i].length; j++) {
				cells[i][j].item.renderFont(cells[i][j].cellRect.x2()-INV_CELL_SIZE/2, cells[i][j].cellRect.y2()-INV_CELL_SIZE/2);
			}
		}
	}
	
	public void renderFull() {
		renderBackdrop();
		
		Cell[][] cells = {invBag, invCrafting, invCrafted};
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells[i].length; j++) {
				cells[i][j].render();
			}
		}

		Game.hero.render(WALK_DOWN, INV_CHAR_RECT.x, INV_CHAR_RECT.y, INV_CHAR_RECT.w, INV_CHAR_RECT.h);
		
		heldItem.render(Helpers.getX(), Helpers.getY());
	}
	
	public void renderTextFull() {
		Cell[][] cells = new Cell[][]{invBag, invCrafting, invCrafted};

		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells[i].length; j++) {
				cells[i][j].item.renderFont(cells[i][j].cellRect.x2()-INV_CELL_SIZE/2, cells[i][j].cellRect.y2()-INV_CELL_SIZE/2);
			}
		}
		
		if(heldItem.id != EMPTY) heldItem.renderFont(Helpers.getX(), Helpers.getY());
	}
	
	public void mouseWheelDidIncrement() {
		if(itemSelected > 0) {
			itemSelected -= 1;
		} else {
			itemSelected = invBar.length - 1;
		}
	}
	
	public void mouseWheelDidDecrement() {
		if(itemSelected < invBar.length - 1) {
			itemSelected += 1;
		} else {
			itemSelected = 0;
		}
	}
	
	public void changeSelectedItemTo(int key) {
		if(key == Keyboard.KEY_1) itemSelected = 0;
		else if(key == Keyboard.KEY_2) itemSelected = 1;
		else if(key == Keyboard.KEY_3) itemSelected = 2;
		else if(key == Keyboard.KEY_4) itemSelected = 3;
		else if(key == Keyboard.KEY_5) itemSelected = 4;
		else if(key == Keyboard.KEY_6) itemSelected = 5;
		else if(key == Keyboard.KEY_7) itemSelected = 6;
		else if(key == Keyboard.KEY_8) itemSelected = 7;
	}
	
	public void deleteItemIfNecessary() {
		if(invBar[itemSelected].item.stackSize < 1) invBar[itemSelected].item = new NoItem();
	}
}
