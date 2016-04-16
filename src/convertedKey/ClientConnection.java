package convertedKey;

import java.io.*;
import java.net.*;
import java.security.*;
import javax.net.ssl.*;

import unencrypted.ServerManager;

public class ClientConnection extends encrypted.ClientConnection {
	
	protected KeyManager[] getKeyManagers() {
		return new KeyAdministrator().getKeyManagers();
	}
	
}
