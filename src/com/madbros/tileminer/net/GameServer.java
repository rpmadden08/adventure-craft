package com.madbros.tileminer.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import com.madbros.tileminer.Game;
import com.madbros.tileminer.GameObjects.HeroMP;
import com.madbros.tileminer.GameStates.MainStateInput;
import com.madbros.tileminer.Packets.Packet;
import com.madbros.tileminer.Packets.Packet.PacketTypes;
import com.madbros.tileminer.Packets.Packet00Login;
import com.madbros.tileminer.Packets.Packet01Disconnect;

public class GameServer extends Thread{
	private DatagramSocket socket;
	private Game game;
	
	private List<HeroMP> connectedPlayers = new ArrayList<HeroMP>();
	
	public GameServer(Game game) {
		this.game = game;
		try {
			this.socket = new DatagramSocket(1331); //specify port # here...  Example DatagramSocket(442)
		} catch (SocketException e) {
			e.printStackTrace();
		} 
		
	}
	
	public void run() {
		while(true) {
			byte[] data = new byte[1024];
			DatagramPacket packet = new DatagramPacket(data, data.length);
			try {
				socket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			this.parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
			
//			String message = new String(packet.getData());
//			System.out.println("Client ["+packet.getAddress().getHostAddress()+":"+packet.getPort()+"]> "+message); 
//			if(message.trim().equalsIgnoreCase("ping")) {
//				sendData("pong".getBytes(), packet.getAddress(), packet.getPort());
//			}
			
		}
	}
	
	private void parsePacket(byte[] data, InetAddress address, int port) {
		String message = new String(data).trim();
		PacketTypes type = Packet.lookupPacket(message.substring(0, 2));
		Packet packet = null;
		switch(type) {
		default:
		case INVALID:
			break;
		case LOGIN:
			packet = new Packet00Login(data);
			System.out.println("["+address.getHostAddress()+":"+port+"] "+((Packet00Login) packet).getUsername()+" has connected...");
			HeroMP hero= new HeroMP(((Packet00Login) packet).getUsername(),address, port);
				
			Game.heroController.heros.add(hero);
			Game.hero = new HeroMP(((Packet00Login) packet).getUsername(), null, -1);
			this.addConnection(hero, ((Packet00Login) packet));
			
			break;
		case DISCONNECT:
			packet = new Packet01Disconnect(data);
			System.out.println("["+address.getHostAddress()+":"+port+"] "+((Packet01Disconnect) packet).getUsername()+" has disconnected...");
			//hero = getHeroMP(((Packet01Disconnect)packet).getUsername());
			//Game.heroController.heros.remove(hero);
			this.removeConnection(((Packet01Disconnect) packet));
			break;
		}
		
	}

	public void addConnection(HeroMP hero, Packet00Login packet) {
		boolean alreadyConnected = false;
		for(HeroMP h : this.connectedPlayers) {
			if(hero.username.equalsIgnoreCase(h.username)) {
				if(h.ipAddress == null) {
					h.ipAddress = hero.ipAddress;
				}
				
				if(h.port == -1 ) {
					h.port = hero.port;
				}
				alreadyConnected = true;
			} else {
				sendData(packet.getData(), h.ipAddress, h.port); 
				packet = new Packet00Login(h.username);
				sendData(packet.getData(), hero.ipAddress, hero.port);
			}
		}
		if(!alreadyConnected) {
			this.connectedPlayers.add(hero);
		}
	}

	public void removeConnection(Packet01Disconnect packet) {
		//HeroMP hero = getHeroMP(packet.getUsername());
		this.connectedPlayers.remove(getHeroMPIndex(packet.getUsername()));
		packet.writeData(this);
	}
	
	public HeroMP getHeroMP(String username) {
		for(HeroMP hero: this.connectedPlayers) {
			if(hero.username.equals(username)) {
				return hero;
			}
		}
		return null;
	}
	
	public int getHeroMPIndex(String username) {
		int index = 0;
		for(HeroMP hero: this.connectedPlayers) {
			if(hero.username.equals(username)) {
				break;
			}
			index++;
		}
		return index;
	}

	public void sendData(byte[] data, InetAddress ipAddress, int port) {
		DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, port);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendDataToAllClients(byte[] data) {
		for(HeroMP p : connectedPlayers) {
			sendData(data, p.ipAddress, p.port);
		}
		
	}

}
