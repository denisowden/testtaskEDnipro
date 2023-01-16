package com.journal.demo.web;

import com.journal.demo.service.authentication.AuthenticationService;
import com.journal.demo.web.dto.LoginDto;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@Api(tags = {"Authorization"}, description = "Authorise user")
public class AuthorizationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginDTO){
        return authenticationService.login(loginDTO);
    }

    @PreAuthorize("authentication.principal!=null")
    @GetMapping("/logout")
    public Long logout() {
        return authenticationService.logout();
    }
}
