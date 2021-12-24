package com.simbaleon.spring.API;

import com.simbaleon.spring.models.professors.ProfessorSubjectService;
import com.simbaleon.spring.models.records.Record;
import com.simbaleon.spring.models.records.RecordService;
import com.simbaleon.spring.models.sessions.Session;
import com.simbaleon.spring.models.sessions.SessionService;
import com.simbaleon.spring.models.users.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "Exams")
@RequestMapping("/exams")
public class ExamGradesController {

    final RecordService recordService;
    final SessionService sessionService;
    final ProfessorSubjectService professorSubjectService;


    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_PROFESSOR') and " +
            "@professorSubjectService.checkProfessorSubject(authentication.principal, #record)")
    public Record setRecordToAStudent(Record record) {
        User professor = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        record.setProfessorFullName(professor.getFullName());
        return recordService.update(record);
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
