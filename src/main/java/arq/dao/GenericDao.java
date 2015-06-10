/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arq.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author salesfilho
 * @param <T>
 */
public abstract class GenericDao<T extends Serializable> implements IGenericDAO<T> {

    private static EntityManager entityManager;
    private static EntityManagerFactory entityFactory;
    private static String persistenceUnit = "Default";

    public GenericDao() {
        entityManager = getInstance();
        //entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getClass();
        //entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        //System.out.println("-------------->" + entityClass.toGenericString());
    }

    public static EntityManager getInstance() {
        if (entityManager == null) {
            entityFactory = Persistence.createEntityManagerFactory(persistenceUnit);
            entityManager = entityFactory.createEntityManager();
        }
        return entityManager;
    }

    public String getPersistenceUnit() {
        return persistenceUnit;
    }

    public void setPersistenceUnit(String persistenceUnit) {
        GenericDao.persistenceUnit = persistenceUnit;
    }

    public void beginTransaction() {
        getInstance().getTransaction().begin();
    }

    public void commitTransaction() {
        getInstance().getTransaction().commit();
    }

    public void rollbackTransaction() {
        getInstance().getTransaction().rollback();
    }
    public void closeConnection() {
        getInstance().close();
    }

    @Override
    public void insert(T entity) {
        getInstance().persist(entity);
    }

    @Override
    public void update(T entity) {
        getInstance().merge(entity);
    }

    @Override
    public void delete(T entity) {
        getInstance().remove(entity);
    }

    @Override
    public T findOne(T entity, Long id) {
        return (T)getInstance().find(entity.getClass(), id);
    }

    @Override
    public List<T> findAll(T entity) {
        return getInstance().createQuery("SELECT t FROM " + entity.getClass().getSimpleName() + " t" ).getResultList();
    }

}
