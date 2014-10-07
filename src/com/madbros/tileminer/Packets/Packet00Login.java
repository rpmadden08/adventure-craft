package com.madbros.tileminer.Packets;

import com.madbros.tileminer.net.GameClient;
import com.madbros.tileminer.net.GameServer;

public class Packet00Login extends Packet{
	
	private String username;

	public Packet00Login(byte[] data) {
		super(00);
		this.username = readData(data);
	}
	
	public Packet00Login(String username) {
		super(00);
		this.username = username;
	}

	@Override
	public void writeData(GameClient client) {
		client.sendData(getData());
	}

	@Override
	public void writeData(GameServer server) {
		server.sendDataToAllClients(getData());
	}

	@Override
	public byte[] getData() {
		return ("00" + this.username).getBytes();
	}
	
	public String getUsername() {
		return username;
	}

	
}
