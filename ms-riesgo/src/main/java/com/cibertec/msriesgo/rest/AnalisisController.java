package com.cibertec.msriesgo.rest;

import com.cibertec.msriesgo.entidades.Analisis;
import com.cibertec.msriesgo.negocio.AnalisisService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/analisis")
public class AnalisisController {

	private final AnalisisService analisisService;

	public AnalisisController(AnalisisService analisisService) {
		this.analisisService = analisisService;
	}

	@GetMapping
	public ResponseEntity<List<Analisis>> listar() {
		return ResponseEntity.ok(analisisService.listarAnalisis());
	}
}
