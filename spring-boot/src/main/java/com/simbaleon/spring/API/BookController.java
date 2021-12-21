package com.simbaleon.spring.API;

import com.simbaleon.spring.books.Book;
import com.simbaleon.spring.books.BookService;
import com.simbaleon.spring.users.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Tag(name = "Books")
@RequestMapping("books/")
public class BookController {
    private final BookService bookService;

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
    @DeleteMapping("{id}")
    public Book delete(@RequestBody @Valid Book request) {
        return bookService.delete(request);
    }
}
