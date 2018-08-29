package com.fiap.roupapp.roupapp.repository;

import com.fiap.roupapp.roupapp.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido,Integer> {


    @Query(value = "SELECT * FROM pedido WHERE ID = ?1",nativeQuery = true)
    Optional<Pedido> findById(Integer id);

    @Query(value = "SELECT ID FROM pedido",nativeQuery = true)
    List<Integer>findId();

    @Query(value = "SELECT * FROM pedido",nativeQuery = true)
    List<Pedido> findAll();
}
