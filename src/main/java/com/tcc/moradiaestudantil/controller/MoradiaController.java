package com.tcc.moradiaestudantil.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tcc.moradiaestudantil.domain.entity.Moradia;
import com.tcc.moradiaestudantil.service.MoradiaService;

@RestController
@RequestMapping("/api/moradia")
public class MoradiaController {

	@Autowired
	private MoradiaService moradiaService;
	
	
	@PostMapping
	public ResponseEntity<Moradia> salvar(@RequestBody Moradia moradia){
		return ResponseEntity.status(HttpStatus.CREATED).body(moradiaService.inserirMoradia(moradia));
	}
	
	@GetMapping
	public ResponseEntity<List<Moradia>> listarTodos (){
		return ResponseEntity.ok().body(moradiaService.listarMoradia());
	}
	
	@GetMapping("/locador/{id}")
	public ResponseEntity<List<Moradia>> listarMoradiaPorLocador(@PathVariable Long id){
		return ResponseEntity.ok().body(moradiaService.listarMoradiaPorLocador(id));
	}
	
	@GetMapping("/filtro")
	public ResponseEntity<List<Moradia>> findByTipoMoradiaAndPreco(
			@RequestParam(name = "tipo") String tipo,
			@RequestParam(name = "preco") Double preco){
		return ResponseEntity.ok().body(moradiaService.findByTipoMoradiaAndPreco(tipo, preco));
	}
}
