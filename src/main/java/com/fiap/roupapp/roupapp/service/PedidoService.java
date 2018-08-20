package com.fiap.roupapp.roupapp.service;

import com.fiap.roupapp.roupapp.entity.Pedido;
import com.fiap.roupapp.roupapp.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class PedidoService {

    private PedidoRepository pedidoRepository;

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Transactional
    public Pedido findPedidoById(Integer id){

        return pedidoRepository.findById(id).get();
    }

    @Transactional
    @Cacheable("Pedidos")
    public List<Pedido>findAll(){

        return pedidoRepository.findAll();
    }
}
