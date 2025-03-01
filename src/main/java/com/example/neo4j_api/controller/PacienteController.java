package com.example.neo4j_api.controller;

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

import com.example.neo4j_api.model.Paciente;
import com.example.neo4j_api.repository.PacienteRepository;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private PacienteRepository pacienteRepository;

    public PacienteController(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }
    
    @PostMapping
    public ResponseEntity<Paciente> createPaciente(@RequestBody Paciente paciente){
        return new ResponseEntity<>(pacienteRepository.save(paciente),HttpStatus.OK) ;
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> getPaciente(){
        return new ResponseEntity<>(pacienteRepository.findAll(),HttpStatus.OK) ;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> update(@PathVariable(value = "id") String id, 
                                        @RequestBody @Valid Paciente paciente) {
        Optional<Paciente> pacienteO = pacienteRepository.findById(id);
        
        if (pacienteO.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Paciente pacienteExistente = pacienteO.get();
        pacienteExistente.setNome(paciente.getNome());
        pacienteExistente.setIdade(paciente.getIdade());
        Paciente pacienteAtualizado = pacienteRepository.save(pacienteExistente);
        
       
        return new ResponseEntity<>(pacienteAtualizado,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id")String id){
        Optional <Paciente> pacienteO = pacienteRepository.findById(id);
        if(pacienteO.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        pacienteRepository.delete(pacienteO.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
