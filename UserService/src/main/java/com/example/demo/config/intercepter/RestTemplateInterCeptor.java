package com.example.demo.config.intercepter;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class RestTemplateInterCeptor implements ClientHttpRequestInterceptor{
	
	@Autowired
	private OAuth2AuthorizedClientManager manager;
	
	private Logger logger = LoggerFactory.getLogger(RestTemplateInterCeptor.class);
	

	public RestTemplateInterCeptor(OAuth2AuthorizedClientManager manager) {
		this.manager = manager;
	}



	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		String token = manager.authorize(OAuth2AuthorizeRequest.withClientRegistrationId("my-internal-client")
			       .principal("internal").build()).getAccessToken().getTokenValue();
		logger.info("OAuth2 access token: {}", token);
		request.getHeaders().add("Authorization", "Bearer " + token);
		
		return execution.execute(request, body);
	}

}
