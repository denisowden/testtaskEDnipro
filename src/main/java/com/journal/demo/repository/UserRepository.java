package com.journal.demo.repository;

import com.journal.demo.entity.User;
import java.util.Optional;


public interface UserRepository extends JpaRepositoryLongIdUnsafe<User> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
