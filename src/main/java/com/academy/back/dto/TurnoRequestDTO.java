package com.academy.back.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class TurnoRequestDTO {

    @NotNull(message = "ERROR: ID del paciente es obligatorio")
    private Long pacienteID;
    @NotNull (message = "ERROR: ID del profesional obligatorio")
    private Long profesionalID;
    @FutureOrPresent(message = "Epa! No tenemos una maquina del tiempo, pone una fecha presente o futura")
    private LocalDate fecha;

}
