package unencrypted;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ServerConnection extends Thread {
	
	private static int connectionCounter = 0;
	private int connectionNumber;
	private Socket cs = null;
	
	public ServerConnection(Socket cs) {
		this.cs = cs;
		connectionNumber = connectionCounter++;
	}
	
	public void run() {
		PrintWriter pw = null;
		BufferedReader br = null;
		try {
			InetAddress iaLocal = cs.getLocalAddress();
			InetAddress iaRemote = cs.getInetAddress();
			int portLocal = cs.getLocalPort();
			int portRemote = cs.getPort();
			System.out.println("Server-Connection #" + connectionNumber + " from " + iaLocal + "/" + portLocal +  " to " +
				iaRemote + "/" + portRemote);
			
			pw = new PrintWriter(cs.getOutputStream());
			br = new BufferedReader(new InputStreamReader(cs.getInputStream()));
			pw.println("Server-Connection #" + connectionNumber + ": Java Server from Johann Weiser on port 4711!!!");
			pw.flush();
			String line = br.readLine();
			while (line != null) {
				System.out.println("Client message received by server #" + connectionNumber + ": " + line);
				pw.println("Server-Connection #" + connectionNumber + ": Java Server from Johann Weiser on port 4711!!!");
				pw.flush();
				line = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pw != null) pw.close();
				if (br != null) br.close();
				cs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
