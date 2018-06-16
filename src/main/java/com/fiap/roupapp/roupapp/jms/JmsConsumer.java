package com.fiap.roupapp.roupapp.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsConsumer {

    private JmsTemplate jmsTemplate;
    private String queueName;

    @Autowired
    public JmsConsumer(JmsTemplate jmsTemplate, @Value("${mq.queue}") String queueName) {
        this.jmsTemplate = jmsTemplate;
        this.queueName = queueName;
    }

    @JmsListener(destination = "${mq.queue}", containerFactory = "jsaFactory")
    public void receiveMessage(String message) {


        System.out.println("RECEBENDO " + message + ".....");

    }
}
