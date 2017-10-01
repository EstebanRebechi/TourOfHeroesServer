package ar.com.tourofheroes.TourOfHeroesServer.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ar.com.tourofheroes.TourOfHeroesServer.model.Hero;

public interface HeroRepository extends CrudRepository<Hero, Long> {
	List<Hero> findByNameIgnoreCaseContaining(String name);
}
