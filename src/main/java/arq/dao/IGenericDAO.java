/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arq.dao;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author salesfilho
 * @param <T>
 */
public interface IGenericDAO <T extends Serializable>{

    void insert(T entity);

    void update(T entity);

    void delete (T entity);

    T findOne(T entity, Long id);

    List<T> findAll(T entity);

}
