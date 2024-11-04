package com.example.gtics_lab8_20210535.Controller;

import com.example.gtics_lab8_20210535.Entity.Evento;
import com.example.gtics_lab8_20210535.Repository.EventoRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@RestController
public class EventoController {
    private final EventoRepository eventoRepository;

    public EventoController(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    @GetMapping({"/eventos", "eventos/{fecha}"})
    public List<Evento> getEventos(@PathVariable(name = "fecha", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        if (fecha != null) {
            return eventoRepository.findByFecha(fecha);
        }
        return eventoRepository.findAllByOrderByFechaAsc();
    }

    @PostMapping("/eventos")
    public ResponseEntity<HashMap<String, Object>> crearEvento(
            @Valid @RequestBody Evento evento,
            @RequestParam(value = "fetchId", required = false) boolean fetchId) {

        HashMap<String, Object> response = new HashMap<>();
        if (evento.getFecha().isBefore(LocalDate.now())) {
            response.put("error", "La fecha del evento debe ser en el futuro.");
            return ResponseEntity.badRequest().body(response);
        }
        eventoRepository.save(evento);
        if (fetchId) {
            response.put("id", evento.getId_evento());
        }
        response.put("estado", "creado");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
