package com.example.gtics_lab8_20210535.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="reservas")
public class Reservas {
    @Id
    @Column(name = "id_reserva", nullable = false)
    private Integer id_evento;

    @Column(name = "nombre_reservante", nullable = false)
    private String nombre_reservante;

    @Column(name = "correo_reservante", nullable = false)
    private String correo_reservante;


    @Column(name = "num_cupos_reservando", nullable = false)
    private Integer num_cupos_reservando;

    @ManyToOne
    @JoinColumn(name="id_evento")
    private Evento evento;
}
