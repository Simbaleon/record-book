package com.simbaleon.spring.models.records;

import com.simbaleon.spring.exceptions.NotFoundException;
import com.simbaleon.spring.models.ModelService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecordService extends ModelService<Record, Long, RecordRepository> {

    @Override
    public Optional<Record> getModelFromDB(Record model) {
        return repository.findBySessionResultAndSubject(model.getSessionResult(), model.getSubject());
    }

    @Override
    public void throwNotFoundException(Record model) {
        throw new NotFoundException(Record.class, "subject",
                model.getSubject().getDisciplineName() + " and " + model.getSubject().getSemester());
    }
}
