/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.crud;

import arq.view.GenericCrudMBean;
import business.BancoBusinessLogic;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
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
    private Endereco endereco;

    public BancoCrudMBean() {
        bancoBusinessLogic = new BancoBusinessLogic();
        endereco = new Endereco();
        banco = new Banco();
        banco.setEndereco(endereco);
        setBean(banco);
    }

    @Override
    public void processDelete(Long id) {
        try {
            super.processDelete(id);
            redirect("/pages/crud/bancoList.xhtml");
        } catch (Exception ex) {
            Logger.getLogger(BancoCrudMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void onStartUpdate(Long id) {
        super.onStartUpdate(id);
        redirect("/pages/crud/bancoEdit.xhtml");
    }
}
