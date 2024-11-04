package com.example.gtics_lab8_20210535.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@Table(name="reservas")
public class Reservas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva", nullable = false)
    private Integer id_reserva;

    @NotBlank(message = "El nombre del reservante es obligatorio")
    @Column(name = "nombre_reservante", nullable = false)
    private String nombre_reservante;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Correo no válido")
    @Column(name = "correo_reservante", nullable = false)
    private String correo_reservante;


    @Min(value = 1, message = "Al menos un cupo debe ser reservado")
    @Column(name = "num_cupos_reservando", nullable = false)
    private Integer num_cupos_reservando;

    @ManyToOne
    @JoinColumn(name="id_evento")
    private Evento evento;
}
