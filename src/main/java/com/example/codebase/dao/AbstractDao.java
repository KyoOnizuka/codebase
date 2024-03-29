package com.example.codebase.dao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T> {
    public static void Main(String arrgs){
        Sample a = new Sample(null, "b");
        String b = a.name();

    }
    protected Class<T> entityClass;
    EntityManager entityManager;
    public Optional<T> findByID(String Id){
        return Optional.ofNullable(this.entityManager.find(this.entityClass, Id));
    }
    public Optional<List<T>> findAll() {
        return Optional.ofNullable(this.entityManager.createQuery("SELECT a FROM " + this.entityClass.getSimpleName() + " a ").getResultList());
    }

}
