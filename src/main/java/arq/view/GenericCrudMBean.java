/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arq.view;

import arq.business.GenericBusinessLogicImp;
import arq.exceptions.BusinessLogicException;
import arq.exceptions.ViewException;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author salesfilho
 * @param <T>
 */
public abstract class GenericCrudMBean<T extends Serializable> implements IGenericCrudMBean<T> {

    private final GenericBusinessLogicImp<T> genericBusinessLogic;
    private T bean = null;

    public GenericCrudMBean() {
        genericBusinessLogic = new GenericBusinessLogicImp();
        bean =  (T) ((ParameterizedType)GenericCrudMBean.class.getGenericSuperclass()).getActualTypeArguments()[0];
    }
    public GenericCrudMBean(T bean) {
        this.bean = bean;
        genericBusinessLogic = new GenericBusinessLogicImp();
    }

    @Override
    public void insert(T bean) throws ViewException {
        try {
            if (bean == null) {
                throw new ViewException("Não é possível gravar objeto nulo");
            }
            genericBusinessLogic.beginTrasaction();
            genericBusinessLogic.insert(bean);
            genericBusinessLogic.commitTrasaction();
        } catch (BusinessLogicException ex) {
            Logger.getLogger(GenericCrudMBean.class.getName()).log(Level.SEVERE, null, ex);
            throw new ViewException("Não é possível gravar objeto");
        }
    }

    @Override
    public void update(T bean) throws ViewException {
        try {
            if (bean == null) {
                throw new ViewException("Não é possível gravar objeto nulo");
            }
            genericBusinessLogic.beginTrasaction();
            genericBusinessLogic.update(bean);
            genericBusinessLogic.commitTrasaction();
        } catch (BusinessLogicException ex) {
            Logger.getLogger(GenericCrudMBean.class.getName()).log(Level.SEVERE, null, ex);
            throw new ViewException("Não é possível atualizar objeto");
        }
    }

    @Override
    public void delete(T bean) throws ViewException {
        try {
            if (bean == null) {
                throw new ViewException("Não é possível gravar objeto nulo");
            }
            genericBusinessLogic.beginTrasaction();
            genericBusinessLogic.delete(bean);
            genericBusinessLogic.commitTrasaction();
        } catch (BusinessLogicException ex) {
            Logger.getLogger(GenericCrudMBean.class.getName()).log(Level.SEVERE, null, ex);
            throw new ViewException("Não é possível excluir objeto");
        }
    }

    @Override
    public List list(T bean) throws ViewException {
        List<T> listResult;
        try {
            listResult = genericBusinessLogic.findAll(bean);
        } catch (BusinessLogicException ex) {
            Logger.getLogger(GenericCrudMBean.class.getName()).log(Level.SEVERE, null, ex);
            throw new ViewException("Não foi possível recuperar objetos.");
        }
        return listResult;
    }
    public T getBean(){
        return this.bean;
    }

  
    
}