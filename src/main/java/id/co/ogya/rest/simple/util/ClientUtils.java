package id.co.ogya.rest.simple.util;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import feign.Feign;
import feign.Request;
import feign.Retryer;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import okhttp3.OkHttpClient;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.util.concurrent.TimeUnit;

public class ClientUtils {
    private ClientUtils() {}

    /**
     * Build a {@link Feign} HTTP Client
     * @param namingPolicy Field naming policy
     * @param timeout Timeout in millisecond
     * @return {@link Feign} instance
     */
    public static Feign.Builder getHttpClient(FieldNamingPolicy namingPolicy, int timeout) {
        return Feign.builder()
                .client(new feign.okhttp.OkHttpClient(getOkHttpClient(timeout)))
                .encoder(new GsonEncoder(getGson(namingPolicy)))
                .decoder(new GsonDecoder(getGson(namingPolicy)))
                .options(new Request.Options(timeout, timeout))
                .retryer(Retryer.NEVER_RETRY)
                ;
    }

    /**
     * Create custom {@link Gson} instance for parsing the json.
     *
     * @return {@link Gson}
     */
    private static Gson getGson(FieldNamingPolicy namingPolicy) {
        GsonBuilder gsonBuilder = new GsonBuilder()
                .setFieldNamingPolicy(namingPolicy);

        return gsonBuilder
                .create();
    }

    /**
     * Create a custom {@link OkHttpClient} instance for trusting any https certificate.
     *
     * @return {@link OkHttpClient}
     */
    private static OkHttpClient getOkHttpClient(long timeout) {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager)trustAllCerts[0]);
            builder.hostnameVerifier((hostname, session) -> true);

            builder.readTimeout(timeout, TimeUnit.MILLISECONDS);
            builder.connectTimeout(timeout, TimeUnit.MILLISECONDS);

            return builder.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
