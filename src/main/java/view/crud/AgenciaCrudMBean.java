/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.crud;

import arq.view.GenericCrudMBean;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.Agencia;
import model.Banco;
import model.Cliente;
import model.Endereco;

/**
 *
 * @author salesfilho
 */
@ManagedBean
@ViewScoped
public class AgenciaCrudMBean extends GenericCrudMBean<Agencia> {

    private Agencia agencia;
    private Banco banco;
    private List<Cliente> clientes;
    private Endereco endereco;

    public AgenciaCrudMBean() {
        agencia = new Agencia();
        clientes = new ArrayList();
        banco = new Banco();
        endereco = new Endereco();
        agencia.setEndereco(endereco);
        agencia.setClientes(clientes);
        agencia.setBanco(banco);
        setBean(agencia);
    }

    @Override
    public void processDelete(Long id) {
        super.processDelete(id);
        redirect("/pages/crud/agenciaList.xhtml");
    }

    @Override
    public void onStartUpdate(Long id) {
        super.onStartUpdate(id);
        redirect("/pages/crud/agenciaEdit.xhtml");
    }
}
