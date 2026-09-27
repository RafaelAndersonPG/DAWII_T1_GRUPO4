package com.cibertec.msriesgo.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

// Tabla analisis: id_recarga, id_tarjeta, saldo_disponible, monto_recarga, fecha_recarga, situacion.
@Entity
@Table(name = "analisis")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Analisis {


	@Id
	private Long idRecarga;

	@Column(nullable = false)
	private Long idTarjeta;

	@Column(nullable = false, precision = 12, scale = 2)
	private BigDecimal saldoDisponible;

	@Column(nullable = false, precision = 12, scale = 2)
	private BigDecimal montoRecarga;

	@Column(nullable = false)
	private LocalDateTime fechaRecarga;

	@Column(nullable = false, length = 20)
	private String situacion;
}
