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
public class UserService extends ModelService<User, Long, UserRepository> implements UserDetailsService {


    public UserService(UserRepository repository) {
        super(repository, User.class);
    }

    @Override
    public User create(User user) {
        if (repository.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalArgumentException("User with email " + user.getUsername() + " exists!");
        }
        if (user.getRole() == null) {
            user.setRole(User.Role.ROLE_STUDENT);
        }
        return repository.save(user);
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
        return repository.findByUsername(username).orElseThrow(()
                -> new NotFoundException(User.class, "username", username));
    }
}