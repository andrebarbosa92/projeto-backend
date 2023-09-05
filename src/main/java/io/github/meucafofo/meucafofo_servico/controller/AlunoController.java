package io.github.meucafofo.meucafofo_servico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.meucafofo.meucafofo_servico.domain.entity.Aluno;
import io.github.meucafofo.meucafofo_servico.service.AlunoService;

@RestController
@RequestMapping("/api/aluno")
public class AlunoController {

	@Autowired
	private AlunoService alunoService;
	
	
	@PostMapping
	public ResponseEntity<Aluno> salvarAluno (@RequestBody Aluno aluno){
		
//		aluno.setTipoUsuario(TipoUsuario.ALUNO);
		
		Aluno usuarioNovo = alunoService.cadastrarAluno(aluno);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioNovo);
	}
	
	@GetMapping
	public ResponseEntity<List<Aluno>> listarTodos (){
		return ResponseEntity.ok().body(alunoService.listarTodos());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Aluno> buscarId(@PathVariable Long id){
		
		return ResponseEntity.ok().body(alunoService.buscarId(id));
	}
}
