package org.innocv.restiud.repository;

import java.util.Optional;

import org.innocv.restiud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByName(String name);

}