package com.fiap.roupapp.roupapp.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
@Entity
@Table(name = "ITEM")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer quantidade;
    private BigDecimal valor;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Pedido pedido;

    @OneToOne(cascade =CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "item")
    private Produto produto;


    public Item() {
    }

    public Item(Integer id, Integer quantidade, BigDecimal valor, Pedido pedido, Produto produto) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
        this.pedido = pedido;
        this.produto = produto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
