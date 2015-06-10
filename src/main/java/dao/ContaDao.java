/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import arq.dao.GenericDao;
import com.mysema.query.jpa.impl.JPAQuery;
import model.Conta;
import model.QConta;

/**
 *
 * @author salesfilho
 */
public class ContaDao extends GenericDao<Conta> {
    QConta qConta = QConta.conta;
    public ContaDao() {
        super();
    }
    
    public Conta findByNumero(Long numero){
        JPAQuery query = new JPAQuery(getInstance());
        Conta conta = query.from(qConta)
                      .where(qConta.numero.eq(numero)).uniqueResult(qConta);
        return conta;
    }
}
