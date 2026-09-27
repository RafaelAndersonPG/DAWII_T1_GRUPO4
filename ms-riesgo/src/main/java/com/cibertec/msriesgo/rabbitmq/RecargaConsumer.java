package com.cibertec.msriesgo.rabbitmq;

import com.cibertec.msriesgo.entidades.Analisis;
import com.cibertec.msriesgo.negocio.AnalisisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class RecargaConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(RecargaConsumer.class);

	private final AnalisisService analisisService;

	public RecargaConsumer(AnalisisService analisisService) {
		this.analisisService = analisisService;
	}

	@RabbitListener(queues = "${riesgo.queue}")
	public void onRecarga(RecargaEvent event) {
		Analisis analisis = analisisService.registrarAnalisis(event);
		LOGGER.info("[ms-riesgo] Recarga consumida. idRecarga={}, monto={}, saldo={}, situacion={}",
				analisis.getIdRecarga(), analisis.getMontoRecarga(),
				analisis.getSaldoDisponible(), analisis.getSituacion());
	}
}
