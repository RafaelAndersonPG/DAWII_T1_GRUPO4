package com.cibertec.mstarjetas.negocio;

import com.cibertec.mstarjetas.entidades.Tarjeta;
import com.cibertec.mstarjetas.repositorio.TarjetaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TarjetaService {

    private final TarjetaRepository tarjetaRepository;

    public TarjetaService(TarjetaRepository tarjetaRepository) {
        this.tarjetaRepository = tarjetaRepository;
    }

    public Tarjeta registrarTarjeta(Tarjeta tarjeta) {
        tarjeta.setIdTarjeta(null);

        if (tarjeta.getSaldoDisponible() == null) {
            tarjeta.setSaldoDisponible(tarjeta.getSaldoAsignado());
        }
        return tarjetaRepository.save(tarjeta);
    }

    public List<Tarjeta> listarTarjetas() {
        return tarjetaRepository.findAll();
    }

    public Tarjeta buscarTarjetaPorId(Long idTarjeta) {
        return tarjetaRepository.findById(idTarjeta)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Tarjeta " + idTarjeta + " no encontrada"));
    }
}
