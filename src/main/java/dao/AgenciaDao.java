/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import arq.dao.GenericDao;
import com.mysema.query.jpa.impl.JPAQuery;
import model.Agencia;
import model.QAgencia;

/**
 *
 * @author salesfilho
 */
public class AgenciaDao extends GenericDao<Agencia> {
    QAgencia qAgencia = QAgencia.agencia;
    public AgenciaDao() {
        super();
    }
    
    public Agencia findByNumero(Long numero){
        JPAQuery query = new JPAQuery(getInstance());
        Agencia agencia = query.from(qAgencia)
                      .where(qAgencia.numero.eq(numero)).uniqueResult(qAgencia);
        return agencia;
    }
}
