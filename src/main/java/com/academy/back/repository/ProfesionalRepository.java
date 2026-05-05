package com.academy.back.repository;

import com.academy.back.model.Profesional;
import com.academy.back.model.Turno;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProfesionalRepository {

    private Map<Long, Profesional> profesionales = new HashMap<>();
    private Long currentId = 1L;

    public Profesional save (Profesional profesional) {
        if (profesional.getId() == null) {
            profesional.setId(currentId++);
        }
        profesionales.put(profesional.getId(), profesional);
        return profesional;
    }

    public List<Profesional> findAll(Long id) {
        return new ArrayList<>(profesionales.values());}

    public Optional<Profesional> findbyId(Long id) {
        return Optional.ofNullable(profesionales.get(id));}

    public void delete(Long id){
        profesionales.remove(id);}
}
