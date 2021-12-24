package com.simbaleon.spring.API;

import com.simbaleon.spring.models.books.Book;
import com.simbaleon.spring.models.users.User;
import com.simbaleon.spring.models.users.UserRepository;
import com.simbaleon.spring.models.users.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@AllArgsConstructor
@Tag(name = "Users")
@RequestMapping("/users")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class UserController {
    final UserService service;

    @PostMapping("/create")
    public User create(@RequestBody @Valid User request) {
        User result = service.create(request);
        return setLinks(result);
    }

    @PutMapping("/edit/{id}")
    public User edit(@RequestBody User request, @PathVariable Long id) {
        request.setId(id);
        User result = service.update(request);
        return setLinks(result);
    }

    @GetMapping("{id}")
    public User get(@PathVariable Long id) {
        User result = service.get(id);
        return setLinks(result);
    }

    @DeleteMapping()
    public User delete(@RequestBody @Valid Long id) {
        return service.delete(id);
    }

    @GetMapping
    public List<User> getAllByRole(@RequestParam String role) {
        return service.getAllModelsFromDB(User.Role.valueOf(role)).stream()
                .map(this::setLinks).collect(Collectors.toList());
    }

    @PutMapping("/role")
    public User setRole(@RequestParam User.Role role, @RequestParam String studentEmail) {
        User student = service.getIfExists(studentEmail);
        student.setRole(role);
        return service.update(student);
    }

    public User setLinks(User result) {
        result.add(linkTo(methodOn(this.getClass()).edit(result, result.getId())).withRel("edit"));
        result.add(linkTo(methodOn(this.getClass()).delete(result.getId())).withRel("delete"));
        result.add(linkTo(methodOn(this.getClass()).get(result.getId())).withRel("get"));
        return result;
    }
}
