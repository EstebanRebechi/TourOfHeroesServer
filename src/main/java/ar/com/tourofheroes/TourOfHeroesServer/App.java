package ar.com.tourofheroes.TourOfHeroesServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	// Para seteos globales
	/*
	 * @Bean public WebMvcConfigurer corsConfigurer() { return new
	 * WebMvcConfigurerAdapter() {
	 *
	 * @Override public void addCorsMappings(CorsRegistry registry) {
	 * registry.addMapping("/heroes").allowedOrigins("http://localhost:4200"); }
	 * }; }
	 */
}
