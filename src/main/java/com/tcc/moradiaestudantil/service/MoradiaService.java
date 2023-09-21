package com.tcc.moradiaestudantil.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcc.moradiaestudantil.domain.dto.AnuncioDTO;
import com.tcc.moradiaestudantil.domain.entity.Locador;
import com.tcc.moradiaestudantil.domain.entity.Moradia;
import com.tcc.moradiaestudantil.repository.LocadorRepository;
import com.tcc.moradiaestudantil.repository.MoradiaRepository;

@Service
public class MoradiaService {

	@Autowired
	private MoradiaRepository moradiaRepository;
	
	@Autowired
	private LocadorRepository locadorRepository;
	
	public Moradia inserirMoradia(Moradia moradia) {
		return moradiaRepository.saveAndFlush(moradia);
	}
	
	@Transactional(readOnly = true)
	public List<Moradia> listarMoradia (){
		return moradiaRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public List<Moradia> listarMoradiaPorLocador(Long id){
		Optional<Locador> locador = locadorRepository.findById(id);
		return moradiaRepository.findByLocador(locador.get());
	}

	public List<AnuncioDTO> findByTipoMoradiaAndPreco(String tipo, Double preco) {
		List<Moradia> moradias = moradiaRepository.buscarTeste(tipo, preco);
		return moradias.stream().map(u -> new AnuncioDTO(u)).collect(Collectors.toList());
	}

}
