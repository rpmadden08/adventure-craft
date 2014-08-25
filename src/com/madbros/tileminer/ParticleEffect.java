package com.madbros.tileminer;



import com.badlogic.gdx.Gdx;

public class ParticleEffect extends com.badlogic.gdx.graphics.g2d.ParticleEffect {

	public int x;
	public int y;
	
	public ParticleEffect(String name, int x, int y) {
		super();
		this.load(Gdx.files.internal("data/"+name), Gdx.files.internal("data"));
		//IOUtils.toString(this.getClass().getResourceAsStream("/data/vertexShader.glsl")
		//this.load(Gdx.files.internal("data/"+name), Gdx.files.internal("data"));
		this.x = x;
		this.y = y;
		
		this.start();
		this.setDuration(100);
		
		
	}
	
	public void checkCompletion() {
		if(this.isComplete()) {
			Game.particleEffectController.remove(this);
		}
	}

}
