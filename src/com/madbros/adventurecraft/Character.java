package com.madbros.adventurecraft;

import static com.madbros.adventurecraft.Constants.TILE_SIZE;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import static com.madbros.adventurecraft.Constants.*;

public class Character {
	//animations
	Sprite[] animations;
	
	int[] walkingAnimationCycle = {0, 1, 0, 2};
	int currentAnimation = WALK_DOWN; //where the animation begins in the sprite animations array
	int currentWalkingAnimationPos = 0;	//what part of the walk animation cycle are we in (0 through 3)
	
	int timeSinceLastAnimation = 0;
	int animationTimePerFrame = 10;

	Margin margin = new Margin(4, 4, 12, 0); //left, right, top, bottom
	
	//absolute position of upper left corner
	Rect aRect = new Rect(TILES_PER_ROW*TILE_SIZE/2 - CHARACTER_SIZE/2,
						  TILES_PER_ROW*TILE_SIZE/2 - CHARACTER_SIZE/2,
						  CHARACTER_SIZE, CHARACTER_SIZE);
	Rect sRect = new Rect(Game.centerScreenX, Game.centerScreenY, CHARACTER_SIZE, CHARACTER_SIZE);
	
	boolean isMovingLeft = false, isMovingRight = false, isMovingUp = false, isMovingDown = false;
	boolean isMoving = false, isAttacking = false;
	
	float currentSpeed = 0.9f;
	
	Block[] collisionDetectionBlocks = new Block[9];
		
	public Character() {
		animations = Textures.characterAnimations;
		walkingAnimationCycle[0] = 0;
	}
	
	public int getX() {
		return aRect.x;
	}
	
	public int getY() {
		return aRect.y;
	}
//	public void startAttacking() {
//		timeSinceLastAnimation = 0;	//getTime()
//		isAttacking = true;
//	}
//	
//	public void stopAttacking() {
//		isAttacking = false;
//	}
	
	public void turnWalkingOff() {
		currentWalkingAnimationPos = 0;
		isMoving = false;
	}
	
	public void startMoving(int dir) {
		switch(dir) {
		case UP:
			if(!isMoving || isMovingDown) {
				currentAnimation = WALK_UP;
			}
			isMovingUp = true;
			isMovingDown = false;
			break;
		case DOWN:
			if(!isMoving || isMovingUp) {
				currentAnimation = WALK_DOWN;
			}
			isMovingUp = false;
			isMovingDown = true;
			break;
		case LEFT:
			if(!isMoving || isMovingRight) {
				currentAnimation = WALK_LEFT;
			}
			isMovingLeft = true;
			isMovingRight = false;
			break;
		case RIGHT:
			if(!isMoving || isMovingLeft) {
				currentAnimation = WALK_RIGHT;
			}
			isMovingLeft = false;
			isMovingRight = true;
			break;
		}

		timeSinceLastAnimation = 0;	//getTime()
		isMoving = true;
	}
	
