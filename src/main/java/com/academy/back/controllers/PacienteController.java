package com.academy.back.controllers;


import com.academy.back.model.Paciente;
import com.academy.back.service.PacienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/pacientes")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService pacienteService;

    //Endpoint para registrar un paciente nuevo
    @PostMapping
    public ResponseEntity<Paciente> Crear(@RequestBody Paciente paciente) {
        log.info("Peticion recibida, creando paciente: {}", paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteService.crear(paciente));
    }

    //Endpoint para obtener un paciente especifico
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> Buscar(@PathVariable Long id) {
        log.info("Peticion recibida, buscando paciente: {}", id);
        return ResponseEntity.ok(pacienteService.buscar(id));
    }

    //Endpoint para listar todos los pacientes
    @GetMapping
    public ResponseEntity<List<Paciente>> BuscarTodos() {
        log.info("Peticion recibida para listar todos los pacientes, procesando....");
        return ResponseEntity.ok(pacienteService.Todos());
    }

    //Endpoint para eliminar un paciente por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> Eliminar(@PathVariable Long id) {
        log.info("Peticion recibida, eliminando paciente: {}", id);
        pacienteService.Eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
