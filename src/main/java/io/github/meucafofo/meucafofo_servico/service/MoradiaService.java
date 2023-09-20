package io.github.meucafofo.meucafofo_servico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.meucafofo.meucafofo_servico.domain.entity.Locador;
import io.github.meucafofo.meucafofo_servico.domain.entity.Moradia;
import io.github.meucafofo.meucafofo_servico.repository.LocadorRepository;
import io.github.meucafofo.meucafofo_servico.repository.MoradiaRepository;

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
