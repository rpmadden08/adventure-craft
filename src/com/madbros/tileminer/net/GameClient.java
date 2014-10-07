package com.madbros.tileminer.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.madbros.tileminer.Game;
import com.madbros.tileminer.GameObjects.HeroMP;
import com.madbros.tileminer.GameStates.MainStateInput;
import com.madbros.tileminer.Packets.Packet;
import com.madbros.tileminer.Packets.Packet00Login;
import com.madbros.tileminer.Packets.Packet01Disconnect;
import com.madbros.tileminer.Packets.Packet.PacketTypes;

public class GameClient extends Thread {
	
	private InetAddress ipAddress;
	private DatagramSocket socket;
	private Game game;
	
	public GameClient(Game game, String ipAddress) {
		this.game = game;
		try {
			this.socket = new DatagramSocket(); //specify port # here...  Example DatagramSocket(442)
			this.ipAddress = InetAddress.getByName(ipAddress);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			
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
//			System.out.println("Server > "+message); 
		}
	}
	
	public void parsePacket(byte[] data, InetAddress address, int port) {
		String message = new String(data).trim();
		PacketTypes type = Packet.lookupPacket(message.substring(0, 2));
		Packet packet = null;
		switch(type) {
		default:
		case INVALID:
			break;
		case LOGIN:
			packet = new Packet00Login(data);
			System.out.println("["+address.getHostAddress()+":"+port+"] "+((Packet00Login) packet).getUsername()+" has joined the game...");
			//Do something for the input of player... Needs to determine if the input is from a player on the server machine or not
			HeroMP hero= new HeroMP(((Packet00Login) packet).getUsername(),address, port);
			
			//this.connectedPlayers.add(hero);
			Game.heroController.heros.add(hero);
			break;
		case DISCONNECT:
			packet = new Packet01Disconnect(data);
			System.out.println("["+address.getHostAddress()+":"+port+"] "+((Packet01Disconnect) packet).getUsername()+" has left the world...");
			//hero = getHeroMP(((Packet01Disconnect)packet).getUsername());
			//Game.heroController.heros.remove(hero);
			Game.heroController.removeHeroMP(((Packet01Disconnect) packet).getUsername());
			break;
		}
		
	}
	
	public void sendData(byte[] data) {
		DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, 1331);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
