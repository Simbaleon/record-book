package com.simbaleon.spring.API;

import com.simbaleon.spring.models.books.Book;
import com.simbaleon.spring.models.books.BookService;
import com.simbaleon.spring.models.sessions.SessionService;
import com.simbaleon.spring.models.subjects.Subject;
import com.simbaleon.spring.models.users.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@AllArgsConstructor
@Tag(name = "Books")
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final SessionService sessionService;

    @PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
    @PostMapping("/create")
    public Book create(@RequestBody @Valid Book request) {
        Book result = bookService.create(request);
        return setLinks(result);
    }
    @PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
    @PutMapping("/edit/{id}")
    public Book edit(@RequestBody Book request, @PathVariable String id) {
        request.setId(id);
        Book result = bookService.update(request);
        return setLinks(result);
    }

    @GetMapping("{id}")
    public Book get(@PathVariable String id) {
        Book result = bookService.get(id);
        result.setSessionList(sessionService.getAllModelsFromDB(result.getId()));
        return setLinks(result);
    }

    public Book setLinks(Book result) {
        result.add(linkTo(methodOn(this.getClass()).edit(result, result.getId())).withRel("edit"));
        result.add(linkTo(methodOn(this.getClass()).delete(result.getId())).withRel("delete"));
        result.add(linkTo(methodOn(this.getClass()).get(result.getId())).withRel("get"));
        return result;
    }

    @PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
    @GetMapping("/group")
    public List<Book> getByGroup(@RequestParam String groupId) {
        return bookService.getAllModelsFromDB(groupId);
    }


    @PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
    @DeleteMapping("/delete")
    public Book delete(@RequestBody @Valid String id) {
        return bookService.delete(id);
    }
}
