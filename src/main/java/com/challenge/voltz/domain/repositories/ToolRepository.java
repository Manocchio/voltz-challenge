package com.challenge.voltz.domain.repositories;

import com.challenge.voltz.domain.entities.Tool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ToolRepository extends JpaRepository<Tool, Integer> {
    Optional<Tool> findByIdAndStatus(Integer id, String status);
}
