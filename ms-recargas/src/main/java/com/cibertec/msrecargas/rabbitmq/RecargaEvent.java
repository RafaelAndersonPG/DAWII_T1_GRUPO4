package com.cibertec.msrecargas.rabbitmq;

import java.math.BigDecimal;
import java.time.LocalDateTime;

// Mensaje que viaja por la cola hacia el area de Riesgo.
public record RecargaEvent(
		Long idRecarga,
		Long idTarjeta,
		BigDecimal saldoDisponible,
		BigDecimal montoRecarga,
		LocalDateTime fechaRecarga
) {
}
