package com.simbaleon.spring.API;

import com.simbaleon.spring.models.Identifiable;
import com.simbaleon.spring.models.sessions.Session;
import com.simbaleon.spring.models.subjects.Subject;
import com.simbaleon.spring.models.subjects.SubjectService;
import com.simbaleon.spring.models.users.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@Tag(name = "Subjects")
@RequestMapping("/subjects")
@PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
public class SubjectController {
    private final SubjectService service;

    @PostMapping("/create")
    public Subject create(@RequestBody @Valid Subject request) {
        Subject result = service.create(request);
        return setLinks(result);
    }

    @PutMapping("/edit/{id}")
    public Subject edit(@RequestBody Subject request, @PathVariable Long id) {
        request.setId(id);
        Subject result = service.update(request);
        return setLinks(result);
    }

    @GetMapping("{id}")
    public Subject get(@PathVariable Long id) {
        Subject result = service.get(id);
        return setLinks(result);
    }

    public Subject setLinks(Subject result) {
        result.add(linkTo(methodOn(this.getClass()).edit(result, result.getId())).withRel("edit"));
        result.add(linkTo(methodOn(this.getClass()).delete(result.getId())).withRel("delete"));
        result.add(linkTo(methodOn(this.getClass()).get(result.getId())).withRel("get"));
        return result;
    }

    @DeleteMapping("/delete/{id}")
    public Subject delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
