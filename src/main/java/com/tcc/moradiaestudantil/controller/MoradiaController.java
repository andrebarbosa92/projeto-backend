package com.tcc.moradiaestudantil.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tcc.moradiaestudantil.domain.dto.AnuncioDTO;
import com.tcc.moradiaestudantil.domain.entity.Moradia;
import com.tcc.moradiaestudantil.service.MoradiaService;
import com.tcc.moradiaestudantil.utils.ServiceResponse;

@RestController
@RequestMapping("/api/moradia")
public class MoradiaController {

	@Autowired
	private MoradiaService moradiaService;
	
	@PostMapping
	public ResponseEntity<ServiceResponse> salvar(@RequestBody Moradia moradia){
		ServiceResponse responseCreated = moradiaService.inserirMoradia(moradia);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseCreated);
	}
	
	@GetMapping
	public ResponseEntity<List<Moradia>> listarTodos (){
		List<Moradia> moradia = moradiaService.listarMoradia();
		return ResponseEntity.ok().body(moradia);
	}
	
	@GetMapping("/meus-anuncios")
	public ResponseEntity<Page<AnuncioDTO>> listarMoradiaPorLocador(
			@RequestParam(name = "page", defaultValue = "0") Integer page, 
			@RequestParam(name = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(name = "orderBy", defaultValue = "id") String orderBy, 
			@RequestParam(name = "direction", defaultValue = "ASC") String direction){
		Page<AnuncioDTO> listMoradiaPage = moradiaService.listarMoradiaPorLocador(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(listMoradiaPage);
	}
	
	@GetMapping("/filtro")
	public ResponseEntity<Page<AnuncioDTO>> findByTipoMoradiaAndPreco(
			@RequestParam(name = "tipo", defaultValue = "Apartamento") String tipo,
			@RequestParam(name = "preco", defaultValue = "9999999999") Double preco,
			@RequestParam(name = "page", defaultValue = "0") Integer page, 
			@RequestParam(name = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(name = "orderBy", defaultValue = "preco") String orderBy, 
			@RequestParam(name = "direction", defaultValue = "ASC") String direction){
			Page<AnuncioDTO> listMoradiaPage = moradiaService.findByTipoMoradiaAndPreco(tipo, preco, page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(listMoradiaPage);
	}

	@GetMapping("/page")
	public ResponseEntity<Page<AnuncioDTO>> findAllPage(
			@RequestParam(name = "page", defaultValue = "0") Integer page, 
			@RequestParam(name = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(name = "orderBy", defaultValue = "id") String orderBy, 
			@RequestParam(name = "direction", defaultValue = "ASC") String direction){
		Page<AnuncioDTO> listMoradiaPage = moradiaService.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(listMoradiaPage);
	}
}
