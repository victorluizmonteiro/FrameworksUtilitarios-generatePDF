package com.fiap.roupapp.roupapp.service;

import com.fiap.roupapp.roupapp.entity.Pedido;
import com.fiap.roupapp.roupapp.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PedidoService {

    private PedidoRepository pedidoRepository;

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }


    @Cacheable(value = "pedidos",key = "#id",unless="result.id < 500")
    @Transactional(readOnly = true)
    public Pedido findPedidoById(String id){

        return  pedidoRepository.findById(id).get();
    }

    @Transactional(readOnly=true,propagation = Propagation.SUPPORTS)
    @Async("fileExecutor")
    public List<Pedido> findAll(){

        return (List<Pedido>) pedidoRepository.findAll();
    }

}
