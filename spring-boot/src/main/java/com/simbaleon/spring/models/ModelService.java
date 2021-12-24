package com.simbaleon.spring.models;


import com.simbaleon.spring.exceptions.NotFoundException;
import com.simbaleon.spring.models.books.BookRepository;
import com.simbaleon.spring.models.sessions.Session;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class ModelService<T extends Identifiable<PK>,
        PK extends Serializable, R extends JpaRepository<T, PK>> {

    protected final R repository;
    protected final Class<T> clazz;

    public Optional<T> getById(PK id) {
        return repository.findById(id);
    }

    public T get(PK id) {
        Optional<T> modelFromDB = getById(id);
        if (modelFromDB.isPresent())
            return modelFromDB.get();
        throw new NotFoundException(clazz, id);
    }

    public T create(T model) {
        if (get(model) == null) {
            return repository.save(model);
        }
        throw new IllegalArgumentException("This " + clazz.getSimpleName() +
                "(" + model.getId() + ") exists!");
    }

    public T update(T model) {
        T modelFromDB = getModelContainingId(model);
        return repository.save(modelFromDB);
    }

    public T delete(PK id)
    {
        Optional<T> modelFromDB = getById(id);
        modelFromDB.ifPresent(repository::delete);
        throw new NotFoundException(clazz, id);
    }

    public T get(T model) {
        return getModel(model).orElse(null);
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
