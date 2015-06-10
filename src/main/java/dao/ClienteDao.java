/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import arq.dao.GenericDao;
import com.mysema.query.jpa.impl.JPAQuery;
import model.Cliente;
import model.QCliente;

/**
 *
 * @author salesfilho
 */
public class ClienteDao extends GenericDao<Cliente> {
    QCliente qCliente = QCliente.cliente;
    public ClienteDao() {
        super();
    }
    
    public Cliente findByCpf(String doc){
        JPAQuery query = new JPAQuery(getInstance());
        Cliente cliente = query.from(qCliente)
                      .where(qCliente.cpf.eq(doc)).uniqueResult(qCliente);
        return cliente;
    }
}
