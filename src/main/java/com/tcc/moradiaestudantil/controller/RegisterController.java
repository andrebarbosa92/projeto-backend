package com.tcc.moradiaestudantil.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tcc.moradiaestudantil.domain.dto.AlunoDTO;
import com.tcc.moradiaestudantil.service.AlunoService;
import com.tcc.moradiaestudantil.service.LocadorService;
import com.tcc.moradiaestudantil.utils.ServiceResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private AlunoService alunoService;
	
	@Autowired
	private LocadorService locadorService;
	
	
	@PostMapping("/aluno")
	public ResponseEntity<ServiceResponse> insertAluno(@RequestBody @Valid AlunoDTO aluno){
		ServiceResponse usuarioNovo = alunoService.insertAluno(aluno);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(aluno.getId()).toUri();
		return ResponseEntity.created(uri).body(usuarioNovo);
	}
}
