/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Calendar;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;

/**
 *
 * @author salesfilho
 */
@Entity
public class ContaInvestimento extends Conta {

    private Double aporteMinimo;
    private Double indiceRendimento;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar dataBase;

    public Double getAporteMinimo() {
        return aporteMinimo;
    }

    public void setAporteMinimo(Double aporteMinimo) {
        this.aporteMinimo = aporteMinimo;
    }

    public Double getIndiceRendimento() {
        return indiceRendimento;
    }

    public void setIndiceRendimento(Double indiceRendimento) {
        this.indiceRendimento = indiceRendimento;
    }

    public Calendar getDataBase() {
        return dataBase;
    }

    public void setDataBase(Calendar dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.aporteMinimo);
        hash = 89 * hash + Objects.hashCode(this.indiceRendimento);
        hash = 89 * hash + Objects.hashCode(this.dataBase);
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
        final ContaInvestimento other = (ContaInvestimento) obj;
        if (!Objects.equals(this.aporteMinimo, other.aporteMinimo)) {
            return false;
        }
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
        return super.toString() + " LIMITE:" + getAporteMinimo() + " DATA BASE: " + getDataBase() + " RENDIMENTO: " + getIndiceRendimento();
    }

}
