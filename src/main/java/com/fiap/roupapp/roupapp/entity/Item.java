package com.fiap.roupapp.roupapp.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.redis.core.RedisHash;

import java.math.BigDecimal;

@RedisHash("itens")
public class Item {

    @Id
    private String id;
    private Integer quantidade;
    private BigDecimal valor;

    @Reference
    private Pedido pedido;

    @Reference
    private Produto produto;


    public Item() {
    }

    public Item(String id, Integer quantidade, BigDecimal valor, Pedido pedido, Produto produto) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
        this.pedido = pedido;
        this.produto = produto;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
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
