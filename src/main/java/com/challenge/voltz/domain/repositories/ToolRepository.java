package com.challenge.voltz.domain.repositories;

import com.challenge.voltz.domain.entities.Tool;
import com.challenge.voltz.domain.entities.enums.ToolStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface ToolRepository extends JpaRepository<Tool, Integer> {
    List<Tool> findAllByStatus (ToolStatus status);
    Optional<Tool> findByIdAndStatus(Integer id, ToolStatus status);
    Optional<Tool> findFirstByTitleIgnoreCase (String title);
}
