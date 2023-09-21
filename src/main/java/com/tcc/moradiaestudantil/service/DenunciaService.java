package com.tcc.moradiaestudantil.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcc.moradiaestudantil.domain.entity.Denuncia;
import com.tcc.moradiaestudantil.repository.DenunciaRepository;

@Service
public class DenunciaService {

	@Autowired
	private DenunciaRepository denunciaRepository;
	
	public Denuncia inserirDenuncia(Denuncia denuncia) {
		return denunciaRepository.saveAndFlush(denuncia);
	}
	
	@Transactional(readOnly = true)
	public List<Denuncia> listarDenuncia (){
		return denunciaRepository.findAll();
	}
}
