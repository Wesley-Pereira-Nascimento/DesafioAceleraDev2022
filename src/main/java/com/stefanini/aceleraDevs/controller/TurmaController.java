package com.stefanini.aceleraDevs.controller;

import com.stefanini.aceleraDevs.dto.TurmaDTO;
import com.stefanini.aceleraDevs.exception.CursoNotFoundException;
import com.stefanini.aceleraDevs.exception.TurmaNotFoundException;
import com.stefanini.aceleraDevs.mapper.TurmaDTOService;
import com.stefanini.aceleraDevs.model.Turma;
import com.stefanini.aceleraDevs.service.TurmaService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@RequestMapping("/turma")
public class TurmaController {

    private final TurmaService turmaService;
    private final TurmaDTOService turmaDTOService;

    @Autowired
    public TurmaController(TurmaService turmaService, TurmaDTOService turmaDTOService) {
        this.turmaService = turmaService;
        this.turmaDTOService = turmaDTOService;
    }

    @Autowired

    @RequestMapping(path = "/turma")
    public ModelAndView loadHtml() {

        ModelAndView mv = new ModelAndView("turma");
        TurmaDTO turmaDTO = new TurmaDTO();

        mv.addObject("turmaDTO", turmaDTO);

        return mv;
    }

    @PostMapping(value = "/turma")
    public String saveTurma(TurmaDTO turma) throws TurmaNotFoundException, CursoNotFoundException {

        Turma newTurma = turmaDTOService.mapTurma(turma);

        turmaService.save(newTurma);

        return "redirect:/turma";
    }
    
    @GetMapping("/turma/lista")
    public ResponseEntity<List<TurmaDTO>> listaTurma(){
   	 List<Turma> listaTurma = turmaService.findAllTurmas(); 
   	 List<TurmaDTO> listDTO = listaTurma.stream().map(TurmaDTO::new)).collect(Collectors.toList()); return
   	 ResponseEntity.ok().body(listDTO); }
    
    
    
    
    

}
