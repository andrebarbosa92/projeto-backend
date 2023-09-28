package com.tcc.moradiaestudantil.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tcc.moradiaestudantil.domain.dto.UsuarioDTO;
import com.tcc.moradiaestudantil.service.UsuarioService;
import com.tcc.moradiaestudantil.utils.ServiceResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private UsuarioService usuarioService;
	
	
	@PostMapping("/aluno")
	public ResponseEntity<ServiceResponse> insertAluno(@RequestBody @Valid UsuarioDTO aluno){
		ServiceResponse usuarioNovo = usuarioService.insertUsuario(aluno);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(aluno.getId()).toUri();
		return ResponseEntity.created(uri).body(usuarioNovo);
	}
	
	@PostMapping("/locador")
	public ResponseEntity<ServiceResponse> insertLocador(@RequestBody @Valid UsuarioDTO locador){
		ServiceResponse usuarioNovo = usuarioService.insertUsuario(locador);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(locador.getId()).toUri();
		return ResponseEntity.created(uri).body(usuarioNovo);
	}
}
