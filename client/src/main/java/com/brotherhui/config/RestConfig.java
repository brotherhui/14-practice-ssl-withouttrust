package com.brotherhui.config;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.conn.ssl.TrustStrategy;
import java.security.cert.X509Certificate;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {


	@Bean
	public RestOperations restOperations(ClientHttpRequestFactory clientHttpRequestFactory) {
		return new RestTemplate(clientHttpRequestFactory);
	}

	@Bean
	public ClientHttpRequestFactory clientHttpRequestFactory(HttpClient httpClient) {
		return new HttpComponentsClientHttpRequestFactory(httpClient);
	}

	@Bean
	public HttpClient create() {
		try {
			SSLContextBuilder sslContextBuilder = new SSLContextBuilder();
// 			sslContextBuilder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
			sslContextBuilder.loadTrustMaterial(new TrustStrategy() {
				public boolean isTrusted(final X509Certificate[] chain,
						final String auth) {
					return true;
				}});
			SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(
					sslContextBuilder.build(), SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory)
					.build();

			return httpClient;
		} catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
