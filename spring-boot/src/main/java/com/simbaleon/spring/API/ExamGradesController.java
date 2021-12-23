package com.simbaleon.spring.API;

import com.simbaleon.spring.exceptions.NotFoundException;
import com.simbaleon.spring.models.professors.ProfessorSubjectRepository;
import com.simbaleon.spring.models.professors.ProfessorSubjectService;
import com.simbaleon.spring.models.records.Record;
import com.simbaleon.spring.models.records.RecordService;
import com.simbaleon.spring.models.sessions.Session;
import com.simbaleon.spring.models.sessions.SessionService;
import com.simbaleon.spring.models.users.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Exams")
@RequestMapping("exams/")
public class ExamGradesController {

    RecordService recordService;
    SessionService sessionService;
    ProfessorSubjectService professorSubjectService;

    @RolesAllowed(User.ROLE_PROFESSOR)
    @PostMapping
    @PreAuthorize("@professorSubjectService.checkProfessorSubject(authentication.principal, #record)")
    public Record setRecordToAStudent(Record record) {
        return recordService.create(record);
    }

    @GetMapping("subjects")
    public Session getExamSubjects(@RequestParam String bookNum, @RequestParam short semester) {
        return sessionService.get(new Session(bookNum, semester));
    }

    @GetMapping("grades")
    public List<Record> getGradesBySemester(String bookNum, int semester) {
        Session sessionBySemester = sessionService.getIfExists(bookNum, semester);
        return recordService.getAllModelsFromDB(bookNum, sessionBySemester.getSubjects());
    }
}
