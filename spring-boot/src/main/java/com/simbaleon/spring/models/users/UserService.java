package com.simbaleon.spring.models.users;

import com.simbaleon.spring.exceptions.NotFoundException;
import com.simbaleon.spring.models.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService extends ModelService<User, Long, UserRepository> implements UserDetailsService {

    private final UserRepository userRepo;

    @Override
    public User create(User user) {
        if (userRepo.findByUsername(user.getUsername()).isPresent()) {
            throwNotFoundException(user);
        }
        if (user.getRole() == null) {
            user.setRole(User.Role.ROLE_STUDENT);
        }
        return userRepo.save(user);
    }

    @Override
    public Optional<User> getModelFromDB(User model) {
        return repository.findByUsername(model.getUsername());
    }

    @Override
    public void throwNotFoundException(User model) {
        throw new NotFoundException(User.class, "username", model.getUsername());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username).orElseThrow(()
                -> new NotFoundException(User.class, "username", username));
    }
}