/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arq.view;

import arq.exceptions.ViewException;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author salesfilho
 * @param <T>
 */
public interface IGenericCrudMBean<T extends Serializable> {

    public void insert(T bean) throws ViewException;

    public void update(T bean) throws ViewException;

    public void delete(T bean) throws ViewException;

    public List<T> findAll() throws ViewException;

    public T find(T bean, Long id) throws ViewException;
}
