package com.farmacia.projetofarmacia.repository;

import com.farmacia.projetofarmacia.model.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutosRepository extends JpaRepository<Produtos,Long> {

    List<Produtos> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);


}
