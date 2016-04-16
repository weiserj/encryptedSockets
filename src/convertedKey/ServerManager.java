package convertedKey;

import java.io.*;
import java.net.*;
import java.security.*;
import javax.net.ssl.*;

public class ServerManager extends encrypted.ServerManager {
	
	protected KeyManager[] getKeyManagers() {
		return new KeyAdministrator().getKeyManagers();
	}

	
}
