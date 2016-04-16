package unencrypted;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientConnection extends Thread {
	
	private static int connectionCounter = 0;
	private int connectionNumber;
	private Socket so = null;
	
	public ClientConnection() {
		connectionNumber = connectionCounter++;
	}
	
	protected Socket createSocket(String serverName, int serverPort, InetAddress loaclAddress, int localPort) {
		try {
			return new Socket(ServerManager.serverName, ServerManager.serverPort, loaclAddress, localPort);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void run() {
		PrintWriter pw = null;
		BufferedReader br = null;
		try {
			InetAddress iaLocal = InetAddress.getByName(ServerManager.clientName);
			  // serverHost, serverPort, localAddress, localPort
			so = createSocket(ServerManager.serverName, ServerManager.serverPort, iaLocal, 0);
			// dump test data
			InetAddress iaRemote = so.getInetAddress();
			int portLocal = so.getLocalPort();
			int portRemote = so.getPort();
			System.out.println("Client-Connection #" + connectionNumber + " from " + iaLocal + "/" + portLocal +  " to " +
				iaRemote + "/" + portRemote);

			pw = new PrintWriter(so.getOutputStream());
			br = new BufferedReader(new InputStreamReader(so.getInputStream()));
			System.out.println("Servermessage received by client #" + connectionNumber + ": " + br.readLine());
			pw.println("Message from client #" + connectionNumber + ": First message by Johann Weiser." );
			pw.flush();
			System.out.println("Servermessage received by client #" + connectionNumber + ": " + br.readLine());
			pw.println("Message from client #" + connectionNumber + ": Second message by Johann Weiser." );
			pw.flush();
			System.out.println("Servermessage received by client #" + connectionNumber + ": " + br.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pw != null) pw.close();
				if (so != null) so.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

}
