package com.simbaleon.spring.API;

import com.simbaleon.spring.models.professors.ProfessorSubject;
import com.simbaleon.spring.models.records.RecordService;
import com.simbaleon.spring.models.sessions.Session;
import com.simbaleon.spring.models.sessions.SessionService;
import com.simbaleon.spring.models.subjects.Subject;
import com.simbaleon.spring.models.subjects.SubjectService;
import com.simbaleon.spring.models.users.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@AllArgsConstructor
@Tag(name = "Sessions")
@RequestMapping("/sessions")
@PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
public class SessionController {
    final SessionService sessionService;
    final RecordService recordService;
    final SubjectService subjectService;

    @PostMapping("/create")
    public Session create(@RequestBody @Valid Session request) {
        Session result = sessionService.create(request);
        return setLinks(result);
    }

    @PutMapping("/edit/{id}")
    public Session edit(@RequestBody Session request, @PathVariable Long id) {
        request.setId(id);
        Session result = sessionService.update(request);
        return setLinks(result);
    }

    @GetMapping("{id}")
    public Session get(@PathVariable Long id) {
        Session result = sessionService.get(id);
        return setLinks(result);
    }

    public Session setLinks(Session result) {
        result.add(linkTo(methodOn(this.getClass()).edit(result, result.getId())).withRel("edit"));
        result.add(linkTo(methodOn(this.getClass()).delete(result.getId())).withRel("delete"));
        result.add(linkTo(methodOn(this.getClass()).get(result.getId())).withRel("get"));
        return result;
    }

    @PutMapping("/new")
    public Session newExamsInSession(@RequestParam String bookNum,
                        @RequestParam Long... subject_ids) {
        Session session = sessionService.getIfExists(bookNum);
        List<Subject> subjectList = new ArrayList<>();
        for (Long id :
                subject_ids) {
            subjectList.add(subjectService.get(id));
        }
        session.setSubjects(subjectList);
        sessionService.update(session);
        return session;
    }

    @DeleteMapping("/delete")
    public Session delete(@RequestBody @Valid Long id) {
        return sessionService.delete(id);
    }
}
