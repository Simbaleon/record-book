package com.simbaleon.spring.books;

import com.simbaleon.spring.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    BookRepository repository;

    public Book update(Book book) {
        Book bookFromDB = getBookContainingId(book);
        return repository.save(bookFromDB);
    }

    public Book create(Book book) {
        if (!isBookExists(book)) {
            repository.save(book);
        }
        return null;
    }

    public Book delete(Book book) {
        Book bookFromDB = getBookContainingId(book);
        repository.delete(book);
        return bookFromDB;

    }

    private Book getBookContainingId(Book book) {
        Optional<Book> bookFromDB = repository.findByFullNameAndFaculty(
                book.getFullName(),
                book.getFaculty()
        );
        if (bookFromDB.isPresent()) {
            book.setId(bookFromDB.get().getId());
        }
        else throw new NotFoundException(Book.class, book.getFullName());
        return book;
    }

    private boolean isBookExists(Book book) {
        Optional<Book> bookFromDB = repository.findByFullNameAndFaculty(
                book.getFullName(),
                book.getFaculty()
        );
        return bookFromDB.isPresent();
    }
}
