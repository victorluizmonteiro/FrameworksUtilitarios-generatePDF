package com.fiap.roupapp.roupapp.repository;

import com.fiap.roupapp.roupapp.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Integer> {


}
