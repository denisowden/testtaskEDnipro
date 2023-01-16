package com.journal.demo.service.token;

import com.journal.demo.configuration.TokenHandler;
import com.journal.demo.entity.Token;
import com.journal.demo.entity.User;
import com.journal.demo.repository.TokenRepository;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TokenServiceImpl implements TokenService {

    @Value("${jwt.exp.hours}")
    private Long tokenExpirationTime;

    private final TokenRepository tokenRepository;
    private final TokenHandler tokenHandler;


    @Override
    public Optional<Token> findByToken(String token) {
        return tokenRepository.findByToken(token);
    }

    @Override
    public Optional<Token> findByUser(User user) {
        return tokenRepository.findByUserId(user.getId());
    }

    @Override
    public Token save(Token token) {
        return tokenRepository.save(token);
    }

    @Override
    public long delete(Token token) {
        tokenRepository.delete(token);
        return 1L;
    }

    @Override
    public long deleteByUser(User user) {
        tokenRepository.deleteByUserId(user.getId());
        return 1L;
    }

    @Override
    public long delete(Long id) {
        tokenRepository.deleteById(id);
        return 1L;
    }

    @Override
    public void deleteTokenByUserId(Long userId) {
        tokenRepository.deleteByUserId(userId);
    }

    @Override
    public long deleteToken(Token token) {
        return Optional.ofNullable(token)
                .map(Token::getId)
                .flatMap(this::findById)
                .map(Token::getId)
                .map(this::delete)
                .orElse(0L);
    }

    @Override
    public Token createToken(User user) {
        final Token token = new Token(
                makeExpirationPoint(),
                tokenHandler.generateAccessToken(user),
                user
        );

        return create(token);
    }

    @Override
    public Token create(Token token){
        return tokenRepository.save(token);
    }

    private Optional<Token> findById(Long id) {
        return tokenRepository.findById(id);
    }

    @Override
    public Instant makeExpirationPoint() {
        return Instant.now()
                .plus(tokenExpirationTime, ChronoUnit.HOURS);
    }
}
