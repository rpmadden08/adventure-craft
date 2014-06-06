package com.madbros.tileminer;

import java.util.Comparator;

import com.madbros.tileminer.Sprites.AnimatedSprite;



public class AtlasComparator implements Comparator<AnimatedSprite>{
    @Override
    public int compare(AnimatedSprite s1, AnimatedSprite s2) {
    	if(s1.stackPosition < s2.stackPosition) {
            return -1;
        } else {
            return 1;
        }
    }


}
