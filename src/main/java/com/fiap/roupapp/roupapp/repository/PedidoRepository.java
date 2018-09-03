package com.fiap.roupapp.roupapp.repository;

import com.fiap.roupapp.roupapp.entity.Pedido;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends CrudRepository<Pedido,String> {


}
