package com.simbaleon.spring.API;

import com.simbaleon.spring.models.professors.ProfessorSubject;
import com.simbaleon.spring.models.professors.ProfessorSubjectService;
import com.simbaleon.spring.models.users.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Tag(name = "Professors' subjects")
@RequestMapping("professor_subjects/")
@RolesAllowed(User.ROLE_EMPLOYEE)
public class ProfessorSubjectController {
    private ProfessorSubjectService service;
    @PostMapping
    public ProfessorSubject create(@RequestBody @Valid ProfessorSubject request) {
        return service.create(request);
    }

    @PutMapping()
    public ProfessorSubject edit(@RequestBody @Valid ProfessorSubject request) {
        return service.update(request);
    }

    @DeleteMapping()
    public ProfessorSubject delete(@RequestBody @Valid ProfessorSubject request) {
        return service.delete(request);
    }
}
