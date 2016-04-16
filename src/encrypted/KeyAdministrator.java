package encrypted;

import java.io.*;
import java.security.*;
import java.security.cert.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.net.ssl.*;

public class KeyAdministrator {
	protected KeyManager[] keyManagers = null;
	protected final char[] password = {'w', 'e', 'i', 's', 'e', 'r'};
	protected final int rsaBits = 2048; // or higher, if you've got the cpu*time
	protected final SecureRandom secureRandom = new SecureRandom(); // reseed periodically 
	protected String keyStoreFile = "keyStore1.jks";

	public  KeyManager[] getKeyManagers() {
		if (keyManagers==null) {
			createKeyManagers(readKeyStore(keyStoreFile));
			System.out.println("Keymanagers for Java-generated key created.");
		}
		return keyManagers;
	}
			
	protected void createKeyManagers(KeyStore keyStore) {
		KeyManagerFactory keyManagerFactory;
		try {
			keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			keyManagerFactory.init(keyStore, password);
			keyManagers = keyManagerFactory.getKeyManagers();
		} catch (NoSuchAlgorithmException | UnrecoverableKeyException | KeyStoreException e) {
			e.printStackTrace();
		}
	}


	protected KeyStore readKeyStore(String dateiName) {
		KeyStore keyStore = null;
	    // get user password and file input stream
	    FileInputStream fis = null;
	    try {
	    	keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
	        fis = new FileInputStream(dateiName);
	        keyStore.load(fis, password);
		} catch (NoSuchAlgorithmException | CertificateException | IOException | KeyStoreException e) {
			e.printStackTrace();
		} finally {
	        if (fis != null) {
	            try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
	    }
	    return keyStore;
	}


	/*
	 * Funktioniert nicht.
	 */
	private void createKeymanagers1a() {
		try {
			// Erzeugen des Schlüsselpaars
		    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		    keyPairGenerator.initialize(rsaBits, secureRandom);
		    KeyPair keyPair =  keyPairGenerator.generateKeyPair();
		    // Erzeugen des Schlüsselspeichers
		    KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
		    keyStore.load(null, password);
			java.security.cert.Certificate[] c = new java.security.cert.Certificate[1];
			keyStore.setKeyEntry("priv1", keyPair.getPrivate(), password, c);
			//k.setKeyEntry("pub1", kp.getPublic(), password, c);
			// Erzeugen der KeyManager
			KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			keyManagerFactory.init(keyStore, password);
			keyManagers = keyManagerFactory.getKeyManagers();
		} catch (NoSuchAlgorithmException | KeyStoreException | CertificateException | IOException | UnrecoverableKeyException  e) {
			e.printStackTrace();
		}
	}

}
