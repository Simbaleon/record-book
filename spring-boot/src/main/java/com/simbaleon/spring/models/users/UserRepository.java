package com.simbaleon.spring.models.users;

import com.simbaleon.spring.models.records.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    List<User> findAllByRole(User.Role role);
    Optional<User> findById(Long id);
}
