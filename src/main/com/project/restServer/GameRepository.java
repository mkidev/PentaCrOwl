package main.com.project.restServer;

import main.com.project.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Created by Marcel Kisilowski on 08.09.15.
 */
public interface GameRepository extends JpaRepository<Game, Long> {
    Optional<Game> findByName(@Param("name") String name);
}
