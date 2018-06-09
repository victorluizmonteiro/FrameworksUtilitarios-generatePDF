package com.fiap.roupapp.roupapp.repository;

import com.fiap.roupapp.roupapp.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Integer> {
}
