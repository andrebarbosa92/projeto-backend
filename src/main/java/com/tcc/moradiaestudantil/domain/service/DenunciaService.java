package com.tcc.moradiaestudantil.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcc.moradiaestudantil.domain.entity.Denuncia;
import com.tcc.moradiaestudantil.domain.repository.DenunciaRepository;

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
