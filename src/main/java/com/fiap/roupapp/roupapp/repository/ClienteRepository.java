package com.fiap.roupapp.roupapp.repository;

import com.fiap.roupapp.roupapp.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Integer> {


    Optional<Cliente> findById(Integer id);


    List<Cliente> findAll();
}
