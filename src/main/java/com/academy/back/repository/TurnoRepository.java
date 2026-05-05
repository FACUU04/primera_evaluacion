package com.academy.back.repository;

import com.academy.back.model.Turno;
import jakarta.validation.constraints.DecimalMin;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TurnoRepository {
    private final Map<Long, Turno> turnos = new HashMap<>();
    private Long currentId = 1L;

    public Turno save (Turno turno) {
        if (turno.getId() == null) turno.setId(currentId++);
        turnos.put(turno.getId(), turno);
        return turno;
    }

    public List<Turno> findAll() {return new ArrayList<>(turnos.values());}
    public Optional<Turno> findById(Long id) {return Optional.ofNullable(turnos.get(id));}
    public void delete (Long id) {turnos.remove(id);}
}
