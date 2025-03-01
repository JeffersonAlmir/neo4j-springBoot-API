package com.example.neo4j_api.repository;

import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.example.neo4j_api.model.Paciente;

public interface PacienteRepository extends Neo4jRepository<Paciente, String> {
    Optional<Paciente> findByCpf(String cpf);
}
