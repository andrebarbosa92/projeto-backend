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

import com.tcc.moradiaestudantil.dto.UsuarioDTO;
import com.tcc.moradiaestudantil.enums.Status;
import com.tcc.moradiaestudantil.enums.TipoUsuario;
import com.tcc.moradiaestudantil.service.UsuarioService;
import com.tcc.moradiaestudantil.utils.ServiceResponse;
import com.tcc.moradiaestudantil.utils.UpdateSenha;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping("/aluno")
	public ResponseEntity<ServiceResponse> insertAluno(@RequestBody @Valid UsuarioDTO aluno){
		aluno.setTipoUsuario(TipoUsuario.ALUNO);
		aluno.setStatus(Status.PENDENTE);
		ServiceResponse usuarioNovo = usuarioService.insertUsuario(aluno);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(aluno.getId()).toUri();
		return ResponseEntity.created(uri).body(usuarioNovo);
	}
	
	@PostMapping("/locador")
	public ResponseEntity<ServiceResponse> insertLocador(@RequestBody @Valid UsuarioDTO locador){
		locador.setTipoUsuario(TipoUsuario.LOCADOR);
		locador.setStatus(Status.PENDENTE);
		ServiceResponse usuarioNovo = usuarioService.insertUsuario(locador);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(locador.getId()).toUri();
		return ResponseEntity.created(uri).body(usuarioNovo);
	}
	
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> findAll(){
		return ResponseEntity.ok().body(usuarioService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id){
		return ResponseEntity.ok().body(usuarioService.findById(id));
	}
	
	@PutMapping("/atualizar-cadastro/{id}")
	public ResponseEntity<UsuarioDTO> updateUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuario){
		usuario.setId(id);
		usuarioService.updateUsuario(id, usuario);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/atualizar-senha/{id}")
	public ResponseEntity<ServiceResponse> updateSenha(@PathVariable Long id, @RequestBody UpdateSenha senhas){
		
		ServiceResponse serviceResponse = usuarioService.updateSenha(id, senhas.getSenhaAntiga(), senhas.getSenhaNova());
		return ResponseEntity.ok(serviceResponse);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id){
		usuarioService.deleteById(id);
		return ResponseEntity.noContent().build();
		
	}
	
}
