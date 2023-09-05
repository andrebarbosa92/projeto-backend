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

import io.github.meucafofo.meucafofo_servico.domain.entity.Moradia;
import io.github.meucafofo.meucafofo_servico.service.MoradiaService;

@RestController
@RequestMapping("/api/moradia")
public class MoradiaController {

	@Autowired
	private MoradiaService moradiaService;
	
	
	@PostMapping
	public ResponseEntity<Moradia> salvar (@RequestBody Moradia moradia){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(moradiaService.inserirMoradia(moradia));
	}
	
	@GetMapping
	public ResponseEntity<List<Moradia>> listarTodos (){
		return ResponseEntity.ok().body(moradiaService.listarMoradia());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<List<Moradia>> listarMoradiaPorLocador(@PathVariable Long id){
		return ResponseEntity.ok().body(moradiaService.listarMoradiaPorLocador(id));
	}
}
