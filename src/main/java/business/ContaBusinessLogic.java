/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import arq.business.GenericBusinessLogic;
import arq.exceptions.BusinessLogicException;
import dao.ContaDao;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.PersistenceException;
import model.Conta;
import model.ContaCorrente;
import model.ContaInvestimento;
import model.ContaPoupanca;

/**
 *
 * @author salesfilho
 */
public class ContaBusinessLogic extends GenericBusinessLogic<Conta> implements IContaBusinessLogic {

    private final ContaDao dao;

    public ContaBusinessLogic() {
        dao = new ContaDao();
    }
    
    @Override
    public void transferir(Conta origem, Conta destino, Double valor) throws BusinessLogicException {
        try {
            dao.beginTransaction();
            origem = (Conta) dao.findOne(origem, origem.getId());
            destino = (Conta) dao.findOne(destino, destino.getId());

            if (origem instanceof ContaPoupanca && origem.getSaldo() < valor) {
                throw new BusinessLogicException("Saldo insuficiente para realizar operação na conta poupança.");
            }
            if (origem instanceof ContaInvestimento && origem.getSaldo() < valor) {
                throw new BusinessLogicException("Saldo insuficiente para realizar operação na conta investimento.");
            }
            if (origem instanceof ContaCorrente && (origem.getSaldo() + ((ContaCorrente) origem).getLimiteChequeEspecial()) < valor) {
                throw new BusinessLogicException("Limite insuficiente para realizar operação na conta corrente.");
            }
            //Realiza o débito depois o crédito
            origem.setSaldo(origem.getSaldo() - valor);
            destino.setSaldo(destino.getSaldo() + valor);

            dao.update(origem);
            dao.update(destino);

            dao.commitTransaction();

        } catch (BusinessLogicException ex) {
            dao.rollbackTransaction();
            Logger.getLogger(ContaBusinessLogic.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenceException("Falha ao realizar operação de transferência.");
        }
    }

    @Override
    public void depositar(Conta destino, Double valor) throws BusinessLogicException {
        try {
            dao.beginTransaction();
            destino = (Conta) dao.findOne(destino, destino.getId());
            destino.setSaldo(destino.getSaldo() + valor);
            dao.update(destino);
            dao.commitTransaction();

        } catch (Exception ex) {
            dao.rollbackTransaction();
            Logger.getLogger(ContaBusinessLogic.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenceException("Falha ao realizar operação de depósito.");
        }
    }

    @Override
    public void sacar(Conta origem, Double valor) throws BusinessLogicException {
        try {
            dao.beginTransaction();
            origem = dao.findOne(origem, origem.getId());

            if (origem instanceof ContaPoupanca && origem.getSaldo() < valor) {
                throw new BusinessLogicException("Saldo insuficiente para realizar operação na conta poupança.");
            }
            if (origem instanceof ContaInvestimento && origem.getSaldo() < valor) {
                throw new BusinessLogicException("Saldo insuficiente para realizar operação na conta investimento.");
            }
            if (origem instanceof ContaCorrente && (origem.getSaldo() + ((ContaCorrente) origem).getLimiteChequeEspecial()) < valor) {
                throw new BusinessLogicException("Limite insuficiente para realizar operação na conta corrente.");
            }

            origem.setSaldo(origem.getSaldo() - valor);
            dao.update(origem);
            dao.commitTransaction();

        } catch (Exception ex) {
            dao.rollbackTransaction();
            //Logger.getLogger(ContaBusinessLogic.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenceException("Falha ao realizar operação de saque.");
        }
    }

    @Override
    public void aplicar(Conta destino, Double valor) throws BusinessLogicException {
        try {
            dao.beginTransaction();
            destino = (Conta) dao.findOne(destino, destino.getId());

            if (!(destino instanceof ContaInvestimento)) {
                throw new BusinessLogicException("Operação não permitida para o tipo de conta selecionada.");
            }
            destino.setSaldo(destino.getSaldo() + valor);
            dao.update(destino);
            dao.commitTransaction();

        } catch (Exception ex) {
            dao.rollbackTransaction();
            //Logger.getLogger(ContaBusinessLogic.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenceException("Falha ao realizar operação de aplicação.");
        }
    }

    @Override
    public Conta findByNumero(Long numero) throws BusinessLogicException {
        return dao.findByNumero(numero);
    }

}
