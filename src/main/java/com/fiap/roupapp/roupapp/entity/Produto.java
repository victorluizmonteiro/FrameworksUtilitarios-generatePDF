package com.fiap.roupapp.roupapp.entity;

import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.math.BigDecimal;


@RedisHash("produtos")
public class Produto {
    @Id
    private String id;
    private String descricao;
    private BigDecimal preco;
    @Reference
    private Item item;


    public Produto(String descricao, BigDecimal preco, Item item) {
        this.descricao = descricao;
        this.preco = preco;
        this.item = item;
    }

    public Produto() {



    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}

