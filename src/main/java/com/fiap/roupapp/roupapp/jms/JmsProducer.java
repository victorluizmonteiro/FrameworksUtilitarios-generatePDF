package com.fiap.roupapp.roupapp.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsProducer {

    private JmsTemplate jmsTemplate;
    private String queueName;

    @Autowired
    public JmsProducer(JmsTemplate jmsTemplate, @Value("${mq.queue}")String queueName) {
        this.jmsTemplate = jmsTemplate;
        this.queueName = queueName;
    }

    public void processMessaging(String message) {

        jmsTemplate.convertAndSend(queueName, message);


    }
}
