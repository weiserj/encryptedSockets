package convertedKey;

import java.io.*;
import java.security.*;
import java.security.cert.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.net.ssl.*;

public class KeyAdministrator extends encrypted.KeyAdministrator{

	public KeyAdministrator() {
		keyStoreFile = "keyStore1.jks";
	}
	
	public static void main(String[] args) {
		new KeyAdministrator().createKeymanagers();
		//readKeyStore("keyStore2.jks");
	}
	
	public  KeyManager[] getKeyManagers() {
		if (keyManagers==null) {
			createKeymanagers();
			System.out.println("Keymanagers for converted RSA key created.");
		}
		return keyManagers;
	}
	
	
	private void createKeymanagers() {
		KeyStore keyStore = readKeyStore(keyStoreFile);
		PrivateKey privateKey = readPrivateKey();
		try {
			java.security.cert.Certificate[] c = new java.security.cert.Certificate[1];
			c[0] = keyStore.getCertificate("xxx1");
			//System.out.println(c[0]);
			keyStore.setKeyEntry("priv1", privateKey, password, c);
			createKeyManagers(keyStore);
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}

	}
	
	public static PrivateKey readPrivateKey() {
		String keyPath = "id_rsa.pkcs8";
		File privKeyFile = new File(keyPath);
		BufferedInputStream bis = null;
		byte[] privKeyBytes = null;
		try {
		    bis = new BufferedInputStream(new FileInputStream(privKeyFile));
			privKeyBytes = new byte[(int)privKeyFile.length()];
			bis.read(privKeyBytes);
			bis.close();
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			KeySpec ks = new PKCS8EncodedKeySpec(privKeyBytes);
			RSAPrivateKey privKey = (RSAPrivateKey) keyFactory.generatePrivate(ks);
			return privKey;
		} catch(IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
		    e.printStackTrace();
		    return null;
		}		
	}

}
