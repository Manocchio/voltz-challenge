package com.challenge.voltz.domain.repositories;

import com.challenge.voltz.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorizedUserRepository extends JpaRepository<User, Integer> {
    public Optional<User> findByName(String name);
}
