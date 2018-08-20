package com.fiap.roupapp.roupapp.controller;

import com.fiap.roupapp.roupapp.database.CarregarDatabase;
import com.fiap.roupapp.roupapp.entity.Pedido;
import com.fiap.roupapp.roupapp.jms.JmsProducerPDF;
import com.fiap.roupapp.roupapp.jms.JmsProducerPedidos;
import com.fiap.roupapp.roupapp.repository.PedidoRepository;
import com.fiap.roupapp.roupapp.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cliente")
public class ClienteController {


    private CarregarDatabase carregarDatabase;
    private JmsProducerPDF jmsProducerPDF;
    private JmsProducerPedidos jmsProducerPedidos;
    private PedidoService pedidoService;

    @Autowired
    public ClienteController(CarregarDatabase carregarDatabase,
                             JmsProducerPDF jmsProducerPDF,
                             JmsProducerPedidos jmsProducerPedidos,
                             PedidoService pedidoService) {
        this.carregarDatabase = carregarDatabase;
        this.jmsProducerPDF = jmsProducerPDF;
        this.jmsProducerPedidos = jmsProducerPedidos;
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity carregarBase() {

        carregarDatabase.carregarBase();

        return ResponseEntity.ok().body("Base de dados carregada com sucesso ");


    }/*

    @Async("fileExecutor")
    @Transactional
    public CompletableFuture<List<Pedido>> buscarPedidos(){

        return CompletableFuture.completedFuture(pedidoRepository.findAll());
    }*/



    @PostMapping("/gerarPedido/{idPedido}")
    public ResponseEntity testeMq(@PathVariable("idPedido") Integer pedidoId) {

       try{
           jmsProducerPedidos.processMessaging(pedidoId);

           return ResponseEntity.ok().body("Pedido(s) gerados com sucesso !");
       }catch (Exception e){

           return ResponseEntity.status(500).body("OPS... Algo de errado aconteceu,tente novamente");
       }


    }

    @PostMapping("/gerarPedido/mes/{mes}")
    public ResponseEntity generatePdfByMonth(@PathVariable("mes") Integer mes) {

    try{
    List<Pedido> pedidos = pedidoService.findAll();

    List<Pedido> pedidosFilter = pedidos.stream().filter(p -> p.getLocalDateTime().getMonth().equals(Month.of(mes))).collect(Collectors.toList());


        pedidosFilter.stream().forEach( p -> jmsProducerPDF.processMessaging(p.getId()));

        return ResponseEntity.ok().body("Pedidos postados na fila com sucesso !");
    }catch (Exception e){
        return ResponseEntity.status(500).body("OPS.. algo de errado aconteceu, tente novamente mais tarde");
    }








    }
}
