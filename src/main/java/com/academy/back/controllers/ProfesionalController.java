package com.academy.back.controllers;


import com.academy.back.model.Profesional;
import com.academy.back.service.ProfesionalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/profesionales")
@RequiredArgsConstructor
public class ProfesionalController {

    private final ProfesionalService profesionalService;

    //Endpoint para registrar un nuevo profesional
    @PostMapping
    public ResponseEntity<Profesional> crear(@RequestBody Profesional profesional) {
        log.info("Peticion Recibida: Creando profesional");
        return ResponseEntity.status(HttpStatus.CREATED).body(profesionalService.crear(profesional));
    }

    //Endpoint para listar profesionales con opcion de listar por especialidad segun parametro
    @GetMapping
    public ResponseEntity<List<Profesional>> listar
    (@RequestParam(required = false) String especialidad){

        if (especialidad != null) {
            log.info("Peticion recibida, buscando todos los profesionales en {}", especialidad);
        } else {
            log.info("Peticion recibida, listando todos los profesionales");
        }
        return ResponseEntity.ok(profesionalService.ListarEspecialidad(especialidad));
    }
}
