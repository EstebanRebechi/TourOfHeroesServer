package ar.com.tourofheroes.TourOfHeroesServer.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.tourofheroes.TourOfHeroesServer.model.Hero;
import ar.com.tourofheroes.TourOfHeroesServer.repositories.HeroRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class HeroController {

	@Autowired
	private HeroRepository repository;

	@GetMapping(value = "/heroes")
	public List<Hero> getHeroes() {
		List<Hero> list = new ArrayList<>();
		Iterable<Hero> heroes = repository.findAll();
		heroes.forEach(list::add);
		return list;
	}

	@PostMapping(value = "/heroes")
	public Hero postHero(@RequestBody Hero hero) {
		repository.save(hero);
		return hero;
	}

	@DeleteMapping(value = "/heroes/{id}")
	public void deleteHero(@PathVariable long id) {
		repository.delete(id);
	}

	@GetMapping(value = "/heroes/{id}")
	public Hero getHero(@PathVariable Long id) {
		return repository.findOne(id);
	}

	@PutMapping(value = "/heroes/{id}")
	public void putHero(@RequestBody Hero hero, @PathVariable Long id) {
		if(!id.equals(hero.getId())) {
			hero.setId(id);
		}
		repository.save(hero);
	}

	@GetMapping(value = "/heroes/n={name}")
	public List<Hero> getByName(@PathVariable String name) {
		return repository.findByNameIgnoreCaseContaining(name);
	}
}
