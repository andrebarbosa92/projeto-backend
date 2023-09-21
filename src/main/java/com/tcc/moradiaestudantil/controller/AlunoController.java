package com.tcc.moradiaestudantil.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tcc.moradiaestudantil.domain.dto.AlunoDTO;
import com.tcc.moradiaestudantil.service.AlunoService;
import com.tcc.moradiaestudantil.utils.ServiceResponse;
import com.tcc.moradiaestudantil.utils.UpdateSenha;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/aluno")
public class AlunoController {

	@Autowired
	private AlunoService alunoService;
	
	
	@PostMapping("/inserir")
	public ResponseEntity<ServiceResponse> insertAluno(@RequestBody @Valid AlunoDTO aluno){
		ServiceResponse usuarioNovo = alunoService.insertAluno(aluno);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(aluno.getId()).toUri();
		return ResponseEntity.created(uri).body(usuarioNovo);
	}
	
	@GetMapping
	public ResponseEntity<List<AlunoDTO>> findAll(){
		return ResponseEntity.ok().body(alunoService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AlunoDTO> findById(@PathVariable Long id){
		return ResponseEntity.ok().body(alunoService.findById(id));
	}
	
	@PutMapping("/atualizar/{id}")
	public ResponseEntity<?> updateAluno(@PathVariable Long id, @RequestBody AlunoDTO aluno){
		aluno.setId(id);
		alunoService.updateAluno(aluno);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/atualizar-senha/{id}")
	public ResponseEntity<ServiceResponse> updateSenha(@PathVariable Long id, @RequestBody UpdateSenha senhas){
		
		ServiceResponse serviceResponse = alunoService.updateSenha(id, senhas.getSenhaAntiga(), senhas.getSenhaNova());
		return ResponseEntity.ok(serviceResponse);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id){
		alunoService.deleteById(id);
		return ResponseEntity.noContent().build();
		
	}
}
