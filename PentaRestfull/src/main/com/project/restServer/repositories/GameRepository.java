package com.project.restServer.repositories;

import com.project.model.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

/**
 * Created by Marcel Kisilowski on 08.09.15.
 */
@RepositoryRestResource(collectionResourceRel = "games", path = "games")
public interface GameRepository extends ReadOnlyRepository<Game> {
    Game findByName(@Param("name") String name);
    List<Game> findByNameLike(@Param("name") String name);
    Page<Game> findAllByOrderByViewersDesc(Pageable pageable);
}
