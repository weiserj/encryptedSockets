package encrypted;

import java.security.cert.*;
import javax.net.ssl.*;

/**
 * Der TrustManager hier vertraut allen Certifikaten, er prüft gar nichts.
 * @author Johann Weiser, TGM
 * @version 2016-04-10
 */
public class TrustAllCertificates implements X509TrustManager{

	@Override
	public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
		// everything okay.
	}

	@Override
	public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
		// everything okay.
	}

	@Override
	public X509Certificate[] getAcceptedIssuers() {
		return new X509Certificate[0];	}

}
