package com.simbaleon.spring.users;

import com.simbaleon.spring.exceptions.NotFoundException;
import com.simbaleon.spring.users.User;
import com.simbaleon.spring.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepo;

    @Transactional
    public User create(User user) {
        if (userRepo.findByUsername(user.getUsername()).isPresent()) {
            throw new ValidationException("Username exists!");
        }
        if (user.getRole() == null) {
            user.setRole(User.Role.ROLE_STUDENT);
        }
        return userRepo.save(user);
    }

    @Transactional
    public User update(User user) {
        return userRepo.save(user);
    }

    @Transactional
    public User upsert(User user) {
        Optional<User> optionalUser = userRepo.findByUsername(user.getUsername());
        if (optionalUser.isEmpty()) {
            return create(user);
        } else {
            return update(user);
        }
    }

    @Transactional
    public User delete(Long id) {
        User user = userRepo.getById(id);
        user.setUsername(user.getUsername().replace("@", String.format("_%s@", user.getId().toString())));
        user = userRepo.save(user);

        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username).orElseThrow(()
                -> new NotFoundException(User.class, username));
    }

    public boolean usernameExists(String username) {
        return userRepo.findByUsername(username).isPresent();
    }

    public User getUser(Long id) {
        return userRepo.getById(id);
    }
}