package com.academy.back.service;

import com.academy.back.dto.TurnoRequestDTO;
import com.academy.back.exceptions.FechaInvalidaException;
import com.academy.back.exceptions.NoEncontradoException;
import com.academy.back.model.Paciente;
import com.academy.back.model.Profesional;
import com.academy.back.model.Turno;
import com.academy.back.repository.PacienteRepository;
import com.academy.back.repository.ProfesionalRepository;
import com.academy.back.repository.TurnoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class TurnoService {

    private final TurnoRepository turnoRepository;
    private final ProfesionalRepository profesionalRepository;
    private final PacienteRepository pacienteRepository;

    //Registra un nuevo turno, validando la existencia del paciente y profesional y evitando duplicados
    public Turno registrarTurno(TurnoRequestDTO dto) {
        log.info("Registrando Turno");

        //Validar que Paciente exista
        Paciente paciente = pacienteRepository.findById(dto.getPacienteID())
                .orElseThrow(() -> {
                    log.error("Paciente {} no encontrado", dto.getPacienteID());
                    return new NoEncontradoException("Paciente no encontrado");
                });

        //Validar que el Profesional exista
        Profesional profesional = profesionalRepository.findbyId(dto.getProfesionalID())
                .orElseThrow(() -> {
                    log.error("Profesional {} no encontrado", dto.getProfesionalID());
                    return new NoEncontradoException("Profesional no encontrado");
                });


        //Validar que no exista turno duplicado
        boolean duplicado = turnoRepository.findAll().stream()
                .anyMatch(t -> t.getPaciente().getId().equals(paciente.getId()) &&
                        t.getProfesional().getId().equals(profesional.getId()) &&
                        t.getFecha().equals(dto.getFecha()));
        if (duplicado) {
            log.warn("ERROR: El Paciente ya tiene turno con el Profesional el {}", dto.getFecha());
            throw new FechaInvalidaException("El Paciente ya tiene un turno con el Profesional en la fecha seleccionada");
        }

        //Crar y Guardar Turno
        Turno turno = new Turno(null, paciente, profesional, dto.getFecha());
        Turno turnoguardado = turnoRepository.save(turno);

        log.info("Turno registrado exitosamente con ID: {}", turno.getId());
        return turnoguardado;
    }
    public List<Turno> listarTodos(){
        return turnoRepository.findAll();}

    public void eliminarTurno(Long id){
        turnoRepository.delete(id);}

    //Buscar turnos por rango de fechas especifico
    public List<Turno> buscarRango(LocalDate desde, LocalDate hasta){
        return turnoRepository.findAll().stream()
                .filter(t -> !t.getFecha().isBefore(desde) && !t.getFecha().isAfter(hasta))
                .collect(Collectors.toList());
    }

    //Buscar turnos por fecha exacta
    public List<Turno> buscarFechaExacta(LocalDate fecha) {
        log.info("Filtrando turnos para el: {}", fecha);
        return turnoRepository.findAll().stream()
                .filter(t -> t.getFecha().equals(fecha))
                .collect(Collectors.toList());
    }

}
