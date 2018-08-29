package com.fiap.roupapp.roupapp.repository;

import com.fiap.roupapp.roupapp.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ProdutoRepository extends JpaRepository<Produto,Integer> {
}
