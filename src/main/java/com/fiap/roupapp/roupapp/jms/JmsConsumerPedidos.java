package com.fiap.roupapp.roupapp.jms;

import com.fiap.roupapp.roupapp.entity.Pedido;
import com.fiap.roupapp.roupapp.repository.PedidoRepository;
import com.fiap.roupapp.roupapp.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JmsConsumerPedidos {

    private String queueName;
    private PedidoService pedidoService;
    private JmsProducerPDF jmsProducerPDF;



    @Autowired
    public JmsConsumerPedidos(@Value("mq.queue.pedidos")String queueName,PedidoService pedidoService, JmsProducerPDF jmsProducerPDF) {
        this.queueName = queueName;
        this.pedidoService = pedidoService;
        this.jmsProducerPDF = jmsProducerPDF;
    }

    @JmsListener(destination = "${mq.queue.pedidos}", containerFactory = "jsaFactory",concurrency ="10-50")
    public void receiveMessageOne(int  pedidoId) {




        System.out.println("Recebendo pedidos...   " + pedidoId);
        if( pedidoId < 0){

        List<Pedido> pedidos = pedidoService.findAll();
        pedidos.stream().forEach( pedido -> jmsProducerPDF.processMessaging(pedido.getIdentificationPedido()));



    }else {
        jmsProducerPDF.processMessaging(pedidoId);

    }


    }



}
