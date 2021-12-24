package com.simbaleon.spring.models.professors;

import com.simbaleon.spring.models.subjects.Subject;
import com.simbaleon.spring.models.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfessorSubjectRepository extends JpaRepository<ProfessorSubject, Long> {
    Optional<ProfessorSubject> findByProfessor(User professor);
    List<ProfessorSubject> findBySubject(Subject subject);
    Optional<ProfessorSubject> findById(Long id);
}
