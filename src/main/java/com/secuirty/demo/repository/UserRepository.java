package com.secuirty.demo.repository;

import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.secuirty.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);
	 
    @Cacheable(value = "users", key = "#id")
    Optional<User> findById(Long id);
}
