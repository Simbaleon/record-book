package com.simbaleon.spring.books;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotEmpty;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByFullNameAndFaculty(@NotEmpty String fullName, @NotEmpty String faculty);
}
