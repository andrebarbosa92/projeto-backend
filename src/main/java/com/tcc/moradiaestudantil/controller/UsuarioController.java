package com.tcc.moradiaestudantil.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcc.moradiaestudantil.domain.dto.LoginDTO;
import com.tcc.moradiaestudantil.domain.dto.UsuarioDTO;
import com.tcc.moradiaestudantil.service.UsuarioService;
import com.tcc.moradiaestudantil.utils.LoginResponse;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
//	@PostMapping("/login")
//	public ResponseEntity<?> loginUsuario (@RequestBody LoginDTO loginDTO){
//		
//		LoginResponse loginResponse = usuarioService.loginUsuario(loginDTO);
//		
//		return ResponseEntity.ok(loginResponse);
//	}
	
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> listarTodos (){
		return ResponseEntity.ok().body(usuarioService.listarTodos());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id){
		
		return ResponseEntity.ok().body(usuarioService.findById(id));
	}
	
}
