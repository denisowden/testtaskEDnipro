package com.journal.demo.service.token;


import com.journal.demo.entity.Token;
import com.journal.demo.entity.User;
import java.time.Instant;
import java.util.Optional;

public interface TokenService {

    Optional<Token> findByToken(String token);

    Optional<Token> findByUser(User user);

    Token save(Token token);

    long delete(Token token);

    long deleteByUser(User user);

    long delete(Long id);

    void deleteTokenByUserId(Long userId);

    long deleteToken(Token token);

    Token createToken(User user);

    Token create(Token token);

    Instant makeExpirationPoint();
}
