package com.examen.examen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examen.examen.model.Persona;
import com.examen.examen.service.PersonaService;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {
    @Autowired
    private PersonaService service;

    @GetMapping
    public List<Persona> listar() {
        return service.obtenerTodos();
    }

    @PostMapping
    public Persona crear(@RequestBody Persona persona) {
        return service.guardar(persona);
    }

    @PutMapping("/{id}")
    public Persona actualizar(@PathVariable Long id, @RequestBody Persona persona) {
    return service.actualizar(id, persona);
}

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    @GetMapping("/pokemons")
    public String fraseGato() {
        return service.obtenerFraseDeGato();
    }

    @GetMapping("/contar")
        public int contarPersonas() {
        return service.contarPersonasSP();
    }

}
