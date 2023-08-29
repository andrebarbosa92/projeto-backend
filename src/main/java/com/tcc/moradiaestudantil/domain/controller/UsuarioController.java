package com.tcc.moradiaestudantil.domain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcc.moradiaestudantil.domain.dto.LoginDTO;
import com.tcc.moradiaestudantil.domain.dto.UsuarioDTO;
import com.tcc.moradiaestudantil.domain.entity.Usuario;
import com.tcc.moradiaestudantil.domain.service.UsuarioService;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping
	public ResponseEntity<?> loginUsuario (@RequestBody LoginDTO loginDTO){
		return null;
	}
	
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> listarTodos (){
		return ResponseEntity.ok().body(usuarioService.listarTodos());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> buscarId(@PathVariable Long id){
		
		return ResponseEntity.ok().body(usuarioService.buscarId(id));
	}
}
