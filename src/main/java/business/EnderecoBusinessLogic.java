/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import arq.business.GenericBusinessLogic;
import dao.EnderecoDao;
import model.Endereco;

/**
 *
 * @author salesfilho
 */
public class EnderecoBusinessLogic extends GenericBusinessLogic<Endereco> {

    private final EnderecoDao dao;

    public EnderecoBusinessLogic() {
        dao = new EnderecoDao();
    }
}
