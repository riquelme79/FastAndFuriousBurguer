/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.dev.riquelme.FastAndFuriousBurguer.api.dto;

import br.dev.riquelme.FastAndFuriousBurguer.domain.model.StatusPedido;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 *
 * @author sesi3dib
 */
public class PedidoDTO {

    @NotBlank(message = "Nome do cliente é obrigatório")
    private String cliente;

    @NotBlank(message = "CPF é obrigatório")
    private String cpf;

    @NotNull(message = "Itens são obrigatórios")
    private List<ItensPedidoDTO> itens;

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

    public List<ItensPedidoDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItensPedidoDTO> itens) {
        this.itens = itens;
    }
}
