/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import arq.exceptions.BusinessLogicException;
import business.AgenciaBusinessLogic;
import business.BancoBusinessLogic;
import business.ClienteBusinessLogic;
import business.ContaBusinessLogic;
import business.EnderecoBusinessLogic;
import java.util.ArrayList;
import java.util.List;
import model.Agencia;
import model.Banco;
import model.Cliente;
import model.Conta;
import model.ContaCorrente;
import model.ContaPoupanca;
import model.Endereco;

/**
 *
 * @author salesfilho
 */
public class TesteBanco {

    static BancoBusinessLogic bancoBusinessLogic;
    static AgenciaBusinessLogic agenciaBusinessLogic;
    static ClienteBusinessLogic clienteBusinessLogic;
    static ContaBusinessLogic contaBusinessLogic;
    static EnderecoBusinessLogic enderecoBusinessLogic;
    Banco banco;
    Agencia agencia;
    Cliente cliente;
    Conta conta;
    Endereco endereco;

    public TesteBanco() {
        bancoBusinessLogic = new BancoBusinessLogic();
        agenciaBusinessLogic = new AgenciaBusinessLogic();
        clienteBusinessLogic = new ClienteBusinessLogic();
        contaBusinessLogic = new ContaBusinessLogic();
        enderecoBusinessLogic = new EnderecoBusinessLogic();
    }

    public static void main(String args[]) throws Exception {
        TesteBanco teste = new TesteBanco();
        teste.novaConta();
        //teste.mostrarContas();
        //Conta c = new ContaCorrente();
        //c.setId(Long.parseLong("7"));
        //teste.depositar(contaBusinessLogic.find(1L), Double.parseDouble("100000"));
        //teste.sacar(contaBusinessLogic.find(1L), Double.parseDouble("200"));
        //teste.mostrarContas();
        //teste.findByConta(7L);
        //teste.novoEndereco();
        //teste.novoBanco();
        //teste.novaAgencia();
        //teste.novaCliente();

        System.exit(0);
    }

    public void novoEndereco() throws BusinessLogicException {
        endereco = new Endereco();
        endereco.setCidade("Tokio");
        endereco.setPais("Japão");
        //enderecoBusinessLogic.insert(endereco);
        List<Endereco> enderecos = enderecoBusinessLogic.findAll(endereco);
        System.out.print(endereco);

    }

    public void novoBanco() throws BusinessLogicException {
        banco = new Banco();
        banco.setRazaoSocial("Banco Sales S/A");
        banco.setNomeFantasia("Banco Sales");
        banco.setEndereco(enderecoBusinessLogic.find(new Endereco(), Long.parseLong("1")));
        bancoBusinessLogic.insert(banco);
    }

    public void novaConta() throws BusinessLogicException {
        conta = new ContaCorrente();
        conta.setNumero(10L);
        conta.setSaldo(Double.parseDouble("10000"));
        ((ContaCorrente) conta).setLimiteChequeEspecial(Double.parseDouble("200"));
        conta.setCliente(clienteBusinessLogic.find(new Cliente(), 1L));

        contaBusinessLogic.beginTrasaction();
        contaBusinessLogic.insert(conta);

        conta = new ContaPoupanca();
        conta.setNumero(8L);
        conta.setSaldo(Double.parseDouble("2000"));
        ((ContaPoupanca) conta).setIndiceRendimento(Double.parseDouble("0.03"));
        conta.setCliente(clienteBusinessLogic.find(new Cliente(), 6L));

        contaBusinessLogic.insert(conta);

        contaBusinessLogic.commitTrasaction();

    }

    public void sacar(Conta conta, Double valor) throws BusinessLogicException {
        System.out.println("Saldo:" + conta.getSaldo());
        contaBusinessLogic.sacar(conta, valor);
        conta = contaBusinessLogic.find(conta, conta.getId());
        System.out.println("Saldo atual:" + conta.getSaldo());
    }

    public void depositar(Conta conta, Double valor) throws BusinessLogicException {
        System.out.println("Saldo:" + conta.getSaldo());
        contaBusinessLogic.depositar(conta, valor);
        conta = contaBusinessLogic.find(conta, conta.getId());
        System.out.println("Saldo atual:" + conta.getSaldo());
    }

    public void findByConta(Long numero) throws BusinessLogicException {
        conta = contaBusinessLogic.findByNumero(numero);
        System.out.println("Conta: " + conta.toString());
    }

    public void mostrarContas() throws BusinessLogicException {
        List<Conta> contas = contaBusinessLogic.findAll(conta);
        for (Conta c : contas) {
            System.out.println("-----------------");
            System.out.println(c.toString());
        }
    }

    private void novaAgencia() throws BusinessLogicException {
        agencia = new Agencia();
        agencia.setNumero(1L);
        agencia.setBanco(bancoBusinessLogic.find(new Banco(), 1L));
        agencia.setEndereco(enderecoBusinessLogic.find(new Endereco(), 2L));
        agencia.setNomeFantasia("XPTO");
        agenciaBusinessLogic.insert(agencia);
    }

    private void novaCliente() throws BusinessLogicException {
        cliente = new Cliente();
        cliente.setAgencia(agenciaBusinessLogic.find(new Agencia(), 1L));
        cliente.setNome("Erika Cistina");
        List enderecos = enderecoBusinessLogic.findAll(new Endereco());
        //cliente.setEnderecos(enderecos);
        //cliente.set
        clienteBusinessLogic.beginTrasaction();
        clienteBusinessLogic.insert(cliente);
        clienteBusinessLogic.commitTrasaction();
        //clienteBusinessLogic.closeConnection();
    }
}
