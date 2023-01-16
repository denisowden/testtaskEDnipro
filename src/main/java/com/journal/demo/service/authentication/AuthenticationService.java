package com.journal.demo.service.authentication;


import com.journal.demo.entity.User;
import com.journal.demo.web.dto.LoginDto;

public interface AuthenticationService {

    String login(LoginDto loginDto);

    Long logout();

    User getActorFromContext();
}
