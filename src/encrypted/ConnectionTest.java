package encrypted;

/**
 * Test des Client/Server - Socket Beispiels.
 * @author Johann Weiser, TGM
 * @version 2016-04-10
 */
public class ConnectionTest {

	public static void main(String[] args) {
		ServerManager sm1 = new ServerManager();
		sm1.start();
		ClientConnection cc1 = new ClientConnection();
		cc1.start();
		ClientConnection cc2 = new ClientConnection();
		cc2.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		sm1.interrupt();
	}

}
