package com.cibertec.msrecargas.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

// Tabla recargas: id_recarga, id_tarjeta, saldo_disponible, monto_recarga, fecha_recarga.
@Entity
@Table(name = "recargas")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Recarga {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idRecarga;

	@Column(nullable = false)
	private Long idTarjeta;

	@Column(nullable = false, precision = 12, scale = 2)
	private BigDecimal saldoDisponible;

	@Column(nullable = false, precision = 12, scale = 2)
	private BigDecimal montoRecarga;

	@Column(nullable = false)
	private LocalDateTime fechaRecarga;
}
