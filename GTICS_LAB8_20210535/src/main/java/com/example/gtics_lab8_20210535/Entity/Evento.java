package com.example.gtics_lab8_20210535.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name="evento")
public class Evento {
    @Id
    @Column(name = "id_evento", nullable = false)
    private Integer id_evento;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "capacidad_maxima", nullable = false)
    private Integer capacidad_maxima;

    @Column(name = "num_reservas_actuales", nullable = false)
    private Integer num_reservas_actuales = 0;


    @ManyToOne
    @JoinColumn(name="id_cat")
    private Categorias categorias;
}
