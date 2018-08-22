package com.fiap.roupapp.roupapp.repository;

import com.fiap.roupapp.roupapp.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Integer> {

    Optional<Pedido> findById(Integer id);


    List<Pedido> findAll();
}
