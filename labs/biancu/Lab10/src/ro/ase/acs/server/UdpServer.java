package ro.ase.acs.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class UdpServer extends Thread {
	private final int port = 7777;
	private DatagramSocket socket;
	
	public UdpServer() {
		try {
			socket = new DatagramSocket(port);
			System.out.println("Server started on port " + port);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		super.run();
		while(true) {
			byte[] buffer = new byte[256];
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			try {
				socket.receive(packet);
				String receivedMessage = new String(packet.getData());
				System.out.println(receivedMessage);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		UdpServer server = new UdpServer();
		try {
			byte[] buffer = new byte[256];
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			server.socket.receive(packet);
			String receivedMessage = new String(packet.getData());
			System.out.println(receivedMessage);
			
			InetAddress clientAddress = packet.getAddress();
			int clientPort = packet.getPort();
			server.start();
			while(true) {
				String message = scanner.nextLine();
				byte[] bytes = message.getBytes();
				DatagramPacket packetToBeSend = 
						new DatagramPacket(bytes, bytes.length,
								clientAddress, clientPort);
				server.socket.send(packetToBeSend);
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		scanner.close();
	}

}
