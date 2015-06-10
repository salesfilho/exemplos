/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import arq.business.GenericBusinessLogic;
import dao.BancoDao;
import model.Banco;

/**
 *
 * @author salesfilho
 */
public class BancoBusinessLogic extends GenericBusinessLogic<Banco> {

    private final BancoDao dao;

    public BancoBusinessLogic() {
        dao = new BancoDao();
    }
}
