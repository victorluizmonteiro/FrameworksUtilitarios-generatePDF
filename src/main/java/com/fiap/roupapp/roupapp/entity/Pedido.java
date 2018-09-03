package com.fiap.roupapp.roupapp.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import javax.xml.crypto.Data;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RedisHash("pedidos")
public class Pedido implements  Serializable{


    private static final long serialVersionUID = -2135572526423631361L;
    @Id
    private String id;

    private String identificationPedido;

    @Reference
    private List<Item> itens;


    @Indexed
    private String identificationClient;

    private LocalDateTime localDateTime;

    public Pedido(String id, String identificationPedido, List<Item> itens, String identificationClient, LocalDateTime localDateTime) {
        this.id = id;
        this.identificationPedido = identificationPedido;
        this.itens = itens;
        this.identificationClient = identificationClient;
        this.localDateTime = localDateTime;
    }

    public Pedido() {
        itens = new ArrayList<Item>();
    }

   /* public void addItem(Item item){
        item.setPedido(this);
        itens.add(item);
    }*/

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



    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getIdentificationPedido() {
        return identificationPedido;
    }

    public void setIdentificationPedido(String identificationPedido) {
        this.identificationPedido = identificationPedido;
    }

    public String getIdentificationClient() {
        return identificationClient;
    }

    public void setIdentificationClient(String identificationClient) {
        this.identificationClient = identificationClient;
    }
}
