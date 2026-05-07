package com.academy.back.service;


import com.academy.back.model.Profesional;
import com.academy.back.repository.ProfesionalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfesionalService {

    private final ProfesionalRepository profesionalRepository;

    //Registrar un nuevo profesional
    public Profesional crear(Profesional profesional) {
        log.info("Creando profesional: {}", profesional.getNombreCompleto());
        Profesional profesionalGuardado = profesionalRepository.save(profesional);
        log.info("Profesional: {} registrado exitosamente con ID: {}", profesionalGuardado, profesional.getId());
        return profesionalGuardado;
    }

    //Listar todos los profesionales y Filtrar por especialidad si se desea
    public List<Profesional> ListarEspecialidad(String especialidad) {
        if (especialidad == null || especialidad.trim().isEmpty()) {
            log.info("Listando todos los profesionales ");
            return profesionalRepository.findAll();
        }
        log.info("Buscando profesionales en: {}", especialidad);
        return profesionalRepository.findAll().stream()
                .filter( p -> p.getEspecialidad().equalsIgnoreCase(especialidad))
                .collect(Collectors.toList());
    }
}
