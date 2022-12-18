package com.farmacia.projetofarmacia.controller;

import com.farmacia.projetofarmacia.model.Categoria;
import com.farmacia.projetofarmacia.model.Produtos;
import com.farmacia.projetofarmacia.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public List<Categoria> getAll(){
        return categoriaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getById(@PathVariable Long id) {
        return categoriaRepository.findById(id)
                .map(res -> ResponseEntity.ok(res))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Categoria>> getByNome(@PathVariable String tipo) {
        return ResponseEntity.ok(categoriaRepository.findAllByTipoContainingIgnoreCase(tipo));
    }

    @PutMapping
    public ResponseEntity<Categoria> putPostagem(@Valid @RequestBody Categoria categoria) {
        if (categoria.getId() != null)
            return ResponseEntity.status(HttpStatus.OK)
                    .body(categoriaRepository.save(categoria));
        else
            return ResponseEntity.badRequest().build();
    }

    @PostMapping
    public ResponseEntity<Categoria> postCategoria(@Valid @RequestBody Categoria categoria){
        if(categoria.getId() == null)
            return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(categoria));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        return categoriaRepository.findById(id)
                .map(res -> {
                    categoriaRepository.deleteById(id);
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
