package com.academy.back.service;


import com.academy.back.exceptions.NoEncontradoException;
import com.academy.back.model.Paciente;
import com.academy.back.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository pacienteRepository;

        public Paciente crear(Paciente paciente) {
            log.info("Creando nuevo paciente DNI:" + paciente.getDni());
            return pacienteRepository.save(paciente);
        }

        public Paciente buscar(Long id) {
            log.info("Buscando paciente por ID:" + id);
            return pacienteRepository.findById(id)
                    .orElseThrow(() -> new NoEncontradoException("Paciente no encontrado con ID" + id)) ;
        }

        public List<Paciente> Todos() {
            log.info("Listando todos los pacientes");
            return pacienteRepository.findAll();
        }

        public void Eliminar (Long id){
            log.info("Eliminando paciente por ID:" + id);
            pacienteRepository.delete(id);
        }
}
