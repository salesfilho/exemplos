/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arq.exceptions;

/**
 *
 * @author salesfilho
 */
public class PersistenciaExcepion extends Exception{

    public PersistenciaExcepion() {
    }

    public PersistenciaExcepion(String msg) {
        super(msg);
    }
    
}
