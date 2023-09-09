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

import io.github.meucafofo.meucafofo_servico.dto.LoginDTO;
import io.github.meucafofo.meucafofo_servico.dto.UsuarioDTO;
import io.github.meucafofo.meucafofo_servico.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUsuario (@RequestBody LoginDTO loginDTO){
		return ResponseEntity.status(HttpStatus.CREATED).body("token");
	}

	@PostMapping("/singup")
	public ResponseEntity<?> singupUsuario (@RequestBody LoginDTO loginDTO){
		return ResponseEntity.status(HttpStatus.CREATED).body("Usuario");
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
