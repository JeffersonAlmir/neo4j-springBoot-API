package com.example.neo4j_api.model;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Node
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medico {
   @Id
   @NotBlank
   private String crm;

   @NotBlank
   private String nome;
   private String especialidade;
}
