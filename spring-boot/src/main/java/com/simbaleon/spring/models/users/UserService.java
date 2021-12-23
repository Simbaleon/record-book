package com.simbaleon.spring.models.users;

import com.simbaleon.spring.exceptions.NotFoundException;
import com.simbaleon.spring.models.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService extends ModelService<User, Long, UserRepository> implements UserDetailsService {

    private final UserRepository userRepo;

    @Override
    public User create(User user) {
        if (userRepo.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalArgumentException("User with email " + user.getUsername() + " exists!");
        }
        if (user.getRole() == null) {
            user.setRole(User.Role.ROLE_STUDENT);
        }
        return userRepo.save(user);
    }

    @Override
    public Optional<User> getModel(User model) {
        return repository.findByUsername(model.getUsername());
    }

    @Override
    public List<User> getAllModelsFromDB(Object... param) {
        User.Role role = (User.Role) param[0];
        return repository.findAllByRole(role);
    }

    @Override
    public Optional<User> getModel(Object... param) {
        String username = (String) param[0];
        return repository.findByUsername(username);
    }

    @Override
    public NotFoundException throwNotFoundException(User model) {
        return new NotFoundException(User.class, "username", model.getUsername());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username).orElseThrow(()
                -> new NotFoundException(User.class, "username", username));
    }
}