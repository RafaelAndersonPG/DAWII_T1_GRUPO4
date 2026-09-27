package com.cibertec.msriesgo.rabbitmq;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public record RecargaEvent(
		Long idRecarga,
		Long idTarjeta,
		BigDecimal saldoDisponible,
		BigDecimal montoRecarga,
		LocalDateTime fechaRecarga
) {
}
