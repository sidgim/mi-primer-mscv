package org.glara.springcloud.msvc.users.repository;

import org.glara.springcloud.msvc.users.models.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
    Optional<User> findByEmail(String email);

    @Query("select u from User u where u.email = ?1")
    Optional<User> findByEmailQuery(String email);

    boolean existsByEmail(String email);
}
