package com.simbaleon.spring.models.records;

import com.simbaleon.spring.models.sessions.SessionResult;
import com.simbaleon.spring.models.subjects.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
    Optional<Record> findBySessionResultAndSubject(SessionResult sessionResult, Subject subject);
}
