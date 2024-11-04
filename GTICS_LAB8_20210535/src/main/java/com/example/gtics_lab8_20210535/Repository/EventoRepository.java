package com.example.gtics_lab8_20210535.Repository;

import com.example.gtics_lab8_20210535.Entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Integer> {
    List<Evento> findByFecha(LocalDate fecha);
    List<Evento> findAllByOrderByFechaAsc();
}
