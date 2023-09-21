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

import com.tcc.moradiaestudantil.domain.dto.LocadorDTO;
import com.tcc.moradiaestudantil.service.LocadorService;
import com.tcc.moradiaestudantil.utils.ServiceResponse;
import com.tcc.moradiaestudantil.utils.UpdateSenha;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/locador")
public class LocadorController {

	@Autowired
	private LocadorService locadorService;

	
	@PostMapping("/inserir")
	public ResponseEntity<ServiceResponse> insertAluno(@RequestBody @Valid LocadorDTO locador){
		ServiceResponse usuarioNovo = locadorService.insertLocador(locador);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(locador.getId()).toUri();
		return ResponseEntity.created(uri).body(usuarioNovo);
	}
	
	@GetMapping
	public ResponseEntity<List<LocadorDTO>> findAll(){
		return ResponseEntity.ok().body(locadorService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<LocadorDTO> findById(@PathVariable Long id){
		return ResponseEntity.ok().body(locadorService.findById(id));
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> updateLocador(@RequestBody LocadorDTO locadorDto, @PathVariable Long id){
		locadorDto.setId(id);
		locadorService.updateLocador(locadorDto);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id){
		
		locadorService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/atualizar-senha/{id}")
	public ResponseEntity<?> updateSenha(@PathVariable Long id, @RequestBody UpdateSenha senhas){
		
		ServiceResponse serviceResponse = locadorService.updateSenha(id, senhas.getSenhaAntiga(), senhas.getSenhaNova());
		return ResponseEntity.ok(serviceResponse);
		
	}
}
