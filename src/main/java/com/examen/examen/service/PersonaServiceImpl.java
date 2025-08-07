package com.examen.examen.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.examen.examen.exception.RecursoNoEncontradoException;
import com.examen.examen.model.Persona;
import com.examen.examen.repository.PersonaRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;

@Service
public class PersonaServiceImpl implements PersonaService{
    @Autowired
    private PersonaRepository repo;

    @Override
    public List<Persona> obtenerTodos() {
        return repo.findAll().stream()
            .filter(p -> p.getCorreo().contains("@"))
            .collect(Collectors.toList());
    }

    @Override
    public Persona guardar(Persona persona) {
        return repo.save(persona);
    }

    @Override
    public Persona actualizar(Long id, Persona persona) {
    Persona existente = repo.findById(id)
        .orElseThrow(() -> new RecursoNoEncontradoException("Persona no encontrada con ID: " + id));
    
    existente.setNombre(persona.getNombre());
    existente.setCorreo(persona.getCorreo());

    return repo.save(existente);
}


    @Override
    public void eliminar(Long id) {
        Persona persona = repo.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("Persona no encontrada con ID: " + id));
        repo.delete(persona);
    }

    //CONSUMIR API EXTERNA 

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String obPokemon() {
        return restTemplate.getForObject("https://pokeapi.co/api/v2/pokemon/", String.class);
    }


   // PROCEDIMIENTO ALMACENADO

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public int contarPersonasSP() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("contar_personas");
        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.OUT);
        query.execute();
        return (int) query.getOutputParameterValue(1);
    }
}
