package com.simbaleon.spring.models.subjects;

import com.simbaleon.spring.exceptions.NotFoundException;
import com.simbaleon.spring.models.ModelService;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class SubjectService extends ModelService<Subject, Long, SubjectRepository> {

    @Override
    public Optional<Subject> getModelFromDB(Subject model) {
        return repository.findByDisciplineName(model.getDisciplineName());
    }

    @Override
    public void throwNotFoundException(Subject model) {
        throw new NotFoundException(Subject.class, "discipline", model.getDisciplineName());
    }
}
