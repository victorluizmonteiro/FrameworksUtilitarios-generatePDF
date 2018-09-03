package com.fiap.roupapp.roupapp.database;

import com.fiap.roupapp.roupapp.entity.Cliente;
import com.fiap.roupapp.roupapp.entity.Item;
import com.fiap.roupapp.roupapp.entity.Pedido;
import com.fiap.roupapp.roupapp.entity.Produto;
import com.fiap.roupapp.roupapp.repository.ClienteRepository;
import com.fiap.roupapp.roupapp.repository.ItemRepository;
import com.fiap.roupapp.roupapp.repository.PedidoRepository;
import com.fiap.roupapp.roupapp.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class CarregarDatabase {

    private ItemRepository itemRepository;
    private ClienteRepository clienteRepository;
    private PedidoRepository pedidoRepository;
    private ProdutoRepository produtoRepository;

    @Autowired
    public CarregarDatabase(ItemRepository itemRepository,
                            ClienteRepository clienteRepository,
                            PedidoRepository pedidoRepository,
                            ProdutoRepository produtoRepository) {
        this.itemRepository = itemRepository;
        this.clienteRepository = clienteRepository;
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
    }

    public void carregarBase() {
        Random random = new Random();
        for (int i = 1; i < 101; i++) {
            int number = random.nextInt(1000);

            String idConverter = String.valueOf(i);

            Cliente cliente = new Cliente();
            cliente.setId(idConverter);
            cliente.setCnpj("11.111.111/111-11");
            cliente.setCpf("222.333.444-88");
            cliente.setNome("Victor");
            cliente.setIdentificationClient(idConverter);


            Produto produto = new Produto();
            produto.setId(idConverter);
            produto.setDescricao("Descrição");
            produto.setPreco(BigDecimal.valueOf(number));


            Item item = new Item();
            item.setId(idConverter);
            item.setQuantidade(10);
            item.setValor(BigDecimal.valueOf(number));

            //Lista de Itens
            List<Item>itens = new ArrayList<>();
            itens.add(item);

            //Relacionando item ao produto
            produto.setItem(item);


            Pedido pedido = new Pedido();
            pedido.setId(idConverter);
            //pedido.addItem(item);
            pedido.setLocalDateTime(LocalDateTime.of(2018, Month.AUGUST,18,0,0));
            pedido.setIdentificationPedido(idConverter);
            pedido.setIdentificationClient(idConverter);



            //Relacionando lista de itens com o pedido
            pedido.setItens(itens);

            List<Pedido>pedidos = new ArrayList<>();
            pedidos.add(pedido);


            //Relacionado Cliente com os pedidos
            cliente.setPedidos(pedidos);

            itemRepository.save(item);
            pedidoRepository.save(pedido);
            clienteRepository.save(cliente);
            produtoRepository.save(produto);


        }
    }

}
