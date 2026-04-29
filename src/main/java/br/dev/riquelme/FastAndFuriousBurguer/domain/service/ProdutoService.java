/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.dev.riquelme.FastAndFuriousBurguer.domain.service;

import br.dev.riquelme.FastAndFuriousBurguer.domain.model.CategoriaProduto;
import br.dev.riquelme.FastAndFuriousBurguer.domain.model.Produto;
import br.dev.riquelme.FastAndFuriousBurguer.domain.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public Produto atualizar(Long id, Produto produtoNovo) {
        Produto produtoExistente = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        if (produtoNovo.getNome() != null) {
            produtoExistente.setNome(produtoNovo.getNome());
        }
        if (produtoNovo.getPreco() != null) { // agora funciona com Double
            produtoExistente.setPreco(produtoNovo.getPreco());
        }
        if (produtoNovo.getDescricao() != null) {
            produtoExistente.setDescricao(produtoNovo.getDescricao());
        }
        if (produtoNovo.getCategoriaProduto() != null) {
            produtoExistente.setCategoriaProduto(produtoNovo.getCategoriaProduto());
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
