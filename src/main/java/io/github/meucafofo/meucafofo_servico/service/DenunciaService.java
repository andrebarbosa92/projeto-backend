package io.github.meucafofo.meucafofo_servico.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.meucafofo.meucafofo_servico.domain.entity.Denuncia;
import io.github.meucafofo.meucafofo_servico.domain.repository.DenunciaRepository;

@Service
public class DenunciaService {

	@Autowired
	private DenunciaRepository denunciaRepository;
	
	public Denuncia inserirDenuncia(Denuncia denuncia) {
		return denunciaRepository.saveAndFlush(denuncia);
	}
	
	public List<Denuncia> listarDenuncia (){
		return denunciaRepository.findAll();
	}
}
