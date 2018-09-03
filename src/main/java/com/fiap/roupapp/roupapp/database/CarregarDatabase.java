package com.fiap.roupapp.roupapp.database;

import com.fiap.roupapp.roupapp.entity.Cliente;
import com.fiap.roupapp.roupapp.entity.Item;
import com.fiap.roupapp.roupapp.entity.Pedido;
import com.fiap.roupapp.roupapp.entity.Produto;
import com.fiap.roupapp.roupapp.repository.ClienteRepository;
import com.fiap.roupapp.roupapp.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Random;

@Component
public class CarregarDatabase {

    private ItemRepository itemRepository;
    private ClienteRepository clienteRepository;


    @Autowired
    public CarregarDatabase(ItemRepository itemRepository, ClienteRepository clienteRepository) {
        this.itemRepository = itemRepository;
        this.clienteRepository = clienteRepository;
    }

    public void carregarBase() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
           Integer number = random.nextInt(1000);

            Cliente cliente = new Cliente();
            cliente.setCnpj("11.111.111/111-11");
            cliente.setCpf("222.333.444-88");
            cliente.setNome("Victor");


            Produto produto = new Produto();
            produto.setDescricao("Descrição");
            produto.setPreco(BigDecimal.valueOf(number));


            Item item = new Item();
            item.setQuantidade(10);
            item.setValor(BigDecimal.valueOf(number));

            item.setProduto(produto);
            produto.setItem(item);

            Pedido pedido = new Pedido();
            pedido.addItem(item);
            pedido.setLocalDateTime(LocalDateTime.of(2018, Month.AUGUST,18,0,0));

            cliente.addPedido(pedido);
            itemRepository.save(item);

            clienteRepository.save(cliente);

        }
    }
}
