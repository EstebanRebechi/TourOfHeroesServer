package ar.com.tourofheroes.TourOfHeroesServer.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.tourofheroes.TourOfHeroesServer.model.User;
import ar.com.tourofheroes.TourOfHeroesServer.repositories.UserRepository;

@RestController
@CrossOrigin(origins = "*")
public class LoginController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping(value = "/users")
	public ResponseEntity<Collection<User>> getAll() {
		return new ResponseEntity<>((Collection<User>) userRepository.findAll(), HttpStatus.OK);
	}

	@PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> create(@RequestBody User customer) {
		return new ResponseEntity<>(userRepository.save(customer), HttpStatus.CREATED);
	}

	@PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> login() {
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
}
