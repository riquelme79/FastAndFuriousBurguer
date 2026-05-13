/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.dev.riquelme.FastAndFuriousBurguer.domain.service;

import br.dev.riquelme.FastAndFuriousBurguer.api.dto.ItensPedidoDTO;
import br.dev.riquelme.FastAndFuriousBurguer.api.dto.PedidoDTO;
import br.dev.riquelme.FastAndFuriousBurguer.domain.model.ItensPedido;
import br.dev.riquelme.FastAndFuriousBurguer.domain.model.Pedido;
import br.dev.riquelme.FastAndFuriousBurguer.domain.model.Produto;
import br.dev.riquelme.FastAndFuriousBurguer.domain.model.StatusPedido;
import br.dev.riquelme.FastAndFuriousBurguer.domain.repository.PedidoRepository;
import br.dev.riquelme.FastAndFuriousBurguer.domain.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public Pedido adicionar(PedidoDTO dto) {
        Pedido pedido = new Pedido();
        pedido.setCliente(dto.getCliente());
        pedido.setCpf(dto.getCpf());
        pedido.setStatusPedido(StatusPedido.ABERTO);
        pedido.setDtAberto(LocalDateTime.now());

        List<ItensPedido> itens = new ArrayList<>();

        for (ItensPedidoDTO itemDTO : dto.getItens()) {
            Optional<Produto> produtoOpt = produtoRepository.findById(itemDTO.getProdutoId());

            if (produtoOpt.isEmpty()) {
                throw new RuntimeException("Produto não encontrado com id: " + itemDTO.getProdutoId());
            }

            Produto produto = produtoOpt.get();

            ItensPedido item = new ItensPedido();
            item.setPedido(pedido);
            item.setProduto(produto);
            item.setQtd(itemDTO.getQtd());
            item.setValUnit(produto.getPreco()); // pega o preço atual do produto
            item.setObs(itemDTO.getObs());

            itens.add(item);
        }

        pedido.setItens(itens);
        
        double total = itens.stream()
                .mapToDouble(i -> i.getValUnit() * i.getQtd())
                .sum();
        pedido.setValorTotal(total);
        
        return pedidoRepository.save(pedido);
    }

    @Transactional
    public Pedido atualizar(Long id, PedidoDTO dto) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado com id: " + id));

        if (dto.getCliente() != null) {
            pedido.setCliente(dto.getCliente());
        }
        if (dto.getCpf() != null) {
            pedido.setCpf(dto.getCpf());
        }
        if (dto.getItens() != null) {
            pedido.getItens().clear();

            for (ItensPedidoDTO itemDTO : dto.getItens()) {
                Produto produto = produtoRepository.findById(itemDTO.getProdutoId())
                        .orElseThrow(() -> new RuntimeException("Produto não encontrado com id: " + itemDTO.getProdutoId()));

                ItensPedido item = new ItensPedido();
                item.setPedido(pedido);
                item.setProduto(produto);
                item.setQtd(itemDTO.getQtd());
                item.setValUnit(produto.getPreco());
                item.setObs(itemDTO.getObs());

                pedido.getItens().add(item);
            }
        }
        
        double total = pedido.getItens().stream()
                .mapToDouble(i -> i.getValUnit() * i.getQtd())
                .sum();
        pedido.setValorTotal(total);
        
        return pedidoRepository.save(pedido);
    }

    public Pedido atualizarStatus(Long id, StatusPedido novoStatus) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado com id: " + id));

        if (pedido.getStatusPedido() == StatusPedido.CANCELADO) {
            throw new RuntimeException("Pedido cancelado não pode ter status alterado");
        }
        if (pedido.getStatusPedido() == StatusPedido.ENTREGUE) {
            throw new RuntimeException("Pedido entregue não pode ter status alterado");
        }

        pedido.setStatusPedido(novoStatus);

        if (novoStatus == StatusPedido.PRONTO) {
            pedido.setDtPronto(LocalDateTime.now());
        } else if (novoStatus == StatusPedido.ENTREGUE) {
            pedido.setDtEntregue(LocalDateTime.now());
        }

        return pedidoRepository.save(pedido);
    }

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
