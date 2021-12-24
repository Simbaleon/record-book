package com.simbaleon.spring.models.professors;

import com.simbaleon.spring.exceptions.NotFoundException;
import com.simbaleon.spring.models.ModelService;
import com.simbaleon.spring.models.sessions.Session;
import com.simbaleon.spring.models.subjects.Subject;
import com.simbaleon.spring.models.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorSubjectService extends ModelService<ProfessorSubject,
        Long, ProfessorSubjectRepository> {


    public ProfessorSubjectService(ProfessorSubjectRepository repository) {
        super(repository, ProfessorSubject.class);
    }

    public boolean checkProfessorSubject(User professor, Subject subject) {
        return getIfExists(professor).getSubject().equals(subject);
    }

    @Override
    public Optional<ProfessorSubject> getModel(ProfessorSubject model) {
        return repository.findByProfessor(model.getProfessor());
    }

    @Override
    public List<ProfessorSubject> getAllModelsFromDB(Object... param) {
        Subject subject = (Subject) param[0];
        return repository.findBySubject(subject);
    }

    @Override
    public Optional<ProfessorSubject> getModel(Object... param) {
        User professor = (User) param[0];
        return repository.findByProfessor(professor);
    }

    @Override
    public NotFoundException throwNotFoundException(ProfessorSubject model) {
        return new NotFoundException(Session.class, model.getProfessor(), model.getSubject());
    }
}
