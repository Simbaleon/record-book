package com.simbaleon.spring.models.sessions;

import com.simbaleon.spring.exceptions.NotFoundException;
import com.simbaleon.spring.models.ModelService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SessionResultService extends ModelService<SessionResult, Long, SessionResultRepository> {

    @Override
    public Optional<SessionResult> getModelFromDB(SessionResult model) {
        return repository.findByBookNumAndSemester(model.getBookNum(), model.getSemester());
    }

    @Override
    public void throwNotFoundException(SessionResult model) {
        throw new NotFoundException(SessionResult.class, "record book", model.getBookNum());
    }
}
