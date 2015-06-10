/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Calendar;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Temporal;

/**
 *
 * @author salesfilho
 */
@Entity
public class ContaPoupanca extends Conta {

    private Double indiceRendimento;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar dataBase;

    public Double getIndiceRendimento() {
        return indiceRendimento;
    }

    public void setIndiceRendimento(Double indiceRendimento) {
        this.indiceRendimento = indiceRendimento;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.indiceRendimento);
        hash = 79 * hash + Objects.hashCode(this.dataBase);
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
        final ContaPoupanca other = (ContaPoupanca) obj;
        if (!Objects.equals(this.indiceRendimento, other.indiceRendimento)) {
            return false;
        }
        if (!Objects.equals(this.dataBase, other.dataBase)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + " RENDIMENTO: " + getIndiceRendimento();
    }

}
