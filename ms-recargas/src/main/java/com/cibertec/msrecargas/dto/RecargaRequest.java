package com.cibertec.msrecargas.dto;

import java.math.BigDecimal;

public record RecargaRequest(
		Long idTarjeta,
		BigDecimal montoRecarga
) {
}
