package com.fiap.roupapp.roupapp.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "PEDIDO")
public class Pedido implements  Serializable{


    private static final long serialVersionUID = -2135572526423631361L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "pedido")
    private List<Item> itens;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
