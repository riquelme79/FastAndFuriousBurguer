/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.dev.riquelme.FastAndFuriousBurguer.api.dto;

import br.dev.riquelme.FastAndFuriousBurguer.domain.model.StatusPedido;
import jakarta.validation.constraints.NotBlank;

/**
 *
 * @author sesi3dib
 */
public class PedidoDTO {

    @NotBlank(message = "Nome do cliente é obrigatório")
    private String cliente;

    @NotBlank(message = "CPF é obrigatório")
    private String cpf;

    private StatusPedido statusPedido;

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

    public StatusPedido getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(StatusPedido statusPedido) {
        this.statusPedido = statusPedido;
    }
}
