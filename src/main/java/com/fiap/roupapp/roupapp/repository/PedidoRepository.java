package com.fiap.roupapp.roupapp.repository;

import com.fiap.roupapp.roupapp.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Integer> {
    @Query(value = "select * from pedido WHERE id = ?1",nativeQuery = true)
    Optional<Pedido> findById(Integer id);

    @Query(value = "select * from pedido",nativeQuery = true)
    List<Pedido> findAll();
}
