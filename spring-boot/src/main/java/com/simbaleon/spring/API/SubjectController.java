package com.simbaleon.spring.API;

import com.simbaleon.spring.models.subjects.Subject;
import com.simbaleon.spring.models.subjects.SubjectService;
import com.simbaleon.spring.models.users.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Tag(name = "Subjects")
@RequestMapping("subjects/")
public class SubjectController {
    private SubjectService service;

    @PostMapping
    public Subject create(@RequestBody @Valid Subject request) {
        return service.create(request);
    }

    @PutMapping()
    public Subject edit(@RequestBody @Valid Subject request) {
        return service.update(request);
    }

    @DeleteMapping()
    public Subject delete(@RequestBody @Valid Subject request) {
        return service.delete(request);
    }
}
