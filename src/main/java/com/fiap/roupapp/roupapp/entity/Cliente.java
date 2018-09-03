package com.fiap.roupapp.roupapp.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



@RedisHash("clients")
public class Cliente implements Serializable {

    private static final long serialVersionUID = -4478427800340465546L;

    @Id
    private String id;

    @Indexed
    private  String identificationClient;
    private String cnpj;
    private String nome;
    private String cpf;


    @Reference
    private List<Pedido> pedidos;


    /*public void addPedido(Pedido pedido){
        pedido.setCliente(this);
        pedidos.add(pedido);
    }*/

    public Cliente() {
        pedidos = new ArrayList<Pedido>();
    }


    public Cliente(String id, String identificationClient, String cnpj, String nome, String cpf, List<Pedido> pedidos) {
        this.id = id;
        this.identificationClient = identificationClient;
        this.cnpj = cnpj;
        this.nome = nome;
        this.cpf = cpf;
        this.pedidos = pedidos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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


    public String getIdentificationClient() {
        return identificationClient;
    }

    public void setIdentificationClient(String identificationClient) {
        this.identificationClient = identificationClient;
    }
}
