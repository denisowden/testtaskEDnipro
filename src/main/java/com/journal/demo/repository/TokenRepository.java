package com.journal.demo.repository;

import com.journal.demo.entity.Token;
import java.util.Optional;

public interface TokenRepository extends JpaRepositoryLongIdUnsafe<Token> {

    Optional<Token> findByToken(String token);

    Optional<Token> findByUserId(Long userId);

    void deleteByUserId(Long userId);
}