	public void stopMoving(int dir) {
		if(dir == UP || dir == DOWN) {
			if(isMovingRight) {
				currentAnimation = WALK_RIGHT;
			} else if(isMovingLeft) {
				currentAnimation = WALK_LEFT;
			} else {
				turnWalkingOff();
			}
			
			if(dir == UP) {
				isMovingUp = false;
				if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
					startMoving(DOWN);
				}
			} else {
				isMovingDown = false;
				if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
					startMoving(UP);
				}
			}
		}
		
		else {
			if(isMovingUp) {
				currentAnimation = WALK_UP;
			} else if(isMovingDown) {
				currentAnimation = WALK_DOWN;
			} else {
				turnWalkingOff();
			}
			
			if(dir == LEFT) {
				isMovingLeft = false;
				if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
					startMoving(RIGHT);
				}
			} else {
				isMovingRight = false;
				if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
					startMoving(LEFT);
				}
			}
		}
	}	
	
	public void cycleWalkAnimation() {
		//System.out.println(timeSinceLastAnimation);
		timeSinceLastAnimation++;	//if(getTime() - timeSinceLastAnimation > MAX_ANIMATION_TIME) {
		if(timeSinceLastAnimation > animationTimePerFrame) {
			timeSinceLastAnimation = 0; //timeSinceLastAnimation = getTime();
			currentWalkingAnimationPos++;
			if(currentWalkingAnimationPos >= walkingAnimationCycle.length) {
				currentWalkingAnimationPos = 0;
			}
		}
	}

	public void getCollisionBlocks() {
		//FIXME: needs to account for different chunks...
		//get position in activeBlocks array
		int x = (aRect.x - TILE_SIZE / 4) / TILE_SIZE;	//not sure why I have to subtract tile_size/4, but it makes it look a little better
		int y = aRect.y / TILE_SIZE;
		
		int j = 0;
		for(int i = 0; i < collisionDetectionBlocks.length; i++) {
			if(x+i/3 >= 0 && x+i/3 < Game.level.activeBlocks.length && y+j >= 0 && y+j < Game.level.activeBlocks[0].length) {
//				collisionDetectionBlocks[i] = Game.level.chunks[4].blocks[0][0];
				collisionDetectionBlocks[i] = Game.level.activeBlocks[x+i/3][y+j]; //3x3 grid of blocks around the character
			}
			j++;
			if(j > 2) j = 0;
		}
	}
	
	public void getCollision(boolean isVerticalMovement, int move) {
		if(Game.debugger.collisionDetectionIsOn) {
			Rect charCRect = new Rect(aRect, this.margin);
			
			for(int i = 0; i < collisionDetectionBlocks.length; i++) {
				if(collisionDetectionBlocks[i] != null) {
					if(collisionDetectionBlocks[i].isCollidable) {		
						int dir = 0;
						if(isVerticalMovement) {
							if(charCRect.detectCollision(collisionDetectionBlocks[i].cRect)) {
								if(isMovingDown) {
									dir = DOWN;
								} else if(isMovingUp) {
									dir = UP;
								}
							}
						} else {
							if(charCRect.detectCollision(collisionDetectionBlocks[i].cRect)) {
								if(isMovingLeft) {
									dir = LEFT;
								} else if(isMovingRight) {
									dir = RIGHT;
								}
							}
						}
						collisionDetectionBlocks[i].collisionTile.characterDidCollide(dir, move, charCRect, collisionDetectionBlocks[i].cRect, this);
						collisionDetectionBlocks[i].sRect = new Rect(collisionDetectionBlocks[i].cRect, this);	//for debug mode
					}
				}
			}
		}
	}
	
	public void xMove(int moveX) {
		Game.level.offsetX += moveX;
		aRect.x += moveX;
		while(Game.level.offsetX >= TILE_SIZE) {
			Game.level.offsetX -= TILE_SIZE;
			Game.level.renderRect.x++;
		} 
		while(Game.level.offsetX < 0) {
			Game.level.offsetX += TILE_SIZE;
			Game.level.renderRect.x--;
		}
	}
	
	public void yMove(int moveY) {
		Game.level.offsetY += moveY;
		aRect.y += moveY;
		while(Game.level.offsetY >= TILE_SIZE) {
			Game.level.offsetY -= TILE_SIZE;
			Game.level.renderRect.y++;
		} 
		while(Game.level.offsetY < 0) {
			Game.level.offsetY += TILE_SIZE;
			Game.level.renderRect.y--;
		}
	}
	
	public void walkForward(int delta) {
		int moveX = 0, moveY = 0;
		
		getCollisionBlocks();
		if(isMovingLeft) {
			moveX = Math.round(-currentSpeed * delta);	// if there is severe lag, the delta value may cause the character to jump significantly ahead...
			xMove(moveX);
			getCollision(HORIZONTAL, moveX);
		} else if(isMovingRight) {
			moveX = Math.round(currentSpeed * delta);
			xMove(moveX);
			getCollision(HORIZONTAL, moveX);
		}
		
		if(isMovingUp) {
			moveY = Math.round(-currentSpeed * delta);
			yMove(moveY);
			getCollision(VERTICAL, moveY);
		} else if(isMovingDown) {
			moveY = Math.round(currentSpeed * delta);
			yMove(moveY);
			getCollision(VERTICAL, moveY);
		}
	}
	
	public void update(int delta) {
		if(isMoving && !isAttacking) {
			cycleWalkAnimation();
			walkForward(delta);
		} else if(isAttacking) {
			
		}
	}
	
	public void render() {
		animations[walkingAnimationCycle[currentWalkingAnimationPos]+currentAnimation].draw(sRect);
		if(Game.debugger.collisionRectsAreOn) {
			Color.red.bind();
			Textures.pixel.draw(new Rect(sRect, margin));
			
			Color.yellow.bind();
			if(collisionDetectionBlocks[0] != null) {
				for(int i = 0; i < collisionDetectionBlocks.length; i++) {
					if(collisionDetectionBlocks[i].sRect != null) {
						Textures.pixel.draw(collisionDetectionBlocks[i].sRect);
					}
				}
			}
			Color.white.bind();
		}
	}
	
	//for inventory menu...
	public void render(int a, int x, int y, int w, int h) {
		animations[walkingAnimationCycle[currentWalkingAnimationPos]+a].draw(x, y, w, h);
	}
}
