package com.fiap.roupapp.roupapp.repository;

import com.fiap.roupapp.roupapp.entity.Cliente;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface ClienteRepository extends CrudRepository<Cliente,String> {

    Cliente findByIdentificationClient(String identificationClient);

    Cliente findAllByIdentificationClient(String identificationClient);


}
