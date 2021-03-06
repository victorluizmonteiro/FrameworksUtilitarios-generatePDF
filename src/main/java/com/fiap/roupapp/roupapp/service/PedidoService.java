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



    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    @Cacheable(value = "pedidoCache")
    public Pedido findPedidoById(Integer id){

        return  pedidoRepository.findById(id).get();
    }

    @Transactional(readOnly=true,propagation = Propagation.SUPPORTS)

    public List<Pedido>findAll(){

        return pedidoRepository.findAll();
    }

    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    @Cacheable(value = "pedidoIdCache")
    public List<Integer>findId(){

        return pedidoRepository.findId();
    }
}
