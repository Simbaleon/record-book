package com.simbaleon.spring.models.sessions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionResultRepository extends JpaRepository<SessionResult, Long> {
    Optional<SessionResult> findByBookNumAndSemester(String bookNum, short semester);
}
