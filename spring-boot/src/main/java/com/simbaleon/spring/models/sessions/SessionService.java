package com.simbaleon.spring.models.sessions;

import com.simbaleon.spring.exceptions.NotFoundException;
import com.simbaleon.spring.models.ModelService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SessionService extends ModelService<Session, Long, SessionRepository> {

    @Override
    public Optional<Session> getModel(Session model) {
        return repository.findByBookNumAndSemester(model.getBookNum(), model.getSemester());
    }

    @Override
    public List<Session> getAllModelsFromDB(Object... param) {
        String bookNum = (String) param[0];
        return repository.findAllByBookNum(bookNum);
    }

    @Override
    public NotFoundException throwNotFoundException(Session model) {
        return new NotFoundException(Session.class, "record book", model.getBookNum());
    }

    @Override
    public Optional<Session> getModel(Object... param) {
        String bookNum = (String) param[0];
        short semester = (short) param[1];
        return repository.findByBookNumAndSemester(bookNum, semester);
    }
}
