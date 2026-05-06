/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.dev.riquelme.FastAndFuriousBurguer.domain.service;

import br.dev.riquelme.FastAndFuriousBurguer.api.dto.ProdutoDTO;
import br.dev.riquelme.FastAndFuriousBurguer.domain.model.Produto;
import br.dev.riquelme.FastAndFuriousBurguer.domain.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public Produto adicionar(ProdutoDTO dto) {
        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setPreco(dto.getPreco());
        produto.setDescricao(dto.getDescricao());
        produto.setCategoriaProduto(dto.getCategoriaProduto());
        return produtoRepository.save(produto);
    }

    @Transactional
    public Produto atualizar(Long id, ProdutoDTO dto) {
        Produto produtoExistente = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        if (dto.getNome() != null) {
            produtoExistente.setNome(dto.getNome());
        }
        if (dto.getPreco() != null) {
            produtoExistente.setPreco(dto.getPreco());
        }
        if (dto.getDescricao() != null) {
            produtoExistente.setDescricao(dto.getDescricao());
        }
        if (dto.getCategoriaProduto() != null) {
            produtoExistente.setCategoriaProduto(dto.getCategoriaProduto());
        }

        return produtoRepository.save(produtoExistente);
    }

    public void excluir(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new RuntimeException("Produto não encontrado para exclusão");
        }
        produtoRepository.deleteById(id);
    }
}
