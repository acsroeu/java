package ro.ase.acs.server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Vector;

public class TcpServer extends Thread {
	private static List<Socket> clients = new Vector<>();
	private Socket socket;
	
	public TcpServer(Socket socket) {
		this.socket = socket;
	}
	
	public String receive() {
		String message = "";
		InputStream inputStream;
		try {
			inputStream = socket.getInputStream();
			DataInputStream dataInputStream =
					new DataInputStream(inputStream);
			message = dataInputStream.readUTF();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return message;
	}
	
	public void send(String message) {
		for(Socket s : clients) {
			OutputStream outputStream;
			try {
				outputStream = s.getOutputStream();
				DataOutputStream dataOutputStream = 
						new DataOutputStream(outputStream);
				dataOutputStream.writeUTF(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void run() {
		super.run();
		while(true) {
			String message = receive();
			send(message);
		}
	}

	public static void main(String[] args) {
		try(ServerSocket server = new ServerSocket(7777)) {
			System.out.println("Server started on port 7777");
			URL url = new URL("http://google.ro");
			URLConnection connection = url.openConnection();
			InputStream inputStream = connection.getInputStream();
			InputStreamReader reader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(reader);
			System.out.println(bufferedReader.readLine());
			
			while(true) {
				Socket socket = server.accept();
				clients.add(socket);
				TcpServer serverInstance = new TcpServer(socket);
				serverInstance.start();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
