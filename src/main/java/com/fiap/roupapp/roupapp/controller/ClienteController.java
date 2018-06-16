package com.fiap.roupapp.roupapp.controller;

import com.fiap.roupapp.roupapp.entity.Cliente;
import com.fiap.roupapp.roupapp.entity.Item;
import com.fiap.roupapp.roupapp.entity.Pedido;
import com.fiap.roupapp.roupapp.entity.Produto;
import com.fiap.roupapp.roupapp.jms.JmsProducer;
import com.fiap.roupapp.roupapp.repository.ClienteRepository;
import com.fiap.roupapp.roupapp.repository.ItemRepository;
import com.fiap.roupapp.roupapp.repository.PedidoRepository;
import com.fiap.roupapp.roupapp.repository.ProdutoRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private ClienteRepository clienteRepository;
    private ItemRepository itemRepository;
    private PedidoRepository pedidoRepository;
    private ProdutoRepository produtoRepository;
    private JmsProducer jmsProducer;

    public ClienteController(ClienteRepository clienteRepository, ItemRepository itemRepository, PedidoRepository pedidoRepository, ProdutoRepository produtoRepository, JmsProducer jmsProducer) {
        this.clienteRepository = clienteRepository;
        this.itemRepository = itemRepository;
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
        this.jmsProducer = jmsProducer;
    }

    @Autowired


    @PostMapping
    public ResponseEntity carregarBase(){

        Cliente cliente = new Cliente();
        cliente.setCnpj("3443");
        cliente.setCpf("344334");
        cliente.setNome("Victor");


        Produto produto = new Produto();
        produto.setDescricao("dffffg");
        produto.setPreco(BigDecimal.TEN);


        Item item = new Item();
        item.setQuantidade(10);
        item.setValor(BigDecimal.TEN);

        item.setProduto(produto);
        produto.setItem(item);

        Pedido pedido = new Pedido();
        pedido.addItem(item);

        cliente.addPedido(pedido);
        itemRepository.save(item);

        clienteRepository.save(cliente);


        return ResponseEntity.ok().body("");


    }

    @PostMapping("/{id}")
    public ResponseEntity gerarPdf(@PathVariable Integer id){

        Cliente cliente= clienteRepository.findById(id).get();

        Document cupom = new Document();

        try{
            PdfWriter writer = PdfWriter.getInstance(cupom,new FileOutputStream("C:\\Users\\Zup\\Desktop\\cupom.pdf"));

            cupom.open();
            cupom.add(new Paragraph("Gerando PDF"));
            cupom.add(new Paragraph(cliente.getCpf()));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    cupom.close();

        return ResponseEntity.ok().body("");

    }

    @RequestMapping("/jms/{message}")
    public ResponseEntity testeMq(@PathVariable("message")String message){

           jmsProducer.processMessaging(message);


        return ResponseEntity.ok().body("");
    }
}
