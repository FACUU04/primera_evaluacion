package com.academy.back.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {

    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private String email;
}
