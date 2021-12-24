package com.simbaleon.spring.models.subjects;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Optional<Subject> findByDisciplineName(String disciplineName);
    List<Subject> findAllBySemester(short semester);
    Optional<Subject> findById(Long id);
}
