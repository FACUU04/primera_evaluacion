package com.academy.back.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Profesional {

    private Long id;
    private String nombreCompleto;
    private String especialidad;

}
