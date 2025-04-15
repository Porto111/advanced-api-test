package utils;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;


public class SSLTrustAllConfig {

    /**
     * Configura o Rest Assured para confiar em todos os certificados SSL.
     * Esta configuração deve ser utilizada apenas em ambientes de teste, nunca em produção.
     */

    public static void trustAllCertificates() {
        try {
            TrustStrategy trustStrategy = new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            };

            SSLSocketFactory sslSocketFactory = new SSLSocketFactory(trustStrategy, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            RestAssured.config = RestAssured.config().sslConfig(
                    SSLConfig.sslConfig().sslSocketFactory(sslSocketFactory)
            );
        } catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException | UnrecoverableKeyException e) {
            throw new RuntimeException("Erro ao configurar SSL para confiar em todos os certificados", e);
        }
    }

    /**
     * Método alternativo usando SSLContext diretamente.
     * Use se o método acima não funcionar.
     */

    public static void trustAllCertificatesAlternative() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }

                        public void checkClientTrusted(X509Certificate[] certs, String authType) {
                        }

                        public void checkServerTrusted(X509Certificate[] certs, String authType) {
                        }
                    }
            };

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            RestAssured.config = RestAssured.config().sslConfig(
                    SSLConfig.sslConfig().sslSocketFactory(new SSLSocketFactory(sslContext))
            );
        } catch (Exception e) {
            throw new RuntimeException("Erro ao configurar SSL para confiar em todos os certificados", e);
        }
    }
}