package com.academy.back.repository;

import com.academy.back.model.Paciente;
import com.academy.back.model.Turno;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PacienteRepository {

    private final Map<Long, Paciente> pacientes = new HashMap<>();
    private Long currentId = 1L;

    public Paciente save(Paciente paciente) {
        if (paciente.getId() == null) {
            paciente.setId(currentId++);
        }
        pacientes.put(paciente.getId(), paciente);
        return paciente;
    }

    public List<Paciente> findAll()
        {return new ArrayList<>(pacientes.values());}

    public Optional<Paciente> findById(Long id) {
        return Optional.ofNullable(pacientes.get(id));}

    public void delete(Long id) {
        pacientes.remove(id);}
}

