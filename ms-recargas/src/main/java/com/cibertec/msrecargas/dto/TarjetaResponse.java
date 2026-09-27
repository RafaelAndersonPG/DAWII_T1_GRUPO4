package com.cibertec.msrecargas.dto;

import java.math.BigDecimal;

public record TarjetaResponse(
		Long idTarjeta,
		String nomTitular,
		BigDecimal saldoAsignado,
		BigDecimal saldoDisponible
) {
}
