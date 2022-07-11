package springboot.utils;

import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

public class Utils {

	/**
	 * Clase ficticia que implementa HostnameVerifier para confiar en todos los
	 * nombres de host
	 */
	public static class TrustAllHosts implements HostnameVerifier {
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}

	/**
	 * Clase ficticia que implementa X509TrustManager para confiar en todos los
	 * certificados
	 */
	public static class TrustAllCertificates implements X509TrustManager {
		public void checkClientTrusted(X509Certificate[] certs, String authType) {
		}

		public void checkServerTrusted(X509Certificate[] certs, String authType) {
		}

		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}
	}

}
