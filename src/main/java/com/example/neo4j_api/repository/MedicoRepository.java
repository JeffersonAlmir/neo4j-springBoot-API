package com.example.neo4j_api.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.example.neo4j_api.model.Medico;
import com.google.common.base.Optional;

public interface MedicoRepository extends Neo4jRepository<Medico, String> {
    Optional<Medico> findByCrm(String crm); 
}
