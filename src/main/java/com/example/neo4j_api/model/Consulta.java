package com.example.neo4j_api.model;

import java.time.LocalDateTime;


import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;


import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Node
@Data
@NoArgsConstructor
public class Consulta {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime dataHora;

    @Relationship(type = "REALIZA")
    @NotNull
    private Medico medico;

    @Relationship(type = "PARTICIPA")
    @NotNull
    private Paciente paciente;

    public Consulta(LocalDateTime dataHora, Medico medico, Paciente paciente) {
        this.dataHora = dataHora;
        this.medico = medico;
        this.paciente = paciente;
    }


    
}
