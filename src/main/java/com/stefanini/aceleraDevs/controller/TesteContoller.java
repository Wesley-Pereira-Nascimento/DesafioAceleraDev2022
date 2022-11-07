package com.stefanini.aceleraDevs.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stefanini.aceleraDevs.dto.TurmaDTO;
import com.stefanini.aceleraDevs.exception.TurmaNotFoundException;
import com.stefanini.aceleraDevs.model.Turma;
import com.stefanini.aceleraDevs.repository.TurmaRepository;
import com.stefanini.aceleraDevs.service.TurmaService;

@Controller
@ResponseBody
@RequestMapping("/teste2")
public class TesteContoller {
	@PersistenceContext
	private EntityManager manager;
	private TurmaService turmaService;
	private TurmaRepository turmaRepository;
	
	
	
	@GetMapping("/teste2")
	public List<Turma> lista() {
		return manager.createQuery("from Turma",Turma.class).getResultList();
				}
	
	@DeleteMapping("/{Id}")
	public  ResponseEntity<Void> deletar(@PathVariable Long id) throws TurmaNotFoundException{
		if(!turmaRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		turmaService.delete(id);
		return ResponseEntity.noContent().build()
;	}
}
