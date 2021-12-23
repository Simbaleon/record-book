package com.simbaleon.spring.API;

import com.simbaleon.spring.models.books.Book;
import com.simbaleon.spring.models.books.BookService;
import com.simbaleon.spring.models.sessions.SessionService;
import com.simbaleon.spring.models.users.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Books")
@RequestMapping("books/")
public class BookController {
    private final BookService bookService;
    private final SessionService sessionService;

    @RolesAllowed(User.ROLE_EMPLOYEE)
    @PostMapping
    public Book create(@RequestBody @Valid Book request) {
        return bookService.create(request);
    }

    @RolesAllowed(User.ROLE_EMPLOYEE)
    @PutMapping()
    public Book edit(@RequestBody @Valid Book request) {
        return bookService.update(request);
    }

    @RolesAllowed(User.ROLE_EMPLOYEE)
    @DeleteMapping()
    public Book delete(@RequestBody @Valid Book request) {
        return bookService.delete(request);
    }

    @GetMapping
    public Book get(@RequestBody @Valid Book request) {
        Book book = bookService.get(request);
        book.setSessionList(sessionService.getAllModelsFromDB(book.getId()));
        return book;
    }

    @RolesAllowed(User.ROLE_EMPLOYEE)
    @GetMapping("/group")
    public List<Book> get(@RequestParam String groupId) {
        return bookService.getAllModelsFromDB(groupId);
    }
}
