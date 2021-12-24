package com.simbaleon.spring.API;

import com.simbaleon.spring.models.books.Book;
import com.simbaleon.spring.models.professors.ProfessorSubject;
import com.simbaleon.spring.models.professors.ProfessorSubjectService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@AllArgsConstructor
@Tag(name = "Professors' subjects")
@RequestMapping("/professor_subjects")
@PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
public class ProfessorSubjectController {
    private final ProfessorSubjectService service;
    @PostMapping("/create")
    public ProfessorSubject create(@RequestBody @Valid ProfessorSubject request) {
        ProfessorSubject result = service.create(request);
        return setLinks(result);
    }

    @PutMapping("/edit/{id}")
    public ProfessorSubject edit(@RequestBody @Valid ProfessorSubject request, @PathVariable Long id) {
        request.setId(id);
        ProfessorSubject result = service.update(request);
        return setLinks(result);
    }

    @DeleteMapping("/delete")
    public ProfessorSubject delete(@RequestBody @Valid Long id) {
        return service.delete(id);
    }

    @GetMapping
    public ProfessorSubject get(@RequestParam Long id) {
        ProfessorSubject result = service.get(id);
        return setLinks(result);
    }

    public ProfessorSubject setLinks(ProfessorSubject result) {
        result.add(linkTo(methodOn(this.getClass()).edit(result, result.getId())).withRel("edit"));
        result.add(linkTo(methodOn(this.getClass()).delete(result.getId())).withRel("delete"));
        result.add(linkTo(methodOn(this.getClass()).get(result.getId())).withRel("get"));
        return result;
    }
}
