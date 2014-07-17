package com.greg.calculator.persistence.dao.common;

import java.io.Serializable;
import java.util.List;

/**
 * Created by GREG on 10.07.2014.
 */
public interface IOperations<T extends Serializable> {

    T findOne(final long id);

    List<T> findAll();

    void create(final T entity);

    void delete(final T entity);

    void deleteById(final long entityId);

}
