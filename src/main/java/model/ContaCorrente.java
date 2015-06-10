/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Objects;
import javax.persistence.Entity;

/**
 *
 * @author salesfilho
 */
@Entity
public class ContaCorrente extends Conta {

    private Double limiteChequeEspecial;

    public Double getLimiteChequeEspecial() {
        return limiteChequeEspecial;
    }

    public void setLimiteChequeEspecial(Double limiteChequeEspecial) {
        this.limiteChequeEspecial = limiteChequeEspecial;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.limiteChequeEspecial);
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
        final ContaCorrente other = (ContaCorrente) obj;
        if (!Objects.equals(this.limiteChequeEspecial, other.limiteChequeEspecial)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + " LIMITE:" + getLimiteChequeEspecial();
    }

}
