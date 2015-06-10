/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import arq.business.GenericBusinessLogic;
import dao.AgenciaDao;
import model.Agencia;

/**
 *
 * @author salesfilho
 */
public class AgenciaBusinessLogic extends GenericBusinessLogic<Agencia> {

    private final AgenciaDao dao;

    public AgenciaBusinessLogic() {
        dao = new AgenciaDao();
    }
    
}
