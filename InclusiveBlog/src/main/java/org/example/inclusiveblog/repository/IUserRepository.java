package org.example.inclusiveblog.repository;

import org.example.inclusiveblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);

    Optional<Object> findByEmail(String email);

    Optional<Object> findByUsername(String username);
}
