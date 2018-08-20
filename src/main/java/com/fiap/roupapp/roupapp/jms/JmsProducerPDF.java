package com.fiap.roupapp.roupapp.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsProducerPDF {

    private JmsTemplate jmsTemplate;
    private String queueName;

    @Autowired
    public JmsProducerPDF(JmsTemplate jmsTemplate, @Value("${mq.queue.pdf}")String queueName) {
        this.jmsTemplate = jmsTemplate;
        this.queueName = queueName;
    }

    public void processMessaging(Integer message) {

        jmsTemplate.convertAndSend(queueName, message);


    }
}
