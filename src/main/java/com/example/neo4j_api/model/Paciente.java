package com.example.neo4j_api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.Node;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Node
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Paciente {
    @Id
    @NotBlank
    private String cpf;
    @NotBlank
    private String nome;
    private int idade;
}
