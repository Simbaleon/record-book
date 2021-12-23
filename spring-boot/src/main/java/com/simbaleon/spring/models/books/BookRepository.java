package com.simbaleon.spring.models.books;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    Optional<Book> findByFullNameAndFaculty(@NotEmpty String fullName, @NotEmpty String faculty);
    List<Book> findAllByGroupId(@NotEmpty String groupId);
}
