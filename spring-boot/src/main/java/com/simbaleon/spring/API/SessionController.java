package com.simbaleon.spring.API;

import com.simbaleon.spring.models.records.RecordService;
import com.simbaleon.spring.models.sessions.Session;
import com.simbaleon.spring.models.sessions.SessionService;
import com.simbaleon.spring.models.subjects.Subject;
import com.simbaleon.spring.models.subjects.SubjectService;
import com.simbaleon.spring.models.users.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Sessions")
@RequestMapping("sessions/")
@RolesAllowed(User.ROLE_EMPLOYEE)
public class SessionController {
    SessionService sessionService;
    RecordService recordService;
    SubjectService subjectService;

    @PostMapping
    public Session create(@RequestBody @Valid Session request) {
        return sessionService.create(request);
    }

    @PutMapping
    public Session update(@RequestBody @Valid Session request) {
        return sessionService.update(request);
    }

    @PutMapping("/new")
    public Session newExamsInSession(@RequestParam String bookNum,
                        @RequestParam Long... subject_ids) {
        Session session = sessionService.getIfExists(bookNum);
        List<Subject> subjectList = new ArrayList<>();
        for (Long id :
                subject_ids) {
            subjectList.add(subjectService.getById(id));
        }
        session.setSubjects(subjectList);
        sessionService.update(session);
        return session;
    }

    @DeleteMapping()
    public Session delete(@RequestBody @Valid Session request) {
        return sessionService.delete(request);
    }
}
