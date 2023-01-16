package com.journal.demo.service.user;

import com.journal.demo.entity.User;
import com.journal.demo.exception.user.UserAlreadyExistsException;
import com.journal.demo.exception.user.UserNotFoundException;
import com.journal.demo.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return userRepository.findByEmail(login)
                .orElse(null);
    }

    @Override
    public User findById(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public boolean isExistsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User create(User user) {

        if (isExistsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException(user.getEmail());
        }

        return userRepository.save(user);
    }

    @Override
    public User update(User user) {

        User actualUser = userRepository
                .findByEmail(user.getEmail())
                .orElseThrow(() -> new UserNotFoundException(user.getEmail()));

        setUserFieldsForUpdate(actualUser, user);

        return userRepository.save(actualUser);
    }

    private void setUserFieldsForUpdate(User actualUser, User user) {
        actualUser.setFirstName(user.getFirstName());
        actualUser.setLastName(user.getLastName());
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
