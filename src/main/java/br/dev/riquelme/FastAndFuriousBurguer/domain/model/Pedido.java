/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.dev.riquelme.FastAndFuriousBurguer.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cliente;
    private String cpf;

    @JsonFormat(pattern = ("dd/MM/yyyy HH:mm:ss"))
    private LocalDateTime dtAberto;
    @JsonFormat(pattern = ("dd/MM/yyyy HH:mm:ss"))
    private LocalDateTime dtPronto;
    @JsonFormat(pattern = ("dd/MM/yyyy HH:mm:ss"))
    private LocalDateTime dtEntregue;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_pedido")
    private StatusPedido statusPedido;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItensPedido> itens;
    
    private Double valorTotal;

    public Pedido() {
    }
   
    public Pedido(Long id, String cliente, String cpf, LocalDateTime dtAberto, LocalDateTime dtPronto, LocalDateTime dtEntregue, StatusPedido statusPedido, List<ItensPedido> itens, Double valorTotal) {
        this.id = id;
        this.cliente = cliente;
        this.cpf = cpf;
        this.dtAberto = dtAberto;
        this.dtPronto = dtPronto;
        this.dtEntregue = dtEntregue;
        this.statusPedido = statusPedido;
        this.itens = itens;
        this.valorTotal = valorTotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDateTime getDtAberto() {
        return dtAberto;
    }

    public void setDtAberto(LocalDateTime dtAberto) {
        this.dtAberto = dtAberto;
    }

    public LocalDateTime getDtPronto() {
        return dtPronto;
    }

    public void setDtPronto(LocalDateTime dtPronto) {
        this.dtPronto = dtPronto;
    }

    public LocalDateTime getDtEntregue() {
        return dtEntregue;
    }

    public void setDtEntregue(LocalDateTime dtEntregue) {
        this.dtEntregue = dtEntregue;
    }

    public StatusPedido getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(StatusPedido statusPedido) {
        this.statusPedido = statusPedido;
    }

    public List<ItensPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItensPedido> itens) {
        this.itens = itens;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
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
        final Pedido other = (Pedido) obj;
        return Objects.equals(this.id, other.id);
    }
}
