package com.project.restServer.repositories;

import com.project.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Marcel Kisilowski on 14.09.15.
 */

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends CrudRepository<User,Long> {

}
