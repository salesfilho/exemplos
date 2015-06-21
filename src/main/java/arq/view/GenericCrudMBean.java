/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arq.view;

import arq.business.GenericBusinessLogicImp;
import arq.exceptions.BusinessLogicException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
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
    private T beanTransito;
    private List<T> listBean;

    public GenericCrudMBean() {
        genericBusinessLogic = new GenericBusinessLogicImp();
        listBean = new ArrayList();

    }

    @PostConstruct
    public void init() {
        //Verifica se tem algum Bean em transito antes de setar o bean inicial
        beanTransito = getBeanTransito();
        if (beanTransito != null) {
            setBean(beanTransito);
        } else {
            setBean(bean);
        }
    }

    public T getBean() {
        return bean;
    }

    public void setBean(T bean) {
        this.bean = bean;
    }

    @Override
    public void insert(T bean) {
        try {
            if (bean == null) {
                addMessage("Falha ao realizar operacção", FacesMessage.SEVERITY_ERROR);
            }
            genericBusinessLogic.beginTrasaction();
            genericBusinessLogic.insert(bean);
            genericBusinessLogic.commitTrasaction();
            addMessage("Operação realizada com sucesso", FacesMessage.SEVERITY_INFO);

        } catch (BusinessLogicException ex) {
            addMessage("Falha ao realizar operacção" + ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    @Override
    public void update(T bean) {
        try {
            if (bean == null) {
                addMessage("Falha ao realizar operacção", FacesMessage.SEVERITY_ERROR);
                return;
            }
            genericBusinessLogic.beginTrasaction();
            genericBusinessLogic.update(bean);
            genericBusinessLogic.commitTrasaction();
            addMessage("Operação realizada com sucesso", FacesMessage.SEVERITY_INFO);

        } catch (BusinessLogicException ex) {
            addMessage("Falha ao realizar operacção" + ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    @Override
    public void delete(T bean) {
        try {
            if (bean == null) {
                addMessage("Falha ao realizar operacção", FacesMessage.SEVERITY_ERROR);
                return;
            }
            genericBusinessLogic.beginTrasaction();
            genericBusinessLogic.delete(bean);
            genericBusinessLogic.commitTrasaction();
            addMessage("Operação realizada com sucesso", FacesMessage.SEVERITY_INFO);

        } catch (BusinessLogicException ex) {
            addMessage("Falha ao realizar operacção" + ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    @Override
    public T find(T bean, Long id) {
        T result = null;
        try {
            genericBusinessLogic.beginTrasaction();
            result = (T) genericBusinessLogic.find(bean, id);
            genericBusinessLogic.commitTrasaction();
        } catch (BusinessLogicException ex) {
            addMessage("Falha ao realizar operacção" + ex.getMessage(), FacesMessage.SEVERITY_ERROR);
            //throw new ViewException("Não foi possível recuperar objeto.");
        }
        return result;
    }

    @Override
    public List findAll() {
        List<T> listResult = null;
        try {
            genericBusinessLogic.beginTrasaction();
            listResult = genericBusinessLogic.findAll(getBean());
            genericBusinessLogic.commitTrasaction();
            addMessage("Operação realizada com sucesso", FacesMessage.SEVERITY_INFO);

        } catch (BusinessLogicException ex) {
            addMessage("Falha ao realizar operacção" + ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        return listResult;
    }

    /* Metodos genericos chamados/utilizados pela View */
    /* Metodos relacionados a listar */
    public void checkBeforeList() {
        System.out.println("************************ ANTES DO LIST *******************************************");
        System.out.println("Bean: " + bean.toString());
    }

    public List<T> list() {
        if (listBean.isEmpty()) {
            listBean = findAll();
        }
        return listBean;
    }

    /* Metodos relacionados a inserir */
    public void checkBeforeInsert() {
        System.out.println("************************ ANTES DO INSERT *******************************************");
        System.out.println("Bean: " + bean.toString());
        if (getBean() == null) {
            addMessage("Falha ao realizar operação", FacesMessage.SEVERITY_ERROR);
        }
    }

    public void processInsert() {
        checkBeforeInsert();
        insert(bean);
    }

    /* Metodos relacionados a atualizar */
    public void onStartUpdate(Long id) {
        setBeanTransito(id);
    }

    public void checkBeforeUpdate() {
        System.out.println("************************ ANTES DO UPDATE *******************************************");
        System.out.println("Bean: " + getBean().toString());
        System.out.println("Metodo getBeanTransito(): " + getBeanTransito());
        System.out.println("*******************************************************************");
    }

    public void processUpdate() {
        checkBeforeUpdate();
        update(getBean());
    }

    /* Metodos de view auxiliares */
    public void addMessage(String messages, FacesMessage.Severity severity) {
        FacesMessage message = new FacesMessage(severity, messages, null);
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

    /* Metodos relacionados a exclusao, chamado antes de excluir um objeto */
    public void checkBeforeDelete() {
        System.out.println("************************ ANTES DO DELETE *******************************************");
        if (getBean() == null) {
            addMessage("Falha ao realizar operação", FacesMessage.SEVERITY_ERROR);
        }
    }

    public void processDelete(Long id) {
        checkBeforeDelete();
        bean = find(getBean(), id);
        delete(bean);
    }

    /* Recupera do FLASH caso tenha sido passado por alguma view ou cria um novo */
    public T getBeanTransito() {
        return (T) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("bean");
    }

    /* Repopula o Bean a partir do ID passado por parâmetro da View e coloca no FLASH para uso em outra view */
    public void setBeanTransito(Long id) {
        beanTransito = (T) find(getBean(), id);
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("bean", beanTransito);
    }

}
