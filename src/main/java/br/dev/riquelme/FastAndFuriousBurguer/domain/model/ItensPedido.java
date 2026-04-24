/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.dev.riquelme.FastAndFuriousBurguer.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ItensPedido {
    
    @Id
    private Long id;
    private int qtd;
    private double valUnit;
    private String obs;

    public ItensPedido() {
    }

    public ItensPedido(Long id, int qtd, double valUnit, String obs) {
        this.id = id;
        this.qtd = qtd;
        this.valUnit = valUnit;
        this.obs = obs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public double getValUnit() {
        return valUnit;
    }

    public void setValUnit(double valUnit) {
        this.valUnit = valUnit;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ItensPedido other = (ItensPedido) obj;
        return this.id == other.id;
    }
    
    
}
