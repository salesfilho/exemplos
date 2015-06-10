/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arq.business;

import arq.dao.GenericDaoImp;
import arq.exceptions.BusinessLogicException;
import business.ContaBusinessLogic;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.PersistenceException;

/**
 *
 * @author salesfilho
 * @param <T>
 */
public abstract class GenericBusinessLogic<T extends Serializable> {

    private final GenericDaoImp dao;

    public GenericBusinessLogic() {
        dao = new GenericDaoImp();
    }

    public void insert(T entity) throws BusinessLogicException {
        try {
            dao.insert(entity);
        } catch (Exception ex) {
            dao.rollbackTransaction();
            Logger.getLogger(ContaBusinessLogic.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenceException("Falha ao realizar operação. Erro:" + ex.getMessage());
        }
    }

    public void update(T entity) throws BusinessLogicException {
        try {
            dao.update(entity);
        } catch (Exception ex) {
            dao.rollbackTransaction();
            Logger.getLogger(ContaBusinessLogic.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenceException("Falha ao realizar operação. Erro:" + ex.getMessage());
        }
    }

    public void delete(T entity) throws BusinessLogicException {
        try {
            dao.delete(entity);
        } catch (Exception ex) {
            dao.rollbackTransaction();
            Logger.getLogger(ContaBusinessLogic.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenceException("Falha ao realizar operação.Erro:" + ex.getMessage());
        }
    }

    public List<T> findAll(T entity) throws BusinessLogicException {
        List<T> entityes;
        try {
            entityes = dao.findAll(entity);
        } catch (Exception ex) {
            dao.rollbackTransaction();
            Logger.getLogger(ContaBusinessLogic.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenceException("Falha ao realizar operação. Erro:" + ex.getMessage());
        }
        return entityes;
    }

    public T find(T entity, Long id) throws BusinessLogicException {
        T entityResult = null;
        try {
            entityResult = (T) dao.findOne(entity, id);
        } catch (Exception ex) {
            dao.rollbackTransaction();
            Logger.getLogger(ContaBusinessLogic.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenceException("Falha ao realizar operação. Erro:" + ex.getMessage());
        }
        return entityResult;
    }

    public void closeConnection() {
        dao.closeConnection();
    }
    public void beginTrasaction() {
        dao.beginTransaction();
    }
    public void commitTrasaction() {
        dao.commitTransaction();
    }

}
