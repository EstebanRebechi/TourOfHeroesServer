package ar.com.tourofheroes.TourOfHeroesServer.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ar.com.tourofheroes.TourOfHeroesServer.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);

	List<User> findByLastName(String lastname);
}