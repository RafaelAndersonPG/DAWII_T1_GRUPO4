package com.cibertec.msrecargas.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// Comunicacion asincrona: publica la recarga y no espera respuesta de Riesgo.
@Component
public class RecargaProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(RecargaProducer.class);

	private final RabbitTemplate rabbitTemplate;
	private final String queueName;

	public RecargaProducer(RabbitTemplate rabbitTemplate, @Value("${riesgo.queue}") String queueName) {
		this.rabbitTemplate = rabbitTemplate;
		this.queueName = queueName;
	}

	public void publicar(RecargaEvent event) {
		// Exchange por defecto: la routing key es el nombre de la cola.
		rabbitTemplate.convertAndSend(queueName, event);
		LOGGER.info("Recarga publicada en RabbitMQ. queue={}, payload={}", queueName, event);
	}
}
