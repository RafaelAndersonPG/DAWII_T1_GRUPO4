package com.cibertec.msrecargas.rest;

import com.cibertec.msrecargas.dto.RecargaRequest;
import com.cibertec.msrecargas.entidades.Recarga;
import com.cibertec.msrecargas.negocio.RecargaService;
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
@RequestMapping("/recargas")
public class RecargaController {

	private final RecargaService recargaService;

	public RecargaController(RecargaService recargaService) {
		this.recargaService = recargaService;
	}

	@PostMapping
	public ResponseEntity<Recarga> registrar(@RequestBody RecargaRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(recargaService.registrarRecarga(request));
	}

	@GetMapping
	public ResponseEntity<List<Recarga>> listar() {
		return ResponseEntity.ok(recargaService.listarRecargas());
	}

	@GetMapping("/{idRecarga}")
	public ResponseEntity<Recarga> buscarPorId(@PathVariable Long idRecarga) {
		return ResponseEntity.ok(recargaService.buscarRecargaPorId(idRecarga));
	}
}
