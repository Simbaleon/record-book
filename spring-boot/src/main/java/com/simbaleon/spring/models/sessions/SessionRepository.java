package com.simbaleon.spring.models.sessions;

import com.simbaleon.spring.models.subjects.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    Optional<Session> findByBookNumAndSemester(String bookNum, short semester);
    List<Session> findAllByBookNum(String bookNum);
    Optional<Session> findById(Long id);
}
