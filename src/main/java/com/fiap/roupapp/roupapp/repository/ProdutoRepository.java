package com.fiap.roupapp.roupapp.repository;

import com.fiap.roupapp.roupapp.entity.Produto;
import org.springframework.data.repository.CrudRepository;

public interface ProdutoRepository extends CrudRepository<Produto,String> {
}
