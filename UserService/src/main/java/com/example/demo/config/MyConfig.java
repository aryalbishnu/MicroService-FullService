package com.example.demo.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.web.client.RestTemplate;

import com.example.demo.config.intercepter.RestTemplateInterCeptor;

@Configuration
public class MyConfig {
	@Autowired
	private ClientRegistrationRepository repository;
	@Autowired
	private OAuth2AuthorizedClientRepository clientRepository;
	
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		List<ClientHttpRequestInterceptor> interCeptors = new ArrayList<>();
		interCeptors.add(new RestTemplateInterCeptor(manager(repository, clientRepository)));
		restTemplate.setInterceptors(interCeptors);
		return restTemplate;
	}
	
	// Declar a bean of oauth2authorizedClientManager
	@Bean
	public OAuth2AuthorizedClientManager manager(
			ClientRegistrationRepository repository,
			OAuth2AuthorizedClientRepository clientRepository
			) {
		OAuth2AuthorizedClientProvider provider = OAuth2AuthorizedClientProviderBuilder.builder().clientCredentials().build();
		DefaultOAuth2AuthorizedClientManager defaultClientManager = new DefaultOAuth2AuthorizedClientManager(repository, clientRepository);
		defaultClientManager.setAuthorizedClientProvider(provider);
		return defaultClientManager;
	}

}
