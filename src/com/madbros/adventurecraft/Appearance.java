package com.madbros.adventurecraft;

import static com.madbros.adventurecraft.Constants.WALK_DOWN;

public class Appearance {
	Sprite[] animations;
	
	int[] walkingAnimationCycle = {1, 2, 3, 4, 5, 6, 7, 8};
	int currentAnimation = WALK_DOWN; //where the animation begins in the sprite animations array
	int currentWalkingAnimationPos = 0;	//what part of the walk animation cycle are we in (0 through 3)
	
	int timeSinceLastAnimation = 0;
	int animationTimePerFrame = 5;
}
