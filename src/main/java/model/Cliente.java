/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author salesfilho
 */
@Entity
@SequenceGenerator(name = "ID_CLIENTE_SEQUENCE", sequenceName = "SQ_CLIENTE", allocationSize = 1)
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(generator = "ID_CLIENTE_SEQUENCE", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    //@JoinColumn(name = "id", referencedColumnName = "id")
    private Agencia agencia;

    @OneToMany(mappedBy = "cliente")
    private List<Conta> contas;

    @OneToMany
    private List<Endereco> enderecos;

    private String nome;
    private String rg;
    private String cpf;
    private String telefoneComercial;
    private String telefoneResidencial;
    private String telefoneCelular;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefoneComercial() {
        return telefoneComercial;
    }

    public void setTelefoneComercial(String telefoneComercial) {
        this.telefoneComercial = telefoneComercial;
    }

    public String getTelefoneResidencial() {
        return telefoneResidencial;
    }

    public void setTelefoneResidencial(String telefoneResidencial) {
        this.telefoneResidencial = telefoneResidencial;
    }

    public String getTelefoneCelular() {
        return telefoneCelular;
    }

    public void setTelefoneCelular(String telefoneCelular) {
        this.telefoneCelular = telefoneCelular;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.agencia);
        hash = 53 * hash + Objects.hashCode(this.contas);
        hash = 53 * hash + Objects.hashCode(this.nome);
        hash = 53 * hash + Objects.hashCode(this.enderecos);
        hash = 53 * hash + Objects.hashCode(this.rg);
        hash = 53 * hash + Objects.hashCode(this.cpf);
        hash = 53 * hash + Objects.hashCode(this.telefoneComercial);
        hash = 53 * hash + Objects.hashCode(this.telefoneResidencial);
        hash = 53 * hash + Objects.hashCode(this.telefoneCelular);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.agencia, other.agencia)) {
            return false;
        }
        if (!Objects.equals(this.contas, other.contas)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.enderecos, other.enderecos)) {
            return false;
        }
        if (!Objects.equals(this.rg, other.rg)) {
            return false;
        }
        if (!Objects.equals(this.cpf, other.cpf)) {
            return false;
        }
        if (!Objects.equals(this.telefoneComercial, other.telefoneComercial)) {
            return false;
        }
        if (!Objects.equals(this.telefoneResidencial, other.telefoneResidencial)) {
            return false;
        }
        if (!Objects.equals(this.telefoneCelular, other.telefoneCelular)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", agencia=" + agencia + ", contas=" + contas + ", enderecos=" + enderecos + ", nome=" + nome + ", rg=" + rg + ", cpf=" + cpf + ", telefoneComercial=" + telefoneComercial + ", telefoneResidencial=" + telefoneResidencial + ", telefoneCelular=" + telefoneCelular + '}';
    }

}
