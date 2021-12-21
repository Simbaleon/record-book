package com.simbaleon.spring.models;


import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.io.Serializable;
import java.util.Optional;

@Setter
@NoArgsConstructor
public abstract class ModelService<T extends Identifiable<PK>,
        PK extends Serializable, R extends JpaRepository<T, PK>> {

    protected R repository;
    protected Class<T> clazz;

    @Transactional
    public T create(T model) {
        if (!isExists(model)) {
            repository.save(model);
        }
        throw new ValidationException("This " + clazz.getSimpleName() +
                "(" + model.getId() + ") exists!");
    }

    @Transactional
    public T update(T model) {
        T modelFromDB = getModelContainingId(model);
        return repository.save(modelFromDB);
    }

    @Transactional
    public T delete(T model)
    {
        T modelFromDB = getModelContainingId(model);
        repository.delete(modelFromDB);
        return modelFromDB;
    }

    private T getModelContainingId(T model) {
        Optional<T> modelFromDB = getModelFromDB(model);
        if (modelFromDB.isPresent()) {
            model.setId(modelFromDB.get().getId());
        }
        else throwNotFoundException(model);
        return model;
    }

    private boolean isExists(T model) {
        Optional<T> modelFromDB = getModelFromDB(model);
        return modelFromDB.isPresent();
    }

    abstract public Optional<T> getModelFromDB(T model);

    abstract public void throwNotFoundException(T model);
}
