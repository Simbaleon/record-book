package com.simbaleon.spring.models.subjects;

import com.simbaleon.spring.exceptions.NotFoundException;
import com.simbaleon.spring.models.ModelService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService extends ModelService<Subject, Long, SubjectRepository> {

    @Override
    protected Optional<Subject> getModel(Subject model) {
        return repository.findByDisciplineName(model.getDisciplineName());
    }

    @Override
    public List<Subject> getAllModelsFromDB(Object... param) {
        short semester = (short) param[0];
        return repository.findAllBySemester(semester);
    }

    @Override
    protected Optional<Subject> getModel(Object... param) {
        String disciplineName = (String) param[0];
        return repository.findByDisciplineName(disciplineName);
    }

    @Override
    public NotFoundException throwNotFoundException(Subject model) {
        return new NotFoundException(Subject.class, "discipline", model.getDisciplineName());
    }
}
