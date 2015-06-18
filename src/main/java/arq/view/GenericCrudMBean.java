/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arq.view;

import arq.business.GenericBusinessLogicImp;
import arq.exceptions.BusinessLogicException;
import arq.exceptions.ViewException;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import view.crud.BancoCrudMBean;

/**
 *
 * @author salesfilho
 * @param <T>
 */
public abstract class GenericCrudMBean<T extends Serializable> implements IGenericCrudMBean<T> {

    private final GenericBusinessLogicImp<T> genericBusinessLogic;
    private T bean;

    public GenericCrudMBean() {
        genericBusinessLogic = new GenericBusinessLogicImp();

    }

    public T getBean() {
        return bean;
    }

    public void setBean(T bean) {
        this.bean = bean;
    }

    @Override
    public void insert(T bean) throws ViewException {
        try {
            if (bean == null) {
                throw new ViewException("Não é possível gravar objeto nulo");
            }
            genericBusinessLogic.insert(bean);
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
            genericBusinessLogic.update(bean);
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
            genericBusinessLogic.delete(bean);
        } catch (BusinessLogicException ex) {
            Logger.getLogger(GenericCrudMBean.class.getName()).log(Level.SEVERE, null, ex);
            throw new ViewException("Não é possível excluir objeto");
        }
    }

    @Override
    public List findAll() throws ViewException {
        List<T> listResult;
        try {
            listResult = genericBusinessLogic.findAll(getBean());
        } catch (BusinessLogicException ex) {
            Logger.getLogger(GenericCrudMBean.class.getName()).log(Level.SEVERE, null, ex);
            throw new ViewException("Não foi possível recuperar objetos.");
        }
        return listResult;
    }

    public void addNotificationMessage(String messages) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, messages, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
        public void redirect(String page) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(
                    FacesContext.getCurrentInstance().
                    getExternalContext().getRequestContextPath() + page);
        } catch (IOException ex) {
            Logger.getLogger(BancoCrudMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
