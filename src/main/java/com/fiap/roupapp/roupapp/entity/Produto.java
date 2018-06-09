package com.fiap.roupapp.roupapp.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descricao;
    private BigDecimal preco;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Item item;


    public Produto(String descricao, BigDecimal preco, Item item) {
        this.descricao = descricao;
        this.preco = preco;
        this.item = item;
    }

    public Produto() {



    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

