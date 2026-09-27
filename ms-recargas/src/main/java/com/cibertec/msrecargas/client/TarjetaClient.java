package com.cibertec.msrecargas.client;

import com.cibertec.msrecargas.dto.TarjetaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Comunicacion sincronica: ms-recargas llama a ms-tarjetas y espera la respuesta.
@FeignClient(
		name = "ms-tarjetas",
		url = "${tarjetas.base-url}"
)
public interface TarjetaClient {

	@GetMapping("/tarjetas/{idTarjeta}")
	TarjetaResponse buscarTarjetaPorId(@PathVariable("idTarjeta") Long idTarjeta);
}
