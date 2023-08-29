package com.tcc.moradiaestudantil.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcc.moradiaestudantil.domain.entity.Locador;
import com.tcc.moradiaestudantil.domain.entity.Moradia;
import com.tcc.moradiaestudantil.domain.repository.LocadorRepository;
import com.tcc.moradiaestudantil.domain.repository.MoradiaRepository;

@Service
public class MoradiaService {

	@Autowired
	private MoradiaRepository moradiaRepository;
	
	@Autowired
	private LocadorRepository locadorRepository;
	
	public Moradia inserirMoradia(Moradia moradia) {
		return moradiaRepository.saveAndFlush(moradia);
	}
	
	public List<Moradia> listarMoradia (){
		return moradiaRepository.findAll();
	}
	
	public List<Moradia> listarMoradiaPorLocador(Long id){
		
		Optional<Locador> locador = locadorRepository.findById(id);
		
		return moradiaRepository.findByLocador(locador.get());
	}
}
