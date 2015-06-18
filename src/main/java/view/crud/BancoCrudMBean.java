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
import java.io.IOException;
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

    private BancoBusinessLogic bancoBusinessLogic;
    private Banco banco;
    private transient Banco beanBanco;
    private Endereco endereco;
    private List<Banco> listBancos;

    public BancoCrudMBean() {
        bancoBusinessLogic = new BancoBusinessLogic();
        banco = new Banco();
        endereco = new Endereco();
        banco.setEndereco(endereco);
    }

    @PostConstruct
    public void init() {
        setBean(getBeanTransito());

    }
    public Banco getBeanTransito(){
        beanBanco = (Banco) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("banco");
        if (beanBanco != null ){
            return beanBanco;
        }
        if (banco == null){
            return new Banco();
        }
        return banco;
    }

    public List<Banco> getBancos() {
        processList();
        return listBancos;
    }

    public void cadastrarOuAlterar() throws ViewException {
        System.out.println("****************** Passou aqui... *************************** ");
        System.out.println("Bean: " + getBean().toString());
        System.out.println("************************************************************* ");

        bancoBusinessLogic.beginTrasaction();
        if (banco.getId() != null) {
            update(banco);
        } else {
            insert(banco);
        }
        bancoBusinessLogic.commitTrasaction();
        addNotificationMessage("Operação realizada com sucesso");
    }

    public void processInsertOrUpdate(Long id) {
        try {
            //Processado update
            if (id != null) {
                banco = new Banco();
                bancoBusinessLogic.beginTrasaction();
                banco = bancoBusinessLogic.find(banco, id);
                bancoBusinessLogic.commitTrasaction();
                setBean(banco);

                //Coloca o objeto no flash para intercambio entre as visoes
                FacesContext.getCurrentInstance().getExternalContext().getFlash().put("banco", banco);

                redirect("/pages/crud/bancoCadastrarAlterar.xhtml");
            } else {
                banco = new Banco();
                endereco = new Endereco();
                banco.setEndereco(endereco);
                setBean(banco);
                redirect("/pages/crud/bancoCadastrarAlterar.xhtml");
            }

        } catch (BusinessLogicException ex) {
            Logger.getLogger(BancoCrudMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void checkBeforeInsert() {

    }

    public void checkBeforeUpdate() {

    }

    public void checkBeforeList() {

    }

    public void processInsert() {

    }

    public void processUpdate() {

    }

    public void processList() {
        try {
            bancoBusinessLogic.beginTrasaction();
            listBancos = findAll();
            bancoBusinessLogic.commitTrasaction();
        } catch (ViewException ex) {
            Logger.getLogger(BancoCrudMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
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
