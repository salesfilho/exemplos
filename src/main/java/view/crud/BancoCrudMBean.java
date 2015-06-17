/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.crud;

import arq.exceptions.ViewException;
import arq.view.GenericCrudMBean;
import business.BancoBusinessLogic;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.Banco;

/**
 *
 * @author salesfilho
 */
@ManagedBean
@ViewScoped
public class BancoCrudMBean extends GenericCrudMBean<Banco> {

    BancoBusinessLogic bancoBusinessLogic;
    Banco banco;
    List<Banco> listBancos;

    public BancoCrudMBean() {
        bancoBusinessLogic = new BancoBusinessLogic();
    }

    @PostConstruct
    public void init() {
        banco = new Banco();
        setBean(banco);
        try {
            listBancos = findAll();
        } catch (ViewException ex) {
            Logger.getLogger(BancoCrudMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Banco> getBancos() {
        return listBancos;
    }

}
