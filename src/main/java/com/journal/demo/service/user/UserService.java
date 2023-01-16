package com.journal.demo.service.user;

import com.journal.demo.entity.User;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findById(Long id);

    boolean isExistsByEmail(String email);

    User create(User user);

    User update(User user);

    Optional<User> findByEmail(String email);
}
