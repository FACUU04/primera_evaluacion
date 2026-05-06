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
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class TurnoService {

    private final TurnoRepository turnoRepository;
    private final ProfesionalRepository profesionalRepository;
    private final PacienteRepository pacienteRepository;

    public Turno registrarTurno(TurnoRequestDTO dto){

        Paciente paciente = pacienteRepository.findById(dto.getPacienteID())
                .orElseThrow(() -> new NoEncontradoException("Paciente no encontrado"));

        Profesional profesional = profesionalRepository.findbyId(dto.getProfesionalID())
                .orElseThrow(() -> new NoEncontradoException("Profesional no encontrado"));

        boolean duplicado = turnoRepository.findAll().stream()
                .anyMatch(t -> t.getPaciente().getId().equals(paciente.getId()) &&
                        t.getProfesional().getId().equals(profesional.getId()) &&
                        t.getFecha().equals(dto.getFecha()));
        if (duplicado){
            throw new FechaInvalidaException("El Paciente ya tiene un turno con el Profesional en fecha seleccionada");
        }

        Turno turno = new Turno(null, paciente, profesional, dto.getFecha());
        return turnoRepository.save(turno);}

    public List<Turno> listarTodos(){
        return turnoRepository.findAll();}

    public void eliminarTurno(Long id){
        turnoRepository.delete(id);}

    public List<Turno> buscarRango(LocalDate desde, LocalDate hasta){
        return turnoRepository.findAll().stream()
                .filter(t -> !t.getFecha().isBefore(desde) && !t.getFecha().isAfter(hasta))
                .collect(Collectors.toList());
    }

}
