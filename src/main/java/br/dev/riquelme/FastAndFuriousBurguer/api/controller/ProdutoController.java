/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.dev.riquelme.FastAndFuriousBurguer.api.controller;

import br.dev.riquelme.FastAndFuriousBurguer.api.dto.ProdutoDTO;
import br.dev.riquelme.FastAndFuriousBurguer.domain.model.CategoriaProduto;
import br.dev.riquelme.FastAndFuriousBurguer.domain.model.Produto;
import br.dev.riquelme.FastAndFuriousBurguer.domain.repository.ProdutoRepository;
import br.dev.riquelme.FastAndFuriousBurguer.domain.service.ProdutoService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fastfurious")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/produto")
    public List<Produto> listas() {
        return produtoRepository.findAll();
//        return produtoRepository.findByNome("X-bacon");
    }

    @GetMapping("/produto/{id}")
    public ResponseEntity<Produto> buscar(@PathVariable Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isPresent()) {
            return ResponseEntity.ok(produto.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/produto/cat/{categoriaProduto}")
    public ResponseEntity<List<Produto>> buscarCat(@PathVariable CategoriaProduto categoriaProduto) {
        List<Produto> produto = produtoRepository.findByCategoriaProduto(categoriaProduto);
        if (produto.isEmpty()) {
            return ResponseEntity.noContent().build(); // Retorna 204 se a lista estiver vazia
        } else {
            return ResponseEntity.ok(produto); // Retorna 200 com a lista de produtos
        }
    }

    @PostMapping("/produto")
    @ResponseStatus(HttpStatus.CREATED)
    public Produto adicionar(@Valid @RequestBody ProdutoDTO dto) {

        return produtoService.adicionar(dto);
    }

    @PutMapping("/produto/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @RequestBody ProdutoDTO dto) {
        Produto atualizado = produtoService.atualizar(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/produto/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        produtoService.excluir(id); 
        return ResponseEntity.noContent().build();
    }
}
