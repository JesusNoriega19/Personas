package com.examen.examen.service;

import java.util.List;

import com.examen.examen.model.Persona;

public interface PersonaService {
    List<Persona> obtenerTodos();
    Persona guardar(Persona persona);
    Persona actualizar(Long id, Persona persona);
    void eliminar(Long id);

    String obPokemon();
    int contarPersonasSP();

}
