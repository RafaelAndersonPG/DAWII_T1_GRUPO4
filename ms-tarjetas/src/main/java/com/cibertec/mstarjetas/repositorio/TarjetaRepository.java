package com.cibertec.mstarjetas.repositorio;

import com.cibertec.mstarjetas.entidades.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarjetaRepository extends JpaRepository<Tarjeta, Long> {
}
