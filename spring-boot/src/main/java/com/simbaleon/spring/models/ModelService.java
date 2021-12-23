package com.simbaleon.spring.models;


import com.simbaleon.spring.exceptions.NotFoundException;
import com.simbaleon.spring.models.sessions.Session;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.ValidationException;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
public abstract class ModelService<T extends Identifiable<PK>,
        PK extends Serializable, R extends JpaRepository<T, PK>> {

    protected R repository;
    protected Class<T> clazz;

    public T getById(PK id) {
        return repository.getById(id);
    }

    public T create(T model) {
        if (get(model) != null) {
            repository.save(model);
        }
        throw new ValidationException("This " + clazz.getSimpleName() +
                "(" + model.getId() + ") exists!");
    }

    public T update(T model) {
        T modelFromDB = getModelContainingId(model);
        return repository.save(modelFromDB);
    }

    public T delete(T model)
    {
        T modelFromDB = getModelContainingId(model);
        repository.delete(modelFromDB);
        return modelFromDB;
    }

    public T get(T model) {
        return getIfExists(model);
    }

    private T getModelContainingId(T model) {
        T modelFromDB = getIfExists(model);
        model.setId(modelFromDB.getId());
        return model;
    }

    public T getIfExists(Object... param) {
        return getModel(param).orElseThrow(() -> new NotFoundException(clazz, param));
    }

    public T getIfExists(T model) {
        return getModel(model).orElseThrow(() -> throwNotFoundException(model));
    }

    abstract public List<T> getAllModelsFromDB(Object... param);

    abstract protected Optional<T> getModel(T model);
    abstract protected Optional<T> getModel(Object... param);

    abstract public NotFoundException throwNotFoundException(T model);
}
