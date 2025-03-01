package com.example.neo4j_api.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.example.neo4j_api.model.Consulta;

public interface ConsultaRepository extends Neo4jRepository<Consulta, Long> {
    
}
