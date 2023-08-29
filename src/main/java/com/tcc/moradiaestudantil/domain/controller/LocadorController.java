package com.tcc.moradiaestudantil.domain.controller;

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

import com.tcc.moradiaestudantil.domain.entity.Locador;
import com.tcc.moradiaestudantil.domain.service.LocadorService;

@RestController
@RequestMapping("/api/locador")
public class LocadorController {

	@Autowired
	private LocadorService locadorService;
	
	
	@PostMapping
	public ResponseEntity<Locador> salvarLocador (@RequestBody Locador locador){
		
//		aluno.setTipoUsuario(TipoUsuario.ALUNO);
		
		Locador usuarioNovo = locadorService.cadastrarLocador(locador);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioNovo);
	}
	
	@GetMapping
	public ResponseEntity<List<Locador>> listarTodos (){
		return ResponseEntity.ok().body(locadorService.listarTodos());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Locador> buscarId(@PathVariable Long id){
		
		return ResponseEntity.ok().body(locadorService.buscarId(id));
	}
}
