package com.project.restServer.repositories;

import com.project.model.Game;
import com.project.model.Stream;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

/**
 * Created by Marcel Kisilowski on 14.09.15.
 */
@RepositoryRestResource(collectionResourceRel = "streams", path = "streams")
public interface StreamRepository extends ReadOnlyRepository<Stream> {
    Stream findByChannel(@Param("channel") String name);
    List<Stream> findByChannelLike(@Param("channel") String name);
    Page<Stream> findAllByOrderByViewersDesc(Pageable pageable);

}
