package com.academy.back.controllers;


import com.academy.back.model.Paciente;
import com.academy.back.service.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<Paciente> Crear(@RequestBody Paciente paciente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteService.crear(paciente));
    }

    @GetMapping
    public ResponseEntity<Paciente> Buscar(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.buscar(id));
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> BuscarTodos() {
        return ResponseEntity.ok(pacienteService.Todos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> Eliminar(@PathVariable Long id) {
        pacienteService.Eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
