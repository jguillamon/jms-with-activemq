package com.paradigma.pocs.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

/**
 *  Esta clase configura la conexion con la cola mediante jms, pero como el starter de
 *  spring-boot viene configurado se podría eliminar este clase y usar directamente en
 *  el application.yml las porpiedades de configuracion siguientes
 *  spring:
 *    activemq:
 *      broker-url: tcp://192.168.99.100:61616
 *  Y podriamos prescindir de esta clase. Me gusta dejarla visible porque me parece mas entendible
 *  que la magia que hace por detras de configuracion de spring-boot.
 *  Pero en un código funcional sería elegante prescindir de esta clase que no aporta demasiado (si no necesitamos configuracion a medida).
 */
@Configuration
public class ActiveMQConfiguration {

    public static final String QUEUE_NAME = "test-queue";

    @Value("${activemq.broker-url}")
    private String brokerUrl;

    @Value("${activemq.user}")
    private String user;

    @Value("${activemq.password}")
    private String password;

    @Bean
    public ConnectionFactory connectionFactory() {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                user, password, brokerUrl);
        return connectionFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        return new JmsTemplate(connectionFactory());
    }

    @Bean
    public Queue queue(){
        return new ActiveMQQueue(QUEUE_NAME);
    }
}
