package encrypted;

import java.io.*;
import java.net.*;
import java.security.*;
import javax.net.ssl.*;

public class ServerManager extends unencrypted.ServerManager {
	
	private SSLContext sslContext = null;
	private SSLServerSocketFactory sslServerSocketFactory = null;
	
	

	public ServerManager() {
		super();
		try {
			sslContext = SSLContext.getInstance("TLS");
			TrustManager[] trustAllCerts =  { new TrustAllCertificates() }; 
			sslContext.init(getKeyManagers(), trustAllCerts, null);
			sslServerSocketFactory = sslContext.getServerSocketFactory();
		} catch (NoSuchAlgorithmException | KeyManagementException e) {
			e.printStackTrace();
		}
	}

	protected KeyManager[] getKeyManagers() {
		return new KeyAdministrator().getKeyManagers();
	}

	@Override
	protected ServerSocket createServerSocket(int serverPort, InetAddress inetAddress) {
		try {
			return sslServerSocketFactory.createServerSocket(serverPort, 50, inetAddress);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
