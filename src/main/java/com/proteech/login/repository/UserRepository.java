package com.proteech.login.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proteech.login.model.User;

@Repository
public interface UserRepository extends JpaRepository {
	Optional findByUsername(String username);

	Boolean existsByUsername(String username);
Boolean existsByEmail(String email);
}
