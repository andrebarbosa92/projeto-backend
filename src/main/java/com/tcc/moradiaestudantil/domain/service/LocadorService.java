package com.tcc.moradiaestudantil.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcc.moradiaestudantil.domain.entity.Locador;
import com.tcc.moradiaestudantil.domain.repository.LocadorRepository;

@Service
public class LocadorService {

	@Autowired
	private LocadorRepository locadorRepository;
	
	public Locador cadastrarLocador (Locador locador) {
		return locadorRepository.save(locador);
	}
	
	@Transactional(readOnly = true)
	public List<Locador> listarTodos (){
		List<Locador> listAluno = locadorRepository.findAll();
		return listAluno;
//		return listAluno.stream().map(u -> new UsuarioDTO(u)).collect(Collectors.toList());
	}
	
	public Locador buscarId(Long id) {
		Optional<Locador> opt = locadorRepository.findById(id);
		Locador locador = (Locador) opt.get();
//		UsuarioDTO dto = new UsuarioDTO(usuario);
		
		return locador;
//		return opt.orElseThrow(() -> new ObjectNotFoundException("Aluno não encontrado. ID: "+ id));
	}
}
