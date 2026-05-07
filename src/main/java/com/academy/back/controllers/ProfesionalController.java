package com.academy.back.controllers;


import com.academy.back.model.Profesional;
import com.academy.back.service.ProfesionalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profesionales")
@RequiredArgsConstructor
public class ProfesionalController {

    private final ProfesionalService profesionalService;

    @PostMapping
    public ResponseEntity<Profesional> crear(@RequestBody Profesional profesional) {
        return ResponseEntity.status(HttpStatus.CREATED).body(profesionalService.crear(profesional));
    }

    @GetMapping
    public ResponseEntity<List<Profesional>> listar(@RequestParam(required = false) String especialidad){
        return ResponseEntity.ok(profesionalService.ListarEspecialidad(especialidad));
    }
}
