package com.fiap.roupapp.roupapp.controller;

import com.fiap.roupapp.roupapp.database.CarregarDatabase;
import com.fiap.roupapp.roupapp.entity.Cliente;
import com.fiap.roupapp.roupapp.entity.Pedido;
import com.fiap.roupapp.roupapp.jms.JmsProducer;
import com.fiap.roupapp.roupapp.repository.ClienteRepository;
import com.fiap.roupapp.roupapp.repository.PedidoRepository;
import com.fiap.roupapp.roupapp.utils.ItextUtils;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.Month;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private ClienteRepository clienteRepository;
    private CarregarDatabase carregarDatabase;
    private JmsProducer jmsProducer;
    private ItextUtils itextUtils;
    private PedidoRepository pedidoRepository;

    public ClienteController(ClienteRepository clienteRepository, CarregarDatabase carregarDatabase, JmsProducer jmsProducer, ItextUtils itextUtils,PedidoRepository pedidoRepository) {
        this.clienteRepository = clienteRepository;
        this.carregarDatabase = carregarDatabase;
        this.jmsProducer = jmsProducer;
        this.itextUtils = itextUtils;
        this.pedidoRepository = pedidoRepository;
    }


    @PostMapping
    public ResponseEntity carregarBase() {

        carregarDatabase.carregarBase();

        return ResponseEntity.ok().body("Base de dados carregada com sucesso ");


    }

    @Async("fileExecutor")
    @Transactional
    public CompletableFuture<List<Pedido>> buscarPedidos(){

        return CompletableFuture.completedFuture(pedidoRepository.findAll());
    }



    @PostMapping("/gerarPedido/{idPedido}")
    public ResponseEntity testeMq(@PathVariable("idPedido") Integer pedidoId) {
        try{

           if( pedidoId < 0){
                List<Pedido> pedidos = buscarPedidos().get();

                pedidos.stream().forEach( p -> jmsProducer.processMessaging(p.getId()));


                return ResponseEntity.ok().body("Todos os pedidos gerados com sucesso !");
            }else {
                jmsProducer.processMessaging(pedidoId);
                return ResponseEntity.ok().body("Pedido gerado com sucesso !" + "Id : " + pedidoId);
            }




        }catch (Exception e){
            return ResponseEntity.status(500).body("OPS... algo de errado aconteceu");
        }


    }

    @PostMapping("/gerarPedido/mes/{mes}")
    public ResponseEntity generatePdfByMonth(@PathVariable("mes") Integer mes) {

    try{
    List<Pedido> pedidos =buscarPedidos().get();

    List<Pedido> pedidosFilter = pedidos.stream().filter(p -> p.getLocalDateTime().getMonth().equals(Month.of(mes))).collect(Collectors.toList());

    pedidosFilter.stream().forEach( p -> jmsProducer.processMessaging(p.getId()));

        return ResponseEntity.ok().body("Pedidos postados na fila com sucesso !");
    }catch (Exception e){
        return ResponseEntity.status(500).body("OPS.. algo de errado aconteceu, tente novamente mais tarde");
    }








    }
}
