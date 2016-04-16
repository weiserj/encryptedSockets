package encrypted;

import java.io.*;
import java.net.*;
import java.security.*;
import javax.net.ssl.*;

import unencrypted.ServerManager;

public class ClientConnection extends unencrypted.ClientConnection {
	
	private static int connectionCounter = 0;
	private int connectionNumber;
	private Socket so = null;
	
	private SSLContext sslContext = null;
	private SSLSocketFactory sslSocketFactory = null;
	
	public ClientConnection() {
		connectionNumber = connectionCounter++;
		try {
			sslContext = SSLContext.getInstance("TLS");
			TrustManager[] trustAllCerts = new TrustManager[] { new TrustAllCertificates() }; 
			sslContext.init(getKeyManagers(), trustAllCerts, null);
			sslSocketFactory = sslContext.getSocketFactory();
		} catch (NoSuchAlgorithmException | KeyManagementException e) {
			e.printStackTrace();
		}
	}

	protected KeyManager[] getKeyManagers() {
		return new KeyAdministrator().getKeyManagers();
	}
	
	@Override
	protected Socket createSocket(String serverName, int serverPort, InetAddress loaclAddress, int localPort) {
		try {
			return sslSocketFactory.createSocket(ServerManager.serverName, ServerManager.serverPort, loaclAddress, localPort);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
