package com.cibertec.msrecargas.negocio;

import com.cibertec.msrecargas.client.TarjetaClient;
import com.cibertec.msrecargas.dto.RecargaRequest;
import com.cibertec.msrecargas.dto.TarjetaResponse;
import com.cibertec.msrecargas.entidades.Recarga;
import com.cibertec.msrecargas.rabbitmq.RecargaEvent;
import com.cibertec.msrecargas.rabbitmq.RecargaProducer;
import com.cibertec.msrecargas.repositorio.RecargaRepository;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RecargaService {

	private final RecargaRepository recargaRepository;
	private final TarjetaClient tarjetaClient;
	private final RecargaProducer recargaProducer;

	public RecargaService(RecargaRepository recargaRepository,
						  TarjetaClient tarjetaClient,
						  RecargaProducer recargaProducer) {
		this.recargaRepository = recargaRepository;
		this.tarjetaClient = tarjetaClient;
		this.recargaProducer = recargaProducer;
	}

	public Recarga registrarRecarga(RecargaRequest request) {
		if (request.idTarjeta() == null || request.montoRecarga() == null
				|| request.montoRecarga().compareTo(BigDecimal.ZERO) <= 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"idTarjeta y montoRecarga (mayor a 0) son obligatorios");
		}

		// 1. Validar que la tarjeta exista (OpenFeign -> ms-tarjetas).
		TarjetaResponse tarjeta = obtenerTarjeta(request.idTarjeta());

		// 2. Registrar con el saldo disponible de ms-tarjetas y la fecha del sistema.
		Recarga recarga = Recarga.builder()
				.idTarjeta(tarjeta.idTarjeta())
				.saldoDisponible(tarjeta.saldoDisponible())
				.montoRecarga(request.montoRecarga())
				.fechaRecarga(LocalDateTime.now())
				.build();
		Recarga guardada = recargaRepository.save(recarga);

		// 3. Publicar la solicitud registrada en la cola para el area de Riesgo.
		recargaProducer.publicar(new RecargaEvent(
				guardada.getIdRecarga(),
				guardada.getIdTarjeta(),
				guardada.getSaldoDisponible(),
				guardada.getMontoRecarga(),
				guardada.getFechaRecarga()
		));

		return guardada;
	}

	public List<Recarga> listarRecargas() {
		return recargaRepository.findAll();
	}

	public Recarga buscarRecargaPorId(Long idRecarga) {
		return recargaRepository.findById(idRecarga)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Recarga " + idRecarga + " no encontrada"));
	}

	private TarjetaResponse obtenerTarjeta(Long idTarjeta) {
		try {
			return tarjetaClient.buscarTarjetaPorId(idTarjeta);
		} catch (FeignException.NotFound ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"La tarjeta " + idTarjeta + " no existe. No se registro la recarga");
		} catch (FeignException ex) {
			throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE,
					"ms-tarjetas no disponible");
		}
	}
}
