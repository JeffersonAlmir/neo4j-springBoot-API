package com.example.neo4j_api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.neo4j_api.model.Consulta;
import com.example.neo4j_api.repository.ConsultaRepository;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/consultas")
public class ConsultaController {
    private final ConsultaRepository consultaRepository;

    public ConsultaController(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }
    
    @PostMapping
    public ResponseEntity<Consulta>  createConsulta(@RequestBody Consulta consulta){
        return new ResponseEntity<>(consultaRepository.save(consulta),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Consulta>> getConsultas(){
        return new ResponseEntity<>(consultaRepository.findAll(),HttpStatus.OK) ;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consulta> update(@PathVariable(value = "id") Long id, 
                                    @RequestBody @Valid Consulta consulta) {
        Optional<Consulta> consultaO = consultaRepository.findById(id);
        
        if (consultaO.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Consulta consultaExistente = consultaO.get();
        consultaExistente.setDataHora(consulta.getDataHora());
        consultaExistente.setMedico(consulta.getMedico());
        consultaExistente.setPaciente(consulta.getPaciente());
        Consulta consultaAtualizado = consultaRepository.save(consultaExistente);
        
       
        return new ResponseEntity<>(consultaAtualizado,HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
        Optional <Consulta> consultaOptional = consultaRepository.findById(id);
        if(consultaOptional.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        consultaRepository.delete(consultaOptional.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
