package br.com.ortiz.domain.dao;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

public class GenericDaoJpaImpl<T, PK extends Serializable>
implements GenericDao<T, PK> {

    @PersistenceContext
    protected EntityManager entityManager;

    protected Class<T> entityClass;

    public GenericDaoJpaImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass()
        .getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }

    public T save(T object) {
        this.entityManager.persist(object);
        return object;
    }

    public Optional<T> find(PK id) {
        return Optional.ofNullable(this.entityManager.find(entityClass, id));
    }

    public T update(T object) {
        return this.entityManager.merge(object);
    }

    public void delete(T object) {
        object = this.entityManager.merge(object);
        this.entityManager.remove(object);
    }

    public List<T> findAll() {
        return entityManager
        .createQuery(String.format("Select obj from %s obj", entityClass.getSimpleName()), entityClass)
        .getResultList();
    }

}
