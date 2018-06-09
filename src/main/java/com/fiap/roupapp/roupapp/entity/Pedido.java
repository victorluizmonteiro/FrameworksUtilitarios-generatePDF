package com.fiap.roupapp.roupapp.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "pedido")
    private List<Item> itens;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Cliente cliente;

    public Pedido(List<Item> itens, Cliente cliente) {
        this.itens = new ArrayList<Item>();
        this.cliente = cliente;
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
}
