package com.simbaleon.spring.models.books;

import com.simbaleon.spring.exceptions.NotFoundException;
import com.simbaleon.spring.models.ModelService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService extends ModelService<Book, String, BookRepository> {

    public BookService(BookRepository repository) {
        super(repository, Book.class);
    }

    @Override
    public Optional<Book> getModel(Book model) {
        return repository.findByFullNameAndFaculty(model.getFullName(), model.getFaculty());
    }

    @Override
    public List<Book> getAllModelsFromDB(Object... params) {
        String group = (String) params[0];
        return repository.findAllByGroupId(group);
    }

    @Override
    public Optional<Book> getModel(Object... param) {
        String fullName = (String) param[0];
        String faculty = (String) param[1];
        return repository.findByFullNameAndFaculty(fullName, faculty);
    }

    @Override
    public NotFoundException throwNotFoundException(Book model) {
        return new NotFoundException(Book.class, "student's full name", model.getFullName());
    }
}
