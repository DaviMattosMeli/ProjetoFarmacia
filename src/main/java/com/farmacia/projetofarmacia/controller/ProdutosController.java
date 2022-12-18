package com.farmacia.projetofarmacia.controller;


import com.farmacia.projetofarmacia.model.Produtos;
import com.farmacia.projetofarmacia.repository.CategoriaRepository;
import com.farmacia.projetofarmacia.repository.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutosController {

    @Autowired
    private ProdutosRepository produtosRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;


    @GetMapping
    public List<Produtos> getAll() {
        return produtosRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produtos> getById(@PathVariable Long id) {
        return produtosRepository.findById(id)
                .map(res -> ResponseEntity.ok(res))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Produtos>> getByNome(@PathVariable String nome) {
        return ResponseEntity.ok(produtosRepository.findAllByNomeContainingIgnoreCase(nome));
    }

    @PostMapping
    public ResponseEntity<Produtos> postProduto(@Valid @RequestBody Produtos produtos) {
        if (categoriaRepository.existsById(produtos.getCategoria().getId()))
            return ResponseEntity.status(HttpStatus.CREATED).body(produtosRepository.save(produtos));

        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<Produtos> putProduto(@Valid @RequestBody Produtos produtos) {
        if (produtosRepository.existsById(produtos.getId())) {//tem id do produtos, vai checar o categoria
            if (categoriaRepository.existsById(produtos.getCategoria().getId())) // tem o categoria ele grava embaixo
                return ResponseEntity.status(HttpStatus.OK).body(produtosRepository.save(produtos));

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        return produtosRepository.findById(id)
                .map(res -> {
                    produtosRepository.deleteById(id);
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

}

