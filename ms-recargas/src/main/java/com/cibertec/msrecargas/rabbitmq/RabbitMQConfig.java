package com.cibertec.msrecargas.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	public static final String RECARGAS_EXCHANGE = "recargas-exchange";
	public static final String RIESGO_QUEUE = "Grupo4_Queue";
	public static final String RIESGO_ROUTING_KEY = "recarga.registrada";

	@Bean
	public DirectExchange recargasExchange() {
		return new DirectExchange(RECARGAS_EXCHANGE);
	}

	@Bean
	public Queue riesgoQueue() {
		return new Queue(RIESGO_QUEUE);
	}

	@Bean
	public Binding riesgoBinding(Queue riesgoQueue, DirectExchange recargasExchange) {
		return BindingBuilder.bind(riesgoQueue)
				.to(recargasExchange)
				.with(RIESGO_ROUTING_KEY);
	}

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
}
