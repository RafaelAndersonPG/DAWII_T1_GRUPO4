package com.cibertec.msrecargas.dto;

import java.math.BigDecimal;

// El cliente solo envia la tarjeta y el monto; saldo y fecha los completa el servicio.
public record RecargaRequest(
		Long idTarjeta,
		BigDecimal montoRecarga
) {
}
