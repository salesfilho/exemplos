/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import arq.business.GenericBusinessLogic;
import dao.ClienteDao;
import model.Cliente;

/**
 *
 * @author salesfilho
 */
public class ClienteBusinessLogic extends GenericBusinessLogic<Cliente> {

    private final ClienteDao dao;

    public ClienteBusinessLogic() {
        dao = new ClienteDao();
    }
}
