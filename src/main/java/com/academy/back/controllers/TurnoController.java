package com.academy.back.controllers;


import com.academy.back.dto.TurnoRequestDTO;
import com.academy.back.model.Turno;
import com.academy.back.service.TurnoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/turnos")
public class TurnoController {

    private final TurnoService turnoService;

    @PostMapping
    public ResponseEntity<Turno> registrarTurno(@Valid @RequestBody TurnoRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(turnoService.registrarTurno(dto));
    }

    @GetMapping
    public ResponseEntity<List<Turno>> listarTurnos(
            @RequestParam(required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
            @RequestParam(required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta){

        if (desde !=null && hasta != null){
            return ResponseEntity.ok(turnoService.buscarRango(desde, hasta));
        } return ResponseEntity.ok(turnoService.listarTodos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTurno(@PathVariable Long id){
        turnoService.eliminarTurno(id);
        return ResponseEntity.noContent().build();
    }
}
