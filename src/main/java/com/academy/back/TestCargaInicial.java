package com.academy.back;


import com.academy.back.model.Paciente;
import com.academy.back.model.Profesional;
import com.academy.back.model.Turno;
import com.academy.back.repository.PacienteRepository;
import com.academy.back.repository.ProfesionalRepository;
import com.academy.back.repository.TurnoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Slf4j
@Configuration
public class TestCargaInicial {

    @Bean
    CommandLineRunner initDatabase(PacienteRepository pacRepo, ProfesionalRepository profRepo, TurnoRepository turnoRepo) {
        return args -> {

            Paciente p1 = pacRepo.save(new Paciente(null, "Lautaro", "Sosa", "123456", "Lautaro@gmail.com"));
            Paciente p2 = pacRepo.save(new Paciente(null, "Juan", "Garcia", "789012", "Juan@hotmail.com"));

            Profesional pro1 = profRepo.save(new Profesional(null, "Dra. Nahiara", "Pediatra"));
            Profesional pro2 = profRepo.save(new Profesional(null, "Dr. Julian de la cruz", "Odontologo"));

            turnoRepo.save(new Turno(null, p1, pro1, LocalDate.now().plusDays(1)));
            turnoRepo.save(new Turno(null, p2, pro2, LocalDate.now().plusDays(3)));
            turnoRepo.save(new Turno(null, p1, pro2, LocalDate.now().plusDays(7)));

            log.info("Datos iniciales cargados");
        };
    }
}

