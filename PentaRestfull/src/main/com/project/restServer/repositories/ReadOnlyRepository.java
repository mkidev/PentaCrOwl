package com.project.restServer.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by Marcel Kisilowski on 14.09.15.
 */

/**
 *
 * @param <T>
 */
@NoRepositoryBean
public interface ReadOnlyRepository<T> extends Repository<T, Long> {
    /**
     *
     * @param id
     * @return
     */
    T findOne(Long id);

    /**
     *
     * @param id
     * @return
     */
    boolean exists(Long id);

    /**
     *
     * @return
     */
    List<T> findAll();

    /**
     *
     * @return
     */
    long count();

    List<T> findAll(Iterable<Long> ids);
    List<T> findAll(Sort sort);
    Page<T> findAll(Pageable pageable);


}
