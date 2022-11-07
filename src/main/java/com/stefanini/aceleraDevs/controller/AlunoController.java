package com.stefanini.aceleraDevs.controller;

import com.stefanini.aceleraDevs.dto.AlunoDTO;
import com.stefanini.aceleraDevs.exception.TurmaNotFoundException;
import com.stefanini.aceleraDevs.model.Aluno;
import com.stefanini.aceleraDevs.repository.AlunoRepository;
import com.stefanini.aceleraDevs.mapper.AlunoDTOService;
import com.stefanini.aceleraDevs.service.AlunoService;



import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AlunoController {

    private final AlunoService alunoService;
    private final AlunoDTOService alunoDTOService;

    @Autowired
    public AlunoController(AlunoService alunoService, AlunoDTOService alunoDTOService) {
        this.alunoService = alunoService;
        this.alunoDTOService = alunoDTOService;
    }

    @Autowired


    @RequestMapping(path = "/aluno")
    public ModelAndView loadHtml() {

        ModelAndView mv = new ModelAndView("aluno");
        AlunoDTO alunoDTO = new AlunoDTO();

        mv.addObject("alunoDTO", alunoDTO);

        return mv;
    }

    @PostMapping(value = "/aluno")
    public String saveAluno(AlunoDTO aluno) throws TurmaNotFoundException {

        Aluno newAluno = alunoDTOService.mapAluno(aluno);

        alunoService.save(newAluno);

        return "redirect:/aluno";
    }
    
	/*
	 * @GetMapping("/teste") public String lista (Model model) {
	 * model.addAttribute("lista", "Aluno222sssss");
	 * 
	 * 
	 * return "teste"; }
	 */
    
	/*
	 * @GetMapping("/teste") public ResponseEntity<List<AlunoDTO>> listaAlunos(){
	 * List<Aluno> listaAlunos = alunoService.findAllAlunos(); List<AlunoDTO>
	 * listDTO = listaAlunos.stream().map(obj -> new
	 * AlunoDTO(obj)).collect(Collectors.toList()); return
	 * ResponseEntity.ok().body(listDTO); }
	 */    
    

}
