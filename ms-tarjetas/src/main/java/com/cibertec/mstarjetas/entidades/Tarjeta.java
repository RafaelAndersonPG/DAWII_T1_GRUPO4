package com.cibertec.mstarjetas.entidades;

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

@Entity
@Table(name = "tarjetas")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tarjeta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTarjeta;

	@Column(nullable = false, length = 100)
	private String nomTitular;

	@Column(nullable = false, precision = 12, scale = 2)
	private BigDecimal saldoAsignado;

	@Column(nullable = false, precision = 12, scale = 2)
	private BigDecimal saldoDisponible;
}
