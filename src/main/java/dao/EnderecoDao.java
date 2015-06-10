/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import arq.dao.GenericDao;
import com.mysema.query.jpa.impl.JPAQuery;
import java.util.List;
import model.Endereco;
import model.QEndereco;

/**
 *
 * @author salesfilho
 */
public class EnderecoDao extends GenericDao<Endereco> {

    QEndereco qEndereco = QEndereco.endereco;

    public EnderecoDao() {
        super();
    }

    public List<Endereco> findByLogradouro(String logradouro) {
        JPAQuery query = new JPAQuery(getInstance());
        List<Endereco> enderecos = query.from(qEndereco)
                .where(qEndereco.logradouro.containsIgnoreCase(logradouro)).list(qEndereco);
        return enderecos;
    }
}
