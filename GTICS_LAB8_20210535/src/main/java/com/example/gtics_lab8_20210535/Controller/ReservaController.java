package com.example.gtics_lab8_20210535.Controller;

import com.example.gtics_lab8_20210535.Entity.Evento;
import com.example.gtics_lab8_20210535.Entity.Reservas;
import com.example.gtics_lab8_20210535.Repository.EventoRepository;
import com.example.gtics_lab8_20210535.Repository.ReservasRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class ReservaController {

    final EventoRepository eventoRepository;
    final ReservasRepository reservaRepository;

    public ReservaController(EventoRepository eventoRepository, ReservasRepository reservaRepository) {
        this.eventoRepository = eventoRepository;
        this.reservaRepository = reservaRepository;
    }

    @PostMapping("/reservas")
    public ResponseEntity<?> crearReserva(@RequestBody Reservas reserva) {
        Evento evento = eventoRepository.findById(reserva.getEvento().getId_evento()).orElse(null);
        if (evento == null) {
            return ResponseEntity.badRequest().body("Evento no encontrado");
        }

        if (evento.getNum_reservas_actuales() + reserva.getNum_cupos_reservando() > evento.getCapacidad_maxima()) {
            return ResponseEntity.badRequest().body("No hay suficientes cupos disponibles");
        }

        evento.setNum_reservas_actuales(evento.getNum_reservas_actuales() + reserva.getNum_cupos_reservando());
        eventoRepository.save(evento);
        reservaRepository.save(reserva);

        return ResponseEntity.ok("Reserva creada exitosamente");
    }

    @PostMapping("/reservas/{id}")
    public ResponseEntity<HashMap<String, Object>> borrarReserva(@PathVariable("id") String idReserva) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            int idInt = Integer.parseInt(idReserva);
            if (reservaRepository.existsById(idInt)) {
                reservaRepository.deleteById(idInt);
                map.put("estado", "borrado existoso");
                return ResponseEntity.ok(map);
            }else {
                map.put("estado", "error");
                map.put("mensaje", "No se econtró la reserva de id: " + idReserva);
                return ResponseEntity.badRequest().body(map);
            }
        } catch (NumberFormatException e) {
            map.put("estado", "error");
            map.put("msg", "El ID debe ser un número entero");
            return ResponseEntity.badRequest().body(map);
        }
    }
}
