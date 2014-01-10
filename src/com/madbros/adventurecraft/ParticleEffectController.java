package com.madbros.adventurecraft;

import java.util.ArrayList;

public class ParticleEffectController {
	
	public ArrayList<ParticleEffect> particleEffects = new ArrayList<ParticleEffect>();
	
	public void update() {
		for(int i = 0; i < particleEffects.size(); i++) {
			particleEffects.get(i).checkCompletion();
		}

	}
	
	public void add(String name,int x, int y) {
		particleEffects.add(new ParticleEffect(name, x, y));
	}

	public void remove(ParticleEffect particleEffect) {
		particleEffects.remove(particleEffect);
	}
}
