package com.fiap.roupapp.roupapp.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsProducerPedidos {

    private JmsTemplate jmsTemplate;
    private String queueName;

    @Autowired
    public JmsProducerPedidos(JmsTemplate jmsTemplate, @Value("${mq.queue.pedidos}")String queueName) {
        this.jmsTemplate = jmsTemplate;
        this.queueName = queueName;
    }

    public void processMessaging(String pedidoIdentification) {

        jmsTemplate.convertAndSend(queueName, pedidoIdentification);


    }
}
