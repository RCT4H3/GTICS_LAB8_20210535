package com.example.gtics_lab8_20210535.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="categorias")
public class Categorias {
    @Id
    @Column(name = "id_cat", nullable = false)
    private Integer id_cat;

    @Column(name = "categoria", nullable = false)
    private String categoria;
}
