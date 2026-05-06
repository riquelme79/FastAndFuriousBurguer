/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.dev.riquelme.FastAndFuriousBurguer.domain.service;

import br.dev.riquelme.FastAndFuriousBurguer.api.dto.PedidoDTO;
import br.dev.riquelme.FastAndFuriousBurguer.domain.model.Pedido;
import br.dev.riquelme.FastAndFuriousBurguer.domain.model.StatusPedido;
import br.dev.riquelme.FastAndFuriousBurguer.domain.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    // POST — cria pedido
    @Transactional
    public Pedido adicionar(PedidoDTO dto) {
        Pedido pedido = new Pedido();
        pedido.setCliente(dto.getCliente());
        pedido.setCpf(dto.getCpf());
        pedido.setStatusPedido(StatusPedido.ABERTO);
        pedido.setDtAberto(LocalDateTime.now());
        return pedidoRepository.save(pedido);
    }

    // PUT — atualiza dados do pedido
    @Transactional
    public Pedido atualizar(Long id, PedidoDTO dto) {
        Optional<Pedido> pedidoOpt = pedidoRepository.findById(id);

        if (pedidoOpt.isEmpty()) {
            throw new RuntimeException("Pedido não encontrado com id: " + id);
        }

        Pedido pedido = pedidoOpt.get();

        if (dto.getCliente() != null) {
            pedido.setCliente(dto.getCliente());
        }
        if (dto.getCpf() != null) {
            pedido.setCpf(dto.getCpf());
        }

        return pedidoRepository.save(pedido);
    }

    // PUT /status/{id} — avança o status do pedido
    @Transactional
    public Pedido atualizarStatus(Long id) {
        Optional<Pedido> pedidoOpt = pedidoRepository.findById(id);

        if (pedidoOpt.isEmpty()) {
            throw new RuntimeException("Pedido não encontrado com id: " + id);
        }

        Pedido pedido = pedidoOpt.get();

        if (pedido.getStatusPedido() == StatusPedido.ABERTO) {
            pedido.setStatusPedido(StatusPedido.PRONTO);
            pedido.setDtPronto(LocalDateTime.now());
        } else if (pedido.getStatusPedido() == StatusPedido.PRONTO) {
            pedido.setStatusPedido(StatusPedido.ENTREGUE);
            pedido.setDtEntregue(LocalDateTime.now());
        } else {
            throw new RuntimeException("Status não pode ser alterado");
        }

        return pedidoRepository.save(pedido);
    }

    // DELETE — cancela pedido
    @Transactional
    public void cancelar(Long id) {
        Optional<Pedido> pedidoOpt = pedidoRepository.findById(id);

        if (pedidoOpt.isEmpty()) {
            throw new RuntimeException("Pedido não encontrado com id: " + id);
        }

        Pedido pedido = pedidoOpt.get();

        if (pedido.getStatusPedido() == StatusPedido.CANCELADO) {
            throw new RuntimeException("Pedido já está cancelado");
        }
        if (pedido.getStatusPedido() == StatusPedido.ENTREGUE) {
            throw new RuntimeException("Pedido já entregue não pode ser cancelado");
        }

        pedido.setStatusPedido(StatusPedido.CANCELADO);
        pedidoRepository.save(pedido);
    }
}
