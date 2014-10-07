package com.madbros.tileminer.GameObjects;

import java.net.InetAddress;

public class HeroMP extends Hero{
	public InetAddress ipAddress;
	public int port;
	
	public HeroMP(String username, InetAddress ipAddress, int port) {
		super(username);
		this.ipAddress = ipAddress;
		this.port = port;
	}
	
	//He created a separate hero that did not deal with an input in case it was on this computer...  Not sure how to tweak that...
	
	@Override
	public void update() {
		super.update();
	}

}
