package unencrypted;

import java.io.*;
import java.net.*;

public class ServerManager extends Thread {
	
	public static final String serverName = "127.0.0.1";
	public static final int serverPort = 4711;
	public static final String clientName = "127.0.0.1";
	
	public ServerManager() {
		this.setDaemon(true);
	}
	
	protected ServerSocket createServerSocket(int serverPort, InetAddress inetAddress) {
		try {
			return new ServerSocket(serverPort, 50, inetAddress);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void run() {
		ServerSocket s = null;
		InetAddress ia = InetAddress.getLoopbackAddress();
		try {
			s = createServerSocket(serverPort, ia);
			while (true) {
				Socket cs = s.accept();
				new ServerConnection(cs).start();
			}
			
		}catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (s != null) {
				try {
					s.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
