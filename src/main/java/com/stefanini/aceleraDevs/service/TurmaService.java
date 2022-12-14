package com.stefanini.aceleraDevs.service;

import com.stefanini.aceleraDevs.exception.TurmaNotFoundException;
import com.stefanini.aceleraDevs.model.Turma;
import com.stefanini.aceleraDevs.repository.TurmaRepository;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TurmaService {

    private final TurmaRepository turmaRepository;

    public TurmaService(TurmaRepository turmaRepository) {
        this.turmaRepository = turmaRepository;
    }

    public List<Turma> findAllTurmas(){
        return turmaRepository.findAll();
    }

    public Turma findById(Long id) throws TurmaNotFoundException {
        return turmaRepository.findById(id)
                .orElseThrow(()-> new TurmaNotFoundException(id));
    }

    public Turma save(Turma turma){
        return turmaRepository.save(turma);
    }
    
    public  void delete(Long id) throws TurmaNotFoundException {
         turmaRepository.deleteById(id);
                
    }

}
