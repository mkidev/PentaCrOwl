package com.project.restServer;

import com.project.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Marcel Kisilowski on 08.09.15.
 */
public interface GameRepository extends JpaRepository<Game, Long> {
    Optional<Game> findByName(String name);
}
