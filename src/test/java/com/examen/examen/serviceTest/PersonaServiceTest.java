package com.examen.examen.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.examen.examen.model.Persona;
import com.examen.examen.repository.PersonaRepository;
import com.examen.examen.service.PersonaServiceImpl;

@ExtendWith(MockitoExtension.class)
public class PersonaServiceTest {

    @Mock
    private PersonaRepository repo;

    @InjectMocks
    private PersonaServiceImpl service;

    @Test
    public void testGuardarPersona() {
        Persona persona = new Persona();
        persona.setNombre("Luis");
        persona.setCorreo("luis@mail.com");

        when(repo.save(persona)).thenReturn(persona);

        Persona guardada = service.guardar(persona);
        assertEquals("Luis", guardada.getNombre());
    }
}