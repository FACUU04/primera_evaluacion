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

    //Registra un nuevo paciente
        public Paciente crear(Paciente paciente) {
            log.info("Creando nuevo paciente DNI: {}", paciente.getDni());
            Paciente pacienteGuardado = pacienteRepository.save(paciente);
            log.info("Paciente {} guardado exitosamente", pacienteGuardado);
            return pacienteGuardado;
        }

        //Buscar por ID, lanza excepcion si no se encuentra
        public Paciente buscar(Long id) {
            log.info("Buscando paciente por ID: {}", id);
            return pacienteRepository.findById(id)
                    .orElseThrow(() -> {
                        log.error("ERROR: Paciente con ID: {} no encontrado", id);
                        return new NoEncontradoException("Paciente no encontrado con ID" + id);
                    });
        }

        //Devuelve la lista completa de pacientes registrados
        public List<Paciente> Todos() {
            log.info("Listando todos los pacientes");
            return pacienteRepository.findAll();
        }

        //Eliminar paciente de sistema por ID
        public void Eliminar (Long id){
            log.info("Eliminando el paciente ID: {}", id);
            pacienteRepository.delete(id);
        }
}
