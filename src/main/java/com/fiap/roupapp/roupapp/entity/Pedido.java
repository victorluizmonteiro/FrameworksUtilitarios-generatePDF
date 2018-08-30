package com.fiap.roupapp.roupapp.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RedisHash("pedidos")
public class Pedido implements  Serializable{


    private static final long serialVersionUID = -2135572526423631361L;
    @Id
    @Indexed
    private String id;

    @Reference
    private List<Item> itens;

    @Reference
    private Cliente cliente;

    private LocalDateTime localDateTime;

    public Pedido(List<Item> itens, Cliente cliente, LocalDateTime localDateTime) {
        this.itens = new ArrayList<Item>();
        this.cliente = cliente;
        this.localDateTime = localDateTime;
    }

    public Pedido() {
        itens = new ArrayList<Item>();
    }

    public void addItem(Item item){
        item.setPedido(this);
        itens.add(item);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
