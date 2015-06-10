/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arq.business;

import arq.exceptions.BusinessLogicException;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author salesfilho
 * @param <T>
 * @param <ID>
 */
public interface IGenericBusinessLogic<T, ID extends Serializable> {

    void insert(T entity) throws BusinessLogicException;

    void update(T entity) throws BusinessLogicException;

    void delete(T entity) throws BusinessLogicException;

    T findOne(ID id) throws BusinessLogicException;

    List<T> findAll() throws BusinessLogicException;

}
