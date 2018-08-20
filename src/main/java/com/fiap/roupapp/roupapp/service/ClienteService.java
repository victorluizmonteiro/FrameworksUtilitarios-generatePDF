package com.fiap.roupapp.roupapp.service;

import com.fiap.roupapp.roupapp.entity.Cliente;
import com.fiap.roupapp.roupapp.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    @Transactional
    public Cliente findById(Integer id){

        return clienteRepository.findById(id).get();
    }

    @Transactional
    @Cacheable("clientes")
    public List<Cliente> findAll(){

        return clienteRepository.findAll();
    }
}
