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

import com.example.neo4j_api.model.Medico;
import com.example.neo4j_api.repository.MedicoRepository;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/medicos")
public class MedicoController {

    private final MedicoRepository medicoRepository;

    public MedicoController(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    @PostMapping
    public ResponseEntity<Medico> createMedico(@RequestBody Medico medico){
        return new ResponseEntity<>(medicoRepository.save(medico),HttpStatus.OK) ;
    }

    @GetMapping
    public ResponseEntity<List<Medico>> getMedicosAll(){
        return new ResponseEntity<> (medicoRepository.findAll(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> getMedicoById(@PathVariable(value = "id") String id){
        Optional<Medico> medicoO = medicoRepository.findById(id);
        if(medicoO.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(medicoO.get(),HttpStatus.OK);
    
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medico> update(@PathVariable(value = "id") String id,
                                    @RequestBody @Valid Medico medico){
        Optional<Medico> medicoO = medicoRepository.findById(id);
        if(medicoO.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Medico medicoExistente = medicoO.get();
        medicoExistente.setEspecialidade(medico.getEspecialidade());
        medicoExistente.setNome(medico.getNome());
        Medico medicoAtualizado = medicoRepository.save(medicoExistente);

        return new ResponseEntity<>(medicoAtualizado,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") String id){
        Optional<Medico> medicOptional =medicoRepository.findById(id);
        if(medicOptional.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        medicoRepository.delete(medicOptional.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
