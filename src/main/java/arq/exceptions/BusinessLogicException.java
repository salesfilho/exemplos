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
public class BusinessLogicException extends Exception{

    public BusinessLogicException() {
    }

    public BusinessLogicException(String msg) {
        super(msg);
    }
    
}
