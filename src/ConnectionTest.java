
/**
 * Sollte alles testen, funktioniert aber nicht.
 * @author Johann Weiser, TGM
 * @version 2016-04-10
 */
public class ConnectionTest {

	public static void main(String[] args) {
		try {
			unencrypted.ConnectionTest.main(null);
			Thread.sleep(1000);
			encrypted.ConnectionTest.main(null);
			Thread.sleep(1000);
			convertedKey.ConnectionTest.main(null);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
