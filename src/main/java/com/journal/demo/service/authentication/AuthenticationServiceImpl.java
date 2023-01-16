package com.journal.demo.service.authentication;

import com.journal.demo.configuration.TokenHandler;
import com.journal.demo.entity.Token;
import com.journal.demo.entity.User;
import com.journal.demo.exception.BadCredentialsException;
import com.journal.demo.service.token.TokenService;
import com.journal.demo.service.user.UserService;
import com.journal.demo.web.dto.LoginDto;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final TokenHandler tokenHandler;
    private final TokenService tokenService;

    @Override
    public String login(LoginDto loginDto) {
        User actor = userService.findByEmail(loginDto.getEmail())
                .orElseThrow(BadCredentialsException::new);

        validatePassword(actor, loginDto.getPassword());

        return tokenService.save(manageToken(actor)).getToken();
    }

    @Override
    public Long logout() {
        tokenService.deleteByUser(getActorFromContext());
        return 1L;
    }

    @Override
    public User getActorFromContext() {
        return Optional.of(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .map(authentication -> (User) authentication.getPrincipal())
                .orElseGet(User::new);
    }

    public void validatePassword(User actor, String password) {
        if (!passwordEncoder.matches(password, actor.getPassword())) {
            throw new BadCredentialsException();
        }
    }

    private Token manageToken(User user) {
        return tokenService.findByUser(user)
                .map(token -> updateExistedToken(token, user))
                .orElseGet(() -> createNewToken(user));
    }

    private Token updateExistedToken(Token token, User user) {
        token.setToken(tokenHandler.generateAccessToken(user));
        token.setExpiration(tokenService.makeExpirationPoint());
        return token;
    }

    private Token createNewToken(User user) {
        return new Token(
                tokenService.makeExpirationPoint(),
                tokenHandler.generateAccessToken(user),
                user
        );
    }
}
