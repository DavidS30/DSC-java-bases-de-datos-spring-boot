package com.devsenior.conexionpostgres.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class Usuario {
    private Long id;
    private String nombre;
    private String correo;
    private LocalDate fechaRegistro;
}
