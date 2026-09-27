package com.cibertec.msrecargas.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	// Declara la cola (durable) si aun no existe en RabbitMQ.
	@Bean
	public Queue riesgoQueue(@Value("${riesgo.queue}") String queueName) {
		return new Queue(queueName, true);
	}

	// Envia los mensajes como JSON legible.
	@Bean
	public MessageConverter jsonMessageConverter(ObjectMapper objectMapper) {
		return new Jackson2JsonMessageConverter(objectMapper);
	}
}
