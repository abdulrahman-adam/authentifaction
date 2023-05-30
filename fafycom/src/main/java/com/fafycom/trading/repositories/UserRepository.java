package com.fafycom.trading.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fafycom.trading.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	Optional<User> findByEmail(String email);
}
