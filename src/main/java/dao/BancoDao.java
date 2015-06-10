/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import arq.dao.GenericDao;
import com.mysema.query.jpa.impl.JPAQuery;
import model.Banco;
import model.QBanco;

/**
 *
 * @author salesfilho
 */
public class BancoDao extends GenericDao<Banco> {
    QBanco qBanco = QBanco.banco;
    public BancoDao() {
        super();
    }
    
    public Banco findByNumero(Long numero){
        JPAQuery query = new JPAQuery(getInstance());
        Banco banco = query.from(qBanco)
                      .where(qBanco.numero.eq(numero)).uniqueResult(qBanco);
        return banco;
    }
}
