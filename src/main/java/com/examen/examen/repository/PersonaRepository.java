package com.examen.examen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examen.examen.model.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long>{ 

}
