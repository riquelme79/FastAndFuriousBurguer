/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.dev.riquelme.FastAndFuriousBurguer.api.controller;

import br.dev.riquelme.FastAndFuriousBurguer.domain.model.Pedido;
import br.dev.riquelme.FastAndFuriousBurguer.domain.model.Produto;
import br.dev.riquelme.FastAndFuriousBurguer.domain.repository.PedidoRepository;
import br.dev.riquelme.FastAndFuriousBurguer.domain.service.PedidoService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fastfurious")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;
    
    @GetMapping("/pedido")
    public List<Pedido> listas() {
        return pedidoRepository.findAll();
//        return pedidoRepository.findByCliente("Riquelme");
    }

    @GetMapping("/pedido/{id}")
    public ResponseEntity<Pedido> buscar(@PathVariable Long id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        if (pedido.isPresent()) {
            return ResponseEntity.ok(pedido.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
