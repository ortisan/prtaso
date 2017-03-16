package br.com.ortiz.domain.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface GenericDao<T, PK extends Serializable> {

    T save(T t);

    Optional<T> find(PK id);

    T update(T t);

    void delete(T t);

    List<T> findAll();
}
