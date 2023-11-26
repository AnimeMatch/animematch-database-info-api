package animatch.app.api.configuration.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

@Configuration
public class RestTemplateConfig {

//    @Bean
//    public RestTemplate restTemplate() throws NoSuchAlgorithmException, KeyManagementException {
//        return new RestTemplate(clientHttpRequestFactory());
//    }

//    private ClientHttpRequestFactory clientHttpRequestFactory() throws NoSuchAlgorithmException, KeyManagementException {
//        SSLContext sslContext = SSLContext.getInstance("TLS");
//        sslContext.init(null, new TrustManager[]{new X509TrustManager() {
//            @Override
//            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) {
//            }
//
//            @Override
//            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) {
//            }
//
//            @Override
//            public X509Certificate[] getAcceptedIssuers() {
//                return null;
//            }
//        }}, null);
//
//        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
//
//        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
//        requestFactory.setBufferRequestBody(false);
//
//        return requestFactory;
//    }



//
//    RestTemplate restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory() {
//        @Override
//        protected void prepareConnection(HttpURLConnection connection, String httpMethod) throws IOException {
//            if (connection instanceof HttpsURLConnection) {
//                try {
//                    SSLContext sslContext = SSLContext.getInstance("TLS");
//                    sslContext.init(null, new TrustManager[]{new X509TrustManager() {
//                        @Override
//                        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) {
//                        }
//
//                        @Override
//                        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) {
//                        }
//
//                        @Override
//                        public X509Certificate[] getAcceptedIssuers() {
//                            return null;
//                        }
//                    }}, null);
//                    ((HttpsURLConnection) connection).setSSLSocketFactory(sslContext.getSocketFactory());
//                } catch (Exception e) {
//                    throw new IOException("Erro ao configurar SSL", e);
//                }
//            }
//            super.prepareConnection(connection, httpMethod);
//        }
//    });

//    RestTemplate restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory() {
//        @Override
//        protected void prepareConnection(HttpURLConnection connection, String httpMethod) throws IOException {
//            if (connection instanceof HttpsURLConnection) {
//                ((HttpsURLConnection) connection).setHostnameVerifier((s, sslSession) -> true);
//            }
//            super.prepareConnection(connection, httpMethod);
//        }
//    });


}
