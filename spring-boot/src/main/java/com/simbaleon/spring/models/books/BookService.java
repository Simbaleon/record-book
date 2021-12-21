package com.simbaleon.spring.models.books;

import com.simbaleon.spring.exceptions.NotFoundException;
import com.simbaleon.spring.models.ModelService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService extends ModelService<Book, String, BookRepository> {

    public BookService(BookRepository repository) {
        this.repository = repository;
        this.clazz = Book.class;
    }

    @Override
    public Optional<Book> getModelFromDB(Book model) {
        return repository.findByFullNameAndFaculty(model.getFullName(), model.getFaculty());
    }

    @Override
    public void throwNotFoundException(Book model) {
        throw new NotFoundException(Book.class, "student's full name", model.getFullName());
    }
}
