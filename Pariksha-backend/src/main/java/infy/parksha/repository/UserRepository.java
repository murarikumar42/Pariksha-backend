package infy.parksha.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import infy.parksha.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	Optional<User> findByEmailId(String emailId);

	
}
