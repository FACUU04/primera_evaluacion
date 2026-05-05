package com.academy.back.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Turno {
    private Paciente paciente;
    private Profesional profesional;
    private LocalDate fecha;
}
