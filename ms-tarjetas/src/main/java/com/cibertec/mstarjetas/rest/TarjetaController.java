package com.cibertec.mstarjetas.rest;

import com.cibertec.mstarjetas.entidades.Tarjeta;
import com.cibertec.mstarjetas.negocio.TarjetaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tarjetas")
public class TarjetaController {

	private final TarjetaService tarjetaService;

	public TarjetaController(TarjetaService tarjetaService) {
		this.tarjetaService = tarjetaService;
	}

	@PostMapping
	public ResponseEntity<Tarjeta> registrar(@RequestBody Tarjeta tarjeta) {
		return ResponseEntity.status(HttpStatus.CREATED).body(tarjetaService.registrarTarjeta(tarjeta));
	}

	@GetMapping
	public ResponseEntity<List<Tarjeta>> listar() {
		return ResponseEntity.ok(tarjetaService.listarTarjetas());
	}

	@GetMapping("/{idTarjeta}")
	public ResponseEntity<Tarjeta> buscarPorId(@PathVariable Long idTarjeta) {
		return ResponseEntity.ok(tarjetaService.buscarTarjetaPorId(idTarjeta));
	}
}
