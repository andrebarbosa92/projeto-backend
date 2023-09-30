package com.tcc.moradiaestudantil.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.tcc.moradiaestudantil.dto.UsuarioDTO;
import com.tcc.moradiaestudantil.enums.Status;
import com.tcc.moradiaestudantil.service.UsuarioService;
import com.tcc.moradiaestudantil.utils.ServiceResponse;
import com.tcc.moradiaestudantil.utils.UpdateSenha;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping
	public ResponseEntity<ServiceResponse> cadastrarUsuario(@RequestBody @Valid UsuarioDTO usuario) {
		usuario.setStatus(Status.PENDENTE);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.insertUsuario(usuario));
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
