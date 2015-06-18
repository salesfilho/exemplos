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
    }

    @PostConstruct
    public void init() {
        setBean(getBeanTransito());

    }

    /* Recupera do FLASH caso tenha sido passado por alguma view ou cria um novo */
    public Banco getBeanTransito() {
        beanBanco = (Banco) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("banco");
        if (beanBanco != null) {
            return beanBanco;
        }
        if (banco == null) {
            return new Banco();
        }
        return banco;
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
        bancoBusinessLogic.beginTrasaction();
        insert(banco);
        bancoBusinessLogic.commitTrasaction();
        addNotificationMessage("Operação realizada com sucesso");
        redirect("/pages/crud/bancoList.xhtml");

    }

    /* Metodos relacionados a atualizar */
    public void checkBeforeUpdate(Long id) {
        setBeanTransito(id);
        redirect("/pages/crud/bancoEdit.xhtml");
    }

    public void processUpdate() throws ViewException {
        bancoBusinessLogic.beginTrasaction();
        update(banco);
        bancoBusinessLogic.commitTrasaction();
        addNotificationMessage("Operação realizada com sucesso");
        redirect("/pages/crud/bancoList.xhtml");
    }

    /* Metodos relacionados a exclusao */
    public void checkBeforeDelete() {
        System.out.println("*******************************************************************");
        System.out.println("Bena: " + getBeanTransito());
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
