package com.cibertec.msriesgo.negocio;

import com.cibertec.msriesgo.entidades.Analisis;
import com.cibertec.msriesgo.rabbitmq.RecargaEvent;
import com.cibertec.msriesgo.repositorio.AnalisisRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AnalisisService {

	public static final String APROBADA = "APROBADA";
	public static final String OBSERVADA = "OBSERVADA";
	private static final BigDecimal LIMITE = new BigDecimal("0.70");

	private final AnalisisRepository analisisRepository;

	public AnalisisService(AnalisisRepository analisisRepository) {
		this.analisisRepository = analisisRepository;
	}

	public Analisis registrarAnalisis(RecargaEvent event) {
		Analisis analisis = Analisis.builder()
				.idRecarga(event.idRecarga())
				.idTarjeta(event.idTarjeta())
				.saldoDisponible(event.saldoDisponible())
				.montoRecarga(event.montoRecarga())
				.fechaRecarga(event.fechaRecarga())
				.situacion(evaluarSituacion(event.montoRecarga(), event.saldoDisponible()))
				.build();
		return analisisRepository.save(analisis);
	}

	public List<Analisis> listarAnalisis() {
		return analisisRepository.findAll();
	}


	String evaluarSituacion(BigDecimal montoRecarga, BigDecimal saldoDisponible) {
		BigDecimal limite = saldoDisponible.multiply(LIMITE);
		return montoRecarga.compareTo(limite) > 0 ? OBSERVADA : APROBADA;
	}
}
