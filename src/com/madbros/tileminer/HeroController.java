package com.madbros.tileminer;

import java.util.ArrayList;
import java.util.List;

import com.madbros.tileminer.GameObjects.HeroMP;

public class HeroController {
	public List<HeroMP> heros = new ArrayList<HeroMP>();
	
	public void removeHeroMP(String username) {
		int index = 0;
		for(HeroMP h: heros) {
			if(h.username.equalsIgnoreCase(username)) {
				break;
			}
			index++;
		}
		this.heros.remove(index);
	}
//	public int getHero(InetAddress ipAddress) {
//		for(HeroMP h : heros) {
//			if(ipAddress== h.ipAddress) {
//				return heros.g
//			}
//		}
//	}
}
