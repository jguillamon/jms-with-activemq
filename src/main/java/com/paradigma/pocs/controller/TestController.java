package com.paradigma.pocs.controller;

import com.paradigma.pocs.config.ActiveMQConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController {

    private JmsTemplate jmsTemplate;

    public TestController(JmsTemplate jmsTemplate){
        this.jmsTemplate = jmsTemplate;
    }

    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    public String doSendMessageToActiveMQ(@RequestBody String message) {
        jmsTemplate.convertAndSend(ActiveMQConfiguration.QUEUE_NAME, message);
        return "Alive!";
    }

    @JmsListener(destination = ActiveMQConfiguration.QUEUE_NAME)
    public void onMessageFromActiveMQ(final String messageFromRabbitMQ){
        log.info("{}", messageFromRabbitMQ);
    }
}
