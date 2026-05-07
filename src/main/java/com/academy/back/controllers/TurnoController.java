package com.academy.back.controllers;


import com.academy.back.dto.TurnoRequestDTO;
import com.academy.back.model.Turno;
import com.academy.back.service.TurnoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/turnos")
public class TurnoController {

    private final TurnoService turnoService;

    //Registrar nuevos turnos. Utiliza @Valid para que cumpla con los requisitos
    @PostMapping
    public ResponseEntity<Turno> registrarTurno(@Valid @RequestBody TurnoRequestDTO dto){
        log.info("Peticion Recibida: Iniciando registro de Turno");
        return ResponseEntity.status(HttpStatus.CREATED).body(turnoService.registrarTurno(dto));
    }

    //Obtiene el listado de turnos, por defecto listado completo o por rango llenando los parametros (desde / hasta)
    @GetMapping
    public ResponseEntity<List<Turno>> listarTurnos(
            @RequestParam(required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
            @RequestParam(required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta){

        if (desde !=null && hasta != null){
            log.info("Peticion Recibida: Filtrando turnos por rango");
            return ResponseEntity.ok(turnoService.buscarRango(desde, hasta));
        }
        log.info("Peticion Recibida: Lista de Turnos");
        return ResponseEntity.ok(turnoService.listarTodos());
    }

    // Endpoint para buscar turnos por fecha exacta
    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<List<Turno>> buscarPorFecha(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        log.info("Peticion Recibida: Buscando turnos para la fecha exacta {}", fecha);
        return ResponseEntity.ok(turnoService.buscarFechaExacta(fecha));
    }

    //Eliminar turnos usando ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTurno(@PathVariable Long id){
        log.info("Peticion Recibida: Eliminando Turno");
        turnoService.eliminarTurno(id);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content si fue exitoso
    }
}
