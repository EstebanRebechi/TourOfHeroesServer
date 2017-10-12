package ar.com.tourofheroes.TourOfHeroesServer.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		List<String> exposedHeaders = new ArrayList<>();
		exposedHeaders.add("Authorization");
		config.setExposedHeaders(exposedHeaders);
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		// config.setMaxAge(864_000_000);
		source.registerCorsConfiguration("/**", config);
		CorsFilter bean = new CorsFilter(source);
		return bean;
	}
}
