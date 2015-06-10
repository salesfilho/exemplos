/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import arq.exceptions.BusinessLogicException;
import model.Conta;

/**
 *
 * @author salesfilho
 */
public interface IContaBusinessLogic {
    public void transferir(Conta origem, Conta destino, Double valor)throws BusinessLogicException;
    public void depositar(Conta destino, Double valor) throws BusinessLogicException;
    public void sacar(Conta origem, Double valor) throws BusinessLogicException;
    public void aplicar(Conta destino, Double valor) throws BusinessLogicException;
    public Conta findByNumero(Long numero) throws BusinessLogicException;
}
