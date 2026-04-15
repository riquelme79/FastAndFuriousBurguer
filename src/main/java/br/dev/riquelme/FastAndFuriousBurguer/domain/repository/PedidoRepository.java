/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.dev.riquelme.FastAndFuriousBurguer.domain.repository;

import br.dev.riquelme.FastAndFuriousBurguer.domain.model.Pedido;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author sesi3dib
 */
public interface PedidoRepository extends JpaRepository<Pedido, Long>{
    List<Pedido> findAll();
}
