package com.fiap.roupapp.roupapp.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Cliente implements Serializable {

    private static final long serialVersionUID = -4478427800340465546L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String cnpj;
    private String nome;
    private String cpf;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "cliente")
    private List<Pedido> pedidos;


    public void addPedido(Pedido pedido){
        pedido.setCliente(this);
        pedidos.add(pedido);
    }

    public Cliente() {
        pedidos = new ArrayList<Pedido>();
    }


    public Cliente(String cnpj, String nome, String cpf, List<Pedido> pedidos) {
        this.cnpj = cnpj;
        this.nome = nome;
        this.cpf = cpf;
        this.pedidos = new ArrayList<Pedido>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}
