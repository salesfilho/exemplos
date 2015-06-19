/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.crud;

import arq.exceptions.BusinessLogicException;
import arq.exceptions.ViewException;
import arq.view.GenericCrudMBean;
import business.BancoBusinessLogic;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.Banco;
import model.Endereco;

/**
 *
 * @author salesfilho
 */
@ManagedBean
@ViewScoped
public class BancoCrudMBean extends GenericCrudMBean<Banco> {

    private final BancoBusinessLogic bancoBusinessLogic;
    private Banco banco;
    private Banco beanBanco;
    private final Endereco endereco;
    private List<Banco> listBancos;

    public BancoCrudMBean() {
        bancoBusinessLogic = new BancoBusinessLogic();
        banco = new Banco();
        endereco = new Endereco();
        banco.setEndereco(endereco);
        listBancos = new ArrayList();
        beanBanco = new Banco();
    }

    @PostConstruct
    public void init() {
        //Verifica se tem algum Bean em transito antes de setar o bean inicial
        beanBanco = getBeanTransito();
        if (beanBanco != null) {
            setBean(beanBanco);
        } else {
            setBean(banco);
        }
    }

    /* Recupera do FLASH caso tenha sido passado por alguma view ou cria um novo */
    public Banco getBeanTransito() {
        beanBanco = (Banco) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("banco");
        return beanBanco;
    }

    /* Repopula o Bean a partir do ID passado por parâmetro da View e coloca no FLASH para uso em outra view */
    public void setBeanTransito(Long id) {
        try {
            bancoBusinessLogic.beginTrasaction();
            beanBanco = bancoBusinessLogic.find(banco, id);
            bancoBusinessLogic.commitTrasaction();

            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("banco", beanBanco);
        } catch (BusinessLogicException ex) {
            Logger.getLogger(BancoCrudMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /* Metodos relacionados a listar */
    public void checkBeforeList() {

    }

    public void processList() {
        try {
            if (listBancos.isEmpty()) {
                bancoBusinessLogic.beginTrasaction();
                listBancos = findAll();
                bancoBusinessLogic.commitTrasaction();
            }

        } catch (ViewException ex) {
            Logger.getLogger(BancoCrudMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Banco> list() {
        processList();
        return listBancos;
    }

    /* Metodos relacionados a inserir */
    public void checkBeforeInsert() {

    }

    public void processInsert() throws ViewException {
        try {
            bancoBusinessLogic.beginTrasaction();
            bancoBusinessLogic.insert(banco);
            bancoBusinessLogic.commitTrasaction();
            addNotificationMessage("Operação realizada com sucesso");
            redirect("/pages/crud/bancoList.xhtml");
        } catch (BusinessLogicException ex) {
            Logger.getLogger(BancoCrudMBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /* Metodos relacionados a atualizar */
    public void onStartUpdate(Long id) {
        setBeanTransito(id);
        redirect("/pages/crud/bancoEdit.xhtml");
    }

    public void checkBeforeUpdate() {
        System.out.println("************************ ANTES DO UPDATE *******************************************");
        System.out.println("Bean: " + banco.toString());
        System.out.println("BeanBanco: " + beanBanco.toString());
        System.out.println("Metodo getBean(): " + getBean() );
        System.out.println("Metodo getBeanTransito(): " + getBeanTransito());
        System.out.println("*******************************************************************");
    }

    public void processUpdate() throws ViewException {
        try {
            checkBeforeUpdate();
            bancoBusinessLogic.beginTrasaction();
            bancoBusinessLogic.update(getBean());
            bancoBusinessLogic.commitTrasaction();
            addNotificationMessage("Operação realizada com sucesso");
            redirect("/pages/crud/bancoList.xhtml");
        } catch (BusinessLogicException ex) {
            Logger.getLogger(BancoCrudMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /* Metodos relacionados a exclusao */
    public void checkBeforeDelete() {
        System.out.println("************************ ANTES DO delete *******************************************");
        System.out.println("Bean: " + banco.toString());
        System.out.println("BeanBanco: " + beanBanco.toString());
        System.out.println("Metodo getBean(): " + getBean() );
        System.out.println("Metodo getBeanTransito(): " + getBeanTransito());
        System.out.println("*******************************************************************");
    }

    public void processDelete(Long id) throws ViewException {
        try {
            bancoBusinessLogic.beginTrasaction();
            banco = bancoBusinessLogic.find(banco, id);
            bancoBusinessLogic.delete(banco);
            bancoBusinessLogic.commitTrasaction();
            addNotificationMessage("Operação realizada com sucesso");
            redirect("/pages/crud/bancoList.xhtml");
        } catch (BusinessLogicException ex) {
            Logger.getLogger(BancoCrudMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
