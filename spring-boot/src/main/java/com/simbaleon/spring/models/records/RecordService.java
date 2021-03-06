package com.simbaleon.spring.models.records;

import com.simbaleon.spring.exceptions.NotFoundException;
import com.simbaleon.spring.models.ModelService;
import com.simbaleon.spring.models.subjects.Subject;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecordService extends ModelService<Record, Long, RecordRepository> {

    public RecordService(RecordRepository repository) {
        super(repository, Record.class);
    }

    @Override
    public Optional<Record> getModel(Record model) {
        return repository.findByBookNumAndSubject(model.getBookNum(), model.getSubject());
    }

    @Override
    public List<Record> getAllModelsFromDB(Object... param) {
        String bookNum = (String) param[0];
        List<Record> records = new ArrayList<>();
        for (int i = 1; i < param.length; i++) {
            Subject subject = (Subject) param[i];
            records.add(repository.findByBookNumAndSubject(bookNum, subject).orElse(null));
        }
        return records.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Override
    public Optional<Record> getModel(Object... param) {
        String bookNum = (String) param[0];
        Subject subject = (Subject) param[1];
        return repository.findByBookNumAndSubject(bookNum, subject);
    }

    @Override
    public NotFoundException throwNotFoundException(Record model) {
        return new NotFoundException(Record.class, "subject",
                model.getSubject().getDisciplineName() + " and " + model.getSubject().getSemester());
    }
}
