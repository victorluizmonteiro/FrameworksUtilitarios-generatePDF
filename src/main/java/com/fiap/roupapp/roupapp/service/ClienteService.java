package com.fiap.roupapp.roupapp.service;

import com.fiap.roupapp.roupapp.entity.Cliente;
import com.fiap.roupapp.roupapp.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    private JdbcTemplate jdbcTemplate;


    @Autowired
    public ClienteService(ClienteRepository clienteRepository, JdbcTemplate jdbcTemplate) {
        this.clienteRepository = clienteRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional(readOnly = true)
    public Cliente findById(Integer id){

        return clienteRepository.findById(id).get();
    }

    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    @Async("fileExecutor")
    @Cacheable(value = "clienteCache")
    public List<Cliente> findAll(){



        return clienteRepository.findAll();
    }
}
